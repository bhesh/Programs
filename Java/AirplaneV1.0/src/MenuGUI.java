// Brian Hession -- 3/15/2011 

/* MenuGUI Class
 * 
 * This class is basically a big 
 * BufferedImage that makes up the
 * start screen and gameover screen.
 * Whether the game started or is
 * over is also kept track of here.
 */

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class MenuGUI {

	public static final int START = 0;
	public static final int HIGHSCORE = 1;
	public static final int X = 2;

	private BufferedImage menu,title,plane;
	private GButton[] b;
	private boolean beginning,highscore,end;
	
	private String name;
	private int high;

	public MenuGUI() {
		
		// Run setup
		setup();
	}
	
	public void repaint() {
	
		Graphics g = menu.getGraphics();
		
		// Beginning of game
		if ( beginning ) {
		
			// Add buttons
			g.setColor(Color.black);
			g.fillRect(0,0,494,472);
			g.setColor(Color.red);
			g.fillRect(10,10,474,452);
			g.drawImage(plane,40,30,411,291,null);
			g.setColor(Color.black);
			g.fillRect(0,290,494,125);
			g.setColor(new Color(255,255,0));
			g.fillRect(0,300,494,105);
			g.drawImage(title,20,311,308,85,null);
			
			// Draws buttons
			for ( int i = 0; i < b.length; ++i ) {
				switch (i) {
					case 2:
						break;
					default:
						g.drawImage(b[i].getImage(),b[i].getX(),b[i].getY(),b[i].getWidth(),b[i].getHeight(),null);
						break;
				}
			}
			
			if ( highscore ) {
				g.setColor(Color.black);
				g.fillRect(menu.getWidth()/5,menu.getHeight()/6,menu.getWidth()*3/5,menu.getHeight()/5+20);
				g.setColor(Color.gray);
				g.fillRect(menu.getWidth()/5+10,menu.getHeight()/6+10,menu.getWidth()*3/5-20,menu.getHeight()/5);
				g.drawImage(b[2].getImage(),b[2].getX(),b[2].getY(),b[2].getWidth(),b[2].getHeight(),null);
				g.setColor(Color.white);
				g.setFont(new Font("Calibri",Font.PLAIN,25));
				g.drawString("HIGHSCORE",130,130);
				g.setFont(new Font("Calibri",Font.PLAIN,15));
				g.drawString(name,130,170);
				g.drawString("" + high,270,170);
				g.drawLine(menu.getWidth()/5+10,menu.getHeight()/6+70,menu.getWidth()*4/5-12,menu.getHeight()/6+70);
			}
		}
		
		// End of game
		else if ( end ) {
			g.setColor(Color.gray);
			g.fillRect(0,0,494,472/2+20);
			g.setColor(Color.black);
			g.fillRect(0,10,494,472/2);
		}
	}
	
	public void repaint( int i ) {
		
		Graphics g = menu.getGraphics();
		
		// Repaint button
		if( i == 2  && !highscore ) return;
		g.drawImage(b[i].getImage(),b[i].getX(),b[i].getY(),b[i].getWidth(),b[i].getHeight(),null);
		
		if ( highscore ) {
				g.setColor(Color.black);
				g.fillRect(menu.getWidth()/5,menu.getHeight()/6,menu.getWidth()*3/5,menu.getHeight()/5+20);
				g.setColor(Color.gray);
				g.fillRect(menu.getWidth()/5+10,menu.getHeight()/6+10,menu.getWidth()*3/5-20,menu.getHeight()/5);
				g.drawImage(b[2].getImage(),b[2].getX(),b[2].getY(),b[2].getWidth(),b[2].getHeight(),null);
				g.setColor(Color.white);
				g.setFont(new Font("Calibri",Font.PLAIN,25));
				g.drawString("HIGHSCORE",130,130);
				g.setFont(new Font("Calibri",Font.PLAIN,15));
				g.drawString(name,130,170);
				g.drawString("" + high,270,170);
				g.drawLine(menu.getWidth()/5+10,menu.getHeight()/6+70,menu.getWidth()*4/5-12,menu.getHeight()/6+70);
		}
	}
	
	public void setup() {
		
		// Make image
		menu = new BufferedImage(494,472,BufferedImage.TYPE_INT_ARGB);
		
		// Variables
		name = "";
		high = 0;		
		
		// Create buttons
		b = new GButton[3];
		createButtons();
		beginning = true;
		highscore = false;
		end = false;
		
		// Plane
		try {
			plane = ImageIO.read(new File("timage.gif"));
		}
		catch(IOException e) {
			System.err.println("Error loading images.");
		}
		
		// Title
		Picture p = new Picture(new File("title.png"));
		title = p.getPic();
		
		// Paint
		repaint();
	}
	
	public void createButtons() {
		
		// Button image arraylist
		BufferedImage[] bi = new BufferedImage[4];
		
		// UNCLiCKED
		bi[0] = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);
		Graphics g1 = bi[0].getGraphics();
		g1.setColor(Color.black);
		g1.fillRect(0,0,100,50);
		g1.setColor(Color.gray);
		g1.fillRect(3,3,94,44);
		g1.setColor(Color.white);
		g1.drawString("START",30,30);
		
		// HOVERED
		bi[1] = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);
		Graphics g2 = bi[1].getGraphics();
		g2.setColor(Color.black);
		g2.fillRect(0,0,100,50);
		g2.setColor(Color.red);
		g2.fillRect(3,3,94,44);
		g2.setColor(Color.white);
		g2.drawString("START",30,30);
		
		// CLICKED
		bi[3] = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);
		Graphics g3 = bi[3].getGraphics();
		g3.setColor(Color.black);
		g3.fillRect(0,0,100,50);
		
		// LOADING
		bi[2] = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);
		Graphics g4 = bi[2].getGraphics();
		g4.setColor(Color.yellow);
		g4.fillRect(0,0,100,50);
		g4.setColor(Color.black);
		g4.drawString("LOADING...",25,30);
		
		// Make button
		b[0]= new GButton(384,310,bi);
		
		bi = new BufferedImage[4];
		
		// UNCLICKED
		bi[0] = new BufferedImage(100,25,BufferedImage.TYPE_INT_RGB);
		g1 = bi[0].getGraphics();
		g1.setColor(Color.black);
		g1.fillRect(0,0,100,25);
		g1.setColor(Color.gray);
		g1.fillRect(3,3,94,19);
		g1.setColor(Color.white);
		g1.drawString("Highscore",22,16);
		
		// HOVERED
		bi[1] = new BufferedImage(100,25,BufferedImage.TYPE_INT_RGB);
		g2 = bi[1].getGraphics();
		g2.setColor(Color.black);
		g2.fillRect(0,0,100,25);
		g2.setColor(Color.red);
		g2.fillRect(3,3,94,19);
		g2.setColor(Color.white);
		g2.drawString("Highscore",22,16);
		
		// CLICKED
		bi[2] = new BufferedImage(100,25,BufferedImage.TYPE_INT_RGB);
		g3 = bi[2].getGraphics();
		g3.setColor(Color.black);
		g3.fillRect(0,0,100,25);
		
		// LOADING
		bi[3] = new BufferedImage(100,25,BufferedImage.TYPE_INT_RGB);
		g4 = bi[3].getGraphics();
		g4.setColor(Color.white);
		g4.fillRect(0,0,100,25);
		g4.setColor(Color.black);
		g4.drawString("...",22,16);
		
		// Make button
		b[1] = new GButton(384,370,bi);
		
		bi = new BufferedImage[4];
		
		// NORMAL
		bi[0] = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
		g1 = bi[0].getGraphics();
		g1.setColor(Color.gray);
		g1.fillRect(0,0,10,10);
		g1.setColor(Color.white);
		g1.drawLine(0,0,10,10);
		g1.drawLine(0,10,10,0);
		
		// HOVERED
		bi[1] = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
		g1 = bi[1].getGraphics();
		g1.setColor(Color.gray);
		g1.fillRect(0,0,10,10);
		g1.setColor(Color.red);
		g1.drawLine(0,0,10,10);
		g1.drawLine(0,10,10,0);
		
		// CLICKED
		bi[2] = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
		g1 = bi[2].getGraphics();
		g1.setColor(Color.gray);
		g1.fillRect(0,0,10,10);
		g1.setColor(Color.black);
		g1.drawLine(0,0,10,10);
		g1.drawLine(0,10,10,0);
		
		// LOADING
		bi[3] = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
		g1 = bi[3].getGraphics();
		g1.setColor(Color.gray);
		g1.fillRect(0,0,10,10);
		g1.setColor(Color.black);
		g1.drawLine(0,0,10,10);
		g1.drawLine(0,10,10,0);
		
		// Make button
		b[2] = new GButton(370,95,bi);
	}
	
	public void setBeginning(boolean b) {
		beginning = b;
	}
	
	public void setEnd(boolean b) {
		end = b;
		if ( end ) {
			menu = new BufferedImage(494,472/2+20,BufferedImage.TYPE_INT_RGB);
		}
		repaint();
	}
	
	public void setName( String s ) {
		name = s;
	}
	
	public void setScore( int h ) {
		high = h;
	}
	
	public void change(int x, int y, int stage) {
	
		// Checks if mouse is on button
		for ( int i = 0; i < b.length; ++i ) {
		
			int bx = b[i].getX();
			int by = b[i].getY();
			int xf = bx + b[i].getWidth();
			int yf = by + b[i].getHeight();
			
			if ( (x > bx && x < xf) && (y > by && y < yf) ) {
				if ( b[i].getStage() != stage ) {
					// Sets stage
					if ( b[i].getStage() == 2 && stage == 0 ) {
						switch (i) {
						case 0:
							beginning = false;
							break;
						case 1:
							highscore = !highscore;
							break;
						case 2:
							highscore = false;
						default:
							break;
						}
						repaint();
					}
					else {
						b[i].setStage(stage);
						repaint(i);
					}
				}
			}
			else {
				if ( b[i].getStage() != 0 ) {
					// Sets stage
					b[i].setStage(0);
					repaint(i);
				}
			}
		}
	}
	
	public BufferedImage getImage() {
		return menu;
	}
	
	public boolean begin() {
		return !beginning;
	}
	
	public boolean end() {
		return end;
	}
}