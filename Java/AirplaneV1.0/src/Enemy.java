// Brian Hession -- 3/14/2011

/* Enemy class
 *
 * Controls the creation and motion of
 * enemy planes.
 */
 
 import java.awt.image.BufferedImage;
 import java.io.*;
 import java.util.ArrayList;
 
 public class Enemy {
 
	private BufferedImage enemy;
	private ArrayList<Motion> e;
	
	private double difficulty;
	private int base_health;
 
	public Enemy() {
	
		// Call setup
		setup();
	}
	
	public void doStuff() {
		
		// Random
		int rand = (int)(Math.random()*100);
		if ( rand <= difficulty ) {
			int x = (int)(Math.random()*10);
			x = x * 50;
			e.add(new Motion(x,-50,0,5));
		}
		
		// Set positions
		for ( int i = 0; i < e.size(); ++i ) {
			e.get(i).setPosition();
			if ( e.get(i).getY() > 500 ) {
				e.remove(i);
				--i;
				base_health -= 2;
			}
		}
		
		// Increase difficulty
		if ( difficulty < 100.0 ) {
			difficulty += 0.001;
		}
	}
	
	public void setup() {
	
		// ArrayList
		e = new ArrayList<Motion>();
		
		// Difficulty
		difficulty = 1.80;
		base_health = 100;
		
		// Picture
		Picture p = new Picture(new File("enemy.png"));
		enemy = p.getPic();
	}
	
	public int getBase() {
		return base_health;
	}
	
	public ArrayList<Motion> getEnemies() {
		return e;
	}
	
	public BufferedImage getImage() {
		return enemy;
	}
}