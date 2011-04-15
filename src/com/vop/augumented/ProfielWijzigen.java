package com.vop.augumented;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vop.tools.DBWrapper;
import com.vop.tools.FullscreenActivity;
import com.vop.tools.VopApplication;
import com.vop.tools.data.Person;


public class ProfielWijzigen extends FullscreenActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.profielwijzigen);
		VopApplication app = (VopApplication) getApplicationContext();
		int id = Integer.parseInt(app.getState().get("userid"));
		Person p = DBWrapper.getProfile(id);
		final EditText newnamebox = (EditText) findViewById(R.id.new_name);
		final EditText newphonebox = (EditText) findViewById(R.id.new_phone);
		final EditText newemailbox = (EditText) findViewById(R.id.new_mail);	
		newnamebox.setText(p.getName());
		newphonebox.setText(p.getPhone());
		newemailbox.setText(p.getEmail());
	}
	
	public void go_back(View v) {
		finish();
	}
	
	public void new_profile(View v) {
		final ProgressDialog wachten = ProgressDialog.show(this, "",
				"Editing profile. Please be patient...", true);
		final EditText newnamebox = (EditText) findViewById(R.id.new_name);
		final EditText newphonebox = (EditText) findViewById(R.id.new_phone);
		final EditText newemailbox = (EditText) findViewById(R.id.new_mail);	
		new Thread(new Runnable(){  
			public void run(){  
				VopApplication app = (VopApplication) getApplicationContext();
				int id = Integer.parseInt(app.getState().get("userid"));
				Person p = DBWrapper.getProfile(id);
				p.setName(newnamebox.getText().toString());
				p.setPhone(newphonebox.getText().toString());
				p.setEmail(newemailbox.getText().toString());
				DBWrapper.save(p); 
				wachten.dismiss();
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(getApplicationContext(),
								"Changing profile succeeded!",
								Toast.LENGTH_LONG).show();
						
					}
				});
			}  
			}).start(); 
		finish();
	}

}