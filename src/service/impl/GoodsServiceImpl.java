package service.impl;
import domain.Goods;
import exception.GoodsExistException;
import factory.DaoFactory;
import service.IGoodsService;


import java.util.List;

public class GoodsServiceImpl implements IGoodsService {

    @Override
    public List showAll() {
        List list = null;
        list = DaoFactory.getGoodsDaoInstance().showAll();
        return list;
    }

    @Override
    public int addGoods(Goods goods) throws GoodsExistException {
        return DaoFactory.getGoodsDaoInstance().addGoods(goods);
    }

    @Override
    public int deleteGoods(String name) throws GoodsExistException {
        return DaoFactory.getGoodsDaoInstance().deleteGoods(name);
    }

    @Override
    public int editGoods(Goods goods) throws GoodsExistException {
        return DaoFactory.getGoodsDaoInstance().editGoods(goods);
    }

}