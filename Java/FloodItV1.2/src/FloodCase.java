   //Brian Hession - 1/24/11
	
	/*Mother panel - controls the action listener*/
   
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.awt.image.BufferedImage;   

   public class FloodCase extends JPanel
   {
      private JPanel game,frame,controls;
      private JButton red,orange,yellow,green,blue,magenta;
      private static JLabel clicksLeft,clicks;
      private BufferedImage background,board;
      private ActionListener listener;
      
      public static int count,countdown;      
      
      public FloodCase()
      {
      	//creates background
         background = new BufferedImage(356, 450, BufferedImage.TYPE_INT_RGB);
         Graphics buffer = background.getGraphics();
         buffer.setColor(Color.BLACK);  
         buffer.fillRect(0,0,356,450);
      	
      	//sets layout
         frame = new JPanel();
         frame.setOpaque(false);
         frame.setLayout(new GridBagLayout());
         GridBagConstraints c = new GridBagConstraints();
         
         Font font = new Font("Verdana",Font.PLAIN,10);
      	
      	//Creates controls panel -------------------
         controls = new JPanel();						//
         controls.setLayout(new GridLayout(2,3));	//
      															//
         red = new JButton();								//
         red.addActionListener(new Listener());		//
         red.setSize(89,50);								//
         red.setBackground(Color.red);					//
         controls.add(red);								//
      															//
         orange = new JButton();							//
         orange.addActionListener(new Listener());	//
         orange.setSize(89,50);							//
         orange.setBackground(Color.orange);			//
         controls.add(orange);							//
      															//
         yellow = new JButton();							//
         yellow.addActionListener(new Listener());	//
         yellow.setSize(89,50);							//
         yellow.setBackground(Color.yellow);			//
         controls.add(yellow);							//
      															//
         green = new JButton();							//
         green.addActionListener(new Listener());	//
         green.setSize(89,50);							//
         green.setBackground(Color.green);			//
         controls.add(green);								//
      															//
         blue = new JButton();							//
         blue.addActionListener(new Listener());	//
         blue.setSize(89,50);								//
         blue.setBackground(Color.blue);				//
         controls.add(blue);								//
      															//
         magenta = new JButton();						//
         magenta.addActionListener(new Listener());//
         magenta.setSize(89,50);							//
         magenta.setBackground(Color.magenta);		//
         controls.add(magenta);							//						
      	//------------------------------------------	
      	
      	//takes board and puts it in game
         game = new Playflood();
         c.anchor = GridBagConstraints.PAGE_START;
         c.fill = GridBagConstraints.NONE;
         c.ipady = 320;		//y to 320
         c.ipadx = 320;		//x to 320
         c.gridwidth = 2;	
         c.gridx = 0;		//grid (0,0)
         c.gridy = 0;
         frame.add(game, c);
         
      	//assign variables
         count = 0;
         countdown = 18;
         
      	//sets the two labels
         clicksLeft = new JLabel("Clicks Left: " + countdown);
         clicksLeft.setFont(font);
         clicksLeft.setForeground(Color.white);
         c.ipady = 15;
         c.ipadx = 100;
         c.gridwidth = 1;
         c.gridx = 0;
         c.gridy = 1;
         frame.add(clicksLeft, c);
      	
         clicks = new JLabel("Clicks: " + count);
         clicks.setHorizontalAlignment(clicks.RIGHT);
         clicks.setFont(font);
         clicks.setForeground(Color.white);
         c.ipady = 15;
         c.ipadx = 100;
         c.gridwidth = 1;
         c.gridx = 1;
         c.gridy = 1;
         frame.add(clicks, c);
         
      	//puts the controls in the game
         c.anchor = GridBagConstraints.PAGE_END;
         c.ipady = 20;
         c.ipadx = 100;
         c.gridwidth = 2;
         c.gridx = 0;
         c.gridy = 2;
         frame.add(controls, c);
         
         add(frame);//adds panels to main panel
      }
      
      public void paintComponent(Graphics g)
      {
         g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
      }
      
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(e.getSource() == red)//if red button is pressed
            {
               Playflood.changeColor(Color.red);//calls changeColor() method in Playflood class
            }
            else if(e.getSource() == orange)
            {
               Playflood.changeColor(Color.orange);
            }
            else if(e.getSource() == yellow)
            {
               Playflood.changeColor(Color.yellow);
            }
            else if(e.getSource() == green)
            {
               Playflood.changeColor(Color.green);
            }
            else if(e.getSource() == blue)
            {
               Playflood.changeColor(Color.blue);
            }
            else if(e.getSource() == magenta)
            {
               Playflood.changeColor(Color.magenta);
            }
            count++;
            countdown--;
            clicks.setText("Clicks: " + count);
            clicksLeft.setText("Clicks Left: " + countdown);
         }
      }
      
      public static void reset()
      {
         count = 0;
         countdown = 18;
         clicks.setText("Clicks: " + count);
         clicksLeft.setText("Clicks Left: " + countdown);
      }
   }