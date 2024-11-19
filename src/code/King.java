
package code;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.scene.image.Image;

/**
 * Class for main player character of game
 * @version January 2023
 * @author Tenzin Migmar, Minkila Bara, Kieran Norman
 */
public class King extends Character  { 

	private boolean isJumping = false;
	private boolean isAttacking = false; 
	private boolean isAlive = true;
	private double yAcceleration = 0.5;
	private double yVelocity = 5; 
	private boolean isCollidingEnemy = false;
	// direction defaults to right (king always starts facing right)
	private String lastDirection = "R";

	ArrayList<Image> idleRightImages = new ArrayList<Image>();
	ArrayList<Image> idleLeftImages = new ArrayList<Image>();
	
	ArrayList<Image> movingRightImages = new ArrayList<Image>();
	ArrayList<Image> movingLeftImages = new ArrayList<Image>();
	
	ArrayList<Image> attackRightImages = new ArrayList<Image>();
	ArrayList<Image> attackLeftImages = new ArrayList<Image>();
	
	ArrayList<Image> jumpRightImages = new ArrayList<Image>();
	ArrayList<Image> jumpLeftImages = new ArrayList<Image>();
	
	ArrayList<Image> deadRightImages = new ArrayList<Image>();
	ArrayList<Image> deadLeftImages = new ArrayList<Image>();
	
	/**
	 * The King class contains all the parameters and methods necessary
	 * for the player's character (movement, animations, etc)
	 * @param xCoord
	 * @param yCoord
	 * @param width
	 * @param height
	 * @param xVelocity
	 */
	public King(int xCoord, int yCoord, int width, int height, double xVelocity) {
		super(xCoord, yCoord, width, height, xVelocity);
		
		// fill array list of idle right images
		for (int i = 1; i < 12; i++) {
			try {
				idleRightImages.add(new Image(new FileInputStream("src/king-idle-right/idle" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-idle-right was not found. Make sure the images are in the king-idle-right folder inside the code folder, inside the source folder!");
			}
		}

		// fill array list of idle left images
		for (int i = 1; i < 12; i++) {
			try {
				idleLeftImages.add(new Image(new FileInputStream("src/king-idle-left/idle" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-idle-left was not found. Make sure the images are in the king-idle-left folder inside the code folder, inside the source folder!");
			}
		}

		// fill array list of moving right images
		for (int i = 1; i < 9; i++) {
			try {
				movingRightImages.add(new Image(new FileInputStream("src/king-run-right/right" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-run-right was not found. Make sure the images are in the king-run-right folder inside the code folder, inside the source folder!");
			}
		}

		// fill array list of moving left images
		for (int i = 1; i < 9; i++) {
			try {
				movingLeftImages.add(new Image(new FileInputStream("src/king-run-left/left" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-run-left was not found. Make sure the images are in the king-run-left folder inside the code folder, inside the source folder!");
			}
		}

		// fill array list of attack right images
		for (int i = 1; i < 6; i++) {
			try {
				attackRightImages.add(new Image(new FileInputStream("src/king-attack-right/attack" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-attack-right was not found. Make sure the images are in the king-attack-right folder inside the code folder, inside the source folder!");
			}
		}	
		
		// fill array list of attack left images
		for (int i = 1; i < 6; i++) {
			try {
				attackLeftImages.add(new Image(new FileInputStream("src/king-attack-left/attack" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-attack-left was not found. Make sure the images are in the king-attack-left folder inside the code folder, inside the source folder!");
			}
		}	
		
		// fill array list of jump right images
		for (int i = 1; i < 4; i++) {
			try {
				jumpRightImages.add(new Image(new FileInputStream("src/king-jump-right/jump" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-jump-right was not found. Make sure the images are in the king-jump-right folder inside the code folder, inside the source folder!");
			}
		}	

		// fill array list of jump left images
		for (int i = 1; i < 4; i++) {
			try {
				jumpLeftImages.add(new Image(new FileInputStream("src/king-jump-left/jump" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-jump-left was not found. Make sure the images are in the king-jump-left folder inside the code folder, inside the source folder!");
			}
		}	
		
		// fill array list of dead right images
		for (int i = 1; i < 5; i++) {
			try {
				deadRightImages.add(new Image(new FileInputStream("src/king-dead-right/dead" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-dead-right was not found. Make sure the images are in the king-dead-right folder inside the code folder, inside the source folder!");
			}
		}	
		
		// fill array list of dead left images
		for (int i = 1; i < 5; i++) {
			try {
				deadLeftImages.add(new Image(new FileInputStream("src/king-dead-left/dead" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for king-dead-left was not found. Make sure the images are in the king-dead-left folder inside the code folder, inside the source folder!");
			}
		}	
		// set the image to idle right 
		this.image = idleRightImages.get(0);	
	}

	/**
	 * This method updates the King objects current state movement and image-wise
	 */
	public void update() {
		
		if (this.isAlive()) {
			// always falling
			this.setY(this.getY() - this.yVelocity); // pulling the king down 
			if (yVelocity > -10) {
				this.yVelocity -= this.yAcceleration; 	 // changing the velocity limited to -15
			}
			
			// jump
			if (this.isJumping()) {			
				if (this.isMovingRight() || this.lastDirection.equals("R")) {
					counter++;
					if (counter == 15) {
						counter = 0;
						if (!(this.getImage().equals(jumpRightImages.get(0)))) {
							setImage(jumpRightImages.get(0));
						}
					}
				}
				// else character jumps left
				else {
					counter++;
					if (counter == 15) {
						counter = 0;
						if (!(this.getImage().equals(jumpLeftImages.get(0)))) {
							setImage(jumpLeftImages.get(0));
						}
					}
				}
			}

			// move left 
			else if (this.isMovingLeft()) {
				counter++;
				if (counter == 15) {
					counter = 0; 			
					if (!(movingLeftImages.contains(this.getImage()))) {
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
						setImage(movingLeftImages.get(6));
					}
					
					else if (this.getImage().equals(movingLeftImages.get(6))) {
						setImage(movingLeftImages.get(7));
					}
					
					else if (this.getImage().equals(movingLeftImages.get(7))) {
						setImage(movingLeftImages.get(0));
					}
				}
			}

			// moving right 
			else if (this.isMovingRight()) {
				counter++;
				if (counter == 15) {
					counter = 0;
					// sets the image to the first moving left image
					// if the current image is none of the running ones (its an idle), set it to the first running one 				
					if (!(movingRightImages.contains(this.getImage()))) {
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
						setImage(movingRightImages.get(6));
					}
					
					else if (this.getImage().equals(movingRightImages.get(6))) {
						setImage(movingRightImages.get(7));
					}

					else if (this.getImage().equals(movingRightImages.get(7))) {
						setImage(movingRightImages.get(0));
					}
				}
			}
			
			// attacking 
			else if (this.isAttacking()) {
				// if direction is right, character is attacking right
				
				// flipping bug is probably caused by this line 
				if (this.isMovingRight() || this.lastDirection.equals("R")) {
					counter++;
					if (counter == 15) {
						counter = 0;
						
						if (!(attackRightImages.contains(this.getImage()))) {
							setImage(attackRightImages.get(0));
						}
						
						else if (this.getImage().equals(attackRightImages.get(0))) {
							setImage(attackRightImages.get(1));
						}

						else if (this.getImage().equals(attackRightImages.get(1))) {
							setImage(attackRightImages.get(2));
						}
						
						else if (this.getImage().equals(attackRightImages.get(3))) {
							setImage(attackRightImages.get(4));
						}

						else if (this.getImage().equals(attackRightImages.get(4))) {
							setImage(attackRightImages.get(0));
						}
					}
				}
				else {
					// otherwise, character attacks left
					counter++;
					if (counter == 15) {
						counter = 0;
						
						if (!(attackLeftImages.contains(this.getImage()))) {
							setImage(attackLeftImages.get(0));
						}
						
						else if (this.getImage().equals(attackLeftImages.get(0))) {
							setImage(attackLeftImages.get(1));
						}

						else if (this.getImage().equals(attackLeftImages.get(1))) {
							setImage(attackLeftImages.get(2));
						}
						
						else if (this.getImage().equals(attackLeftImages.get(3))) {
							setImage(attackLeftImages.get(4));
						}

						else if (this.getImage().equals(attackLeftImages.get(4))) {
							setImage(attackLeftImages.get(0));
						}
					}
				}
			}

			// idle 
			else {		
				// if direction is right, character is right idling
				if (this.isMovingRight() || this.lastDirection.equals("R")) {
					counter++;
					if (counter == 15) {
						counter = 0;
				
						if (!(idleRightImages.contains(this.getImage())) || this.getImage().equals(idleRightImages.get(10))) {
							setImage(idleRightImages.get(0));
						}

						else if (this.getImage().equals(idleRightImages.get(0))) {
							setImage(idleRightImages.get(1));
						}

						else if (this.getImage().equals(idleRightImages.get(1))) {
							setImage(idleRightImages.get(2));
						}

						else if (this.getImage().equals(idleRightImages.get(2))) {
							setImage(idleRightImages.get(3));
						}

						else if (this.getImage().equals(idleRightImages.get(3))) {
							setImage(idleRightImages.get(4));
						}

						else if (this.getImage().equals(idleRightImages.get(4))) {
							setImage(idleRightImages.get(5));
						}

						else if (this.getImage().equals(idleRightImages.get(5))) {
							setImage(idleRightImages.get(6));
						}

						else if (this.getImage().equals(idleRightImages.get(6))) {
							setImage(idleRightImages.get(7));
						}

						else if (this.getImage().equals(idleRightImages.get(7))) {
							setImage(idleRightImages.get(8));
						}
						
						else if (this.getImage().equals(idleRightImages.get(8))) {
							setImage(idleRightImages.get(9));
						}
						
						
						else if (this.getImage().equals(idleRightImages.get(9))) {
							setImage(idleRightImages.get(10));
						}
						
						
						else if (this.getImage().equals(idleRightImages.get(10))) {
							setImage(idleRightImages.get(0));
						}
					}
				}
				
				// else, character idles left 
				else {
					counter++;
					if (counter == 15) {
						counter = 0;
						
						if (!(idleLeftImages.contains(this.getImage())) || this.getImage().equals(idleLeftImages.get(10))) {
							setImage(idleLeftImages.get(0));
						}

						else if (this.getImage().equals(idleLeftImages.get(0))) {
							setImage(idleLeftImages.get(1));
						}

						else if (this.getImage().equals(idleLeftImages.get(1))) {
							setImage(idleLeftImages.get(2));
						}

						else if (this.getImage().equals(idleLeftImages.get(2))) {
							setImage(idleLeftImages.get(3));
						}

						else if (this.getImage().equals(idleLeftImages.get(3))) {
							setImage(idleLeftImages.get(4));
						}

						else if (this.getImage().equals(idleLeftImages.get(4))) {
							setImage(idleLeftImages.get(5));
						}

						else if (this.getImage().equals(idleLeftImages.get(5))) {
							setImage(idleLeftImages.get(6));
						}

						else if (this.getImage().equals(idleLeftImages.get(6))) {
							setImage(idleLeftImages.get(7));
						}

						else if (this.getImage().equals(idleLeftImages.get(7))) {
							setImage(idleLeftImages.get(8));
						}
						
						else if (this.getImage().equals(idleLeftImages.get(8))) {
							setImage(idleLeftImages.get(9));
						}

						else if (this.getImage().equals(idleLeftImages.get(9))) {
							setImage(idleLeftImages.get(10));
						}
						
						else if (this.getImage().equals(idleLeftImages.get(10))) {
							setImage(idleLeftImages.get(0));
						}
					}
				}
			}
		}
		
		else {
			// if the last direction was right 
			if (this.isMovingRight() || this.lastDirection.equals("R")) {
				counter++;
				if (counter == 15) {
					counter = 0;
					if (!(this.getImage().equals(deadRightImages.get(0)))) {
						setImage(deadRightImages.get(0));
					}
					else if (this.getImage().equals(deadRightImages.get(0))) {
						setImage(deadRightImages.get(1));
					}
					else if (this.getImage().equals(deadRightImages.get(1))) {
						setImage(deadRightImages.get(2));
					}
					else {
						setImage(deadRightImages.get(3));
					}
				}
			}
			
			else {
				counter++;
				if (counter == 15) {
					counter = 0;
					if (!(this.getImage().equals(deadLeftImages.get(0)))) {
						setImage(deadLeftImages.get(0));
					}
					else if (this.getImage().equals(deadLeftImages.get(0))) {
						setImage(deadLeftImages.get(1));
					}
					else if (this.getImage().equals(deadLeftImages.get(1))) {
						setImage(deadLeftImages.get(2));
					}
					else {
						setImage(deadLeftImages.get(3));
					}
				}
			}
		}
	}
	
	/**
	 * This method gets the last direction that the king was facing
	 * @return String last direction
	 */
	public String getLastDirection() {
		return this.lastDirection;
	}

	/**
	 * This method allows the king character to jump 
	 */
	public void jump() {
		// if not jumping, change it to 10 
		if (!this.isJumping) {
			this.yVelocity = 10;
			this.isJumping = true;
		}
	}
	
	/**
	 * This method is responsible for changing the lastDirection
	 * dependent on whether movement has stopped
	 */
	public void stop() {
		if(this.isMovingRight) {
			this.isMovingRight = false;
			this.lastDirection = "R";
		}
		
		if(this.isMovingLeft) {
			this.isMovingLeft = false;
			this.lastDirection = "L"; 
		}
	}

	/**
	 * This method checks if the character is currently jumping
	 * @return boolean isJumping
	 */
	public boolean isJumping() {
		return this.isJumping;
	}
	
	/**
	 * This method sets the jumping state to true 
	 */
	public void setJumping() {
		this.isJumping = true;
	}
	
	/**
	 * This method helps facilitate the landing of the character
	 * on the tiles after it has finished jumping 
	 */
	public void land() {
		this.yVelocity = 0;
		this.isJumping = false;
	}

	/**
	 * This method is responsible for the king character attacks
	 */
	public void attack() {
		if(!isAttacking()) {
			this.isAttacking = true;
		}
		else {
			this.isAttacking = false;
		}
	}
	
	/**
	 * This method checks if the king character is currently attacking
	 * @return boolean isAttacking
	 */
	public boolean isAttacking() {
		return isAttacking;
	}
	
	/**
	 * This method checks if king character is currently alive
	 * @return boolean isAlive
	 */
	public boolean isAlive() {
		return this.isAlive;
	}
}