package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.ServiceFactory;
import service.IGoodsService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "�鿴ȫ����ƷServlet", urlPatterns = { "/showAll" })
public class ShowAllServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IGoodsService service = ServiceFactory.getGoodsServiceInstance();
        try {   // �����ɹ�
            request.setAttribute("list_Goods", service.showAll());    // bean�ļ���

            System.out.println("����������Ʒ�ɹ���");
            System.out.println("���Ǽ��ؿ�ס��");
            request.getRequestDispatcher("/pages/goods_show.jsp").forward(request, response);
        } catch (Exception e) {    // ����ʧ��
            System.out.println("����ʧ����~");
            // ����д�����ض���
            response.getWriter().print("<script language='javascript'>alert('����ʧ��');" +
//                    "window.location.href='/login_regist.jsp';" +
                    "history.go(-1);" +
                    "</script>");
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

