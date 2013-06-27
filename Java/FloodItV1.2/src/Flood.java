	//Brian Hession - 1/24/11
	
	/*Simple class called Flood...each little square*/

   import java.awt.*;
	
   public class Flood
   {
      private Color color;
      private Boolean control;
      private int x,y;
   
      public Flood()//ive been told this is 'good' programming
      {
         color = Color.red;
         control = false;
         x = 0;
         y = 0;
      }
      
      public Flood(int n, int m)
      {
         color = Color.red;
         control = false;
         x = n;
         y = m;
      }
   		
      public Flood(Color c, Boolean b, int n, int m)
      {
         color = c;
         control = b;
         x = n;
         y = m;
      }
      
      public Color getColor()
      {
         return color;
      }
   		
      public Boolean getControl()
      {
         return control;
      }
      
      public int getX()
      {
         return x;
      }
   	
      public int getY()
      {
         return y;
      }
   		
      public void setColor(Color c)
      {
         color = c;
      }
   			
      public void setControl(Boolean b)
      {
         control = b;
      }
   }