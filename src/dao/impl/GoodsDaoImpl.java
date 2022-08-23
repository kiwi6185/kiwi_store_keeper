package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import domain.Goods;
import exception.GoodsExistException;
import org.apache.commons.dbutils.QueryRunner;

import dao.IGoodsDao;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.DbcpPool;

public class GoodsDaoImpl implements IGoodsDao {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public  List showAll() {

        String sql = "SELECT * FROM tb_goods";
        Connection conn = DbcpPool.getConnection();     // ��ôÿ�β�ѯ�������ݿ����ӳ��л�ȡһ������ conn
        List<Goods> list = null;

        // ���÷���query,���ݽ��������ʵ����BeanListHandler
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<Goods>(Goods.class)); // Ȼ��ִ�в�ѯ
            // ����Ĺؼ��ͳ���������������Զ��ر� conn ����
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            DbcpPool.close(conn);
        }

//        for(Goods s : list) {
//            System.out.println(s);
//        }

        return list;
    }

    /**
     * ���һ����Ʒ��
     *
     * @param goods -- ��Ʒ
     *
     * @return int -- ���¼�����1�����һ����Ʒ��0������Ʒ���
     * @throws GoodsExistException
     */
    @Override
    public int addGoods(Goods goods) throws GoodsExistException{
        String sql = "INSERT INTO tb_goods (gs_name, gs_pic, gs_description, gs_price) VALUES (?, ?, ?, ?)";
        String goodsname = goods.getGs_name();
        Object params[] = { goodsname, goods.getGs_pic(), goods.getGs_description(), goods.getGs_price()};
        int result = 0;
        Connection conn = DbcpPool.getConnection();
        try {
            result = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry '" + goodsname + "' for key 'PRIMARY'")) {
                System.out.println("���ʧ�ܣ���Ʒ'" + goodsname + "'�Ѵ��ڣ�");
                throw new GoodsExistException("���ʧ�ܣ���Ʒ'" + goodsname + "'�Ѵ��ڣ�");
            } else {
                System.out.println("���ʧ�ܣ�ԭ������");
                throw new GoodsExistException("���ʧ�ܣ�ԭ������");
            }
        } finally {
            DbcpPool.close(conn); // ʵ����û�б�Ҫ��queryRunner���йر����Ӳ������������ע���Ǵ��
        }
        return result;
    }

    @Override
    public int deleteGoods(String name) throws GoodsExistException {
        Connection conn = DbcpPool.getConnection(); // ��������ݿ����ӳ�������һ�� conn
        //����queryrunner����
        QueryRunner queryRunner = new QueryRunner();
        String sql = "delete from tb_goods where gs_name = ?";
        int row = 0;
        try {
            row = queryRunner.update(conn, sql, name);  // Ȼ��� conn ���� qr��updat����������������治�ܹ��������洫��ȥ�� conn
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbcpPool.close(conn);
        }
        return row;
    }

    @Override
    public int editGoods(Goods goods) throws GoodsExistException {
        Connection conn = DbcpPool.getConnection();
        // ����QueryRunner�����
        QueryRunner qr = new QueryRunner();
        // д�޸����ݵ�sql���
        String sql = "UPDATE tb_goods SET gs_description=?, gs_pic=?, gs_price=? WHERE gs_name=?";
        String gdName = goods.getGs_name();
        // ����Object���飬�洢���е�ʵ�ʲ���
        Object params[] = { goods.getGs_description(), goods.getGs_pic(), goods.getGs_price(), gdName};
        // ����QueryRunner��ķ���update�����ݱ��е������޸�
        int row = 0;
        try {
            row = qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbcpPool.close(conn);
        }
        System.out .println(row);

//        try {
//            file.saveAs(goods.getGs_pic());
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        } catch (SmartUploadException e1) {
//            e1.printStackTrace();
//        }

        return row;
    }

//    public static void main(String[] args) throws GoodsExistException {
//        Goods g = new Goods();
//        g.setGs_name("13");
//        g.setGs_description("100ֻ");
//        g.setGs_price("45");
//        new GoodsDaoImpl().editGoods(g, );
//    }

}