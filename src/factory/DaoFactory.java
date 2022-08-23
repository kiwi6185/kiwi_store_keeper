package factory;
import dao.IGoodsDao;
import dao.IUserDao;
import dao.impl.GoodsDaoImpl;
import dao.impl.UserDaoImpl;

public class DaoFactory {
    public static IUserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }

    public static IGoodsDao getGoodsDaoInstance() {
        return new GoodsDaoImpl();
    }
}
