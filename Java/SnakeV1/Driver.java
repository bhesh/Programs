// Brian Hession -- January 17, 2012

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setTitle("Snake V1.0 -- Brian Hession");
		frame.setSize(287, 479);
		frame.setResizable(false);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Snake());

		frame.setVisible(true);
	}
}
