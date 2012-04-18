package com.cmpe195.SeniorProject.AndroidApp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DebugScreen extends Activity{

	private Button sendButton1;
	private Button sendButton2;
	private Button sendButton3;
	private Button sendButtonALL;
	
	private Button shootLaser;
	
	private TextView textView;
	
	private TextView eyepee;
	private Socket socket;
	//private String serverIpAddress = "192.168.1.77";
	//private static final int serverPort = 4406;
	
	private Button upButton;
	private Button leftButton;
	private Button rightButton;
	private Button downButton;
	private Button leftRotationButton;
	private Button rightRotationButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_screen);
        
        sendButton1 = (Button) findViewById(R.id.sendButton1);
        sendButton2 = (Button) findViewById(R.id.sendButton2);
        sendButton3 = (Button) findViewById(R.id.sendButton3);
        sendButtonALL = (Button) findViewById(R.id.sendallButton);
        
        shootLaser = (Button) findViewById(R.id.shootLaser);
        
        eyepee = (TextView) findViewById(R.id.eyepee);
        
        upButton = (Button) findViewById(R.id.moveForward);
//        leftButton = (Button) findViewById(R.id.moveLeft);
//        rightButton = (Button) findViewById(R.id.moveRight);
        downButton = (Button) findViewById(R.id.moveBackward);
        leftRotationButton = (Button) findViewById(R.id.leftRotation);
        rightRotationButton = (Button) findViewById(R.id.rightRotation);
        
        // get bundle (from ConfigScreen)
        Bundle bundle = getIntent().getExtras();
        // extract bundle
        String ipAddress = bundle.getString("ipAddress");
        int port = bundle.getInt("port");
        
        eyepee.setText(ipAddress); // display IP Address, to ensure correct IP address is being sent from previous screen
        
        
        try {
        	//InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
        	//socket = new Socket(serverAddr, serverPort);
        	InetAddress serverAddr = InetAddress.getByName(ipAddress);
        	socket = new Socket(serverAddr, port);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        
       /* try {
        	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	String str = in.readLine();
        	
        	eyepeetwo.setText(str);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        */
        
        
        // send user-inputed string to server
        sendButton1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  EditText editText = (EditText) findViewById(R.id.input1);
                  String str = editText.getText().toString();
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
        
        // send user-inputed string to server
        sendButton2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  EditText editText = (EditText) findViewById(R.id.input2);
                  String str = editText.getText().toString();
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
        
        // send user-inputed string to server
        sendButton3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  EditText editText = (EditText) findViewById(R.id.input3);
                  String str = editText.getText().toString();
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
        
        // send user-inputed string to server
        sendButtonALL.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  EditText editText1 = (EditText) findViewById(R.id.input1);
                  String str1 = editText1.getText().toString();
                  
                  EditText editText2 = (EditText) findViewById(R.id.input2);
                  String str2 = editText2.getText().toString();
                  
                  EditText editText3 = (EditText) findViewById(R.id.input3);
                  String str3 = editText3.getText().toString();
                  
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str1 + " " + str2 + " " + str3);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
        
        
        
        // move Robot forward (up)
        upButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  String str = "dbg:000:000:fwd:001:00a:000:000:000:";
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
        
  /*      // move Robot to the left
        leftButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  String str = "dbg:000:000:lft:001:00a:000:000:000:";
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
   */     
  /*      // move Robot to the right
        rightButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  String str = "dbg:000:000:rgh:001:00a:000:000:000:";
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
  */      
        // move Robot backward (down)
       downButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  String str = "dbg:000:000:rev:001:00a:000:000:000:";
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
        
       // move Robot 90 deg to the left
       leftRotationButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  String str = "dbg:000:000:rol:001:00a:000:000:000:";
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
       
       // move Robot 90 deg to the right
       rightRotationButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  String str = "dbg:000:000:ror:001:00a:000:000:000:";
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
        
       // shoot laser
       shootLaser.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               try {
                  String str = "dbg:000:000:lsr:001:00a:000:000:000:";
                  PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                  out.println(str);
                  Log.d("Client", "Client sent message");
               } catch (UnknownHostException e) {
                  textView.setText("Error1");
                  e.printStackTrace();
               } catch (IOException e) {
                  textView.setText("Error2");
                  e.printStackTrace();
               } catch (Exception e) {
                  textView.setText("Error3");
                  e.printStackTrace();
               }
            }
        });
 
    }

}
