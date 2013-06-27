// Brian Hession -- 3/14/2011

/* Plane class
 * 
 * This class creates the player image
 * and controls its motion.
 */
 
 import java.awt.image.BufferedImage;
 import java.io.*;
 
 public class Plane extends Motion {
 
	private BufferedImage plane;
 
	public Plane() {
		setX(190);
		setY(320);
		setVX(0);
		setVY(0);
		
		Picture p = new Picture(new File("plane.png"));
		plane = p.getPic();
	}
	
	public void doStuff() {
		if ( (getVX() > 0 && getX() < 500 - plane.getWidth()*3/4) || (getVX() < 0 && getX() > -plane.getWidth()/4) )
			setPosition();
	}
	
	public BufferedImage getImage() {
		return plane;
	}
}