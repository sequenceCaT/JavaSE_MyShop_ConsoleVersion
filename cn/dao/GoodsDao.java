package cn.dao;

import cn.bean.Goods;
import cn.bean.User;

/**
 * 商品类
 */
 
public interface GoodsDao{
	
	//显示商品
	public void showGoods(Goods[] goods,String type);
	
	//添加至购物清单(库存不够返回false)
	public boolean addToCart(Goods[] myGoods,Goods[] goods,int id,int count);
	
	//显示购物清单
	public void showCart(Goods[] myGoods);
	
	//换购
	public void exchangeBuy(Goods[] myGoods, Goods[] exchangeGoods);
	
	//购买
	public boolean buy(Goods[] myGoods,User user);
	
	//计算总价
	public double total(Goods[] myGoods);
	
}