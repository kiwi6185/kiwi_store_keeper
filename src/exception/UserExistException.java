package exception;

public class UserExistException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 异常信息
    @SuppressWarnings("unused")
    private String message;

    // 构造函数
    public UserExistException(String message) {
        super(message);
        this.message = message;
    }

}
