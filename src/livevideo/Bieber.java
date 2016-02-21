package livevideo;

import processing.core.PApplet;
import processing.core.PImage;

public class Bieber
{
	// Bieber needs to know his x & y position on the screen
	public int x, y;
	
	// Bieber needs to know about his artwork (image file)
	private PImage artwork;
	
	// Bieber needs to know what canvas he is drawing on
	private PApplet canvas;
	
	// constructor
	public Bieber(PApplet canvas)
	{
		// store our canvas reference
		this.canvas = canvas;
		
		// put the Biebs at position 0,0
		this.x = 0;
		this.y = 0;
		
		// load in our graphic
		this.artwork = this.canvas.loadImage("bieber.png");
	}
	
	// reset Bieber's position
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	// draw Bieber to the canvas
	public void display()
	{
		this.canvas.image(this.artwork, this.x - this.artwork.width/2, this.y - this.artwork.height/2);
	}

}
