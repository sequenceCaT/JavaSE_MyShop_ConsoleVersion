package cn.dao;

import cn.bean.User;

public interface UserDao{

	//登录
	public User login(User[] u);

	//注册
	public User register(User[] u);
	
	//充值
	public boolean charge(User u);
	
}