package com.vop.map.overlays;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.webkit.WebChromeClient.CustomViewCallback;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.vop.arwalks.Messages;
import com.vop.arwalks.ShowMessage;

public class SpecialImageOverlay extends ItemizedOverlay<CustomOverlayItem> {

	private ArrayList<CustomOverlayItem> mOverlays = new ArrayList<CustomOverlayItem>();
	Context mContext;

	public SpecialImageOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	public SpecialImageOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	@Override
	protected CustomOverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	public void addOverlay(CustomOverlayItem overlay) {
		Drawable d =overlay.getMarker(0);
		if( d!= null){
			boundCenter(d);
			overlay.setMarker(d);
		}
		
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected boolean onTap(int index) {
		CustomOverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		if (item != null) {
			Intent myIntent = new Intent(mContext, ShowMessage.class);
			myIntent.putExtra("id", item.getLocation().getId());
			mContext.startActivity(myIntent);
		}
		return true;
	}

}
