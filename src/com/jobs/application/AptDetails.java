package com.jobs.application;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonRender;
import ru.yandex.yandexmapkit.utils.GeoPoint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

public class AptDetails extends ExtActivity{	
	private TextView tvDetMedname;
	private TextView tvDetMedcost;
	private TextView tvDetMeddate;
	
	private TextView tvDetAptname;
	private TextView tvDetAptdist;
	private TextView tvDetAptAddr;
	private TextView tvDetAptPhon;
	
	private MapView mMapView;
	private MapController mMapController;
	private OverlayManager mOverlayManager;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aptdetails);
        
        this.tvDetMedname = (TextView) findViewById(R.id.tv_det_medname);
        this.tvDetMedcost = (TextView) findViewById(R.id.tv_det_cost);
        this.tvDetMeddate = (TextView) findViewById(R.id.tv_det_date);
        this.tvDetAptname = (TextView) findViewById(R.id.tv_det_aptname);
        this.tvDetAptdist = (TextView) findViewById(R.id.tv_det_dist);
        this.tvDetAptAddr = (TextView) findViewById(R.id.tv_det_addr);
        this.tvDetAptPhon = (TextView) findViewById(R.id.tv_det_phones);
        
        
        this.tvDetMedname.setText(MainActivity.selectedMed);
        this.tvDetMedcost.setText("Цена: " + MainActivity.apts.get(MainActivity.selectedApt).getCost() + " р.");
        this.tvDetMeddate.setText("Наличие на: " + MainActivity.apts.get(MainActivity.selectedApt).getDate());
        
        this.tvDetAptname.setText(MainActivity.apts.get(MainActivity.selectedApt).getTitle());
        this.tvDetAptdist.setText(MainActivity.sDist);
        this.tvDetAptAddr.setText("Адрес: " + MainActivity.apts.get(MainActivity.selectedApt).getAddr());
        this.tvDetAptPhon.setText("Телефоны: " + MainActivity.apts.get(MainActivity.selectedApt).getPhone());
        
        
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.showZoomButtons(true);
        mMapView.showFindMeButton(false);
        mMapView.showJamsButton(false);
        mMapController = mMapView.getMapController();
        mMapController.setPositionNoAnimationTo(new GeoPoint(MainActivity.apts.get(MainActivity.selectedApt).getX(), MainActivity.apts.get(MainActivity.selectedApt).getY()), 15);       
        
        mOverlayManager = mMapController.getOverlayManager();
        mOverlayManager.getMyLocation().setEnabled(false);
        
        showObject(MainActivity.apts.get(MainActivity.selectedApt));
	}
	
    public void showObject(AptItem _defitem){
        Resources res = getResources();
        Bitmap innerBitmap = BitmapFactory.decodeResource(res , R.drawable.map_green);

        Overlay overlay = new Overlay(mMapController);

        OverlayItem mapapt = new OverlayItem(new GeoPoint(_defitem.getX() , _defitem.getY()), innerBitmap);
        BalloonItem balloonmapapt = new BalloonItem(mapapt.getGeoPoint(), innerBitmap);
        balloonmapapt.setAlign(BalloonRender.ALIGN_LEFT | BalloonRender.ALIGN_CENTER);
        balloonmapapt.setText("<b>"+_defitem.getTitle() + "</b><br/>" + _defitem.getPhone());
        mapapt.setBalloonItem(balloonmapapt);
        overlay.addOverlayItem(mapapt);

        mOverlayManager.addOverlay(overlay);
    }

}
