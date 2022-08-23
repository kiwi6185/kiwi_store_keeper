package dao.impl;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.IUserDao;
import domain.User;
import exception.UserExistException;
import util.DbcpPool;

public class UserDaoImpl implements IUserDao {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 根据用户名查询。用户名是主键，返回至多一条记录
     *
     * @param fd_username -- 用户名
     *
     * @return User -- 用户
     */
    public User selectOneByUsername(String fd_username) {
        String sql = "SELECT * FROM tb_users WHERE fd_username = '" + fd_username + "'";
        User user = null;
        Connection conn = DbcpPool.getConnection();
        try {
            user = queryRunner.query(conn, sql, new BeanHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbcpPool.close(conn); // 实际上没有必要，queryRunner已有关闭连接操作。
        }
        return user;
    }

    /**
     * 添加一个用户。
     *
     * @param user -- 用户
     *
     * @return int -- 更新计数，1：添加一个用户；0：无用户添加
     * @throws UserExistException
     */
    public int addOneUser(User user) throws UserExistException {
        String sql = "INSERT INTO tb_users (fd_username, fd_password, fd_email) VALUES (?, ?, ?)";
        String username = user.getFd_username();
        Object params[] = { username, user.getFd_password(), user.getFd_email()};
        int result = 0;
        Connection conn = DbcpPool.getConnection();
        try {
            result = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry '" + username + "' for key 'PRIMARY'")) {
                throw new UserExistException("注册失败，用户'" + username + "'已存在！");
            } else {
                throw new UserExistException("注册失败，原因不明！");
            }
        } finally {
            DbcpPool.close(conn); // 实际上没有必要，queryRunner已有关闭连接操作。
        }
        return result;
    }
}
