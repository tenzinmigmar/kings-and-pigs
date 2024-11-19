package code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * CannonPig is a type of pig enemy that inherits
 * from the Character class
 * @author Tenzin Migmar 
 */
public class CannonPig extends Character  { 
	private String direction = "R";
	ArrayList<Image> images = new ArrayList<Image>();
	int counter = 0;
	private Image image;
	
	/**
	 * Creates a cannon pig object at the specified x, y coordinates
	 * width, height, xVelocity, and two x coordinates between 
	 * which the box pig character move back and forth from 
	 * @param xCoord
	 * @param yCoord
	 * @param width
	 * @param height
	 * @param xVelocity
	 */
	public CannonPig(int xCoord, int yCoord, int width, int height, double xVelocity) {
		super(xCoord, yCoord, width, height, xVelocity);
		
		// fill array list of lighting match images
		for (int i = 1; i < 7; i++) {
			try {
				images.add(new Image(new FileInputStream("src/cannon-pig/cannon" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for cannon-pig was not found. Make sure the images are in the cannon-pig folder inside the code folder, inside the source folder!");
			}
		}
		
		this.image = images.get(0);
	}
	
	/**
	 * Updates the cannon pig object continuously; mostly helpful for 
	 * updating the animations of the cannon pig  
	 */
	public void update() {
		counter++;
		if (counter == 25) {
			counter = 0;
			if (this.getImage().equals(images.get(0))) {
				this.setImage(images.get(1));
			}
			else if (this.getImage().equals(images.get(1))) {
				this.setImage(images.get(2));
			}
			else if (this.getImage().equals(images.get(2))) {
				this.setImage(images.get(3));
			}
			else if (this.getImage().equals(images.get(3))) {
				this.setImage(images.get(4));
			}
			else if (this.getImage().equals(images.get(4))) {
				this.setImage(images.get(5));
			}
			else if (this.getImage().equals(images.get(5))) {
				this.setImage(images.get(0));
			}
		}
	}
	
	/**
	 * Returns the current image object the cannon pig
	 * object is currently set to 
	 * @return Image image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Setter for the image object 
	 * @param image 
	 */
	public void setImage(Image image) {
		this.image = image;
	}
}