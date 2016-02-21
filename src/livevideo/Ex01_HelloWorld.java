package livevideo;

import processing.core.PApplet;
import processing.video.Capture;

public class Ex01_HelloWorld extends PApplet
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
			
			// draw it onto the screen at position 0, 0
			image(video, 0, 0);
		}		
	}
}
