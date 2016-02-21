package livevideo;

import processing.core.PApplet;
import processing.video.Capture;

public class Ex07_FlyingHamsters extends PApplet
{
	// declare an object of type Capture - this will be the object
	// that handles our connection with your webcam
	Capture video;
	
	// set up the color we are looking for
	float desiredRed, desiredGreen, desiredBlue;
	
	// set up a threshold - this is how close a pixel needs to be
	// in order to qualify as a trackable pixel (we can change this
	// to make our program more or less sensitive)
	int threshold = 15;
	
	// our character
	Bieber character;
	
	// an array of Hamsters that will always fall down
	Hamster[] theHamsters;
	
	// runs once at the beginning of your program
	public void setup()
	{
		// size our canvas
		size(640, 480);
		
		// set up our desired color (black is our initial tracking color)
		this.desiredRed = 0;
		this.desiredGreen = 0;
		this.desiredBlue = 0;
		
		// start up our video camera
		this.video = new Capture(this, 640, 480);
		this.video.start();		
		
		// set up our character
		this.character = new Bieber(this);
		
		// create our hamsters
		this.theHamsters = new Hamster[10];
		for (int i = 0; i < this.theHamsters.length; i++)
		{
			// pick a location for this hamster
			int xPos = (int) (random(0, width));
			int yPos = (int) (random(-1000, -200));
			this.theHamsters[i] = new Hamster(this, xPos, yPos);
		}
	}
	
	// runs 60 times a second
	public void draw()
	{
		// see if there is video available to be read from the camera
		if (video.available())
		{
			// read in a frame of video
			video.read();
			
			// now let's examine the pixels that make up this frame of video
			video.loadPixels();
			
			// set up some accumulator variables to keep track of all
			// of the qualifying pixels that we see
			int numGoodPixels = 0;
			int goodPixelX = 0;
			int goodPixelY = 0;
			
			// use a nested loop to examine all pixels
			for (int x = 0; x < video.width; x++)
			{
				for (int y = 0; y < video.height; y++)
				{
					// we are now examining pixel x,y
					// however, video is delivered as a single dimensional array, not a 2D array
					// therefore we have to convert our x,y position to a 1D location
					int location = x + y*video.width;	

					// compute how far away from our desired color this
					// pixel is
					float testMatch = dist(this.desiredRed, this.desiredGreen, this.desiredBlue,
							  			   red(video.pixels[location]), green(video.pixels[location]), blue(video.pixels[location]));
					
					// is this a better match than the one we know about?
					if (testMatch < threshold)
					{
						numGoodPixels++;
						goodPixelX += x;
						goodPixelY += y;
					}
				}
			}
			
			// draw it onto the screen at position 0, 0
			image(video, 0, 0);
			
			// average out all of our good pixels
			if (numGoodPixels > 0)
			{
				float centerX = (float) (goodPixelX / numGoodPixels);
				float centerY = (float) (goodPixelY / numGoodPixels);

				// set our character here
				this.character.setPosition((int)centerX,  (int)centerY);
			}
			
			// display our character
			character.display();

			// display our hamsters
			for (int i = 0; i < this.theHamsters.length; i++)
			{
				// did the character hit this hamster?  if so, remove it from the game
				if (this.theHamsters[i].hitTest(this.character.x, this.character.y))
				{
					println("Bieber got one!");
				}
				
				this.theHamsters[i].move();
				this.theHamsters[i].display();
			}
		}		
	}
	
	// this runs when the mouse is pressed
	public void mousePressed()
	{
		// figure out where we are on the video
		int location = mouseX + mouseY*video.width;
		
		// grab the current color on the video
		video.loadPixels();
		float r = red(video.pixels[location]);
		float g = green(video.pixels[location]);
		float b = blue(video.pixels[location]);
		
		// this is now our desired color!
		this.desiredRed = r;
		this.desiredGreen = g;
		this.desiredBlue = b;
		
		println("Tracking color: " + this.desiredRed + ", " + this.desiredGreen + ", " + this.desiredBlue);
	}
}
