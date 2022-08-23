package controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.*;
import domain.Goods;
import exception.GoodsExistException;
import factory.ServiceFactory;
import service.IGoodsService;

@WebServlet("/Edit")

public class EditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // 图片的上传
    // 声明servletconfig对象，作为initialize()方法的参数
    ServletConfig servletconfig;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

    // 初始化servletconfig对象
    public void init(ServletConfig config) throws ServletException {
        // 图片的上传
        this.servletconfig = config;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应报文的字符编码和MIME类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        // 图片的上传
        // 1.实例化一个SmartUpload对象
        SmartUpload su = new SmartUpload();
        // 2.初始化SmartUpolad对象
        try {
            su.initialize(servletconfig, request, response);
        } catch (ServletException e1) {
            e1.printStackTrace();
        }
        // 3.设置文件上传的 限制
        su.setAllowedFilesList("jpg,png,jpeg,gif,bmp");
        // 单个文件最大字节数
//        su.setMaxFileSize(3 * 1024 * 1024);
        // 文件总最大字节数
//        su.setTotalMaxFileSize(12 * 1024 * 1024);
        // 4.使用upload上传

        try {
            su.upload();
        } catch (ServletException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (SmartUploadException e2) {
            e2.printStackTrace();
        }
        // 5.文件保存
        Date curDate = new Date();
        long d = curDate.getTime();// long型

        // 创建request对象
        Request req = su.getRequest();
        // 获取上传文件
        // 获取全部上传文件
        Files files = su.getFiles();


        // 获取指定位置的文件
        File file = files.getFile(0);
        // 获取文件扩展名
        String extFile = file.getFileExt();
        String mainFile = String.valueOf(d);
        String filename = "pics/" + mainFile + "." + extFile;
        System.out.println(filename);
        try {
            file.saveAs(filename);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (SmartUploadException e1) {
            e1.printStackTrace();
        }

        // 封装模型对象Goods
        Goods goods = new Goods();
        String goodsname = req.getParameter("gs_name");
        goods.setGs_name(goodsname);
        goods.setGs_pic(filename);
        goods.setGs_description(req.getParameter("gs_description"));
        goods.setGs_price(req.getParameter("gs_price"));

        IGoodsService service = ServiceFactory.getGoodsServiceInstance();
        // 调用service层提供的添加用户服务实现用户的添加
        try {
            service.editGoods(goods);
            response.getWriter().print("<script language='javascript'>alert('修改成功！');" +
                    "</script>");
            response.sendRedirect(request.getContextPath() + "/showAll");
//            response.sendRedirect("LoginUI");
        } catch (GoodsExistException e) {
            request.setAttribute("message", e.getMessage());
            response.getWriter().print("<script language='javascript'>alert('修改失败！');" +
                    "history.go(-1);" +
                    "</script>");
//            request.getRequestDispatcher("RegisterUI").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
