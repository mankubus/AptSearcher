package com.jobs.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonRender;
import ru.yandex.yandexmapkit.overlay.balloon.OnBallonListener;
import ru.yandex.yandexmapkit.overlay.location.MyLocationOverlay;
import ru.yandex.yandexmapkit.utils.GeoPoint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.MotionEvent;

public class AptViewer extends ExtActivity implements OnBallonListener{
	private MapView mMapView;
	private MapController mMapController;
	private OverlayManager mOverlayManager;
	private TeLocationOverlay mylock;
	private OverlayItem mapapt;
	
	public class intdob
	{
		public int index;
		public double rank;
		
		public intdob(int a, double b)
		{
			this.index = a;
			this.rank = b;
		}
	}
	
	public class TeLocationOverlay extends MyLocationOverlay
	{
		private MapController mMapCtrl;
		
		public TeLocationOverlay(MapController arg0) {
			super(arg0);
			this.mMapCtrl = arg0;
		}

		@Override
		public void onLocationChanged(Location arg0)
		{
			GeoPoint currentGP = new GeoPoint(arg0.getLatitude(), arg0.getLongitude());
		
			showMe(currentGP);
			int zm = showApts(currentGP);
			this.mMapCtrl.setPositionNoAnimationTo(currentGP, zm);
			return;
		}
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manyapt);
        
        mMapView = (MapView) findViewById(R.id.manymaps);
        mMapView.showZoomButtons(true);
        mMapView.showFindMeButton(false);
        mMapView.showJamsButton(false);
        mMapController = mMapView.getMapController();
        
        mOverlayManager = mMapController.getOverlayManager();
        mOverlayManager.getMyLocation().setEnabled(false);
        
        //54,839757 83,108789
        mylock = new TeLocationOverlay(mMapController);
        mylock.setEnabled(true);
        mylock.findMe();
	}
	
    public void showObject(AptItem _defitem){
        Resources res = getResources();
        Bitmap innerBitmap = BitmapFactory.decodeResource(res , R.drawable.map_green);

        Overlay overlay = new Overlay(mMapController);

        OverlayItem mapapt = new OverlayItem(new GeoPoint(_defitem.getX() , _defitem.getY()), innerBitmap);
        BalloonItem balloonmapapt = new BalloonItem(mapapt.getGeoPoint(), innerBitmap);
        balloonmapapt.setAlign(BalloonRender.ALIGN_LEFT | BalloonRender.ALIGN_CENTER);
        balloonmapapt.setText("<b>"+_defitem.getTitle() + "</b><br/>" + _defitem.getCost() + "ð.");
        balloonmapapt.setOnBallonListener(this);
        mapapt.setBalloonItem(balloonmapapt);
        overlay.addOverlayItem(mapapt);

        mOverlayManager.addOverlay(overlay);
    }
    
    public void showMe(GeoPoint gp){
        Resources res = getResources();
        Bitmap innerBitmap = BitmapFactory.decodeResource(res , R.drawable.map_red);

        Overlay overlay = new Overlay(mMapController);
        
        if (mapapt != null)
        {
        	mapapt.setGeoPoint(gp);
        }
        else
        {
        	mapapt = new OverlayItem(gp, innerBitmap);
        }
        
        BalloonItem balloonmapapt = new BalloonItem(mapapt.getGeoPoint(), innerBitmap);
        balloonmapapt.setAlign(BalloonRender.ALIGN_LEFT | BalloonRender.ALIGN_CENTER);
        balloonmapapt.setText("ß");
        balloonmapapt.setOnBallonListener(this);
        mapapt.setBalloonItem(balloonmapapt);
        overlay.addOverlayItem(mapapt);

        mOverlayManager.addOverlay(overlay);
    }
    
    public void onBallonClick(MotionEvent arg0, BalloonItem ballonItem) {
        int k = -1;
        String s = "";
        for (int i = 0;i < MainActivity.apts.size();i++)
        {
        	s = "<b>"+MainActivity.apts.get(i).getTitle() + "</b><br/>" + MainActivity.apts.get(i).getCost() + "ð.";
        	if (ballonItem.getText().equals(s))
        	{
        		k = i;
        	}
        }
        
        if (k == -1) return;
        
        MainActivity.selectedApt = k;
  	  	
  	    Intent intent = new Intent().setClass(this, AptDetails.class);
  	  	startActivity(intent);
    }
    
    public double rad(double a)
    {
    	return (Math.PI * 180) / a;
    }
    
    public double getRank(GeoPoint myLoc, double lat, double lng){
    	double q = rad( lng );
    	q = q - rad(myLoc.getLon());
    	return 6371 * Math.acos( Math.cos( rad(myLoc.getLat()) ) * Math.cos( rad( lat ) ) * Math.cos( q ) + Math.sin( rad(myLoc.getLat()) ) * Math.sin( rad( lat ) ) );
    	
    }
    public int showApts(GeoPoint myLocation)
    {   	    	
    	ArrayList<intdob> ar = new ArrayList<intdob>();
    	ar.clear();
    	
    	double r = 0;
    	for(int i = 0; i < MainActivity.apts.size(); i++)
    	{	
    		r = getRank(myLocation, MainActivity.apts.get(i).getX(), MainActivity.apts.get(i).getY());
    		intdob _id= new intdob(i, r);
    		ar.add(_id);
    	}
    	
    	int zoom = 15;
    	if (MainActivity.apts.size() > 4)
    	{
    		int g = (int) (ar.get(4).rank  - 0.2); 
    		zoom = 18 - g;
    		if (zoom < 9) zoom = 9;
    	}
    	
    	Collections.sort(ar, new Comparator<intdob>() {
    		@SuppressWarnings({ "unchecked", "rawtypes" })
			public int compare(intdob o1, intdob o2) 
    		{
    			return (((Comparable) o1.rank).compareTo(o2.rank));
   			}
    	});
    	
    	for(int i = 0; (i < ar.size())&&(i < 5); i++)
    	{
    		showObject(MainActivity.apts.get(ar.get(i).index));
    	}
    	
    	return zoom;
    }

}
