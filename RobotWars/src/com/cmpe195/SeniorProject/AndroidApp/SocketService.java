package com.cmpe195.SeniorProject.AndroidApp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class SocketService extends Service {
	
	boolean connected = false;
	Socket socket;
	
	Bundle bundle;
	String ipAddress;
	int port;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Service created ...", Toast.LENGTH_LONG).show();
		connected = true;
		Thread backgroundSocket = new Thread(new Runnable() {
			public void run() {
				connect();
			}
		});
		backgroundSocket.start();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service destroyed ...", Toast.LENGTH_LONG).show();
		connected = false;
	}
	
	private void connect()
	{
		if(connected) {
			try {
	        	// create socket connection based on IP address and port number
				InetAddress serverAddr = InetAddress.getByName("192.168.1.114");
	        	socket = new Socket(serverAddr, 4406);
	        	     	
	        	// send string to server
	        	String str = "f";
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                out.println(str);
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
                e.printStackTrace();
            }
			
		}
			
	}

	private Intent getIntent() {
		// TODO Auto-generated method stub
		return null;
	}
}
