package util;
import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import dao.impl.UserDaoImpl;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DbcpPool {
    private static DataSource ds;// 定义一个连接池对象

    static {
        try {
            Properties pro = new Properties();
            pro.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            pro.setProperty("url", "jdbc:mysql://localhost:3306/数据库名?useUnicode=true&characterEncoding=UTF-8");
            pro.setProperty("username", "xxx");
            pro.setProperty("password", "xxx");
            pro.setProperty("maxTotal", "30");
            pro.setProperty("maxIdle", "10");
            pro.setProperty("maxWait", "1000");
            pro.setProperty("removeAbandoned", "false");
            pro.setProperty("removeAbandonedTimeout", "120");
            pro.setProperty("testOnBorrow", "true");
            pro.setProperty("logAbandoned", "true");

            ds = BasicDataSourceFactory.createDataSource(pro);// 得到一个连接池对象
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化连接错误，请检查配置文件！");
        }
    }

    /**
     * 从连接池中获取连接
     *
     * @return 一个新链接
     */
    public static synchronized Connection getConnection() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (Exception e) {
            System.out.println("获取链接失败:" + e);
        }
        return conn;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();// 关闭
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();// 关闭
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //// === 工具方法 ===
    /**
     * 执行INSERT/UPDATE/DELETE SQL语句
     *
     * @param sql SQL语句，字符串类型
     * @return 执行结果，int类型，-1表示异常
     */
    public static int executeUpdate(String sql) {
        int result = -1;

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, stmt, conn);
        }
        return result;
    }

    /**
     * 执行SELECT SQL语句
     *
     * @param sql SQL语句，字符串类型
     * @return ResultSet结果集
     */
    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        conn = ds.getConnection();
        stmt = conn.createStatement();

        return stmt.executeQuery(sql);
    }

    /**
     * 执行动态更新SQL语句
     *
     * @param sql 含有参数的动态SQL语句。
     * @return int类型，-1表示异常
     */
    public static int executePreparedStatementUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            if (params != null)
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, pstmt, conn);
        }
        return -1;
    }

    /**
     * 执行动态查询SQL语句
     *
     * @param sql 含有参数的动态SQL语句。
     * @return rs
     */
    public static ResultSet executePreparedStatementQuery(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = ds.getConnection();
        pstmt = conn.prepareStatement(sql);

        if (params != null)
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

        return pstmt.executeQuery();
    }

    /**
     * 事务回滚
     */
    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.selectOneByUsername("123").getFd_password());
    }
}
