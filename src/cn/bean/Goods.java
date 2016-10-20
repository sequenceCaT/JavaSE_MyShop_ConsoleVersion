package cn.bean;

public class Goods{
	
	private int id;
	private String name;
	private String type;
	private double price;
	private int count;
	
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
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public Goods(){
		
	}
	
	public Goods(int id, String name, String type, double price, int count){
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.count = count;
	}
	
}