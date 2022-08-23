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
@WebServlet(description = "登录Servlet", urlPatterns = { "/Login" })
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
        // 获取用户填写的登录用户名
        String username = request.getParameter("username");
        // 获取用户填写的登录密码
        String password = request.getParameter("password");
        IUserService service = ServiceFactory.getUserServiceInstance();
        // 这么写其实不管登录成功还是失败都会跳转到 message.jsp
        try {   // 登录成功
            service.login(username, password); // 这个
//            request.setAttribute("user", username + "：登录成功，恭喜！");
            System.out.println("登录成功");
//            request.getRequestDispatcher("/showAll").forward(request, response); 不用转发，应该用重定向
            response.sendRedirect(request.getContextPath() + "/showAll");
        } catch (UserExistException e) {    // 登录失败
            System.out.println("登录失败");
            // 弹窗写法：重定向
//            response.getWriter().print("<script language='javascript'>alert('登录失败');" +
////                    "window.location.href='/login_regist.jsp';" +
//                    "history.go(-1);" +
//                    "</script>");
            // 文字提示写法
            // 登录失败的话，在 request 放一个 is_login_succeed = false 如何转发到登录页面
            request.setAttribute("is_login_succeed", false);    // 一个登录失败的标志
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

