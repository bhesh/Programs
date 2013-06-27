// Brian Hession -- 3/16/2011

/* Background class
 *
 * Controls the scenery and motion of the
 * background.
 */
 
 import java.awt.image.BufferedImage;
 import java.awt.*;
 import java.io.*;
 import java.util.ArrayList;
 
 public class Background {
 
	private BufferedImage bg,b,r,c;
	private Motion[] sand;
	private ArrayList<Motion> scene;
 
	public Background() {
	
		// Setup
		setup();
	}
	
	public void doStuff() {
		
		// Random Scene
		int rand1 = (int)(Math.random()*80);
		if ( rand1 == 1 ) {
			int x = (int)(Math.random()*10);
			x = x * 50;
			scene.add(new Motion(x,-50,0,2));
			int rand2 = (int)(Math.random()*2);
			scene.get(scene.size()-1).setStage(rand2);
		}
		
		// Motion
		for ( int i = 0; i < sand.length; ++i ) {
			sand[i].setPosition();
			if ( sand[i].getY() > b.getHeight()*5 )
				sand[i].setY(-b.getHeight());
		}
		for ( int i = 0; i < scene.size(); ++i ) {
			scene.get(i).setPosition();
			if ( scene.get(i).getY() > 500 ) {
				scene.remove(i);
				--i;
			}
		}
		
		// Graphics
		Graphics g = bg.getGraphics();
		for ( int i = 0; i < sand.length; ++i ) {
			g.drawImage(b,0,sand[i].getY(),b.getWidth(),b.getHeight(),null);
		}
		for ( int i = 0; i < scene.size(); ++i ) {
			if ( scene.get(i).getStage() == 0 ) {
				g.drawImage(r,scene.get(i).getX(),scene.get(i).getY(),r.getWidth(),r.getHeight(),null);
			}
			else {
				g.drawImage(c,scene.get(i).getX(),scene.get(i).getY(),c.getWidth(),c.getHeight(),null);
			}
		}
	}
	
	public void setup() {
	
		// Make Image
		bg = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
	
		// Make arraylist/arrays
		sand = new Motion[6];
		scene = new ArrayList<Motion>();
		
		// Get picture
		Picture p = new Picture(new File("sand.png"));
		b = p.getPic();
		p = new Picture(new File("boulder.png"));
		r = p.getPic();
		p = new Picture(new File("cactus.png"));
		c = p.getPic();
		
		// Add to arrays
		for ( int i = -1; i < sand.length-1; ++i ) {
			sand[i+1] = new Motion(0,b.getHeight()*i,0,2);
		}
	}
	
	public BufferedImage getImage() {
		return bg;
	}
}