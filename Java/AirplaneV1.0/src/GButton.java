// Brian Hession -- 3/4/2011

/* GButton Class
 * 
 * This the button class that i made myself that 
 * can be placed anywhere on the screen via the
 * paint/paintComponent method.
 * GButton = Graphic Button (haha)
 */

import java.awt.image.BufferedImage;

public class GButton {

	public final static int NORMAL = 0;
	public final static int HOVERED = 1;
	public final static int CLICKED = 2;
	public final static int LOADING = 3;

	private int x,y,width,height,stage;
	private BufferedImage[] bi;
	private boolean clicked;

	public GButton() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		bi = null;
		stage = 0;
		clicked = false;
	}
	
	public GButton( int x, int y, BufferedImage[] bi ) {
		this.x = x;
		this.y = y;
		width = bi[0].getWidth();
		height = bi[0].getHeight();
		this.bi = bi;
		stage = 0;
		clicked = false;
	}
	
	public void setStage( int stage ) {
		this.stage = stage;
	}
	
	public void setClick( boolean b ) {
		clicked = b;
	}
	
	public void click() {
		clicked = !clicked;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getStage() {
		return stage;
	}
	
	public BufferedImage getImage() {
		return bi[ stage ];
	}
	
	public boolean isClicked() {
		return clicked;
	}
}