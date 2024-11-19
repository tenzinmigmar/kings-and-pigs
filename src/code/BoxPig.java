package code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;


/**
 * BoxPig is a type of pig enemy that inherits
 * from the Character class
 * @author Tenzin Migmar, Minkila Bara, Kieran Norman 
 */
public class BoxPig extends Character  { 
	// the present direction the box pig is moving in 
	private String direction = "R";
	private int x1; 
	private int x2; 
	ArrayList<Image> movingRightImages = new ArrayList<Image>();
	ArrayList<Image> movingLeftImages = new ArrayList<Image>();
	
	/**
	 * Creates a box pig object at the specified x, y coordinates
	 * width, height, xVelocity, and two x coordinates between 
	 * which the box pig character move back and forth from 
	 * @param xCoord
	 * @param yCoord
	 * @param width
	 * @param height
	 * @param xVelocity
	 * @param x1
	 * @param x2
	 */
	public BoxPig(int xCoord, int yCoord, int width, int height, double xVelocity, int x1, int x2) {
		super(xCoord, yCoord, width, height, xVelocity);
		
		// fill array list of moving right images
		for (int i = 1; i < 7; i++) {
			try {
				movingRightImages.add(new Image(new FileInputStream("src/box-pig-run-right/right" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for box-pig-run-right was not found. Make sure the images are in the box-pig-run-right folder inside the code folder, inside the source folder!");
			}
		}
		
		// fill array list of moving left images
		for (int i = 1; i < 7; i++) {
			try {
				movingLeftImages.add(new Image(new FileInputStream("src/box-pig-run-left/left" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for box-pig-run-left was not found. Make sure the images are in the box-pig-run-left folder inside the code folder, inside the source folder!");
			}
		}
		this.x1 = x1;
		this.x2 = x2;
		this.image = movingRightImages.get(0);
	}
	
	/**
	 * Updates the box pig object continuously; mostly helpful for 
	 * updating the animations of the box pig 
	 */
	public void update() {
		
		// these conditional statements facilitate the back and 
		// forth movement of the pig enemy
		if (this.getX() <= x1) {
			this.direction = "R";
		}
		
		else if (this.getX() >= x2){	
			this.direction = "L";
		}
		
		if (this.direction.equals("R")) {

			counter++;
			if (counter == 25) {
				counter = 0;
	
				if (!(movingRightImages.contains(this.getImage())) || this.getImage().equals(movingRightImages.get(5))) {
					setImage(movingRightImages.get(0));
				}

				else if (this.getImage().equals(movingRightImages.get(0))) {
					setImage(movingRightImages.get(1));
				}

				else if (this.getImage().equals(movingRightImages.get(1))) {
					setImage(movingRightImages.get(2));
				}

				else if (this.getImage().equals(movingRightImages.get(2))) {
					setImage(movingRightImages.get(3));
				}

				else if (this.getImage().equals(movingRightImages.get(3))) {
					setImage(movingRightImages.get(4));
				}

				else if (this.getImage().equals(movingRightImages.get(4))) {
					setImage(movingRightImages.get(5));
				}

				else if (this.getImage().equals(movingRightImages.get(5))) {
					setImage(movingRightImages.get(0));
				}
			}
		}
		
		else {
			counter++;
			if (counter == 25) {
				counter = 0;
				if (!(movingLeftImages.contains(this.getImage())) || this.getImage().equals(movingLeftImages.get(5))) {
					setImage(movingLeftImages.get(0));
				}

				else if (this.getImage().equals(movingLeftImages.get(0))) {
					setImage(movingLeftImages.get(1));
				}

				else if (this.getImage().equals(movingLeftImages.get(1))) {
					setImage(movingLeftImages.get(2));
				}

				else if (this.getImage().equals(movingLeftImages.get(2))) {
					setImage(movingLeftImages.get(3));
				}

				else if (this.getImage().equals(movingLeftImages.get(3))) {
					setImage(movingLeftImages.get(4));
				}

				else if (this.getImage().equals(movingLeftImages.get(4))) {
					setImage(movingLeftImages.get(5));
				}

				else if (this.getImage().equals(movingLeftImages.get(5))) {
					setImage(movingLeftImages.get(0));
				}
			}
		}
	}
	
	/**
	 * Returns the current direction that box pig is moving in 
	 * @return String direction
	 */
	public String getDirection() {
		return this.direction;
	}

}
