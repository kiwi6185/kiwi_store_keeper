package factory;
import service.IGoodsService;
import service.IUserService;
import service.impl.GoodsServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {
    public static IUserService getUserServiceInstance() {
        return new UserServiceImpl();
    }

    public static IGoodsService getGoodsServiceInstance() {
        return new GoodsServiceImpl();
    }
}
