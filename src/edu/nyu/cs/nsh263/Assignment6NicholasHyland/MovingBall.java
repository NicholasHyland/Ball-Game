package edu.nyu.cs.nsh263.Assignment6NicholasHyland;

/**
 * 
 * @author Nicholas Hyland
 * @version 1.0
 * @assignment Assignment Number 6 - Moving Ball
 *
 */

import processing.core.PApplet;

public class MovingBall extends PApplet {

	private int xLocation;
	private int yLocation;
	private int maxSpeed = 5;
	private int speedY = 1;
	private int speedX = 1;

	public int getxLocation() {
		return this.xLocation;
	}
	
	public int getyLocation() {
		return this.yLocation;
	}
	
	public MovingBall(int maxSpeed, int speedY, int xLocation, int yLocation) {
		this.maxSpeed = maxSpeed;
		this.speedY = speedY;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
	}
	
	PlayBall parent;
	
	public MovingBall(PlayBall ball) {
		this.yLocation = (int)(Math.random() * ball.height);
		this.xLocation = (int)(Math.random() * ball.width-800);
		
		this.speedY = (int)((Math.random() * this.maxSpeed) + 1);
		this.speedX = (int)((Math.random() * this.maxSpeed) + 1);
		
		this.parent = ball;

	}
	
	public void move() {
		this.yLocation += this.speedY;
		this.xLocation += this.speedX;
	}
	
	public void show() {
		this.checkBounds();
		
		parent.fill(255, 255, 255);
		parent.ellipse(xLocation, yLocation, 50, 50);
	}
	
	public void checkBounds() {
		if (this.yLocation > parent.height) {
			this.yLocation = 0;
		}
		if (this.xLocation > parent.width) {
			this.xLocation = 0;
		}

	}
	
}
