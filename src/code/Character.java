package code;
import javafx.scene.image.Image;

import javafx.scene.shape.Rectangle;

/**
 * This is the super class that all characters
 * (pigs, kings, etc) inherit from because of
 * shared functionality
 * @author Tenzin Migmar 
 */
public class Character extends Rectangle {
	
	protected int counter;
	protected Image image;
	protected boolean isMovingLeft;
	protected boolean isMovingRight;
	protected boolean isAlive = true;
	protected double xVelocity;
	
	public Character(double x, double y, double width, double height, double xVelocity) {
		super(x, y, width, height); 
		this.xVelocity = xVelocity;
	}
	
	/**
	 * Updates the position of the character object
	 */
	public void update() {

	}
	
	/**
	 * Sets character movement to left
	 */
	public void setMoveLeft() {
		this.isMovingLeft = true;
	}
	
	/**
	 * Causes character to move left a certain number of pixels
	 * dependent on a specified xVelocity 
	 */
	public void moveLeft(double elapsedSeconds) {
		this.isMovingLeft = true;
		setX(this.getX() - elapsedSeconds * this.xVelocity);
	}
	
	/**
	 * Boolean method that returns whether a character is 
	 * presenting moving left 
	 * @return boolean isMovingLeft
	 */
	public boolean isMovingLeft() {
		return this.isMovingLeft;
	}
	
	/**
	 * Sets character movement to right
	 */
	public void setMoveRight() {
		this.isMovingRight= true;
	}
	
	/**
	 * Causes character to move right a certain number of pixels
	 * dependent on a specified xVelocity 
	 */
	public void moveRight(double elapsedSeconds) {
		this.isMovingRight = true;
		setX(this.getX() + elapsedSeconds * this.xVelocity);
	}
	
	/**
	 * Boolean method that returns whether a character is 
	 * presenting moving left 
	 * @return boolean isMovingRight 
	 */
	public boolean isMovingRight() {
		return this.isMovingRight;
	}
	
	/**
	 * Getter method for the image object of Character
	 * @return Image image 
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Setter method for the image object of Character
	 */
	public void setImage(Image newImage) {
		this.image = newImage;
	}
	
	/**
	 * Getter method for the xVelocity of Character
	 * @return double xVelocity  
	 */
	public double getXVelocity() {
		return this.xVelocity; 
	}

	/**
	 * Getter method for the current Direction of the character
	 * @return String direction
	 */
	public String getDirection() {
		return null;
	}
}



