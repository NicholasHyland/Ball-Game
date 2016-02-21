package livevideo;

import processing.core.PApplet;
import processing.video.Capture;

public class Ex02_ExaminingPixels extends PApplet
{
	// declare an object of type Capture - this will be the object
	// that handles our connection with your webcam
	Capture video;
	
	// runs once at the beginning of your program
	public void setup()
	{
		// size our canvas
		size(640, 480);
		
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
			
			// use a nested loop to examine all pixels
			for (int x = 0; x < video.width; x++)
			{
				for (int y = 0; y < video.height; y++)
				{
					// we are now examining pixel x,y
					// however, video is delivered as a single dimensional array, not a 2D array
					// therefore we have to convert our x,y position to a 1D location
					int location = x + y*video.width;					
				}
			}
			
			// draw it onto the screen at position 0, 0
			image(video, 0, 0);
		}		
	}
}
