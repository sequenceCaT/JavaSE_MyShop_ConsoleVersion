package cn.util;

import cn.bean.User;
import cn.bean.Goods;
import cn.dao.UserDao;
import cn.dao.GoodsDao;
import cn.dao.impl.UserDaoImpl;
import cn.dao.impl.GoodsDaoImpl;
import java.util.Scanner;

public class ShopUtil{
	
	//商品列表
	private Goods[] goods = new Goods[10];
	//换购列表
	private Goods[] exchangeGoods = new Goods[3];
	//用户列表
	private User[] users = new User[10];
	//当前用户
	private User presentUser;
	//购物车
	private Goods[] myGoods = new Goods[15];

	UserDao userDao = new UserDaoImpl();
	GoodsDao goodsDao = new GoodsDaoImpl();

	Scanner input = new Scanner(System.in);
	
 	//初始化
	public void init(){
		for(int i=0;i<goods.length;i++){
			goods[i] = new Goods();
		}
		for(int i=0;i<exchangeGoods.length;i++){
			exchangeGoods[i] = new Goods();
			exchangeGoods[i].setType("换购");
		}
		for(int i=0;i<users.length;i++){
			users[i] = new User();
		}
		for(int i=0;i<myGoods.length;i++){
			myGoods[i] = new Goods();
		}
		
		goods[0].setId(1);
		goods[0].setName("三星电视");
		goods[0].setType("电器");
		goods[0].setPrice(6999);
		goods[0].setCount(15);
		goods[1].setId(2);
		goods[1].setName("海尔冰箱");
		goods[1].setType("电器");
		goods[1].setPrice(3599);
		goods[1].setCount(30);
		goods[2].setId(3);
		goods[2].setName("西门子洗衣机");
		goods[2].setType("电器");
		goods[2].setPrice(3999);
		goods[2].setCount(50);
		goods[3].setId(4);
		goods[3].setName("格力空调");
		goods[3].setType("电器");
		goods[3].setPrice(2999);
		goods[3].setCount(40);

		goods[4].setId(5);
		goods[4].setName("羽绒服");
		goods[4].setType("服饰");
		goods[4].setPrice(99);
		goods[4].setCount(300);
		goods[5].setId(6);
		goods[5].setName("连衣裙");
		goods[5].setType("服饰");
		goods[5].setPrice(199);
		goods[5].setCount(200);
		goods[6].setId(7);
		goods[6].setName("耐克跑鞋");
		goods[6].setType("服饰");
		goods[6].setPrice(399);
		goods[6].setCount(60);

		goods[7].setId(8);
		goods[7].setName("水晶富士");
		goods[7].setType("水果");
		goods[7].setPrice(16);
		goods[7].setCount(90);
		goods[8].setId(9);
		goods[8].setName("香蕉");
		goods[8].setType("水果");
		goods[8].setPrice(18);
		goods[8].setCount(50);
		goods[9].setId(10);
		goods[9].setName("葡萄");
		goods[9].setType("水果");
		goods[9].setPrice(15);
		goods[9].setCount(88);

		users[0].setId(1);
		users[0].setName("admin");
		users[0].setPasswd("123456");
		users[0].setMoney(100000);
		
		exchangeGoods[0].setId(101);
		exchangeGoods[0].setName("立白洗衣粉");
		exchangeGoods[0].setPrice(8);
		exchangeGoods[0].setCount(10);
		exchangeGoods[1].setId(102);
		exchangeGoods[1].setName("立白洗洁精");
		exchangeGoods[1].setPrice(10);
		exchangeGoods[1].setCount(15);
		exchangeGoods[2].setId(103);
		exchangeGoods[2].setName("飘柔旅行版");
		exchangeGoods[2].setPrice(9);
		exchangeGoods[2].setCount(30);
		
	}
	
	//界面显示
	public void view(){

		init();

		System.out.println("--------欢迎来到XX商城--------");

		boolean isBuyCountinue = false;

		do{
			System.out.println("请选择你需要浏览的商品类别：");
			System.out.println("		1.电器");
			System.out.println("		2.服饰");
			System.out.println("		3.水果");
			System.out.println("请选择：");

			int choice = input.nextInt();
			boolean choiceFlag = false;
			while(!choiceFlag){
				boolean isChoiceOk = (choice==1)?true:(choice==2||choice==3?true:false);
				if(isChoiceOk){
					choiceFlag = true;
				}else{
					System.out.println("你的输入有误，请重新输入：");
				}
			}

			switch(choice){
				case 1:goodsDao.showGoods(goods,"电器");break;
				case 2:goodsDao.showGoods(goods,"服饰");break;
				case 3:goodsDao.showGoods(goods,"水果");break;
			}

			System.out.println("请选择你需要购买的商品编号：");
			int number = input.nextInt();

			if(presentUser==null){
				System.out.println("你还没有登录，请登录或注册：");
				System.out.println("		1.登录");
				System.out.println("		2.注册");
				System.out.println("请选择：");
				choice = input.nextInt();
				choiceFlag = false;
				while(!choiceFlag){
					if(choice==1){
						presentUser=userDao.login(users);
						if(presentUser==null){
							System.exit(0);
						}else{
							choiceFlag = true;
						}
					}else if(choice==2){
						presentUser=userDao.register(users);
						if(presentUser==null){
							System.exit(0);
						}else{
							choiceFlag = true;
						}
					}else{
						System.out.println("你的输入有误，请重新输入：");
						choice = input.nextInt();
					}
				}
			}

			System.out.println("请输入你要购买的件数：");
			int count = input.nextInt();

			boolean isAddToCartFlag = false;
			while(!isAddToCartFlag){
				for(Goods g:goods){
					if(g.getId()==number){
						boolean isAddSuccessful = goodsDao.addToCart(myGoods,goods,number,count);
						if(isAddSuccessful){
							System.out.println("添加至购物车成功！");
							isAddToCartFlag = true;
						}else{
							System.out.println("商品库存不足，请重新输入。可购买量应在"+
								g.getCount()+"以下：");
							count = input.nextInt();
						}
					}
				}
				if(!isAddToCartFlag){
					System.out.println("你输入的id不合法，请重新输入：");
					number = input.nextInt();
				}
				
			}

			System.out.println("请问你需要继续购物吗？(y/n)");
			String isContinue = input.nextLine();
			boolean isContinueFlag = false;
			while(!isContinueFlag){
				if(isContinue.equals("y")||isContinue.equals("Y")){
					isContinueFlag = true;
					isBuyCountinue = true;
				}else if(isContinue.equals("N")||isContinue.equals("n")){
					isContinueFlag = true;
					isBuyCountinue = false;
				}else{
					System.out.println("你的输入不合法，请重新输入：");
					isContinue = input.nextLine();
				}
			}

		}while(isBuyCountinue);

		goodsDao.exchangeBuy(myGoods,exchangeGoods);

		goodsDao.showCart(myGoods);
		System.out.println("请问你要结账吗？(y/n)");
		String isCash = input.nextLine();
		boolean isCashFlag = false;
		while(!isCashFlag){
			if(isCash.equals("y")||isCash.equals("Y")){
				isCashFlag = true;
			}else if(isCash.equals("n")||isCash.equals("N")){
				System.out.println("你取消了购买，程序退出。");
				System.exit(0);
			}else{
				System.out.println("你的输入不合法，请重新输入：");
				isCash = input.nextLine();
			}
		}

		boolean flag = goodsDao.buy(myGoods,presentUser);
		if(flag){
			System.out.println("购买成功，祝你生活愉快！");
		}else{
			System.out.println("购买失败，程序退出！");
			System.exit(0);
		}

	}

}