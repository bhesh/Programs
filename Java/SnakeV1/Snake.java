// Brian Hession -- January 17, 2012

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.ArrayList;

public class Snake extends JPanel {

	private int width = 28;
	private int height = 45;
	private Timer t;
	private boolean[][] board;
	private boolean add_apple;
	private boolean movable;
	private Player p;
	private Coord apple;
	private int score;

	public Snake() {

		movable = true;

		// KEY BINDING
		Action up = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				if (p.getVY() == 0 && movable) {
					p.setVelocity(0, -1);
					movable = false;
				}
			}
		};
		Action down = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				if (p.getVY() == 0 && movable) {
					p.setVelocity(0, 1);
					movable = false;
				}
			}
		};
		Action left = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				if (p.getVX() == 0 && movable) {
					p.setVelocity(-1, 0);
					movable = false;
				}
			}
		};
		Action right = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				if (p.getVX() == 0 && movable) {
					p.setVelocity(1, 0);
					movable = false;
				}
			}
		};
		getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
		getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");
		getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
		getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
		getActionMap().put("up", up);
		getActionMap().put("down", down);
		getActionMap().put("left", left);
		getActionMap().put("right", right);

		setup();
	}

	public void setup() {

		score = 0;
		add_apple = true;
		apple = new Coord(-1, -1);
		p = new Player();

		// create board
		board = new boolean[width][height];

		// set each spot to false
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				if (j == 0 || i == 0 || i == width - 1 || j == height - 1)
					board[i][j] = false;
				else
					board[i][j] = true;
			}
		}

		t = new Timer(75, new Listener());
		t.start();
	}

	protected void paintComponent(Graphics g) {

		// Create background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 600);

		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 15, 435);

		// Draw player
		g.setColor(Color.YELLOW);
		g.fillOval(p.getX() * 10, p.getY() * 10, 10, 10);

		g.setColor(Color.GREEN);
		ArrayList<Coord> tail = p.getTail();
		for (int i = 0; i < tail.size(); ++i) {
			Coord c = tail.get(i);
			g.fillOval(c.getX() * 10, c.getY() * 10, 10, 10);
		}

		// Create border
		g.setColor(Color.CYAN);
		for (int i = 0; i < width; ++i) {
			g.fillOval(i * 10, 0, 10, 10);
			g.fillOval(i * 10, (height * 10) - 10, 10, 10);
		}
		for (int i = 1; i < height; ++i) {
			g.fillOval(0, i * 10, 10, 10);
			g.fillOval((width * 10) - 10, i * 10, 10, 10);
		}

		// Draw apple
		g.setColor(Color.RED);
		g.fillOval(apple.getX() * 10, apple.getY() * 10, 10, 10);
	}

	public void spawnApple() {

		// Spawns apple in random unoccupied location
		int x = 0;
		int y = 0;
		while (!board[x][y]) {
			x = (int) (Math.random() * (width - 2) + 1);
			y = (int) (Math.random() * (height - 2) + 1);
		}

		apple.setXY(x, y);
		add_apple = false;
	}

	public void eatApple() {

		// Removes apple and adds to tail
		p.addTail();
		add_apple = true;

		// Adds score
		score += 50;
	}

	public boolean checkCollision() {

		// Checks for collision
		if (!board[p.getX()][p.getY()])
			return false;
		return true;
	}

	public void updateField() {

		// Updates which spots are taken
		ArrayList<Coord> tail = p.getTail();
		if (!tail.isEmpty()) {
			Coord c = tail.get(tail.size() - 1);
			if (c.getY() + 1 < height - 1)
				board[c.getX()][c.getY() + 1] = true;
			if (c.getY() - 1 > 0)
				board[c.getX()][c.getY() - 1] = true;
			if (c.getX() + 1 < width - 1)
				board[c.getX() + 1][c.getY()] = true;
			if (c.getX() - 1 > 0)
				board[c.getX() - 1][c.getY()] = true;

			for (int i = 0; i < tail.size(); ++i) {
				board[tail.get(i).getX()][tail.get(i).getY()] = false;
			}
		}
	}

	public void endgame() {

		JOptionPane.showMessageDialog(this, "Game Over");

		setup();
	}

	// Goes through clock cycles
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			p.update();
			movable = true;

			updateField();

			if (apple.getX() == p.getX() && apple.getY() == p.getY())
				eatApple();

			if (add_apple)
				spawnApple();

			repaint();

			if (!checkCollision()) {
				endgame();
				t.stop();
			}
		}
	}
}
