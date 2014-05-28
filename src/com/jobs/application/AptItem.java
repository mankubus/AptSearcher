package com.jobs.application;

import java.util.ArrayList;

public class AptItem {
	private String title;
	private String dist;
	private String addr;
	private String phone;
	private double cost;
	private double coor_x;
	private double coor_y;
	private String date;
	
	/** Constructor */
	public AptItem(String _title, String _dist, String _addr, String _phone, double _cost, double x, double y){
		this.title = _title;
		this.dist = _dist;
		this.addr = _addr;
		this.phone = _phone;
		this.cost = _cost;
		this.coor_x = x;
		this.coor_y = y;
		
		this.date = "31.11.2011";
	}
	
	public String getTitle(){
	    return this.title;
	}

	public String getDist(){
	    return this.dist;
	}
	
	public String getAddr(){
	    return this.addr;
	}	
	
	public String getPhone(){
	    return this.phone;
	}
	
	public double getCost(){
	    return this.cost;
	}
	
	public double getX(){
		return this.coor_x;
	}
	
	public double getY(){
		return this.coor_y;
	}

	public String getDate(){
	    return this.date;
	}
	
	@Override
	  public String toString() {
	    String result = getTitle();
	    return result;
	  }
	
	/** Получаем список аптек тут */
	public static ArrayList<AptItem> getItems(String dist, String _medicament)
	{	
		ArrayList<AptItem> _aptItems = new ArrayList<AptItem>();
		
		AptItem _it = new AptItem("Моя аптека", "Советский", "Морской просп., 24", "330-74-56", 120, 54.83895, 83.105529);
		_aptItems.add(_it);
		
		_it = new AptItem("Аптека 78", "Советский", "Морской просп., 22", "330-24-16", 122, 54.838929, 83.106314);
		_aptItems.add(_it);

		_it = new AptItem("Радуга", "Советский", "Морской просп., 6", "330-44-92", 122, 54.840376, 83.109412);
		_aptItems.add(_it);
		
		_it = new AptItem("Аптека ур-го центра", "Советский", "ул. Терешковой, 36", "330-36-37", 122, 54.839083, 83.112116);
		_aptItems.add(_it);
		
		_it = new AptItem("Аквамарин-С", "Советский", "ул. Академическая, 19", "330-19-37", 122, 54.837423, 83.111129);
		_aptItems.add(_it);
		
		_it = new AptItem("Тестовая", "Советский", "ул. Академическая, 19", "330-19-37", 121, 54.838423, 83.114129);
		_aptItems.add(_it);
		return _aptItems;
	}

}
