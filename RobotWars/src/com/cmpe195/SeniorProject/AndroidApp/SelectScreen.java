package com.cmpe195.SeniorProject.AndroidApp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectScreen extends Activity{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_screen);
		
		Button configButton = (Button) findViewById(R.id.ConfigButton);
		configButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startConfigScreen();
				
			}
		});
		
		Button creditsButton = (Button) findViewById(R.id.CreditsButton);
		creditsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startCreditsScreen();
			}
		});
	}
	
	private void startConfigScreen()
 	{
 		try{
 			Intent search = new Intent(this, ConfigScreen.class);
 			startActivity(search);
 		}
 		catch (ActivityNotFoundException afne)
 		{
 			Toast.makeText(this, "Activity not found!", Toast.LENGTH_SHORT).show();
 		}
 	}
 	
 	private void startCreditsScreen()
 	{
 		try{
 			Intent search = new Intent(this, CreditsScreen.class);
 			startActivity(search);
 		}
 		catch (ActivityNotFoundException afne)
 		{
 			Toast.makeText(this, "Activity not found!", Toast.LENGTH_SHORT).show();
 		}
 	}
 	
}
