// Brian Hession -- 3/4/2011

/* LoadSave Class
 * 
 * This class loads and saves data
 * by writing and reading a text
 * file called "Airplane.sav"
 */

import java.io.*;
import java.awt.Point;
import javax.swing.*;

public class LoadSave {

	private String data_in,data_out;
	private Point coor;
	private String highscore_name;
	private int highscore;

	public LoadSave() {
		coor = new Point(1,1);
		
		highscore_name = "";
		highscore = 0;
	}
	
	public void load() {
		try {
			FileInputStream save = new FileInputStream("Airplane.sav");
			
			DataInputStream in = new DataInputStream( save );
			
			while ( in.available() > 0 ) {
				readData(in.readLine());
			}
			
			in.close();
		}
		catch (Exception e) {
			System.err.println("File input error");
			save();
			load();
		}
	}
	
	public void save() {
		FileOutputStream save;
		PrintStream p;
		
		write();
		
		try {
			save = new FileOutputStream("Airplane.sav");
			
			p = new PrintStream( save );
			
			p.println (data_out);
			
			p.close();
		}
		catch (Exception e) {
			System.err.println ("Error writing to file");
		}
	}
	
	public void readData( String str ) {
		
		int index = str.indexOf(' ');
		String test = str.substring(0,index);
		
		if ( test.equals("Coordinate") ) {
			int index2 = str.indexOf(',');
			int x = Integer.parseInt(str.substring(index+1,index2));
			int y = Integer.parseInt(str.substring(index2+1));
			coor = new Point(x,y);
		}
		
		if ( test.equals("Highscore") ) {
			int index2 = str.indexOf(',');
			highscore_name = str.substring(index+1,index2);
			highscore = Integer.parseInt(str.substring(index2+1));
		}
	}
	
	public void write() {
		String x = "" + (int)coor.getX();
		String y = "" + (int)coor.getY();
		
		data_out = "Coordinate " + x + "," + y + "\n";
		data_out = data_out + "Highscore " + highscore_name + "," + highscore;
	}
	
	public void setCoor( Point coor ) {
		this.coor = coor;
	}
	
	public void setHighscore( int high ) {
		highscore = high;
	}
	
	public void setName( String n ) {
		highscore_name = n;
	}
	
	public Point getCoor() {
		return coor;
	}
	
	public int getHighscore() {
		return highscore;
	}
	
	public String getName() {
		return highscore_name;
	}
	
	
}