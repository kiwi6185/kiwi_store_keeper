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
        Connection conn = DbcpPool.getConnection();     // 那么每次查询，从数据库连接池中获取一个连接 conn
        List<Goods> list = null;

        // 调用方法query,传递结果集处理实现类BeanListHandler
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<Goods>(Goods.class)); // 然后执行查询
            // 问题的关键就出在这里，它并不会自动关闭 conn 连接
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
     * 添加一个物品。
     *
     * @param goods -- 物品
     *
     * @return int -- 更新计数，1：添加一个物品；0：无物品添加
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
                System.out.println("添加失败，物品'" + goodsname + "'已存在！");
                throw new GoodsExistException("添加失败，物品'" + goodsname + "'已存在！");
            } else {
                System.out.println("添加失败，原因不明！");
                throw new GoodsExistException("添加失败，原因不明！");
            }
        } finally {
            DbcpPool.close(conn); // 实际上没有必要，queryRunner已有关闭连接操作。所以这个注释是错的
        }
        return result;
    }

    @Override
    public int deleteGoods(String name) throws GoodsExistException {
        Connection conn = DbcpPool.getConnection(); // 这里从数据库连接池里拿了一个 conn
        //创建queryrunner对象
        QueryRunner queryRunner = new QueryRunner();
        String sql = "delete from tb_goods where gs_name = ?";
        int row = 0;
        try {
            row = queryRunner.update(conn, sql, name);  // 然后把 conn 传给 qr的updat，但是这个函数里面不能关我们外面传进去的 conn
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
        // 创建QueryRunner类对象
        QueryRunner qr = new QueryRunner();
        // 写修改数据的sql语句
        String sql = "UPDATE tb_goods SET gs_description=?, gs_pic=?, gs_price=? WHERE gs_name=?";
        String gdName = goods.getGs_name();
        // 定义Object数组，存储？中的实际参数
        Object params[] = { goods.getGs_description(), goods.getGs_pic(), goods.getGs_price(), gdName};
        // 调用QueryRunner类的方法update将数据表中的数据修改
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
//        g.setGs_description("100只");
//        g.setGs_price("45");
//        new GoodsDaoImpl().editGoods(g, );
//    }

}