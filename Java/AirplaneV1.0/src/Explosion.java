// Brian Hession -- 3/16/2011

/* Explosion Class
 * 
 * Controls the creation, animation, and 
 * motion of both the enemy explosions
 * and the player explosion.
 */
 
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
 
public class Explosion {

	private BufferedImage[] exp;
	private ArrayList<Motion> e;
	private Motion pexp;
	private boolean dead,gameover;
	
	public Explosion() {
		
		// Setup
		setup();
	}
	
	public void doStuff() {
	
		if ( dead ) {
			pexp.setStage(pexp.getStage() + 1);
			if ( pexp.getStage() > 24 ) {
				pexp.setStage(0);
				gameover = true;
			}
		}
	
		// Animate explosion
		for ( int i = 0; i < e.size(); ++i ) {
			e.get(i).setPosition();
			e.get(i).setStage(e.get(i).getStage() + 1);
			if ( e.get(i).getStage() > 24 ) {
				e.remove(i);
				--i;
			}
		}	
	}
	
	public void setup() {
	
		// Boolean
		dead = false;
		gameover = false;
		
		// Explosions
		e = new ArrayList<Motion>();
		
		// Motion
		pexp = new Motion();
		
		// Make image
		Picture p = new Picture(new File("explosion.png"));
		BufferedImage temp = p.getPic();
		exp = new BufferedImage[25];
		int count = 0;
		for ( int i = 0; i < 5; ++i ) {
			for ( int j = 0; j < 5; ++j ) {
				exp[count] = temp.getSubimage(j*64,i*64,
					64,64);
				++count;
			}
		}
	}
	
	public void add( int x, int y ) {
		e.add(new Motion(x,y,0,2));
	}
	
	public void dead( int x, int y ) {
		dead = true;
		pexp.setX(x);
		pexp.setY(y);
	}
	
	public void gameover( boolean b ) {
		gameover = b;
	}
	
	public boolean dead() {
		return dead;
	}
	
	public boolean gameover() {
		return gameover;
	}
	
	public ArrayList<Motion> getExp() {
		return e;
	}
	
	public BufferedImage[] getImage() {
		return exp;
	}
	
	public Motion getPexp() {
		return pexp;
	}
}