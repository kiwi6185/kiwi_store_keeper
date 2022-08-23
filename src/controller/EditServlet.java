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

    // ͼƬ���ϴ�
    // ����servletconfig������Ϊinitialize()�����Ĳ���
    ServletConfig servletconfig;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

    // ��ʼ��servletconfig����
    public void init(ServletConfig config) throws ServletException {
        // ͼƬ���ϴ�
        this.servletconfig = config;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ������Ӧ���ĵ��ַ������MIME����
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        // ͼƬ���ϴ�
        // 1.ʵ����һ��SmartUpload����
        SmartUpload su = new SmartUpload();
        // 2.��ʼ��SmartUpolad����
        try {
            su.initialize(servletconfig, request, response);
        } catch (ServletException e1) {
            e1.printStackTrace();
        }
        // 3.�����ļ��ϴ��� ����
        su.setAllowedFilesList("jpg,png,jpeg,gif,bmp");
        // �����ļ�����ֽ���
//        su.setMaxFileSize(3 * 1024 * 1024);
        // �ļ�������ֽ���
//        su.setTotalMaxFileSize(12 * 1024 * 1024);
        // 4.ʹ��upload�ϴ�

        try {
            su.upload();
        } catch (ServletException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (SmartUploadException e2) {
            e2.printStackTrace();
        }
        // 5.�ļ�����
        Date curDate = new Date();
        long d = curDate.getTime();// long��

        // ����request����
        Request req = su.getRequest();
        // ��ȡ�ϴ��ļ�
        // ��ȡȫ���ϴ��ļ�
        Files files = su.getFiles();


        // ��ȡָ��λ�õ��ļ�
        File file = files.getFile(0);
        // ��ȡ�ļ���չ��
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

        // ��װģ�Ͷ���Goods
        Goods goods = new Goods();
        String goodsname = req.getParameter("gs_name");
        goods.setGs_name(goodsname);
        goods.setGs_pic(filename);
        goods.setGs_description(req.getParameter("gs_description"));
        goods.setGs_price(req.getParameter("gs_price"));

        IGoodsService service = ServiceFactory.getGoodsServiceInstance();
        // ����service���ṩ������û�����ʵ���û������
        try {
            service.editGoods(goods);
            response.getWriter().print("<script language='javascript'>alert('�޸ĳɹ���');" +
                    "</script>");
            response.sendRedirect(request.getContextPath() + "/showAll");
//            response.sendRedirect("LoginUI");
        } catch (GoodsExistException e) {
            request.setAttribute("message", e.getMessage());
            response.getWriter().print("<script language='javascript'>alert('�޸�ʧ�ܣ�');" +
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
