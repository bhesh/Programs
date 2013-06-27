	//Brian Hession - 1/24/11
	
	/*creates a grid of Flood objects which make up the game*/
	
   import java.awt.*;
	
   public class Board
   {
      private Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
      private Flood[][] board;
      public static int SIZE=10;
   
      public Board()
      {
      	//8x8 grid
         board = new Flood[SIZE][SIZE];
      	
      	//assign colors
         for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
            {
            	//just makes board (default red)
               board[i][j] = new Flood(i,j);
            }
         
         makeBoard();
      }
      
      public void makeBoard()
      {
         for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
            {
            	//random color generator
               int k = (int)(Math.random()*6);
               board[i][j].setColor(colors[k]);
               board[i][j].setControl(false);
            }
            
         //makes sure the first square does not match its surroundings
         Color c1 = board[0][1].getColor();
         Color c2 = board[1][0].getColor();
         for(int i=0;i<colors.length;i++)
         {   
            if(c1 != colors[i] && c2 != colors[i])
            {
               board[0][0].setColor(colors[i]);
               board[0][0].setControl(true);
               break;
            }
         }
      }
      
      public Flood[][] getBoard()
      {
         return board;
      }
      
      public void reset()
      {
         makeBoard();
      }
   }