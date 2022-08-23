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
        // 获取用户填写的登录用户名
        String goodsname = request.getParameter("detele_name");
        IGoodsService service = ServiceFactory.getGoodsServiceInstance();
        try {   // 查到了
            service.deleteGoods(goodsname); // 这个
//            request.setAttribute("user", username + "：登录成功，恭喜！");
            System.out.println("删除卡住了吗");
            response.getWriter().print("<script language='javascript'>alert('删除成功！');" +
                    "</script>");
            response.sendRedirect(request.getContextPath() + "/showAll");
        } catch (GoodsExistException e) {    // 登录失败
            System.out.println("删除失败");
            // 弹窗写法：重定向
            response.getWriter().print("<script language='javascript'>alert('删除失败');" +
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