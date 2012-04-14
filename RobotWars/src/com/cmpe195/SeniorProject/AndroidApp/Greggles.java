package com.cmpe195.SeniorProject.AndroidApp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Greggles extends Activity {
	 
	private TextView Greggles;
	
    /** Called when the activity is first created. */
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.greggles);
	     
	     Button start = (Button)findViewById(R.id.startButton);
	     Button stop = (Button)findViewById(R.id.cancelButton);
	     
	     start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(Greggles.this, SocketService.class));
			}
	     });
	     
	     stop.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		stopService(new Intent(Greggles.this, SocketService.class));
	    	}
	     });
	     
	 }
}
