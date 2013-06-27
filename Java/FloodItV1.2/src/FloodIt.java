	//Brian Hession - 1/25/11
	
	/*Main class of the game - Run from here*/
	
   import javax.swing.JFrame;

   public class FloodIt
   {	
      public static void main(String[] args)
      {
         JFrame frame = new JFrame();
      
         frame.setTitle("Flood It V1.2 - Brian Hession");
         frame.setSize(356,450);
         frame.setLocation(50,50);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new FloodCase());
        
         frame.setVisible(true);
      }
   }