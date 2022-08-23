package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import exception.UserExistException;
import factory.ServiceFactory;
import service.IUserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "ע��Servlet", urlPatterns = { "/Register" })
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ������Ӧ���ĵ��ַ������MIME����
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        // ��װģ�Ͷ���user
        User user = new User();
        user.setFd_username(request.getParameter("username"));
        user.setFd_email(request.getParameter("email"));
        user.setFd_password(request.getParameter("password"));
//        user.setFd_usertype(request.getParameter("usertype"));

        IUserService service = ServiceFactory.getUserServiceInstance();
        // ����service���ṩ��ע���û�����ʵ���û�ע��
        try {
            service.register(user);
            response.getWriter().print("<script language='javascript'>alert('ע��ɹ���');" +
                    "</script>");
            response.sendRedirect(request.getContextPath() + "/Login");
//            response.sendRedirect("LoginUI");
        } catch (UserExistException e) {
//            request.setAttribute("message", e.getMessage());
            response.getWriter().print("<script language='javascript'>alert('ע��ʧ�ܣ�');" +
                    "history.go(-1);" +
                    "</script>");
//            request.getRequestDispatcher("RegisterUI").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
