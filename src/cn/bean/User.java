package cn.bean;

public class User{
	
	private int id;
	private String name;
	private String passwd;
	private double money;
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPasswd(){
		return passwd;
	}
	
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	
	public double getMoney(){
		return money;
	}
	
	public void setMoney(double money){
		this.money = money;
	}
	
	public User(){
	
	}
	
	public User(int id, String name, String passwd, double money){
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.money = money;
	}
}