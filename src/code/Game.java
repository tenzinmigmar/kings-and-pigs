package code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;

/**
 * Class with helper methods for facilitating set-up, checking collisions,
 * drawing, etc.
 * @version January 2023
 * @author Tenzin Migmar, Minkila Bara, Kieran Norman
 */
public class Game {

	public Game() {

	}

	/**
	 * This method takes a txt file of the parameters for all the tile objects,
	 * reads in the txt file and creates an array list of the instantiated objects
	 * @return ArrayList of tiles 
	 */
	public ArrayList<Tiles> loadLevel() {
		String fileName = "src/tiles.txt";
		try {
			File file = new File(fileName);
			Scanner fsc = new Scanner(file);

			int n = fsc.nextInt();
			fsc.nextLine();
			ArrayList<Tiles> levelTiles = new ArrayList<Tiles>();

			// information from each object line from the file is split and placed into a
			// String array before creating a new object instance of tiles to add to
			for (int i = 0; i < n; i++) {
				String[] tilesInfo = fsc.nextLine().split(",");
				Tiles levelTile = new Tiles(Double.parseDouble(tilesInfo[0]), Double.parseDouble(tilesInfo[1]),
						Double.parseDouble(tilesInfo[2]), Double.parseDouble(tilesInfo[3]),
						new Image(new FileInputStream("src/tile-images/" + tilesInfo[4] + ".png")),
						Boolean.parseBoolean(tilesInfo[5]), tilesInfo[6]);
				levelTiles.add(levelTile);
			}
			fsc.close();
			return levelTiles;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ArrayList<Tiles> levelTiles = new ArrayList<Tiles>();
			return levelTiles;
		}
	}

	
	/**
	 * This method creates an array list of the UI images 
	 * @return Array list of UI images
	 */
	public ArrayList<Tiles> loadUI() {
		ArrayList<Tiles> uiImages = new ArrayList<Tiles>();

		Image heart = null;
		Image bar = null;
		try {
			heart = new Image(new FileInputStream("src/ui-images/heart.png"));
			bar = new Image(new FileInputStream("src/ui-images/bar.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Tiles healthBar = new Tiles(41, 11, 99, 51, bar, false, "healthBar");
		uiImages.add(healthBar);
		Tiles heart3 = new Tiles(96, 30, 18, 14, heart, false, "heart");
		uiImages.add(heart3);
		Tiles heart2 = new Tiles(80, 30, 18, 14, heart, false, "heart");
		uiImages.add(heart2);
		Tiles heart1 = new Tiles(62, 30, 18, 14, heart, false, "heart");
		uiImages.add(heart1);

		return uiImages;

	}

	/**
	 * This method creates an array list of enemy characters
	 * @return Array list of enemies
	 */
	public ArrayList<Character> loadEnemies() {
		ArrayList<Character> enemies = new ArrayList<Character>();
		BoxPig boxPig1 = new BoxPig(300, 300, 36, 42, 30, 301, 500); // floor pig
		BoxPig boxPig2 = new BoxPig(778, 230, 36, 42, 30, 780, 860); // door pig
		CannonPig cannonPig = new CannonPig(740, 100, 37, 26, 30);
		enemies.add(boxPig1);
		enemies.add(cannonPig);
		enemies.add(boxPig2);

		return enemies;
	}

	/**
	 * This method checks if the player has collided with a diamond and 
	 * if so, removes it from the array list
	 */
	public void diamondCollision(King player, ArrayList<Diamond> diamonds) {
		for (int i = 0; i < diamonds.size(); i++) {
			if (player.getBoundsInLocal().intersects(diamonds.get(i).getBoundsInLocal())) {
				diamonds.remove(i);
			}
		}
	}

	/**
	 * This method facilitates the movement of the pig enemies
	 */
	public void movePigs(Character boxPig1, Character boxPig2, double elapsedSeconds) {
		if (boxPig1.getDirection().equals("R")) {
			boxPig1.setX(boxPig1.getX() + elapsedSeconds * boxPig1.getXVelocity());
		} else {
			boxPig1.setX(boxPig1.getX() - elapsedSeconds * boxPig1.getXVelocity());
		}

		if (boxPig2.getDirection().equals("R")) {
			boxPig2.setX(boxPig2.getX() + elapsedSeconds * boxPig2.getXVelocity());
		} else {
			boxPig2.setX(boxPig2.getX() - elapsedSeconds * boxPig2.getXVelocity());
		}
	}

	/**
	 * This method creates an array list of diamonds
	 * @return Array list of diamonds
	 */
	public ArrayList<Diamond> loadDiamonds() {
		Diamond diamond1 = new Diamond(415, 315, 27, 21);
		Diamond diamond2 = new Diamond(683, 177, 27, 21);
		Diamond diamond3 = new Diamond(908, 215, 27, 21);
		ArrayList<Diamond> diamonds = new ArrayList<Diamond>();
		diamonds.add(diamond1);
		diamonds.add(diamond2);
		diamonds.add(diamond3);
		return diamonds;
	}

	/*
	 * This method is used to check collision for tiles and enemies
	 */
	public void checkCollision(ArrayList<Tiles> levelTiles, King king, ArrayList<Character> enemies,
			ArrayList<Image> winOrLose, ArrayList<Tiles> uiImages) {
		// variables to change the size of hitbox easily
		int kingHitboxXReduction = 17;
		int kingHitboxYReduction = 20;
		int enemyHitboxXReduction = 20;
		int enemyHitboxYReduction = 23;


		// invisible hitbox since king has blank space around it
		Rectangle hitbox = new Rectangle(king.getX() + kingHitboxXReduction, king.getY() + kingHitboxYReduction,
				king.getWidth() - kingHitboxXReduction * 2, king.getHeight() - kingHitboxYReduction * 2);

		// goes through level tiles to check if king collides with them
		for (Tiles collide : levelTiles) {
			// only checks solid tiles
			if (collide.isSolid()) {

				// check floor tiles
				if (collide.getType().equals("floor")) {

					if (hitbox.intersects(collide.getX(), collide.getY(), collide.getWidth(), collide.getHeight())
							&& hitbox.getY() + hitbox.getHeight() - 10 < collide.getY()) {
						king.land();
						king.setY(collide.getY() - kingHitboxYReduction - hitbox.getHeight());
					}
					// check wall tiles
				} else if (collide.getType().equals("wall")) {

					if (hitbox.intersects(collide.getX(), collide.getY(), collide.getWidth(), collide.getHeight())) {
						// left wall
						if (hitbox.getX() > collide.getX() + collide.getWidth() - 5) {
							king.setX(collide.getX() + hitbox.getWidth());

							// right wall
						} else if (hitbox.getX() < collide.getX()) {
							king.setX(collide.getX() - kingHitboxXReduction - hitbox.getWidth());
						}
					}

					// check ceiling tiles
					// king sort of floats because his jump velocity doesn't change here
				} else if (collide.getType().equals("ceiling")) {

					if (hitbox.intersects(collide.getX(), collide.getY(), collide.getWidth(), collide.getHeight())) {
						king.setY(collide.getY() + collide.getHeight());

					}
					// check if king reaches goal
				} else if (collide.getType().equals("goal")) {
					if (hitbox.intersects(collide.getX() + 50, collide.getY() + 50, collide.getWidth() - 50,
							collide.getHeight())) {

						try {
							winOrLose.add(new Image(new FileInputStream("src/win-and-lose-images/Win screen.png")));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}

				for (Character enemy : enemies) {
					if (hitbox.intersects(enemy.getX() + enemyHitboxXReduction, enemy.getY() + enemyHitboxYReduction,
							enemy.getWidth() - enemyHitboxXReduction * 2, enemy.getHeight() - enemyHitboxYReduction * 2)) {
						if (uiImages.size() > 0) {
							uiImages.remove(0);
						}
					}
				}

			}
		}
	}

}
