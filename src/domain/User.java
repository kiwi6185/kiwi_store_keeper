package domain;

public class User {
    /**
     * 依据数据表结构，声明成员变量
     */
    private String fd_username;
    private String fd_password;
    private String fd_email;

    public String getFd_email() {
        return fd_email;
    }

    public void setFd_email(String fd_email) {
        this.fd_email = fd_email;
    }

    /**
     * 无参构造方法
     */
    public User() {
    }

    /**
     * 为属性提供setter和getter方法，public类型
     */
    public String getFd_username() {
        return fd_username;
    }

    public void setFd_username(String fd_username) {
        this.fd_username = fd_username;
    }

    public String getFd_password() {
        return fd_password;
    }

    public void setFd_password(String fd_password) {
        this.fd_password = fd_password;
    }
}
