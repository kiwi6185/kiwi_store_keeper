package dao;

import com.jspsmart.upload.File;
import domain.Goods;
import exception.GoodsExistException;

import java.util.List;

public interface IGoodsDao {

    List showAll();

    int addGoods(Goods goods) throws GoodsExistException;

    int deleteGoods(String name) throws GoodsExistException;

    int editGoods(Goods goods) throws GoodsExistException;
}
