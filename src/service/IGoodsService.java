package service;
import domain.Goods;
import exception.GoodsExistException;

import java.util.List;

public interface IGoodsService {

    // 查询全部物品并展示
    List showAll();

    // 新增一个物品
    int addGoods(Goods goods) throws GoodsExistException;

    // 删除一个物品
    int deleteGoods(String name) throws GoodsExistException;

    // 编辑一个物品
    int editGoods(Goods goods) throws GoodsExistException;

}