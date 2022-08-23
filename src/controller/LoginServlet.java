package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.UserExistException;
import factory.ServiceFactory;
import service.IUserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "��¼Servlet", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ��ȡ�û���д�ĵ�¼�û���
        String username = request.getParameter("username");
        // ��ȡ�û���д�ĵ�¼����
        String password = request.getParameter("password");
        IUserService service = ServiceFactory.getUserServiceInstance();
        // ��ôд��ʵ���ܵ�¼�ɹ�����ʧ�ܶ�����ת�� message.jsp
        try {   // ��¼�ɹ�
            service.login(username, password); // ���
//            request.setAttribute("user", username + "����¼�ɹ�����ϲ��");
            System.out.println("��¼�ɹ�");
//            request.getRequestDispatcher("/showAll").forward(request, response); ����ת����Ӧ�����ض���
            response.sendRedirect(request.getContextPath() + "/showAll");
        } catch (UserExistException e) {    // ��¼ʧ��
            System.out.println("��¼ʧ��");
            // ����д�����ض���
//            response.getWriter().print("<script language='javascript'>alert('��¼ʧ��');" +
////                    "window.location.href='/login_regist.jsp';" +
//                    "history.go(-1);" +
//                    "</script>");
            // ������ʾд��
            // ��¼ʧ�ܵĻ����� request ��һ�� is_login_succeed = false ���ת������¼ҳ��
            request.setAttribute("is_login_succeed", false);    // һ����¼ʧ�ܵı�־
            request.getRequestDispatcher("/login_regist.jsp").forward(request, response);
//            request.setAttribute("message", e.getMessage());
        }
        finally {
//            request.getRequestDispatcher("/pages/goods_show.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

