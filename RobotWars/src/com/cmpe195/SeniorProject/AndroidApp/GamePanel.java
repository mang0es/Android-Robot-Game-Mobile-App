package com.cmpe195.SeniorProject.AndroidApp;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cmpe195.SeniorProject.AndroidApp.robot.Robot;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = GamePanel.class.getSimpleName();
	private GameThread thread;
	private Bitmap background;
	GameScreen gameScreen;
	
	private Robot robot1;
	private Robot robot2;
	private Robot robot3;
	private Robot robot4;
	
	private int x_old;
	private int y_old;
    private int x_delta;
    private int y_delta;
	private int x_new;
	private int y_new;
	
	private final int NOMOVEMENT = 0;
	private final int MOVERIGHT = 1;
	private final int MOVEDOWN = 2;
	private final int MOVELEFT = 3;
	private final int MOVEUP = 4;
	
	public GamePanel(Context context) {
		super(context);
		
		gameScreen = (GameScreen) context;
		
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		// create robot and load bitmap
		// robot = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.fileName), x, y);
		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_0deg), 70, 70, Robot.FACINGRIGHT);
		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_0deg), 70, 650, Robot.FACINGRIGHT);
		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_180deg), 1230, 70, Robot.FACINGLEFT);
		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_180deg), 1230, 650, Robot.FACINGLEFT);
		background = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gamescreen_background));
		
		// create the game loop thread
		thread = new GameThread(getHolder(), this);
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
	}

	/*public Canvas getCanvas()
	{
		Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.gamescreen_background).copy(Bitmap.Config.ARGB_8888, true);
		return new Canvas(bg);
	}*/
		
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
		        	x_old = robot1.getX();
		        	y_old = robot1.getY();
		        	/* try {
		        		 gameScreen.sendData("Robot 1 INITIAL - X:" + robot1.getX() + " Y:" + robot1.getY());
		             } catch (IOException e) {
		            	 e.printStackTrace();
		             }*/
		        }
		        else if (robot2.isTouched()) 
		        {    	
		        	x_old = robot2.getX();
		        	y_old = robot2.getY();
		        	 /*try {
		        		 gameScreen.sendData("Robot 2 INITIAL - X:" + robot2.getX() + " Y:" + robot2.getY());
		             } catch (IOException e) {
		            	 e.printStackTrace();
		             }
		             */
		        }
		        else if (robot3.isTouched()) 
		        {    	
		        	x_old = robot3.getX();
		        	y_old = robot3.getY();
		        	 /*try {
		        		 gameScreen.sendData("Robot 3 INITIAL - X:" + robot3.getX() + " Y:" + robot3.getY());
		             } catch (IOException e) {
		            	 e.printStackTrace();
		             }*/
		        }
		        else if (robot4.isTouched()) 
		        {    	
		        	x_old = robot4.getX();
		        	y_old = robot4.getY();
		        	 /*try {
		        		 gameScreen.sendData("Robot 4 INITIAL - X:" + robot4.getX() + " Y:" + robot4.getY());
		             } catch (IOException e) {
		            	 e.printStackTrace();
		             }*/
		        }
		    break;
               case MotionEvent.ACTION_MOVE:
                   
                   // the gestures
                   if (robot1.isTouched()) 
                   {    	
                       System.out.println("Robot 1 moving");
                       // robot1 was picked up and is being dragged
                       // restrict movement
                       x_delta = Math.abs(x_old - (int)event.getX());
                       y_delta = Math.abs(y_old - (int)event.getY());
                       
                       // dragging left and right
                       if( y_delta < x_delta )
                       {
                           robot1.setX((int)event.getX());
                       }
                       // dragging up and down
                       else if ( x_delta < y_delta )
                       {
                           robot1.setY((int)event.getY());
                       }
                   }
                   else if (robot2.isTouched()) 
                   {
                       System.out.println("Robot 2 moving");
                       // robot2 was picked up and is being dragged
                       
                       // restrict movement
                       x_delta = Math.abs(x_old - (int)event.getX());
                       y_delta = Math.abs(y_old - (int)event.getY());
                       
                       // dragging left and right
                       if( y_delta < x_delta )
                       {
                           robot2.setX((int)event.getX());
                       }
                       // dragging up and down
                       else if ( x_delta < y_delta )
                       {
                           robot2.setY((int)event.getY());
                       }
                   }
                   else if (robot3.isTouched()) 
                   {
                       System.out.println("Robot 3 moving");
                       // robot3 was picked up and is being dragged
                       
                       // restrict movement
                       x_delta = Math.abs(x_old - (int)event.getX());
                       y_delta = Math.abs(y_old - (int)event.getY());
                       
                       // dragging left and right
                       if( y_delta < x_delta )
                       {
                           robot3.setX((int)event.getX());
                       }
                       // dragging up and down
                       else if ( x_delta < y_delta )
                       {
                           robot3.setY((int)event.getY());
                       }
                   }
                   else if (robot4.isTouched()) 
                   {
                       System.out.println("Robot 4 moving");
                       // robot4 was picked up and is being dragged
                       
                       // restrict movement
                       x_delta = Math.abs(x_old - (int)event.getX());
                       y_delta = Math.abs(y_old - (int)event.getY());
                       
                       // dragging left and right
                       if( y_delta < x_delta )
                       {
                           robot4.setX((int)event.getX());
                       }
                       // dragging up and down
                       else if ( x_delta < y_delta )
                       {
                           robot4.setY((int)event.getY());
                       }
                   }
                   break;
		   case MotionEvent.ACTION_UP:
			// touch was released
			   
			   // Robot 1
                   if (robot1.isTouched()) {
                       robot1.setTouched(false);
                       
                       // Center of grid blocks (X-axis): 70, 215, 360, 505, 650, 795, 940, 1085, 1230
                       // 70 + 145x <= 1230
                       // Center of grid blocks (Y-axis): 70, 215, 360, 505, 650
                       // 70 + 145y <= 650
                       
                       /* animation tests */
                       /*
                        ImageView imageView1 = (ImageView) findViewById(R.drawable.robot_teal_0deg);
                        imageView1.setImageBitmap(robot1.getBitmap());
                        TranslateAnimation slide = new TranslateAnimation(robot1.getX(), 0, robot1.getY(), 0);   
                        slide.setDuration(1000);   
                        slide.setFillAfter(true);   
                        imageView1.startAnimation(slide);
                        //imageView1.setVisibility(View.VISIBLE);
                        */
                       
                       /*MotionEvent motionEvent = MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis()+100,
                        MotionEvent.ACTION_UP,
                        0.0f,0.0f,0);
                        GamePanel.dispatchTouchEvent(motionEvent);
                        */
                       
                       /* correct x coordinate
                        * to center on grid block */
                       
                       if(robot1.getX() <= 145)
                       {
                           robot1.setX(70);
                       }
                       else if(robot1.getX() >= 146 && robot1.getX() <= 290)
                       {
                           robot1.setX(215);
                       }
                       else if(robot1.getX() >= 291 && robot1.getX() <= 435)
                       {
                           robot1.setX(360);
                       }
                       else if(robot1.getX() >= 436 && robot1.getX() <= 580)
                       {
                           robot1.setX(505);
                       }
                       else if(robot1.getX() >= 581 && robot1.getX() <= 725)
                       {
                           robot1.setX(650);
                       }
                       else if(robot1.getX() >= 726 && robot1.getX() <= 870)
                       {
                           robot1.setX(795);
                       }
                       else if(robot1.getX() >= 871 && robot1.getX() <= 1015)
                       {
                           robot1.setX(940);
                       }
                       else if(robot1.getX() >= 1016 && robot1.getX() <= 1160)
                       {
                           robot1.setX(1085);
                       }
                       else if(robot1.getX() >= 1161)
                       {
                           robot1.setX(1230);
                       }
                       
                       /* correct y coordinate
                        * to center on grid block */
                       
                       if(robot1.getY() <= 145)
                       {
                           robot1.setY(70);
                       }
                       else if(robot1.getY() >= 146 && robot1.getY() <= 290)
                       {
                           robot1.setY(215);
                       }
                       else if(robot1.getY() >= 291 && robot1.getY() <= 435)
                       {
                           robot1.setY(360);
                       }
                       else if(robot1.getY() >= 436 && robot1.getY() <= 580)
                       {
                           robot1.setY(505);
                       }
                       else if(robot1.getY() >= 581)
                       {
                           robot1.setY(650);
                       }
                       
	                x_new = robot1.getX();
	                y_new = robot1.getY();
	                
	                int direction = getMovementDirection(x_old, y_old, x_new, y_new);
	                
	                switch (direction)
	                {
		                case NOMOVEMENT: // no movement; shoot laser
	                		try {
								gameScreen.sendData("gme:" + "001:" + "blu:" + "lsr:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
							} catch (IOException e) {
								e.printStackTrace();
							}
	                		break;
		                case MOVERIGHT:	// move right code etc
		                	
		                	if (robot1.getFacingDirection() == robot1.FACINGRIGHT)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGLEFT) // robot is facing left and goes reverse to the right
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing left
		                		 *  2. robot is told to go right
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGUP) // robot is facing up and goes to the right
		                	{
		                		// move and rotate
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGDOWN) // robot is facing down and goes to the right
		                	{
		                		// move and rotate
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}		                	
		                	/*else // robot is facing up or down and moving right
		                	{
		                		// doesn't matter which way it's facing, since it's going to face and go right at the end anyway
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGRIGHT);
		                	}*/
		                	break;
		                case MOVEDOWN:
		                	// move down code
		                	
		                	if (robot1.getFacingDirection() == robot1.FACINGDOWN)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGUP) // robot is facing left and goes reverse to the right
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing down
		                		 *  2. robot is told to go up
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGRIGHT) // robot is facing right and goes to down
		                	{
		                		// move and rotate
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_270deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGDOWN);
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGLEFT) // robot is facing down and goes to the right
		                	{
		                		// move and rotate
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_270deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGDOWN);
		                	}
		                	/*else // robot is facing left or right and moving down
		                	{
		                		// doesn't matter which way it's facing, since it's going to face and go right at the end anyway
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_270deg), x_new, y_new, Robot.FACINGDOWN);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGDOWN);
		                	}*/
		                	break;
		                case MOVELEFT:
		                	// move left code
		                	
		                	if (robot1.getFacingDirection() == robot1.FACINGLEFT)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGRIGHT) // robot is facing right and goes reverse to the left
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing left
		                		 *  2. robot is told to go right
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGUP) // robot is facing up and moving left
		                	{
		                		// move and rotate
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGLEFT);
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGDOWN) // robot is facing down and moving left
		                	{
		                		// doesn't matter which way it's facing, since it's going to face and go left at the end anyway
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGLEFT);
		                	}
		                	/*else // robot is facing up or down and moving left
		                	{
		                		// doesn't matter which way it's facing, since it's going to face and go left at the end anyway
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGLEFT);
		                	}*/
		                	break;
		                case MOVEUP:
		                	// move up code
		                	
		                	if (robot1.getFacingDirection() == robot1.FACINGUP)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGDOWN) // robot is facing up and goes reverse to down
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing up
		                		 *  2. robot is told to go down
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGLEFT) // robot is facing left and goes to up
		                	{
		                		// move and rotate
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGUP);
		                	}
		                	else if (robot1.getFacingDirection() == robot1.FACINGRIGHT) // robot is facing right and goes to up
		                	{
		                		// move and rotate
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGUP);
		                	}
		                	/*else // robot is facing left or right and moving up
		                	{
		                		// doesn't matter which way it's facing, since it's going to face and go right at the end anyway
		                		robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "001:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot1.setFacingDirection(Robot.FACINGDOWN);
		                	}*/
		                	break;
	                	default:
	                		break;
	                }
	                
	                /*if ( ((x_new - x_old) == 0) && ((y_new - y_old) == 0) )
	                {
	                	try {
		                	gameScreen.sendData("gme:" + "002:" + "blu:" + "lsr:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
		                	//gameScreen.sendData("gme:" + "002:" + "blu:" + "fwd:" + String.format("%04d",x_new) + " " + String.format("%04d",y_new) + " " + String.format("%04d",x_old) + " " + String.format("%04d",y_old) + " " + "100:");
		                	//gameScreen.sendData("Robot 2 FINAL - X:" + robot2.getX() + " Y:" + robot2.getY());
		                } catch (IOException e) {
		                	e.printStackTrace();
		                }
	                }
	                
	                
	                if ( (x_new - x_old) > 0 )
	                {	
	                	if ( ((y_new - y_old) > 0) && ((y_new - y_old) < 145) )
	                	{
			                try {
			                	gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
			                	//gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + " " + String.format("%04d",y_new) + " " + String.format("%04d",x_old) + " " + String.format("%04d",y_old) + " " + "100:");
			                	//gameScreen.sendData("Robot 1 FINAL - X:" + robot1.getX() + " Y:" + robot2.getY());
			                } catch (IOException e) {
			                	e.printStackTrace();
			                }
	                	}
	                }
	                
	                if ( (x_new - x_old) < 0 )
	                {
	                	if ( ((y_new - y_old) > 0) && ((y_new - y_old) < 145) )
	                	{
		                	try {	
			                	gameScreen.sendData("gme:" + "001:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
			                	//gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + " " + String.format("%04d",y_new) + " " + String.format("%04d",x_old) + " " + String.format("%04d",y_old) + " " + "100:");
			                	//gameScreen.sendData("Robot 1 FINAL - X:" + robot1.getX() + " Y:" + robot2.getY());
			                } catch (IOException e) {
			                	e.printStackTrace();
			                }
	                	}
	                }
	                
	                if ( (y_new - y_old) > 0 ) // FACING DOWN
	                {
	                	if ( ((x_new - x_old) > 0) && ((x_new - x_old) < 145) )
	                	{
		                	int num = (y_new - y_old);
		                	Log.d(TAG, "y_new - y_old = " + num);
		                	robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_270deg), x_new, y_new, Robot.DOWN);
		                	try {	
			                	gameScreen.sendData("gme:" + "001:" + "blu:" + "ror:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
			                	//gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + " " + String.format("%04d",y_new) + " " + String.format("%04d",x_old) + " " + String.format("%04d",y_old) + " " + "100:");
			                	//gameScreen.sendData("Robot 1 FINAL - X:" + robot1.getX() + " Y:" + robot2.getY());
			                } catch (IOException e) {
			                	e.printStackTrace();
			                }
	                	}
	                }
	                
	                if ( (y_new - y_old) < 0 ) // FACING UP
	                {
	                	if ( ((x_new - x_old) > 0) && ((x_new - x_old) < 145) )
	                	{
		                	int num = (y_new - y_old);
		                	Log.d(TAG, "y_new - y_old = " + num);
		                	robot1 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_90deg), x_new, y_new, Robot.UP);
		                	try {
			                	gameScreen.sendData("gme:" + "001:" + "blu:" + "rol:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
			                	//gameScreen.sendData("gme:" + "001:" + "blu:" + "fwd:" + String.format("%04d",x_new) + " " + String.format("%04d",y_new) + " " + String.format("%04d",x_old) + " " + String.format("%04d",y_old) + " " + "100:");
			                	//gameScreen.sendData("Robot 1 FINAL - X:" + robot1.getX() + " Y:" + robot2.getY());
			                } catch (IOException e) {
			                	e.printStackTrace();
			                }
	                	}
	                }
	                */
	            }
	            
	            
	           // Robot 2
                   else if (robot2.isTouched()) {
                       robot2.setTouched(false);
                       
                       
                       // Center of grid blocks (X-axis): 70, 215, 360, 505, 650, 795, 940, 1085, 1230
                       // 70 + 145x <= 1230
                       // Center of grid blocks (Y-axis): 70, 215, 360, 505, 650
                       // 70 + 145y <= 650
                       
                       /* correct x coordinate
                        * to center on grid block */
                       
                       if(robot2.getX() <= 145)
                       {
                           robot2.setX(70);
                       }
                       else if(robot2.getX() >= 146 && robot2.getX() <= 290)
                       {
                           robot2.setX(215);
                       }
                       else if(robot2.getX() >= 291 && robot2.getX() <= 435)
                       {
                           robot2.setX(360);
                       }
                       else if(robot2.getX() >= 436 && robot2.getX() <= 580)
                       {
                           robot2.setX(505);
                       }
                       else if(robot2.getX() >= 581 && robot2.getX() <= 725)
                       {
                           robot2.setX(650);
                       }
                       else if(robot2.getX() >= 726 && robot2.getX() <= 870)
                       {
                           robot2.setX(795);
                       }
                       else if(robot2.getX() >= 871 && robot2.getX() <= 1015)
                       {
                           robot2.setX(940);
                       }
                       else if(robot2.getX() >= 1016 && robot2.getX() <= 1160)
                       {
                           robot2.setX(1085);
                       }
                       else if(robot2.getX() >= 1161)
                       {
                           robot2.setX(1230);
                       }
                       
                       /* correct y coordinate
                        * to center on grid block */
                       
                       if(robot2.getY() <= 145)
                       {
                           robot2.setY(70);
                       }
                       else if(robot2.getY() >= 146 && robot2.getY() <= 290)
                       {
                           robot2.setY(215);
                       }
                       else if(robot2.getY() >= 291 && robot2.getY() <= 435)
                       {
                           robot2.setY(360);
                       }
                       else if(robot2.getY() >= 436 && robot2.getY() <= 580)
                       {
                           robot2.setY(505);
                       }
                       else if(robot2.getY() >= 581)
                       {
                           robot2.setY(650);
                       }

                    x_new = robot2.getX();
	                y_new = robot2.getY();
	                
	                int direction = getMovementDirection(x_old, y_old, x_new, y_new);
	                
	                switch (direction)
	                {
	                	case NOMOVEMENT: // no movement; shoot laser
	                		try {
								gameScreen.sendData("gme:" + "002:" + "blu:" + "lsr:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
							} catch (IOException e) {
								e.printStackTrace();
							}
	                		break;
		                case MOVERIGHT:	// move right code etc
		                	
		                	if (robot2.getFacingDirection() == robot2.FACINGRIGHT)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGLEFT) // robot is facing left and goes reverse to the right
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing left
		                		 *  2. robot is told to go right
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGUP) // robot is facing up and goes to the right
		                	{
		                		// move and rotate
		                		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGDOWN) // robot is facing down and goes to the right
		                	{
		                		// move and rotate
		                		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	break;
		                case MOVEDOWN:
		                	// move down code
		                	
		                	if (robot2.getFacingDirection() == robot2.FACINGDOWN)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGUP) // robot is facing left and goes reverse to the right
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing down
		                		 *  2. robot is told to go up
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGRIGHT) // robot is facing right and goes to down
		                	{
		                		// move and rotate
		                		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_270deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot2.setFacingDirection(Robot.FACINGDOWN);
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGLEFT) // robot is facing down and goes to the right
		                	{
		                		// move and rotate
		                		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_270deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot2.setFacingDirection(Robot.FACINGDOWN);
		                	}
		                	break;
		                case MOVELEFT:
		                	// move left code
		                	
		                	if (robot2.getFacingDirection() == robot2.FACINGLEFT)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGRIGHT) // robot is facing right and goes reverse to the left
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing left
		                		 *  2. robot is told to go right
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGUP) // robot is facing up and moving left
		                	{
		                		// move and rotate
		                		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot2.setFacingDirection(Robot.FACINGLEFT);
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGDOWN) // robot is facing down and moving left
		                	{
		                		// doesn't matter which way it's facing, since it's going to face and go left at the end anyway
		                		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot2.setFacingDirection(Robot.FACINGLEFT);
		                	}
		                	break;
		                case MOVEUP:
		                	// move up code
		                	
		                	if (robot2.getFacingDirection() == robot2.FACINGUP)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGDOWN) // robot is facing up and goes reverse to down
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing up
		                		 *  2. robot is told to go down
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGLEFT) // robot is facing left and goes to up
		                	{
		                		// move and rotate
		                		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot2.setFacingDirection(Robot.FACINGUP);
		                	}
		                	else if (robot2.getFacingDirection() == robot2.FACINGRIGHT) // robot is facing right and goes to up
		                	{
		                		// move and rotate
		                		robot2 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_teal_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "002:" + "blu:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot2.setFacingDirection(Robot.FACINGUP);
		                	}
		                	break;
	                	default:
	                		break;
	                }
	                
	                /*try {
	                	gameScreen.sendData("gme:" + "002:" + "blu:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
	                	//gameScreen.sendData("gme:" + "002:" + "blu:" + "fwd:" + String.format("%04d",x_new) + " " + String.format("%04d",y_new) + " " + String.format("%04d",x_old) + " " + String.format("%04d",y_old) + " " + "100:");
	                	//gameScreen.sendData("Robot 2 FINAL - X:" + robot2.getX() + " Y:" + robot2.getY());
	                } catch (IOException e) {
	                	e.printStackTrace();
	                }*/
	            }
	            
	            
	            // Robot 3
                   else if (robot3.isTouched()) {
                       robot3.setTouched(false);
                       
                       // Center of grid blocks (X-axis): 70, 215, 360, 505, 650, 795, 940, 1085, 1230
                       // 70 + 145x <= 1230
                       // Center of grid blocks (Y-axis): 70, 215, 360, 505, 650
                       // 70 + 145y <= 650
                       
                       /* correct x coordinate
                        * to center on grid block */
                       
                       if(robot3.getX() <= 145)
                       {
                           robot3.setX(70);
                       }
                       else if(robot3.getX() >= 146 && robot3.getX() <= 290)
                       {
                           robot3.setX(215);
                       }
                       else if(robot3.getX() >= 291 && robot3.getX() <= 435)
                       {
                           robot3.setX(360);
                       }
                       else if(robot3.getX() >= 436 && robot3.getX() <= 580)
                       {
                           robot3.setX(505);
                       }
                       else if(robot3.getX() >= 581 && robot3.getX() <= 725)
                       {
                           robot3.setX(650);
                       }
                       else if(robot3.getX() >= 726 && robot3.getX() <= 870)
                       {
                           robot3.setX(795);
                       }
                       else if(robot3.getX() >= 871 && robot3.getX() <= 1015)
                       {
                           robot3.setX(940);
                       }
                       else if(robot3.getX() >= 1016 && robot3.getX() <= 1160)
                       {
                           robot3.setX(1085);
                       }
                       else if(robot3.getX() >= 1161)
                       {
                           robot3.setX(1230);
                       }
                       
                       /* correct y coordinate
                        * to center on grid block */
                       
                       if(robot3.getY() <= 145)
                       {
                           robot3.setY(70);
                       }
                       else if(robot3.getY() >= 146 && robot3.getY() <= 290)
                       {
                           robot3.setY(215);
                       }
                       else if(robot3.getY() >= 291 && robot3.getY() <= 435)
                       {
                           robot3.setY(360);
                       }
                       else if(robot3.getY() >= 436 && robot3.getY() <= 580)
                       {
                           robot3.setY(505);
                       }
                       else if(robot3.getY() >= 581)
                       {
                           robot3.setY(650);
                       }
                       
	                x_new = robot3.getX();
	                y_new = robot3.getY();

	                int direction = getMovementDirection(x_old, y_old, x_new, y_new);
	                
	                switch (direction)
	                {
		                case NOMOVEMENT: // no movement; shoot laser
	                		try {
								gameScreen.sendData("gme:" + "003:" + "ora:" + "lsr:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
							} catch (IOException e) {
								e.printStackTrace();
							}
	                		break;
	                	case MOVERIGHT:	// move right code etc
		                	
		                	if (robot3.getFacingDirection() == robot3.FACINGRIGHT)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGLEFT) // robot is facing left and goes reverse to the right
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing left
		                		 *  2. robot is told to go right
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGUP) // robot is facing up and goes to the right
		                	{
		                		// move and rotate
		                		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGDOWN) // robot is facing down and goes to the right
		                	{
		                		// move and rotate
		                		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	break;
		                case MOVEDOWN:
		                	// move down code
		                	
		                	if (robot3.getFacingDirection() == robot3.FACINGDOWN)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGUP) // robot is facing left and goes reverse to the right
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing down
		                		 *  2. robot is told to go up
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGRIGHT) // robot is facing right and goes to down
		                	{
		                		// move and rotate
		                		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_270deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot3.setFacingDirection(Robot.FACINGDOWN);
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGLEFT) // robot is facing down and goes to the right
		                	{
		                		// move and rotate
		                		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_270deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot3.setFacingDirection(Robot.FACINGDOWN);
		                	}
		                	break;
		                case MOVELEFT:
		                	// move left code
		                	
		                	if (robot3.getFacingDirection() == robot3.FACINGLEFT)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGRIGHT) // robot is facing right and goes reverse to the left
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing left
		                		 *  2. robot is told to go right
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGUP) // robot is facing up and moving left
		                	{
		                		// move and rotate
		                		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot3.setFacingDirection(Robot.FACINGLEFT);
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGDOWN) // robot is facing down and moving left
		                	{
		                		// doesn't matter which way it's facing, since it's going to face and go left at the end anyway
		                		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot3.setFacingDirection(Robot.FACINGLEFT);
		                	}
		                	break;
		                case MOVEUP:
		                	// move up code
		                	
		                	if (robot3.getFacingDirection() == robot3.FACINGUP)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGDOWN) // robot is facing up and goes reverse to down
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing up
		                		 *  2. robot is told to go down
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGLEFT) // robot is facing left and goes to up
		                	{
		                		// move and rotate
		                		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot3.setFacingDirection(Robot.FACINGUP);
		                	}
		                	else if (robot3.getFacingDirection() == robot3.FACINGRIGHT) // robot is facing right and goes to up
		                	{
		                		// move and rotate
		                		robot3 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "003:" + "ora:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot3.setFacingDirection(Robot.FACINGUP);
		                	}
		                	break;
	                	default:
	                		// shoot laser
	                		
	                		try {
								gameScreen.sendData("gme:" + "003:" + "ora:" + "lsr:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
							} catch (IOException e) {
								e.printStackTrace();
							}
	                		break;
	                }
	                
	               /* try {
	                	gameScreen.sendData("gme:" + "003:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
	                	//gameScreen.sendData("gme:" + "003:" + "org:" + "fwd:" + String.format("%04d",x_new) + " " + String.format("%04d",y_new) + " " + String.format("%04d",x_old) + " " + String.format("%04d",y_old) + " " + "100:");
	                	//gameScreen.sendData("Robot 3 FINAL - X:" + robot3.getX() + " Y:" + robot3.getY());
	                } catch (IOException e) {
	                	e.printStackTrace();
	                }*/
	            }
	            
	            
	            // Robot 4
	            else if (robot4.isTouched()) {
	                robot4.setTouched(false);
                    
	                // Center of grid blocks (X-axis): 70, 215, 360, 505, 650, 795, 940, 1085, 1230
	        		// 70 + 145x <= 1230
	        		// Center of grid blocks (Y-axis): 70, 215, 360, 505, 650
	        		// 70 + 145y <= 650
	                
	                /* correct x coordinate
	                 * to center on grid block */
	                
	                if(robot4.getX() <= 145)
	                {
               			robot4.setX(70);
               		}
	                else if(robot4.getX() >= 146 && robot4.getX() <= 290)
	                {
               			robot4.setX(215);
               		}
	                else if(robot4.getX() >= 291 && robot4.getX() <= 435)
	                {
               			robot4.setX(360);
               		}
	                else if(robot4.getX() >= 436 && robot4.getX() <= 580)
	                {
               			robot4.setX(505);
               		}
	                else if(robot4.getX() >= 581 && robot4.getX() <= 725)
	                {
               			robot4.setX(650);
               		}
	                else if(robot4.getX() >= 726 && robot4.getX() <= 870)
	                {
               			robot4.setX(795);
               		}
	                else if(robot4.getX() >= 871 && robot4.getX() <= 1015)
	                {
               			robot4.setX(940);
               		}
	                else if(robot4.getX() >= 1016 && robot4.getX() <= 1160)
	                {
               			robot4.setX(1085);
               		}
	                else if(robot4.getX() >= 1161)
	                {
               			robot4.setX(1230);
               		}
	                
	                /* correct y coordinate
	                 * to center on grid block */
	                
	                if(robot4.getY() <= 145)
	                {
               			robot4.setY(70);
               		}
	                else if(robot4.getY() >= 146 && robot4.getY() <= 290)
	                {
               			robot4.setY(215);
               		}
	                else if(robot4.getY() >= 291 && robot4.getY() <= 435)
	                {
               			robot4.setY(360);
               		}
	                else if(robot4.getY() >= 436 && robot4.getY() <= 580)
	                {
               			robot4.setY(505);
               		}
	                else if(robot4.getY() >= 581)
	                {
               			robot4.setY(650);
               		}
	                
	                x_new = robot4.getX();
	                y_new = robot4.getY();
	                
	                int direction = getMovementDirection(x_old, y_old, x_new, y_new);
	                
	                switch (direction)
	                {
		                case NOMOVEMENT: // no movement; shoot laser
	                		try {
								gameScreen.sendData("gme:" + "004:" + "ora:" + "lsr:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
							} catch (IOException e) {
								e.printStackTrace();
							}
	                		break;
	                	case MOVERIGHT:	// move right code etc
		                	
		                	if (robot4.getFacingDirection() == robot4.FACINGRIGHT)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGLEFT) // robot is facing left and goes reverse to the right
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing left
		                		 *  2. robot is told to go right
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGUP) // robot is facing up and goes to the right
		                	{
		                		// move and rotate
		                		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGDOWN) // robot is facing down and goes to the right
		                	{
		                		// move and rotate
		                		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_0deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	break;
		                case MOVEDOWN:
		                	// move down code
		                	
		                	if (robot4.getFacingDirection() == robot4.FACINGDOWN)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGUP) // robot is facing left and goes reverse to the right
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing down
		                		 *  2. robot is told to go up
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGRIGHT) // robot is facing right and goes to down
		                	{
		                		// move and rotate
		                		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_270deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot4.setFacingDirection(Robot.FACINGDOWN);
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGLEFT) // robot is facing down and goes to the right
		                	{
		                		// move and rotate
		                		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_270deg), x_new, y_new, Robot.FACINGRIGHT);
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot4.setFacingDirection(Robot.FACINGDOWN);
		                	}
		                	break;
		                case MOVELEFT:
		                	// move left code
		                	
		                	if (robot4.getFacingDirection() == robot4.FACINGLEFT)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGRIGHT) // robot is facing right and goes reverse to the left
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing left
		                		 *  2. robot is told to go right
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGUP) // robot is facing up and moving left
		                	{
		                		// move and rotate
		                		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot4.setFacingDirection(Robot.FACINGLEFT);
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGDOWN) // robot is facing down and moving left
		                	{
		                		// doesn't matter which way it's facing, since it's going to face and go left at the end anyway
		                		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_180deg), x_new, y_new, Robot.FACINGLEFT);
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot4.setFacingDirection(Robot.FACINGLEFT);
		                	}
		                	break;
		                case MOVEUP:
		                	// move up code
		                	
		                	if (robot4.getFacingDirection() == robot4.FACINGUP)
		                	{
		                		// move and don't rotate
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGDOWN) // robot is facing up and goes reverse to down
		                	{
		                		/*  robot moves in reverse case
		                		 *  1. robot is already facing up
		                		 *  2. robot is told to go down
		                		 *  3. robot moves backwards
		                		 */
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "rev:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGLEFT) // robot is facing left and goes to up
		                	{
		                		// move and rotate
		                		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "rgh:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot4.setFacingDirection(Robot.FACINGUP);
		                	}
		                	else if (robot4.getFacingDirection() == robot4.FACINGRIGHT) // robot is facing right and goes to up
		                	{
		                		// move and rotate
		                		robot4 = new Robot(BitmapFactory.decodeResource(getResources(), R.drawable.robot_orange_90deg), x_new, y_new, Robot.FACINGUP);
		                		try {
									gameScreen.sendData("gme:" + "004:" + "ora:" + "lft:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
								} catch (IOException e) {
									e.printStackTrace();
								}
		                		robot4.setFacingDirection(Robot.FACINGUP);
		                	}
		                	break;
	                	default:
	                		// shoot laser
	                		
	                		try {
								gameScreen.sendData("gme:" + "004:" + "ora:" + "lsr:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
							} catch (IOException e) {
								e.printStackTrace();
							}
	                		break;
	                } 
	                
	                /*
	                try {
	                	gameScreen.sendData("gme:" + "004:" + "ora:" + "fwd:" + String.format("%04d",x_new) + String.format("%04d",y_new) + String.format("%04d",x_old) + String.format("%04d",y_old) + "100:");
	                	//gameScreen.sendData("gme:" + "004:" + "org:" + "fwd:" + String.format("%04d",x_new) + " " + String.format("%04d",y_new) + " " + String.format("%04d",x_old) + " " + String.format("%04d",y_old) + " " + "100:");
	                	//gameScreen.sendData("Robot 4 FINAL - X:" + robot4.getX() + " Y:" + robot4.getY());
	                } catch (IOException e) {
	                	e.printStackTrace();
	                }*/
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
	
	public int getMovementDirection(int x_old, int y_old, int x_new, int y_new)
	{
		int delta_x = x_new - x_old;
		int delta_y = y_new - y_old;
			
		if (delta_x == delta_y)
		{
			return NOMOVEMENT;
		}
				if (Math.abs(delta_x) > Math.abs(delta_y))
		{
			// true if X distance change > y
			if (delta_x > 0) return MOVERIGHT;
			else return MOVELEFT;
		}
		else
		{
			if (delta_y > 0) return MOVEDOWN;
			else return MOVEUP;
		}
	}
	
	


}