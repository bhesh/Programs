// Brian Hession -- 3/1/2011

/* Picture Class
 * 
 * This class takes an image from file and and
 * puts it in a BufferedImage. Then it takes the
 * pixel color in the top-left corner of the image
 * and makes it and all other pixels of the same
 * color clear (adds an alpha value of 1).
 */
 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Picture {

	private BufferedImage bi;

	public Picture( File f ) {
		try {
			// Stores file in BufferedImage
			bi = ImageIO.read( f );
		}
		catch (IOException e) {
			System.err.println("Error reading file");
		}
		
		// Finds width and height
		int rows = bi.getHeight();
		int columns = bi.getWidth();
		
		// Takes color of pixel in top-left corner
		int check = bi.getRGB(0,0);
		
		// Filters through all pixels in image
		for ( int row = 0;row < rows; ++row ) {
			for ( int col = 0;col < columns; ++col ) {
				// Checks if pixel color matches top-left
				if ( bi.getRGB(col,row) == check ) {
					// Changes pixel color to one that is clear
					bi.setRGB(col,row,BufferedImage.TYPE_4BYTE_ABGR);
				}
			}
		}
	}
	
	// Returns BufferedImage
	public BufferedImage getPic() {
		return bi;
	}
}