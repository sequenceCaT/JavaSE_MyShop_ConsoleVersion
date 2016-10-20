package cn.dao.impl;

import java.util.Scanner;
import cn.dao.UserDao;
import cn.bean.User;

public class UserDaoImpl implements UserDao{
	
	//登录
	public User login(User[] u){
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("请输入你的用户名");
		String username = input.nextLine();
		
		boolean isLogin = true;
		while(isLogin){
			for(int i=0;i<u.length;i++){
				if(u[i].getName().equals(username)){
					System.out.println("请输入你的密码");
					String password = input.nextLine();
					int counter = 0;
					while(counter!=3){
						if(u[i].getPasswd().equals(password)){
							System.out.print("登陆成功！");
							System.out.println("欢迎用户"+u[i].getName());
							return u[i];
						}else{
							System.out.println("密码错误，请重新输入");
							counter++;
							password = input.nextLine();
						}
					}
					if(isLogin){
						System.out.println("错误次数过多，强制退出");
						return null;
					}
				}else{
					continue;
				}
			}
			if(isLogin){
				System.out.println("你输入的用户名不存在，请重新输入：");
				username = input.nextLine();
			}
		}
		return null;
	}
	
	//注册
	public User register(User[] u){
		
		Scanner input = new Scanner(System.in);
		System.out.println("请输入用户名");
		String username = input.nextLine();
		
		for(int i=0;i<u.length;i++){
			if(u[i].getName()!=null&&u[i].getName().equals(username)){
				System.out.println("你输入的用户名已存在，请重新输入：");
				username = input.nextLine();
				i = 0;
			}
		}
		
		System.out.println("请输入密码");
		String password = input.nextLine();
		while(password.length()<6){
			System.out.println("密码长度太短，请重新输入：");
			password = input.nextLine();
		}
		System.out.println("请输入确认密码：");
		String confirmPwd = input.nextLine();
		while(!password.equals(confirmPwd)){
			System.out.println("确认密码错误，请重新输入：");
			confirmPwd = input.nextLine();
		}
		
		for(int i=0;i<u.length;i++){
			if(u[i].getId()==0){
				u[i].setId(u[i-1].getId()+1);
				u[i].setName(username);
				u[i].setPasswd(password);
				u[i].setMoney(5000);
				System.out.println("注册成功，系统赠送5000元。");
				return u[i];
			}
		}
		
		return null;
		
	}
	
	//充值
	public boolean charge(User u){
		Scanner input = new Scanner(System.in);
		System.out.println("请输入充值金额");
		double money = input.nextDouble();
		
		u.setMoney(u.getMoney()+money);
		System.out.println("充值成功！");
		return true;
		
	}
}