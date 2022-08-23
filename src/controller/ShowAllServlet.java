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
@WebServlet(description = "查看全部物品Servlet", urlPatterns = { "/showAll" })
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
        try {   // 搜索成功
            request.setAttribute("list_Goods", service.showAll());    // bean的集合

            System.out.println("搜索所有物品成功！");
            System.out.println("还是加载卡住了");
            request.getRequestDispatcher("/pages/goods_show.jsp").forward(request, response);
        } catch (Exception e) {    // 搜索失败
            System.out.println("搜索失败啦~");
            // 弹窗写法：重定向
            response.getWriter().print("<script language='javascript'>alert('搜索失败');" +
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

