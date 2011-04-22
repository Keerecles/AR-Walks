package com.vop.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import com.vop.augumented.R;
import com.vop.overlays.Marker;
import com.vop.tools.data.Person;
import com.vop.tools.data.Point;
import com.vop.tools.data.Traject;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class VopApplication extends Application {
	public final static String PREFS = "VOPPREFS";
	public final static String LOGTAG= "vop_log";
	
	private HashMap<String, String> state;
	private Marker[] punten;
	private double lng;
	private double lat;
	private double alt;

	private float roll;
	private double pitch;
	private double heading;
	private float output[];
	private float max_afstand;
	private int dichtste_punt;
	private Traject traject;
	private Person persoon;
	private Marker POI[];

	public Person getPersoon() {
		return persoon;
	}

	public void setPersoon(Person persoon) {
		this.persoon = persoon;
	}

	public Traject getTraject() {
		return traject;
	}

	public void setTraject(Traject traject) {
		this.traject = traject;
	}

	public int getDichtste_punt() {
		return dichtste_punt;
	}

	public float getMax_afstand() {
		return max_afstand;
	}

	public void setMax_afstand(float max_afstand) {
		this.max_afstand = max_afstand;
	}

	public  synchronized float[] getValues() {
		return output;
	}

	public  synchronized void setValues(float[] values) {
		/*if(this.output != null){
			for(int i = 0;i<16;i++){
				this.output[i] = (float) (0.535144118 * values[i] -0.132788237 * input_1[i] -0.402355882 * input_2[i] -0.154508496 * output_1[i] -0.0625 * output_2[i]);
			}
			input_2 = input_1; 
			input_1 = values; 
			output_2 = output_1; 
			output_1 = output;
		}
		else{
			input_2 = values;
			input_1 = values;
			output_2 = values;
			output_1 = values;
			this.output = values;
		}*/
		//this.output = values;
		/*if(first == false){
			histo[i]= values;
			for(int j=0;j<16;j++){
				output[j]= 0;
				for(int k=0;k<10;k++) output[j]+=histo[k][j];
				output[j]= (float) (output[j]*1.0/50);
			}
			i = i+1;
			if(i == 50) i =0;
		}
		else{
			first=false;
			i =0;
			histo = new float[50][16];
			for(int j =0;j<50;j++){
				histo[j] = values;
			}
			output = values;
			
		}*/
		this.output=values;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getAlt() {
		return alt;
	}

	public void setAlt(double alt) {
		this.alt = alt;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}

	public VopApplication() {
		super();
		state = new HashMap<String, String>();
	}

	public Marker[] getPunten() {
		return punten;
	}

	public void setPunten(Marker[] punten) {
		this.punten = punten;
	}

	public HashMap<String, String> getState() {
		return state;
	}

	public void setState(HashMap<String, String> state) {
		this.state = state;
	}

	public String putState(String k, String v) {
		return state.put(k, v);
	}

	private boolean isLocked = false;

	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			wait();
		}
		isLocked = true;
	}

	public synchronized void unlock() {
		isLocked = false;
		notify();
	}
	public void construeer() {
		Marker POI[];
		ArrayList<com.vop.tools.data.Location> loc = DBWrapper.getLocations(2);
		POI = new Marker[loc.size()];
		List<Marker> list = new ArrayList<Marker>();
		for (com.vop.tools.data.Location l : loc) {
			list.add(new Marker(l.getName(), l.getDescription(),
					l.getLongitude(), l.getLatitute(), alt, lat, lng,
					alt, roll));
		}
		Collections.sort(list);
		//POI = (Marker[]) list.toArray();
		for(int i=0;i<list.size();i++){
			POI[i] = list.get(i);
		}
		/*int j = 0;
		for (com.vop.tools.data.Location l : loc) {
			POI[j] = new Marker(l.getName(), l.getDescription(),
					l.getLongitude(), l.getLatitute(), alt, lat, lng,
					alt, roll);
			j++;
		}*/
		this.setPunten(POI);
		Toast toast = Toast.makeText(getApplicationContext(), "POI inladen", Toast.LENGTH_SHORT);
		toast.show();
	}
	public void vernieuw() {
		for (int j=0;j<punten.length;j++) {
			punten[j] = new Marker(punten[j].getTitel(), punten[j].getInfo(),
					punten[j].getLng(), punten[j].getLat(), punten[j].getAlt(),
					getApplicationContext());
		}
		Toast toast = Toast.makeText(getApplicationContext(), "POI vernieuwen", Toast.LENGTH_SHORT);
		toast.show();
	}
	public void construeer2(final Activity activity) {
				ArrayList<com.vop.tools.data.Location> loc = DBWrapper.getLocations(2);
				POI = new Marker[loc.size()];
				List<Marker> list = new ArrayList<Marker>();
				for (com.vop.tools.data.Location l : loc) {
					list.add(new Marker(l.getName(), l.getDescription(),
							l.getLongitude(), l.getLatitute(), alt, lat, lng,
							alt, roll));
				}
				Collections.sort(list);
				for(int i=0;i<list.size();i++){
					POI[i] = list.get(i);
				}
				setPunten(POI);	
	}

}
