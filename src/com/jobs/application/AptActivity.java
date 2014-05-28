package com.jobs.application;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AptActivity  extends ExtActivity implements OnClickListener{
	private ListView lvApts;
	private ImageView imgAptNext;
	
	private ArrayList <HashMap<String, Object>> aptItems;
	private static final String TITLEKEY = "apttitle";
	private static final String DISTKEY = "aptdist";
	private static final String COSTKEY = "aptcost";
	private static final String IMGKEY = "apticon";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apts);

        
        this.lvApts = (ListView) findViewById(R.id.lvApts);
        this.imgAptNext = (ImageView) findViewById(R.id.img_balloon);
        
        this.imgAptNext.setClickable(true);
        this.imgAptNext.setOnClickListener(this);

        aptItems = new ArrayList<HashMap<String,Object>>();
        
        Refresh();
        
        lvApts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	      //@Override
    	      public void onItemClick(AdapterView<?> av, View view, int index, long arg3) {
    	    	  MainActivity.selectedApt = index;
    	    	  Intent intent = new Intent( "com.jobs.application.AptDetailsActivityAct");
    	    	  startActivity(intent);
    	      }
      	});
        
        SimpleAdapter adapter = new SimpleAdapter(this, 
        		aptItems, 
                R.layout.aptitems,
                new String[]{TITLEKEY, DISTKEY, COSTKEY, IMGKEY},
                new int[]{R.id.tvAptTitle, R.id.tvAptDist, R.id.tvAptCost, R.id.imgAptNext});
        
        lvApts.setAdapter(adapter);
        lvApts.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
	
    private void Refresh() {
    	MainActivity.apts = AptItem.getItems(MainActivity.sDist, MainActivity.selectedMed);
    	MainActivity.loadedApts = true;
    	
    	HashMap<String, Object> AptHash;
    	
    	for(int i=0;i<MainActivity.apts.size();i++)
    	{
    		AptItem a = MainActivity.apts.get(i);
    		
    		AptHash = new HashMap<String, Object>();
    		AptHash.put(TITLEKEY, a.getTitle());
    		AptHash.put(DISTKEY, a.getAddr());
    		AptHash.put(COSTKEY, a.getCost()+"ð.");
    		AptHash.put(IMGKEY,  R.drawable.map_icon);
    		
    		aptItems.add(AptHash);
    	}
    	
    	return;
    }
    
    public void onClick(View v) {
	  	Intent manyapt_intent = new Intent("com.jobs.application.AptViewerAct");
  	  	startActivity(manyapt_intent);
    }
}
