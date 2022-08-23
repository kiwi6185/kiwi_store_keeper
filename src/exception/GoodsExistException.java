package exception;

public class GoodsExistException extends Throwable {
    private static final long serialVersionUID = 1L;
    // 异常信息
    private String message;

    // 构造函数
    public GoodsExistException(String message) {
//        super(message);
        this.message = message;
    }
}
