package com.jobs.application;

import android.app.Activity;
import android.os.Bundle;

public class ExtActivity extends Activity{
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	
		if (MainActivity.sDist != null) outState.putString("sDist", MainActivity.sDist);
		if (MainActivity.sMed != null) outState.putString("sMed", MainActivity.sMed);
		outState.putBoolean("loadedApts", MainActivity.loadedApts);
		if (MainActivity.selectedApt != 0) outState.putInt("selectedApt", MainActivity.selectedApt);
		if (MainActivity.selectedMed != null) outState.putString("selectedMed", MainActivity.selectedMed);
		
		//Log.d("usersss", " obj saved");
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
        	if (savedInstanceState.containsKey("sDist")) MainActivity.sDist = savedInstanceState.getString("sDist");
        	if (savedInstanceState.containsKey("sMed")) MainActivity.sMed = savedInstanceState.getString("sMed");
        	if (savedInstanceState.containsKey("selectedMed")) MainActivity.selectedMed = savedInstanceState.getString("selectedMed");
        	if (savedInstanceState.containsKey("selectedApt")) MainActivity.selectedApt = savedInstanceState.getInt("selectedApt");
        	if (savedInstanceState.containsKey("loadedApts")) MainActivity.loadedApts = savedInstanceState.getBoolean("loadedApts");
        	
        	if ((MainActivity.sDist != null)&&(MainActivity.selectedMed != null)&&(MainActivity.loadedApts))MainActivity.apts = AptItem.getItems(MainActivity.sDist, MainActivity.selectedMed);
        	
        	//Log.d("usersss", " obj LOADED!!!!!!!!!!!!!!!!");
        }
	}
}
