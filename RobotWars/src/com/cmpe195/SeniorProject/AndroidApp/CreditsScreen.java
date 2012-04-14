package com.cmpe195.SeniorProject.AndroidApp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class CreditsScreen extends Activity{
	
	MediaPlayer mediaplayer;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits_screen);
        
        mediaplayer = MediaPlayer.create(this, R.raw.starshipamazing_bonk);
        mediaplayer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		mediaplayer.release();
		finish();
	}
	
}
