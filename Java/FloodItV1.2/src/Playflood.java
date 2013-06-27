	//Brian Hession - 1/24/11
	
	/*Controls the graphics and gameplay*/
	
   import java.awt.*;
   import javax.swing.*;
   import java.awt.image.BufferedImage;
   import java.awt.event.*;
   import java.util.ArrayList;
	
   public class Playflood extends JPanel
   {
      private static BufferedImage myImage;
      private static Board start;
      private static Flood[][] board;
      private static Color check_button;
      private Timer t;
      private ArrayList<Flood> flood;
      
      public static Color button;
   	
      public Playflood() 
      {   
         setUp();
      }
      
      public void setUp()
      {	//imports Board
         start = new Board();
      	
      	//takes the board from Board
         board = start.getBoard();
         
         drawBoard();
         
      	//sets initial colors
         check_button = board[0][0].getColor();
         button = check_button;
         
      	//constructs arraylist
         flood = new ArrayList<Flood>();
         
      	//starts timer
         t = new Timer(10, new Listener());
         t.start();
      }
      
      public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
      
      public void drawBoard()
      {
      	//creates a buffered image
         myImage = new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);
         Graphics buffer = myImage.getGraphics();
         
      	//Draws board
         for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
            {
               Color color = board[i][j].getColor();
               buffer.setColor(color);
               buffer.fillRect(i*32,j*32,32,32);
            }
      }
      
      public void checkBoard()
      {
      	//this method checks for new squares under control
         while(flood.size() > 0)//keeps looping until arraylist is gone
         {
            Flood f = flood.get(0);
            if(f.getX() != 0)//wont go off screen
               if(checkColor(board[f.getX()-1][f.getY()]))//checks next spots color
                  if(!board[f.getX()-1][f.getY()].getControl())//makes sure spot doesnt have control
                  {
                     board[f.getX()-1][f.getY()].setControl(true);//sets control to true
                     flood.add(board[f.getX()-1][f.getY()]);//adds it to arraylist
                  }
            if(f.getX() != Board.SIZE-1)//wont go off screen
               if(checkColor(board[f.getX()+1][f.getY()]))//checks next spots color
                  if(!board[f.getX()+1][f.getY()].getControl())//makes sure spot doesnt have control
                  {
                     board[f.getX()+1][f.getY()].setControl(true);//sets control to true
                     flood.add(board[f.getX()+1][f.getY()]);//adds it to arraylist
                  }
            if(f.getY() != 0)//wont go off screen
               if(checkColor(board[f.getX()][f.getY()-1]))//checks next spots color
                  if(!board[f.getX()][f.getY()-1].getControl())//makes sure spot doesnt have control
                  {
                     board[f.getX()][f.getY()-1].setControl(true);//sets control to true
                     flood.add(board[f.getX()][f.getY()-1]);//adds it to arraylist
                  }
            if(f.getY() != Board.SIZE-1)//wont go off screen
               if(checkColor(board[f.getX()][f.getY()+1]))//checks next spots color
                  if(!board[f.getX()][f.getY()+1].getControl())//makes sure spot doesnt have control
                  {
                     board[f.getX()][f.getY()+1].setControl(true);//sets control to true
                     flood.add(board[f.getX()][f.getY()+1]);//adds it to arraylist
                  }
            flood.remove(0);//removes checked square
         }
      }
      
            
   			
      public boolean checkColor(Flood f)
      {
      	//this method checks if the color matches the chosen color
         if(f.getColor().equals(button))
            return true;
         return false;
      }
   	
      public void changeColors(Color c)
      {
         for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
            {
               if(board[i][j].getControl())//checks for control
               {
                  flood.add(board[i][j]);//adds to an arraylist for later
                  board[i][j].setColor(button);//changes color
               }
            }
         
         checkBoard();//jumps to checkBoard() method
      	
         drawBoard();//redraws
         
         repaint();//repaints
         
      	//checks whether game is over
         if(checkWinnings())
         {
            JOptionPane.showMessageDialog(null,"You Win! You used " + FloodCase.count + " clicks.");
            reset();
         }
         if(checkLosings())
         {
            JOptionPane.showMessageDialog(null,"You Lose! You ran out of clicks.");
            reset();
         }
      }
      
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
         	//checks if color changed ever 10 miliseconds
         	//this was the only way i could think of to control this panel from FloodCase
            if(!check_button.equals(button))
            {
               check_button = button;
               changeColors(button);
            }
         }
      }
      
      public boolean checkWinnings()
      {
      	//checks if all pieces are under control
         for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
               if(!board[i][j].getControl())
                  return false;
         return true;
      }
      
      public boolean checkLosings()
      {
      	//checks if out of clicks
         if(FloodCase.countdown <= 0)
            return true;
         return false;
      }
     	
      public static void changeColor(Color c)
      {
         button = c;
      }
      
      public void reset()
      {
         FloodCase.reset();
         start.reset();
         drawBoard();
         repaint();
         check_button = board[0][0].getColor();
         button = check_button;
         t.restart();
      }
   }
