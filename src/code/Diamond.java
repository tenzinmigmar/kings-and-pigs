/**
 * 
 */
package code;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Diamonds are collected in Kings and Pigs
 * as a side-objective for players
 * @author Tenzin Migmar 
 */
public class Diamond extends Rectangle {
	ArrayList<Image> images = new ArrayList<Image>();
	Image image;
	int counter = 0;

	/**
	 * Creates a diamond object with the specified x, y
	 * coordinates and width, height 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Diamond(int x, int y, int width, int height) {
		super(x,y,width,height);

		for (int i = 1; i < 11; i++) {
			try {
				images.add(new Image(new FileInputStream("src/diamond-images/diamond" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for diamond-images was not found. Make sure the images are in the diamond-images folder inside the code folder, inside the source folder!");
			}
		}
		this.image = images.get(0);
	}

	/**
	 * Updates continuously the shining animation of the diamond
	 */
	public void update() {	
		counter++;
		if (counter == 25) {
			counter = 0;
			if (this.image.equals(images.get(0))) {
				setImage(images.get(1));
			}
			else if (this.image.equals(images.get(1))) { 
				setImage(images.get(2));
			}
			else if (this.image.equals(images.get(2))) {
				setImage(images.get(3));
			}
			else if (this.image.equals(images.get(3))) {
				setImage(images.get(4));
			}
			else if (this.image.equals(images.get(4))) {
				setImage(images.get(5));
			}
			else if (this.image.equals(images.get(5))) { 
				setImage(images.get(6));
			}
			else if (this.image.equals(images.get(6))) { 
				setImage(images.get(7));
			}
			else if (this.image.equals(images.get(7))) { 
				setImage(images.get(8));
			}
			else if (this.image.equals(images.get(8))) { 
				setImage(images.get(9));	
			}
			else if (this.image.equals(images.get(9))) { 
				setImage(images.get(0));		
			}
		}
	}
	
	/**
	 * Getter method for the image the Diamond
	 * object is currently set to  
	 * @return Image image 
	 */
	public Image getImage() {
		return this.image;
	}
	
	/**
	 * Setter method for the image that the Diamond
	 * object is currently set to 
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}
}