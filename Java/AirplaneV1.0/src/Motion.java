// Brian Hession -- 3/1/2011

/* Motion Class
 * 
 * This class is an Object that records and
 * computes the motion, coordinates, and stages
 * of images in the game.
 */

public class Motion {

	private int x,y,vx,vy,stage;
	
	// Default constructor
	public Motion() {
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
		stage = 0;
	}
	
	// Main constructor used
	public Motion( int x, int y, int vx, int vy ) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		stage = 0;
	}
	
	// Sets and stores new position
	public void setPosition() {
		x = x + vx;
		y = y + vy;
	}
	
	// Sets the X position
	public void setX( int p ) {
		x = p;
	}
	
	// Sets the Y position
	public void setY( int p ) {
		y = p;
	}
	
	// Sets the velocity in x direction
	public void setVX( int v ) {
		vx = v;
	}
	
	// Sets the velocity in y direction
	public void setVY( int v ) {
		vy = v;
	}
	
	// Stops the object
	public void stop() {
		vx = 0;
		vy = 0;
	}
	
	// Sets the XY position
	public void setXY( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	// Sets the stage
	public void setStage( int s ) {
		stage = s;
	}
	
	public int getX() {
		// Returns x coordinate
		return x;
	}
	
	public int getY() {
		// Returns y coordinate
		return y;
	}
	
	public int getVX() {
		// Returns x velocity
		return vx;
	}
	
	public int getVY() {
		// Returns y velocity
		return vy;
	}
	
	public int getStage() {
		return stage;
	}
}