package com.jobs.application;

import java.util.ArrayList;

public class MedItem {
	private String title;
	
	/** Constructor */
	public MedItem(String _title){
		this.title = _title;
	}
	
	public String getTitle(){
	    return this.title;
	}
	
	@Override
	  public String toString() {
	    String result = getTitle();
	    return result;
	  }
	
	/** �������� ������ ���������� ��� */
	/** �������� - ��������! */
	public static ArrayList<MedItem> getItems(String _name, String _dist)
	{
		
		ArrayList<MedItem> _medItems = new ArrayList<MedItem>();
		
		MedItem _it = new MedItem("������");
		_medItems.add(_it);
		_it = new MedItem("��������");
		_medItems.add(_it);
		_it = new MedItem("���������");
		_medItems.add(_it);
		_it = new MedItem("����������");
		_medItems.add(_it);
		_it = new MedItem("�����������");
		_medItems.add(_it);
		_it = new MedItem(_name);
		_medItems.add(_it);
		
		return _medItems;
	}
	
}
