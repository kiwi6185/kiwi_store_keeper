package service;
import domain.User;
import exception.UserExistException;

public interface IUserService {
    /**
     * 登录
     *
     * @param fd_username -- 用户名
     * @param fd_password -- 用户密码
     *
     * @return true：成功；false：失败
     */
    void login(String fd_username, String fd_password) throws UserExistException;
    //登陆失败抛出异常，也可以返回一个布尔值

    /**
     * 注册
     *
     * @param user -- 用户
     *
     * @return boolean -- 标志，true：注册成功；false：注册失败
     * @throws UserExistException
     */
    void register(User user) throws UserExistException;

}