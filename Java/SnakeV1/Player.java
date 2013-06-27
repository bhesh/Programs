// Brian Hession -- January 17, 2012

import java.util.ArrayList;

public class Player {

	private int x;
	private int y;
	private int vx;
	private int vy;
	private boolean add;
	private ArrayList<Coord> tail;

	public Player() {

		setup();
	}

	public void setup() {

		x = 4;
		y = 4;
		vx = 0;
		vy = 1;

		add = false;

		tail = new ArrayList<Coord>();
	}

	public void update() {

		if (!tail.isEmpty()) {
			Coord temp = new Coord(x, y);
			tail.add(temp);
			if (!add)
				tail.remove(0);
			else
				add = false;
		}

		x += vx;
		y += vy;
	}

	public void setXY(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public void setVelocity(int vx, int vy) {

		this.vx = vx;
		this.vy = vy;
	}

	public int getX() {

		return x;
	}

	public int getY() {

		return y;
	}

	public int getVX() {

		return vx;
	}

	public int getVY() {

		return vy;
	}

	public void addTail() {

		if (tail.isEmpty())
			tail.add(new Coord(x, y));
		else
			add = true;
	}

	public ArrayList<Coord> getTail() {

		return tail;
	}
}
