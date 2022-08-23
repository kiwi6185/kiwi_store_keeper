package dao;
import domain.User;
import exception.UserExistException;

public interface IUserDao {
    /**
     * 根据用户名查询。用户名是主键，返回至多一条记录
     *
     * @param fd_username -- 用户名
     *
     * @return User -- 用户
     */
    User selectOneByUsername(String fd_username);

    /**
     * 添加一个用户。
     *
     * @param user -- 用户
     *
     * @return int -- 更新计数，1：添加一个用户；0：无用户添加
     * @throws UserExistException
     */
    int addOneUser(User user) throws UserExistException;

}
