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
     * �����û�����ѯ���û�������������������һ����¼
     *
     * @param fd_username -- �û���
     *
     * @return User -- �û�
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
            DbcpPool.close(conn); // ʵ����û�б�Ҫ��queryRunner���йر����Ӳ�����
        }
        return user;
    }

    /**
     * ���һ���û���
     *
     * @param user -- �û�
     *
     * @return int -- ���¼�����1�����һ���û���0�����û����
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
                throw new UserExistException("ע��ʧ�ܣ��û�'" + username + "'�Ѵ��ڣ�");
            } else {
                throw new UserExistException("ע��ʧ�ܣ�ԭ������");
            }
        } finally {
            DbcpPool.close(conn); // ʵ����û�б�Ҫ��queryRunner���йر����Ӳ�����
        }
        return result;
    }
}
