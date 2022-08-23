package service.impl;
import domain.User;
import exception.UserExistException;
import factory.DaoFactory;
import service.IUserService;

public class UserServiceImpl implements IUserService {

    @Override
    public void login(String fd_username, String fd_password) throws UserExistException {
        // 比如这里可以记录 xxx时间访问了 userService 然后参数是什么
        User user = null; // 根据用户名查询到的用户
        user = DaoFactory.getUserDaoInstance().selectOneByUsername(fd_username);
        if (user == null) {
            throw new UserExistException("用户名不正确！");
        } else if (!user.getFd_password().equals(fd_password)) {
            throw new UserExistException("密码不正确！");
        }
    }

    @Override
    public void register(User user) throws UserExistException {
        DaoFactory.getUserDaoInstance().addOneUser(user);
    }

}
