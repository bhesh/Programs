// Brian Hession -- 3/1/2011 

/* Airplanes is a game where you shoot down enemy
 * planes and score points while avoid getting hit
 * yourself.
 * 
 * This is the Driver. Run from here.
 */
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Driver {

	private static JFrame frame;
	private static Game game;
	
	public static void main(String[] args)
	{		
		frame = new JFrame();
		
		game = new Game();
		
		// Loads data
		load();
		
		frame.setTitle("Airplane V1.0 - Brian Hession");
		frame.setSize(500,500);
		// Remembers window location
		frame.setLocation(game.getCoor());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(game);
				
		frame.addComponentListener(new ComponentListener() {
		
			public void componentMoved(ComponentEvent e) {
				// If window moves, it saves location
				int x = frame.getX();
				int y = frame.getY();
				Point p = new Point(x,y);
				game.setCoor(p);
				save();
			}
	
			public void componentHidden(ComponentEvent e) {}
			public void componentResized(ComponentEvent e) {}
			public void componentShown(ComponentEvent e) {}
		});		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	// Saves data
	public static void save() {
		game.save();
	}
	
	// Loads data
	public static void load() {
		game.load();
	}
}
