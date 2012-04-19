package com.cmpe195.SeniorProject.AndroidApp;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.cmpe195.SeniorProject.AndroidApp.robot.Robot;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = GamePanel.class.getSimpleName();
	private GameThread thread;
	private Robot robot1;
	private Robot robot2;
	private Robot robot3;
	private Robot robot4;
	private Bitmap background;
	GameScreen gameScreen;
	
	
	public GamePanel(Context context) {
		super(context);
		
		gameScreen = (GameScreen) context;
		
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		// create robot and load bitmap
		// robot = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.fileName), x, y);
		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal), 70, 70);
		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal), 70, 650);
		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange), 1230, 70);
		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange), 1230, 650);
		background = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gamescreen_background));
		
		// create the game loop thread
		thread = new GameThread(getHolder(), this);
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
	}

	
	
	@Override
	public void onDraw(Canvas canvas) {
		// BLACK fills the canvas with black
		// TRANSPARENT shows path of the image and flickers 
		//canvas.drawColor(Color.BLACK);
		
		canvas.drawBitmap(background, 0, 0, null);
		robot1.draw(canvas);
		robot2.draw(canvas);
		robot3.draw(canvas);
		robot4.draw(canvas);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and we can safely start the game loop
		thread.setRunning(true);
		thread.start();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish; this is a clean shutdown
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
		try {
			thread.join();
			retry = false;
		} catch (InterruptedException e) {
			// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) 
    {
		
		int action = event.getAction();
		   switch(action) {
		   case MotionEvent.ACTION_DOWN:
	        	System.out.println("ACTION_DOWN");
		    	
		    	// delegating event handling to the robot
		        
		        // clear touched
	        	/*
		        robot1.setTouched(false);
		        robot2.setTouched(false);
		        robot3.setTouched(false);
		        robot4.setTouched(false);
		        */
		        
		        
		        robot1.handleActionDown((int)event.getX(), (int)event.getY());
		        robot2.handleActionDown((int)event.getX(), (int)event.getY());
		        robot3.handleActionDown((int)event.getX(), (int)event.getY());
		        robot4.handleActionDown((int)event.getX(), (int)event.getY());
		        
		        /*
		        try {
            	gameScreen.sendData("INITIAL");
    			gameScreen.sendData("Robot 1: X:" + robot1.getX() + " Y:" + robot1.getY() );
    			gameScreen.sendData("Robot 2: X:" + robot2.getX() + " Y:" + robot2.getY() );
    			gameScreen.sendData("Robot 3: X:" + robot3.getX() + " Y:" + robot3.getY() );
    			gameScreen.sendData("Robot 4: X:" + robot4.getX() + " Y:" + robot4.getY() );
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
		        */
		        
		        if (robot1.isTouched()) 
		        {    	
		        	 try {
		        		 gameScreen.sendData("Robot 1 INITIAL - X:" + robot1.getX() + " Y:" + robot1.getY());
		             } catch (IOException e) {
		            	 e.printStackTrace();
		             }
		        }
		        else if (robot2.isTouched()) 
		        {    	
		        	 try {
		        		 gameScreen.sendData("Robot 2 INITIAL - X:" + robot2.getX() + " Y:" + robot2.getY());
		             } catch (IOException e) {
		            	 e.printStackTrace();
		             }
		        }
		        else if (robot3.isTouched()) 
		        {    	
		        	 try {
		        		 gameScreen.sendData("Robot 3 INITIAL - X:" + robot3.getX() + " Y:" + robot3.getY());
		             } catch (IOException e) {
		            	 e.printStackTrace();
		             }
		        }
		        else if (robot4.isTouched()) 
		        {    	
		        	 try {
		        		 gameScreen.sendData("Robot 4 INITIAL - X:" + robot4.getX() + " Y:" + robot4.getY());
		             } catch (IOException e) {
		            	 e.printStackTrace();
		             }
		        }
		        
		    break;
		   case MotionEvent.ACTION_MOVE:
			   
			   // the gestures
		        if (robot1.isTouched()) 
		        {    	
		        	System.out.println("Robot 1 moving");
		            // robot1 was picked up and is being dragged
		            robot1.setX((int)event.getX());
		            robot1.setY((int)event.getY());
		            
		        }
		        else if (robot2.isTouched()) 
		        {
		            System.out.println("Robot 2 moving");
		            // robot2 was picked up and is being dragged
		            robot2.setX((int)event.getX());
		            robot2.setY((int)event.getY());
		        }
		        else if (robot3.isTouched()) 
		        {
		            System.out.println("Robot 3 moving");
		            // robot3 was picked up and is being dragged
		            robot3.setX((int)event.getX());
		            robot3.setY((int)event.getY());
		        }
		        else if (robot4.isTouched()) 
		        {
		            System.out.println("Robot 4 moving");
		            // robot4 was picked up and is being dragged
		            robot4.setX((int)event.getX());
		            robot4.setY((int)event.getY());
		        }
		    break;
		   case MotionEvent.ACTION_UP:
			// touch was released
	            if (robot1.isTouched()) {
	                robot1.setTouched(false);
	                try {
	                	gameScreen.sendData("Robot 1 FINAL - X:" + robot1.getX() + " Y:" + robot1.getY());
	                } catch (IOException e) {
	                	e.printStackTrace();
	                }
	            }
	            else if (robot2.isTouched()) {
	                robot2.setTouched(false);
	                try {
	                	gameScreen.sendData("Robot 2 FINAL - X:" + robot2.getX() + " Y:" + robot2.getY());
	                } catch (IOException e) {
	                	e.printStackTrace();
	                }
	            }
	            else if (robot3.isTouched()) {
	                robot3.setTouched(false);
	                try {
	                	gameScreen.sendData("Robot 3 FINAL - X:" + robot3.getX() + " Y:" + robot3.getY());
	                } catch (IOException e) {
	                	e.printStackTrace();
	                }
	            }
	            else if (robot4.isTouched()) {
	                robot4.setTouched(false);
	                try {
	                	gameScreen.sendData("Robot 4 FINAL - X:" + robot4.getX() + " Y:" + robot4.getY());
	                } catch (IOException e) {
	                	e.printStackTrace();
	                }
	            }
	            
	         /*   try {
	            	gameScreen.sendData("FINALPOS");
	    			gameScreen.sendData("Robot 1: X:" + robot1.getX() + " Y:" + robot1.getY() );
	    			gameScreen.sendData("Robot 2: X:" + robot2.getX() + " Y:" + robot2.getY() );
	    			gameScreen.sendData("Robot 3: X:" + robot3.getX() + " Y:" + robot3.getY() );
	    			gameScreen.sendData("Robot 4: X:" + robot4.getX() + " Y:" + robot4.getY() );
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}*/
		    break;
		   case MotionEvent.ACTION_CANCEL:
		    break;
		   case MotionEvent.ACTION_OUTSIDE:
		    break;
		   default:
		   }
	this.invalidate();
	return true;
	}
	
	/*public Canvas getCanvas()
	{
		Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.gamescreen_background).copy(Bitmap.Config.ARGB_8888, true);
		return new Canvas(bg);
	}*/
	
}