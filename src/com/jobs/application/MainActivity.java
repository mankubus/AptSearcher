package com.jobs.application;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends ExtActivity implements OnClickListener, OnTouchListener{	
	private EditText edNameMed;
	private Button btnSearch;
	private Spinner spDistinct;
	
	public static String sDist = null;
	public static String sMed = null;
	public static int selectedApt  = 0;
	public static String selectedMed = null;
	public static boolean loadedApts = false;
	public static ArrayList<AptItem> apts;
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.btnSearch = (Button) findViewById(R.id.btnSearch);
        this.edNameMed = (EditText) findViewById(R.id.edNameMed);
        this.spDistinct = (Spinner) findViewById(R.id.spDistrict);
        
        this.btnSearch.setOnClickListener(this);
        this.edNameMed.setOnTouchListener(this);
        ArrayAdapter<CharSequence> spDistict_adapter = 
        		ArrayAdapter.createFromResource(this, R.array.district_array, 
        		android.R.layout.simple_spinner_item);
        
        spDistict_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDistinct.setAdapter(spDistict_adapter);
        
	}
	
	public void onClick(View v)
	{
		sDist =  this.spDistinct.getSelectedItem().toString();
		sMed = this.edNameMed.getText().toString();
		
  	  	Intent medact_intent = new Intent("com.jobs.application.MedActivityAct");
  	  	startActivity(medact_intent);
	}

	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		if (((EditText) arg0).getText() != null)
		if (((EditText) arg0).getText().toString().equals("Название лекарства"))
		{
			((EditText) arg0).setText("");
		}
		return false;
	}
}