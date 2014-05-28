package com.jobs.application;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MedActivity extends ExtActivity{
	private ListView lvMeds;
	
	private ArrayList<MedItem> medItems = new ArrayList<MedItem>();
	private	ArrayAdapter<MedItem> arrAdapter = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicines);
        
        this.lvMeds = (ListView) findViewById(R.id.lvMeds);
    	lvMeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  	      //@Override
  	      public void onItemClick(AdapterView<?> av, View view, int index, long arg3) {
  	    	  MainActivity.selectedMed = medItems.get(index).getTitle();
  	    	  Intent intent = new Intent( "com.jobs.application.AptActivityAct");
  	    	  startActivity(intent);
  	      }
    	});
    	
        arrAdapter = new ArrayAdapter<MedItem>(this, R.layout.meditems, medItems);
        
        lvMeds.setAdapter(arrAdapter);
        lvMeds.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        Refresh();
	}
	
    private void Refresh() {
        ArrayList<MedItem> newItems = MedItem.getItems(MainActivity.sMed, MainActivity.sDist);
        medItems.clear();
        medItems.addAll(newItems);
        arrAdapter.notifyDataSetChanged();
    }
}
