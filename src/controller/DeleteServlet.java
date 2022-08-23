package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.GoodsExistException;
import factory.ServiceFactory;
import service.IGoodsService;


@WebServlet("/Delete")

public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ��ȡ�û���д�ĵ�¼�û���
        String goodsname = request.getParameter("detele_name");
        IGoodsService service = ServiceFactory.getGoodsServiceInstance();
        try {   // �鵽��
            service.deleteGoods(goodsname); // ���
//            request.setAttribute("user", username + "����¼�ɹ�����ϲ��");
            System.out.println("ɾ����ס����");
            response.getWriter().print("<script language='javascript'>alert('ɾ���ɹ���');" +
                    "</script>");
            response.sendRedirect(request.getContextPath() + "/showAll");
        } catch (GoodsExistException e) {    // ��¼ʧ��
            System.out.println("ɾ��ʧ��");
            // ����д�����ض���
            response.getWriter().print("<script language='javascript'>alert('ɾ��ʧ��');" +
//                    "window.location.href='/login_regist.jsp';" +
                    "history.go(-1);" +
                    "</script>");
//            request.setAttribute("message", e.getMessage());
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