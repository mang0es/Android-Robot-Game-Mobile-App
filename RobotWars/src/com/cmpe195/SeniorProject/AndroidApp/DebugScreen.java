package com.cmpe195.SeniorProject.AndroidApp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class DebugScreen extends Activity {
	private TextView eyepee;
	private Socket socket;

	private RadioGroup radioGroup;
	private RadioButton radioButton;
	private int selectedId;
	
	private String bot;
	private String botID;
	private String botColor;
	
	
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.debug_screen);
	    
	    // get bundle (from ConfigScreen)
	    Bundle bundle = getIntent().getExtras();
	    // extract bundle
	    String ipAddress = bundle.getString("ipAddress");
	    int port = bundle.getInt("port");
	    
	    try {
	    	//InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
	    	//socket = new Socket(serverAddr, serverPort);
	    	InetAddress serverAddr = InetAddress.getByName(ipAddress);
	    	socket = new Socket(serverAddr, port);
	    } catch (UnknownHostException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    RadioButtonListener();
	    
	}
	
	public void RadioButtonListener() {
		radioGroup = (RadioGroup) findViewById(R.id.radioBot);
		
		Button leftButton = (Button) findViewById(R.id.LeftArrowButton);
		leftButton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {		
		
				// get selected radio button from radioGroup
				selectedId = radioGroup.getCheckedRadioButtonId();
				
				// find the radiobutton by returned id
		        radioButton = (RadioButton) findViewById(selectedId);
		        
		        bot = radioButton.getText().toString();
		        
/*		        try {
					String str = "Bot Number:" + getBot();
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}*/
		        
		        getRobotID(bot); 
		        
		        try {
					String str = "dbg:" + botID + botColor + "lef:" + "0000" + "0000" + "0000" + "0000" + "100:";
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
			}
		});
		
		Button rightButton = (Button) findViewById(R.id.RightArrowButton);
		rightButton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {		
		
				// get selected radio button from radioGroup
				selectedId = radioGroup.getCheckedRadioButtonId();
				
				// find the radiobutton by returned id
		        radioButton = (RadioButton) findViewById(selectedId);
		        
		        bot = radioButton.getText().toString();
		        
/*		        try {
					String str = "Bot Number:" + getBot();
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}*/
		        
		        getRobotID(bot); 
		        
		        try {
					String str = "dbg:" + botID + botColor + "rgh:" + "0000" + "0000" + "0000" + "0000" + "100:";
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
			}
		});
		
		Button forwardButton = (Button) findViewById(R.id.UpArrowButton);
		forwardButton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {		
		
				// get selected radio button from radioGroup
				selectedId = radioGroup.getCheckedRadioButtonId();
				
				// find the radiobutton by returned id
		        radioButton = (RadioButton) findViewById(selectedId);
		        
		        bot = radioButton.getText().toString();
		        
/*		        try {
					String str = "Bot Number:" + getBot();
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}*/
		        
		        getRobotID(bot); 
		        
		        try {
					String str = "dbg:" + botID + botColor + "fwd:" + "0000" + "0000" + "0000" + "0000" + "100:";
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
			}
		});
		
		Button reverseButton = (Button) findViewById(R.id.DownArrowButton);
		reverseButton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {		
		
				// get selected radio button from radioGroup
				selectedId = radioGroup.getCheckedRadioButtonId();
				
				// find the radiobutton by returned id
		        radioButton = (RadioButton) findViewById(selectedId);
		        
		        bot = radioButton.getText().toString();
		        
/*		        try {
					String str = "Bot Number:" + getBot();
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}*/
		        
		        getRobotID(bot); 
		        
		        try {
					String str = "dbg:" + botID + botColor + "rev:" + "0000" + "0000" + "0000" + "0000" + "100:";
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
			}
		});
		
		Button laserButton = (Button) findViewById(R.id.LaserButton);
		laserButton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {		
		
				// get selected radio button from radioGroup
				selectedId = radioGroup.getCheckedRadioButtonId();
				
				// find the radiobutton by returned id
		        radioButton = (RadioButton) findViewById(selectedId);
		        
		        bot = radioButton.getText().toString();
		        
/*		        try {
					String str = "Bot Number:" + getBot();
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}*/
		        
		        getRobotID(bot); 
		        
		        try {
					String str = "dbg:" + botID + botColor + "lsr:" + "0000" + "0000" + "0000" + "0000" + "100:";
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
			}
		});
		
		Button danceButton = (Button) findViewById(R.id.DanceButton);
		danceButton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {		
		
				// get selected radio button from radioGroup
				selectedId = radioGroup.getCheckedRadioButtonId();
				
				// find the radiobutton by returned id
		        radioButton = (RadioButton) findViewById(selectedId);
		        
		        bot = radioButton.getText().toString();
		        
		        /*try {
					String str = "Bot Number:" + getBot();
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}*/
		        
		        getRobotID(bot); 
		        
		        try {
					String str = "dbg:" + botID + botColor + "dan:" + "0000" + "0000" + "0000" + "0000" + "100:";
	                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	                out.println(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
			}
		});

	}        

	public void getRobotID(String bot) {
		if(bot.equals("Robot 1")) {
			botID = "001:";
			botColor = "blu:";
		}
		else if (bot.equals("Robot 2")) {
			botID = "002:";
			botColor = "blu:";
		}
		else if (bot.equals("Robot 3")) {
			botID = "003:";
			botColor = "ora:";
		}
		else if (bot.equals("Robot 4")) {
			botID = "004:";
			botColor = "ora:";
		}
		else {
			botID = "ALL:";
			botColor = "ALL:";
		}
	}
	
	public String getBot() {
		return bot;
	}
	
}
