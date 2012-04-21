package com.cmpe195.SeniorProject.AndroidApp.robot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Robot {

	private Bitmap bitmap;	// the actual bitmap
	private int x;			// the X coordinate
	private int y;			// the Y coordinate
	private boolean touched;	// if robot is touched/picked up
	private int direction;	// the direction of robot
	
	public static final int NOMOVEMENT = 0;
	public static final int FACINGRIGHT = 1;
	public static final int FACINGDOWN = 2;
	public static final int FACINGLEFT = 3;
	public static final int FACINGUP = 4;
	
	public Robot(Bitmap bitmap, int x, int y, int initialDir) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.direction = initialDir;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public boolean isTouched() {
		return touched;
	}
	
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
	}

	/**
	 * Handles the {@link MotionEvent.ACTION_DOWN} event. If the event happens on the 
	 * bitmap surface then the touched state is set to <code>true</code> otherwise to <code>false</code>
	 * @param eventX - the event's X coordinate
	 * @param eventY - the event's Y coordinate
	 */
	public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
			if (eventY >= (y - bitmap.getHeight() / 2) && (eventY <= (y + bitmap.getHeight() / 2))) {
				// droid touched
				setTouched(true);
			} else {
				setTouched(false);
			}
		} else {
			setTouched(false);
		}

	}
	
	public int getFacingDirection() {
		return direction;
	}
	
	public void setFacingDirection(int n) { 
		direction = n;
	}
	
/*	public boolean doIHaveToTurn(int n)
	{	
		if (direction != n) // return YES I NEED TO TURN
			return true;

		return false; // return NOPE
	}*/
}
