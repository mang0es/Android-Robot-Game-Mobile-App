package com.cmpe195.SeniorProject.AndroidApp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class StartScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
    }

    public boolean onTouchEvent(MotionEvent event)
 	{
     	if (event.getAction() == (MotionEvent.ACTION_UP))
     		nextScreen();
 		return true;
 	}
     
     protected void nextScreen()
     {
     	try {
 	    	Intent select = new Intent(this, SelectScreen.class);
 			startActivity(select);

     	}
     	catch (ActivityNotFoundException afne)
 		{
 			Toast.makeText(this, "Activity not found!", Toast.LENGTH_SHORT).show();
 		}
     }


}

