package edu.nyu.cs.nsh263.Assignment6NicholasHyland;

/**
 * 
 * @author Nicholas Hyland
 * @version 1.0
 * @assignment Assignment Number 6 - Play Ball
 *
 */

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class PlayBall extends PApplet {
	
	long startTime;
	//PImage img;
	
	public double ballDistance(User player, MovingBall thisball) {
		double distance = (Math.sqrt(Math.pow((player.getxLocation(mouseX)-thisball.getxLocation()), 2) + Math.pow((player.getyLocation(mouseY)-thisball.getyLocation()), 2)));
		return distance;
	}

	public void totalScore(long startTime, long currentTime) {
		long elapsedTime = (currentTime - startTime);
		
		System.out.println(startTime);
		System.out.println(currentTime);
		text("Score: " + elapsedTime, 850, 50);
	}
	
	private ArrayList<MovingBall> balls = new ArrayList<MovingBall>();

	private User player = new User(this);
	
	boolean start = false;
	boolean end = false;
	boolean startCount = true;
	
	public void setup() {
		size(1000, 600);
		background(0, 0, 0);
		
		for (int i = 0; i < 40; i++) {
			balls.add(new MovingBall(this));
		}
		
		//img = loadImage("Picture.png");
	}

	
	public void draw() {
		
	if (player.getxLocation(mouseX) > 500) {
		start = true;
	}
		
	if(start && !end) {
		
		if (startCount) {
		startTime = System.currentTimeMillis();
		startCount = false;
		}
		
		fill(0, 0, 0);
		background(0, 0, 0);
		
		for (int i = 0; i < balls.size(); i++) {
			MovingBall thisBall = balls.get(i);
			
			thisBall.show();
			thisBall.move();
			
			player.show();
			player.move();
			player.getxLocation(mouseX);
			player.getyLocation(mouseY);
			
			long currentTime = System.currentTimeMillis();
			totalScore(startTime, currentTime);
			
			if (ballDistance(player, thisBall) < 40) {
				//image(img, 0, 0);
				end = true;
			}
		}
	}
}
	
	public static void main(String[] args) {
		
	}
}
