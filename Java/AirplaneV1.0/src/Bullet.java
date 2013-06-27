// Brian Hession -- 3/14/2011

/* Bullet class
 * 
 * Controls creation and motion of player
 * bullets.
 */
 
 import java.awt.image.BufferedImage;
 import java.io.*;
 import java.util.ArrayList;
 
 public class Bullet {
 
	private BufferedImage bullet;
	private ArrayList<Motion> b;
	
	private int ammo;
 
	public Bullet() {
	
		// Setup
		setup();
	}
	
	public void doStuff() {
		
		// Motion
		for ( int i = 0; i < b.size(); ++i ) {
			b.get(i).setPosition();
			if ( b.get(i).getY() < -20 ) {
				b.remove(i);
				--i;
			}
		}
	}
	
	public void setup() {
	
		// No_bullets
		ammo = 50;
	
		// ArrayList of bullets
		b = new ArrayList<Motion>();
	
		// Get picture
		Picture p = new Picture(new File("bullet.png"));
		bullet = p.getPic();
	}
	
	public void add( int x, int y ) {
		
		// Add bullet
		b.add(new Motion(x,y,0,-10));
		--ammo;
	}
	
	public void addAmmo() {
		
		// Add bullets
		ammo += 30;
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public ArrayList<Motion> getBullets() {
		return b;
	}
	
	public BufferedImage getImage() {
		return bullet;
	}
}