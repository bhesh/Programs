// Brian Hession -- 3/1/2011 

/* Game Class
 * 
 * This is the main game class. It's a JPanel made
 * purely of graphics. All aspects of the game are
 * controled here. Including the timers, listeners,
 * etc.
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel implements MouseListener, MouseMotionListener{

	// ----------------------------------------------------
	// GLOBAL PARAMETERS ----------------------------------
	// Game
	private Background b;
	private Plane player;
	private Enemy enemies;
	private Bullet bullets;
	private Explosion explosions;
	private int plane_health,base_health,flash,score;
	private int newAmmo;
	private boolean space_isDown,hit;
	private Timer t,shoot;
	
	// Menu
	private boolean newhighscore;
	private String name;
	private MenuGUI menu;
	
	// SAVING
	private static LoadSave data;
	private int highscore;
	private String highscore_name;
	// ----------------------------------------------------
	
	public Game() {
	
		// Saves/Loads data
		data = new LoadSave();
	
		// Highscore
		load();
		highscore = data.getHighscore();
		highscore_name = data.getName();
		
		// Temperary (for controls)
		player = new Plane();
		space_isDown = false;
		
		// Make menu
		menu = new MenuGUI();
		menu.setName(highscore_name);
		menu.setScore(highscore);
		
		// Mouse listeners
		addMouseMotionListener(this);
		addMouseListener(this);
		
		
		// Set Actions --------------------------------------
		Action Left = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player.setVX(-12);}};
		Action Right = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player.setVX(12);}};
		Action StopL = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if ( player.getVX() < 0 ) // Fixes stopping bug
				player.setVX(0);}};
		Action StopR = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if ( player.getVX() > 0 ) // Fixes stopping bug
				player.setVX(0);}};
		Action Space = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				space_isDown = true;}};
		Action StopS = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				space_isDown = false;}};
		
		// BIND KEYS ----------------------------------------
		// Pressing keys
		getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "Left");
		getActionMap().put("Left", Left);
		getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "Right");
		getActionMap().put("Right", Right);
		getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "Space");
		getActionMap().put("Space", Space);
		
		
		// Releasing Keys
		getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "StopL");
		getActionMap().put("StopL", StopL);
		getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "StopR");
		getActionMap().put("StopR", StopR);
		getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "StopS");
		getActionMap().put("StopS", StopS);
		// --------------------------------------------------
	}
	
	/* GRAPHICS IS HERE **********************************/
	/*****************************************************/
	protected void paintComponent(Graphics g) {
	
		if ( menu.begin() ) {
			// Background
			g.drawImage(b.getImage(),0,0,500,500,null);
			
			// Enemies
			for ( int i = 0; i < enemies.getEnemies().size(); ++i) {
				g.drawImage(enemies.getImage(),enemies.getEnemies().get(i).getX(),enemies.getEnemies().get(i).getY(),enemies.getImage().getWidth(),enemies.getImage().getHeight(),null);
			}
			
			// Bullets
			for ( int i = 0; i < bullets.getBullets().size(); ++i) {
				g.drawImage(bullets.getImage(),bullets.getBullets().get(i).getX(),bullets.getBullets().get(i).getY(),bullets.getImage().getWidth(),bullets.getImage().getHeight(),null);
			}
			
			// Plane
			if ( !explosions.dead() ) {
				if ( flash % 7 != 2 && flash % 7 != 3 )
				g.drawImage(player.getImage(),player.getX(),player.getY(),player.getImage().getWidth(),player.getImage().getHeight(),null);
			}
			else {
				if ( !explosions.gameover() )
					g.drawImage(explosions.getImage()[explosions.getPexp().getStage()],explosions.getPexp().getX(),explosions.getPexp().getY(),explosions.getImage()[explosions.getPexp().getStage()].getWidth()*2,explosions.getImage()[explosions.getPexp().getStage()].getHeight()*2,null);
			}
			
			// Explosion
			for ( int i = 0; i < explosions.getExp().size(); ++i ) {
				g.drawImage(explosions.getImage()[explosions.getExp().get(i).getStage()],explosions.getExp().get(i).getX(),explosions.getExp().get(i).getY(),explosions.getImage()[explosions.getExp().get(i).getStage()].getWidth(),explosions.getImage()[explosions.getExp().get(i).getStage()].getHeight(),null);
			}
			
			// Menu bar at the bottom
			g.setColor(Color.black);
			g.fillRect(0,getHeight()-40,getWidth(),40);
		
			// Score Text
			g.setColor(Color.white);
			g.drawString("Score: " + score,10,getHeight()-24);
				
			// Bullet Text
			g.drawString("Bullets: " + bullets.getAmmo(),10,getHeight()-6);
			
			// Plane Health
			g.drawString("Plane Health",100,getHeight()-15);
			g.setColor(Color.red);
			g.fillRect(180,getHeight()-30,100,20);
			g.setColor(Color.green);
			g.fillRect(180,getHeight()-30,plane_health,20);
			
			// Base Health
			g.setColor(Color.white);
			g.drawString("Base Health",308,getHeight()-15);
			g.setColor(Color.red);
			g.fillRect(385,getHeight()-30,100,20);
			g.setColor(Color.green);
			g.fillRect(385,getHeight()-30,base_health,20);
			
			// Gameover menu
			if ( explosions.gameover() ) {
				g.drawImage(menu.getImage(),0,90,menu.getImage().getWidth(),menu.getImage().getHeight(),null);
				g.setColor(Color.red);
				g.drawString("You Lose!",220,140);
				g.drawString("Score: " + score,210,160);
				if ( newhighscore ) {
					g.setColor(Color.white);
					g.drawString("NEW HIGHSCORE!",195,180);
					g.drawString("Enter your name:",202,220);
					g.fillRect(200,240,100,20);
					g.setColor(Color.black);
					g.drawString(name,201,255);
				}
				else g.drawString("Press Enter to reset.",195,220);
			}
		}
		else {
			g.drawImage(menu.getImage(),0,0,menu.getImage().getWidth(),menu.getImage().getHeight(),null);
		}
	}
	/*****************************************************/
	/*****************************************************/
	
	public void setup() {
	
		// Set healths
		plane_health = 100;
		base_health = 100;
		
		// Set Defaults
		space_isDown = false;
		hit = false;
		newhighscore = false;
		flash = 1;
		score = 0;
		newAmmo = 1000;
		
		// Make background
		b = new Background();
		
		// Make player
		player = new Plane();
		
		// Make enemies
		enemies = new Enemy();
		
		// Make bullets
		bullets = new Bullet();
		
		// Make explosions
		explosions = new Explosion();
		
		// Make and start timer
		t = new Timer(50, new Listener());
		setFocusable(true);
		t.start();
		
		shoot = new Timer(150,new Shootable());
		setFocusable(true);
	}
	
	public void collisions() {
		
		// Cycles through arraylist of enemies
		for ( int i = 0; i < enemies.getEnemies().size(); ++i ) {
			// Checks collision with player
			if ( !hit ) {
				if (collisionTest( enemies.getEnemies().get(i), enemies.getImage(), player, player.getImage() )) {
				
					// Do if player is hit
					plane_health -= 20; // Lower health
					repaint(180,getHeight()-30,100,20); // Repaint health
					flash = 2; // Change flash variable
					hit = true; // Plane was hit
					explosions.add(enemies.getEnemies().get(i).getX(),enemies.getEnemies().get(i).getY()); // Make enemy explode
					enemies.getEnemies().remove(i); // Remove enemy
					--i; // (needed)
					// If player died
					if ( plane_health <= 0 ) {
						explosions.dead(player.getX()-25,player.getY()); // Make player explode
					}
					continue; // Skips rest of for-loop
				}
			}
			// Cycles through arraylist of bullets and checks collision with bullet
			for ( int j = 0; j < bullets.getBullets().size(); ++j) {
				if (collisionTest( enemies.getEnemies().get(i), enemies.getImage(), bullets.getBullets().get(j), bullets.getImage() )) {
				
					// Do if enemy is shot
					explosions.add(enemies.getEnemies().get(i).getX()-10,enemies.getEnemies().get(i).getY()); // Make enemy explode
					bullets.getBullets().remove(j); // Remove bullet
					enemies.getEnemies().remove(i); // Remove enemy
					score += 50; // Increase score
					repaint(0,getHeight()-40,100,40); // Repaint score
					--i; // (needed)
					break; // Jump out of for-loop completely
				}
			}
		}
	}
	
	public boolean collisionTest( Motion a, BufferedImage one, Motion b, BufferedImage two ) {
	
		// Turn images into rectangles
		Rectangle r1 = new Rectangle(a.getX(),a.getY(),one.getWidth(),one.getHeight());
		Rectangle r2 = new Rectangle(b.getX(),b.getY(),two.getWidth(),two.getHeight());
		
		// Find intersections
		if ( !r1.intersects(r2) ) return false;
		Rectangle r = r1.intersection(r2);
		
		// Finds the greater x and y value
		int x,y;
		x = ( r1.getX() < r2.getX() ) ? (int)r2.getX() : (int)r1.getX();
		y = ( r1.getY() < r2.getY() ) ? (int)r2.getY() : (int)r1.getY();
		
		// Check if non-alpha pixels match
		int x1 = x - (int)r1.getX();
		int y1 = y - (int)r1.getY();
		int x2 = x - (int)r2.getX();
		int y2 = y - (int)r2.getY();
		for ( int i = 0; i < r.getWidth(); ++i ) { // Columns of image
			for ( int j = 0; j < r.getHeight(); ++j ) { // Rows of image
				if (one.getRGB(i+x1,j+y1) != BufferedImage.TYPE_4BYTE_ABGR)
						if (two.getRGB(i+x2,j+y2) != BufferedImage.TYPE_4BYTE_ABGR)
								return true; // Objects collided
			}
		}
		
		return false; // Objects have not collided
	}
	
	public class Listener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
		
			// Background
			b.doStuff();
			
			// Plane
			player.doStuff();
			
			// Makes plane flash if hit
			if ( hit ) ++flash;
			// Stop the flashing after 5 flashes
			if ( flash >= 32 ) {
				flash = 1;
				hit = false;
			}
			
			// Enemies
			enemies.doStuff();
			
			// Bullets
			bullets.doStuff();
			
			// Shoot
			if ( space_isDown && !shoot.isRunning() ) {
				if ( bullets.getAmmo() > 0 ) {
					bullets.add(player.getX()+34,player.getY()-10); // Add bullet
					shoot.start(); // Start bullet delay
					repaint(0,getHeight()-40,100,40); // Repaint bullet area
				}
			}
			
			// Add more ammo
			if ( score >= newAmmo ) {
				bullets.addAmmo(); // Adds 30 bullets
				newAmmo += 1000; // Sets next score target
				repaint(0,getHeight()-40,100,40); // Repaint bullet area
			}
			
			// Explosions
			explosions.doStuff();
			
			// Test collisions
			collisions();
			
			// Health
			// Checks if base health has changed
			if ( base_health != enemies.getBase() ) {
				base_health = enemies.getBase(); // Sets new base health
				if ( base_health <= 0 ) explosions.gameover(true);
				repaint(180,getHeight()-30,100,20); // Repaints health
			}
			
			// If gameover
			if ( explosions.gameover() ) {
				t.stop(); // Stops game timer
				menu.setEnd(true); // Sets menu to end of game
				if ( score > highscore ) {
					name = "|";
					newhighscore = true;
					data.setHighscore(score);
				}
				addKeyListener(new Typer());
				setFocusable(true);
			}
		
			// Repaint screen
			repaint(0,0,getWidth(),getHeight()-40);
		}
	}
	
	public class Shootable implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			shoot.stop(); // Stops timer
		}
	}
	
	public void mouseMoved( MouseEvent e ) {
		menu.change(e.getX(),e.getY(),GButton.HOVERED);
		repaint();
	}
	
	public void mousePressed( MouseEvent e ) {
		menu.change(e.getX(),e.getY(),GButton.CLICKED);
		repaint();
	}
	
	public void mouseReleased( MouseEvent e ) {
		menu.change(e.getX(),e.getY(),GButton.NORMAL);
		
		if ( menu.begin() ) {
		
			// Remove mouse events
			removeMouseMotionListener(this);
			removeMouseListener(this);
			
			// Setup
			setup();
		}
		
		// Repaint
		repaint();
	}
	
	
	public class Typer extends KeyAdapter {
		public void keyPressed( KeyEvent e ) {
			int x = e.getKeyCode();
			if ( x == 10 ) {
				if ( newhighscore )
				data.setName(name.substring(0,name.length()-1));
				save();
				removeKeyListener(this);
				reset();
			}
			if ( newhighscore ) {
				if ( x == 8 ) {
					if ( name.length() > 1 ) {
						name = name.substring(0,name.length()-2) + '|';
					}
				}
				else if ( e.getKeyCode() > 31 && e.getKeyCode() < 127 ) {
					if ( e.getKeyChar() != ',' ) {
						name = name.substring(0,name.length()-1) + e.getKeyChar() + '|';
					}
				}
				repaint(200,240,100,50);
			}
		}
	}
	
	public void reset() {
	
		// Highscore
		load();
		highscore = data.getHighscore();
		highscore_name = data.getName();
		
		// Temperary (for controls)
		player = new Plane();
		space_isDown = false;
		
		// Make menu
		menu = new MenuGUI();
		menu.setName(highscore_name);
		menu.setScore(highscore);
		
		// Mouse listeners
		addMouseMotionListener(this);
		addMouseListener(this);
		
		// Repaint
		repaint();
	}
	
	// ----------------------------------------------------
	// LOADING AND SAVING GAME DATA -----------------------
	public void load() {
		data.load();
	}
	
	public void save() {
		data.save();
	}
	
	public void setCoor( Point p ) {
		data.setCoor(p);
	}
	
	public Point getCoor() {
		return data.getCoor();
	}
	// ----------------------------------------------------
	// ----------------------------------------------------
	
	public void mouseDragged( MouseEvent e ) {}
	public void mouseClicked( MouseEvent e ) {}
	public void mouseEntered( MouseEvent e ) {}
	public void mouseExited( MouseEvent e ) {}
}