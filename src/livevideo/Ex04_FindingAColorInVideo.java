package livevideo;

import processing.core.PApplet;
import processing.video.Capture;

public class Ex04_FindingAColorInVideo extends PApplet
{
	// declare an object of type Capture - this will be the object
	// that handles our connection with your webcam
	Capture video;
	
	// set up the color we are looking for
	float desiredRed, desiredGreen, desiredBlue;
	
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
			
			// assume that the first pixel is our closest matching pixel
			float bestMatch = dist(this.desiredRed, this.desiredGreen, this.desiredBlue,
									  red(video.pixels[0]), green(video.pixels[0]), blue(video.pixels[0]));
			int bestX = 0;
			int bestY = 0;
			
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
					if (testMatch < bestMatch)
					{
						bestMatch = testMatch;
						bestX = x;
						bestY = y;
					}
				}
			}
			
			// draw it onto the screen at position 0, 0
			image(video, 0, 0);
			
			// now let's draw a circle on top of our best color match!
			stroke(0,255,0);
			strokeWeight(10);
			noFill();
			ellipse(bestX, bestY, 50, 50);
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
