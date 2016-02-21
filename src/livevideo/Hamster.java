package livevideo;

import processing.core.PApplet;
import processing.core.PImage;

public class Hamster
{
	// all hamsters need to know their position on the screen
	private int x, y;
	
	// how fast should a hamster move down the screen
	private int speed;
		
	// all hamsters need to know about the canvas that they are drawing onto
	private PApplet canvas;

	// all hamsters need to know about their artwork (but they can share this since it's the same for everyone)
	private static PImage artwork;

	// constructor
	public Hamster(PApplet canvas, int x, int y)
	{
		// store our canvas ref
		this.canvas = canvas;
		
		// store our location
		this.x = x;
		this.y = y;
		
		// pick a random speed
		this.speed = (int) this.canvas.random(3,15);
		
		// has our artwork been loaded yet?  if not, load it in
		if (this.artwork == null)
		{
			this.artwork = this.canvas.loadImage("hamster.png");
		}
	}
	
	// move - hamsters always move down the screen
	public void move()
	{
		this.y += this.speed;
		
		// if we go off the edge of the screen we should pop back to the top
		if (this.y > this.canvas.height)
		{
			this.y = -200;
			this.x = (int) this.canvas.random(0, this.canvas.width);
			
			// pick a random speed
			this.speed = (int) this.canvas.random(3,15);
		}
	}
		
	// display
	public void display()
	{
		this.canvas.image(artwork, x-artwork.width/2, y-artwork.height/2);
	}
	
	// hit test
	public boolean hitTest(int cx, int cy)
	{
		if (this.canvas.dist(cx, cy, this.x, this.y) < 100)
		{
			// reposition ourselves
			this.y = -200;
			this.x = (int) this.canvas.random(0, this.canvas.width);
			
			// pick a random speed
			this.speed = (int) this.canvas.random(3,15);
			
			return true;
		}
		return false;
	}

	
}
