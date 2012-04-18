package com.cmpe195.SeniorProject.AndroidApp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class ConfigScreen extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config_screen);
		
		Button connectButton = (Button) findViewById(R.id.debugButton);
		connectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startDebugScreen();
				//finish();
			}
		});
		
		Button gameButton = (Button) findViewById(R.id.gameButton);
		gameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startGameScreen();
				//finish();
			}
		});
		
		Button ssc = (Button) findViewById(R.id.ssc);
		ssc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startGreggles();
				//finish();
			}
		});
			
	}	
	
	private void startDebugScreen()
 	{
 		try{
 			
 			EditText serverIPaddress = (EditText) findViewById(R.id.ipAddress_input);
 			EditText portNumber = (EditText) findViewById(R.id.portNumber_input);
 			
 			String ipAddress = serverIPaddress.getText().toString();
 			String portNum = portNumber.getText().toString();
 			
 			// commented out - check that IP address is of format ###.###.###.###
 			/* InputFilter[] filters = new InputFilter[1];
 			filters[0] = new InputFilter() {
 			@Override
 			public CharSequence filter(CharSequence source, int start, int end,
 			     android.text.Spanned dest, int dstart, int dend) {
 			     if (end > start) {
 			           String destTxt = dest.toString();
 			           String resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend);
 			           if (!resultingTxt.matches ("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) { 
 			                return "";
 			           } else {
 			                String[] splits = resultingTxt.split("\\.");
 			                for (int i=0; i<splits.length; i++) {
 			                    if (Integer.valueOf(splits[i]) > 255) {
 			                        return "";
 			                    }
 			                 }
 			             }
 			          }
 			     return null;
 			     }

 			};
 			serverIPaddress.setFilters(filters);
 			*/

			// don't start the activity if the IP/port fields are blank
			if(ipAddress.equals("") == true || portNum.equals("") == true)
			{
				Toast.makeText(this, "Enter an IP Address and Port Number first.", Toast.LENGTH_SHORT).show();
			}
			else
			{
				int port = Integer.parseInt(portNum);
	 			// pass data to another activity
	 	 		Intent search = new Intent(this, DebugScreen.class);
	 	 			 			
	 	 		Bundle bundle = new Bundle();
	 	 		bundle.putString("ipAddress", ipAddress);
	 	 		bundle.putInt("port", port);
	 	 		search.putExtras(bundle);
	
	 	 		//Toast.makeText(this, "Valid; Entering DebugScreen!", Toast.LENGTH_SHORT).show();
	 	 		startActivity(search);
			}

  		}
 		catch (ActivityNotFoundException afne)
 		{
 			Toast.makeText(this, "Activity not found!", Toast.LENGTH_SHORT).show();
 		}
 	}
	
	
	private void startGameScreen()
 	{
 		try{
 			EditText serverIPaddress = (EditText) findViewById(R.id.ipAddress_input);
 			EditText portNumber = (EditText) findViewById(R.id.portNumber_input);
 			
 			String ipAddress = serverIPaddress.getText().toString();
 			String portNum = portNumber.getText().toString();
			// don't start the activity if the IP/port fields are blank
			if(ipAddress.equals("") == true || portNum.equals("") == true)
			{
				Toast.makeText(this, "Enter an IP Address and Port Number first.", Toast.LENGTH_SHORT).show();
			}
			else
			{
 			int port = Integer.parseInt(portNum);
 			
 			// pass data to another activity
 			Intent search = new Intent(this, GameScreen.class);
 			 			
 			Bundle bundle = new Bundle();
 			bundle.putString("ipAddress", ipAddress);
 			bundle.putInt("port", port);
 			search.putExtras(bundle);
 			
 			startActivity(search);
 			
			}
 		}
 		catch (ActivityNotFoundException afne)
 		{
 			Toast.makeText(this, "Activity not found!", Toast.LENGTH_SHORT).show();
 		}
 	}
	
	private void startGreggles()
 	{
 		try{
 			Intent search = new Intent(this, Greggles.class);
 			
 			startActivity(search); 			
 		}
 		catch (ActivityNotFoundException afne)
 		{
 			Toast.makeText(this, "Activity not found!", Toast.LENGTH_SHORT).show();
 		}
 	}
	
	
}
