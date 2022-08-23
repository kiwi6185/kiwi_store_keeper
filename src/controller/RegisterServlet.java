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
@WebServlet(description = "注册Servlet", urlPatterns = { "/Register" })
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
        // 设置响应报文的字符编码和MIME类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        // 封装模型对象user
        User user = new User();
        user.setFd_username(request.getParameter("username"));
        user.setFd_email(request.getParameter("email"));
        user.setFd_password(request.getParameter("password"));
//        user.setFd_usertype(request.getParameter("usertype"));

        IUserService service = ServiceFactory.getUserServiceInstance();
        // 调用service层提供的注册用户服务实现用户注册
        try {
            service.register(user);
            response.getWriter().print("<script language='javascript'>alert('注册成功！');" +
                    "</script>");
            response.sendRedirect(request.getContextPath() + "/Login");
//            response.sendRedirect("LoginUI");
        } catch (UserExistException e) {
//            request.setAttribute("message", e.getMessage());
            response.getWriter().print("<script language='javascript'>alert('注册失败！');" +
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
