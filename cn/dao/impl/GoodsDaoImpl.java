package cn.dao.impl;

import java.util.Scanner;
import cn.dao.GoodsDao;
import cn.bean.Goods;
import cn.bean.User;

public class GoodsDaoImpl implements GoodsDao{
	
	//显示商品
	public void showGoods(Goods[] goods,String type){
		
		System.out.println("以下是"+type+"商品的在售清单：");
		System.out.println("id\t名称\t价格\t余量");
		for(int i=0;i<goods.length;i++){
			if(goods[i].getType().equals(type)){
				System.out.println(goods[i].getId()+"\t"+goods[i].getName()+"\t"+
					goods[i].getPrice()+"\t"+goods[i].getCount());
			}
		}
	}
	
	//添加至购物清单(库存不够返回false)
	public boolean addToCart(Goods[] myGoods,Goods[] goods,int id,int count){
		
		for(int i=0;i<goods.length;i++){
			if(goods[i].getId()==id){
				if(goods[i].getCount()>=count){
					for(int j=0;j<myGoods.length;j++){
						if(myGoods[j].getId()==0){
							myGoods[j].setId(goods[i].getId());
							myGoods[j].setName(goods[i].getName());
							myGoods[j].setPrice(goods[i].getPrice());
							myGoods[j].setCount(count);
							goods[i].setCount(goods[i].getCount()-count);
							return true;
						}
					}
					
				}else{
					return false;
				}
			}
		}
		
		System.out.println("未知错误");
		return false;
		
	}
	
	//显示购物清单
	public void showCart(Goods[] myGoods){
		System.out.println("你的购物清单如下：");
		System.out.println("名称\t\t价格\t数量\t总价");
		for(int i=0;i<myGoods.length;i++){
			if(myGoods.getId()!=0){
				System.out.println(myGoods[i].getName()+"\t\t"+myGoods[i].getPrice()+"\t"+
					myGoods[i].getCount()+"\t"+myGoods[i].getPrice()*myGoods[i].getCount());
			}
			
		}
		System.out.println("总价为"+total(myGoods)+"元");
		
	}
	
	//换购
	public void exchangeBuy(Goods[] myGoods, Goods[] exchangeGoods){
		
		if(total(myGoods)>=3000){
			Scanner input = new Scanner(System.in);
			System.out.println("您购物已满3000元，可以参加换购！");
			showGoods(exchangeGoods,"换购");
			System.out.println("请选择你需要换购的商品编号：");
			int no = input.nextInt();
			boolean flag = false;
			while(!flag){
				for(int j=0;j<exchangeGoods.length;j++){
					if(exchangeGoods[j].getId()==no){
						for(int k=0;k<myGoods.length;k++){
							if(myGoods[k].getId()==0){
							myGoods[k].setId(exchangeGoods[j].getId());
							myGoods[k].setName(exchangeGoods[j].getName());
							myGoods[k].setPrice(exchangeGoods[j].getPrice());
							myGoods[k].setCount(1);
							exchangeGoods[j].setCount(exchangeGoods[j].getCount()-1);
							System.out.println("换购商品添加购物车成功！");
							flag = true;
							}
						}
					}else{
						continue;
					}
				}
				if(!flag){
					System.out.println("商品编号不存在，请重新输入：");
					no = input.nextInt();
				}
			}
		}
	}
	
	//购买
	public boolean buy(Goods[] myGoods,User user){
		double resume = user.getMoney()-total(myGoods);
		if(resume<0){
			System.out.println("购买失败，资金不够！");
			return false;
		}else{
			user.setMoney(resume);
			System.out.println("以下是你本次购物的清单：");
			showCart(myGoods);
			System.out.println("总价："+total(myGoods)+"元");
			System.out.println("你的账户余额还剩"+resume+"元");
			return true;
		}
	}
	
	//计算总价
	public double total(Goods[] myGoods){
		double sum = 0;
		for(int i=0;i<myGoods.length;i++){
			sum+=myGoods[i].getPrice()*myGoods[i].getCount();
		}
		return sum;
	}
}