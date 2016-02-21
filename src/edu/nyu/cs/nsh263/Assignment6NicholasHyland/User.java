package edu.nyu.cs.nsh263.Assignment6NicholasHyland;

/**
 * 
 * @author Nicholas Hyland
 * @version 1.0
 * @assignment Assignment Number 6 - User
 *
 */

import processing.core.PApplet;

public class User extends PApplet {

	private int xLocation;
	private int yLocation;
	
	public int getxLocation(int xLocation) {
		this.xLocation = xLocation;
		return this.xLocation;
	}
	
	public int getyLocation(int yLocation) {
		this.yLocation = yLocation;
		return this.yLocation;
	}
	
	public User(int xLocation, int yLocation) {
		
		this.xLocation = xLocation;
		this.yLocation = yLocation;
	}

	PlayBall parent;
	
	public User(PlayBall player) {
		this.yLocation = mouseY;
		this.xLocation = mouseX;
		
		this.parent = player;
	}
	
	public void show() {
		
		parent.fill(100, 100, 100);
		parent.ellipse(xLocation, yLocation, 30, 30);
	}
	
	public void move() {
		this.yLocation = mouseY;
		this.xLocation = mouseX;
	}
}
