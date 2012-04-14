package com.cmpe195.SeniorProject.AndroidApp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.cmpe195.SeniorProject.AndroidApp.robot.Robot;

public class GameScreen extends Activity{

	GamePanel gamepanel;
	Socket socket;
	GameScreen gameScreen = this;
	
	private static final String TAG = GameScreen.class.getSimpleName();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // game_screen.xml has the grid layout
        //setContentView(R.layout.game_screen);
        
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set GameScreen as the View
       
        // get bundle (from ConfigScreen)
        Bundle bundle = getIntent().getExtras();
        // extract bundle
        String ipAddress = bundle.getString("ipAddress");
        int port = bundle.getInt("port");
          
        try {
			openSocket(ipAddress, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
        gamepanel = new GamePanel(this);
        //gamepanel.setZOrderOnTop(true);
        setContentView(gamepanel);
        Log.d(TAG, "View added");
        Toast.makeText(this, "Opening Socket...", Toast.LENGTH_LONG).show();
	}
	
	public void openSocket(String ipAddress, int portNumber) throws IOException
	{
		// hardcoded IP and Port:
		//InetAddress serverAddr = InetAddress.getByName("192.168.1.114");
		//socket = new Socket(serverAddr, 4406);
		
		// IP and Port passed from config screen:
		InetAddress serverAddr = InetAddress.getByName(ipAddress);
		socket = new Socket(serverAddr, portNumber);
	}
	
	public void sendData(String message) throws IOException
	{
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        out.println(message);
	}
	
	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
	}
	
	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}
	
	@Override
    public void onBackPressed() {
		AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
		alertBox.setMessage("Are you sure you want to exit?")
		       .setCancelable(false)
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   try {
		        		   socket.close();
		        		   Log.d(TAG, "Closing Socket...");
		        	   } catch (IOException e) {
		        		   // TODO Auto-generated catch block
		        		   e.printStackTrace();
		        	   }
		        	   GameScreen.this.finish();
		           }
		       })
		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		//AlertDialog alert = builder.create();
		alertBox.show();
       return;
    }
	
		
}
