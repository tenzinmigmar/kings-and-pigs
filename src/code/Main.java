package code;

import java.beans.EventHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This is the main class to run the game Kings and Pigs; 
 * all of the functionality/objects within the game are 
 * used in this class where everything is brought together
 */
public class Main extends Application {

	final int pauseDuration = 10;
	King king = new King(100, 100, 87, 87, 100);
	Game kingsAndPigs = new Game();
	ArrayList<Tiles> levelTiles = kingsAndPigs.loadLevel();
	ArrayList<Tiles> uiImages = kingsAndPigs.loadUI();
	ArrayList<Diamond> diamonds = kingsAndPigs.loadDiamonds();
	ArrayList<Character> enemies = kingsAndPigs.loadEnemies();
	ArrayList<Image> winOrLose = new ArrayList<Image>();

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method gets called automatically on the application launch. You can use
	 * this to initialize items before the start() method is called. You do not NEED
	 * to use this method.
	 */
	public void init() {

	}

	/**
	 * Start of the application. This is called automatically after the init()
	 * method completes.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create your layout
		primaryStage.setTitle("Kings and Pigs");
		StackPane mainPane = new StackPane();
		Canvas canvas = new Canvas(1000, 407);
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		Scene scene = new Scene(mainPane);

		final BooleanProperty wPressed = new SimpleBooleanProperty();
		final BooleanProperty aPressed = new SimpleBooleanProperty();
		final BooleanProperty dPressed = new SimpleBooleanProperty();
		final BooleanProperty spacePressed = new SimpleBooleanProperty();
		final BooleanBinding keyPressed = (aPressed).or(dPressed).or(wPressed).or(spacePressed);

		/**
		 * This is the animation timer which handles the movement
		 * and animating of characters within the game 
		 */
		AnimationTimer timer = new AnimationTimer() {
			private long lastUpdate;

			public void start() {
				lastUpdate = System.nanoTime();
				super.start();
			}

			@Override
			public void handle(long now) {
				long elapsedNanoSeconds = now - lastUpdate;
				double elapsedSeconds = elapsedNanoSeconds / 1_000_000_000.0;

				kingsAndPigs.movePigs(enemies.get(0), enemies.get(2), elapsedSeconds);
				kingsAndPigs.diamondCollision(king, diamonds);
				update();
				draw(gc);

				if (aPressed.get()) {
					king.moveLeft(elapsedSeconds);
				}

				if (dPressed.get()) {
					king.moveRight(elapsedSeconds);
				}

				if (wPressed.get()) {
					king.jump();
				}

				if (spacePressed.get()) {
					king.attack();
				}

				lastUpdate = now;
			}
		};

		// event handler for key presses
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.A) {
				aPressed.set(true);
			}

			if (e.getCode() == KeyCode.D) {
				dPressed.set(true);
			}

			if (e.getCode() == KeyCode.W) {
				wPressed.set(true);
			}

			if (e.getCode() == KeyCode.SPACE) {
				spacePressed.set(true);
			}

		});

		// event handler for key releases
		scene.setOnKeyReleased(e -> {
			king.stop();
			if (e.getCode() == KeyCode.A) {
				aPressed.set(false);
			}

			if (e.getCode() == KeyCode.D) {
				dPressed.set(false);
			}

			if (e.getCode() == KeyCode.W) {
				wPressed.set(false);
			}

			if (e.getCode() == KeyCode.SPACE) {
				spacePressed.set(false);
			}
		});

		mainPane.getChildren().add(canvas);
		primaryStage.setScene(scene);

		timer.start();
		primaryStage.show();
	}

	/**
	 * This method is used to draw on the Canvas
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html
	 * @param gc The GraphicsContext from the canvas you wish to draw on.
	 */
	public void draw(GraphicsContext gc) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				if (winOrLose.isEmpty()) {
					
					// check if king is dead
					if (uiImages.size() <= 1) {
						try {
							winOrLose.add(new Image(new FileInputStream("src/win-and-lose-images/Lose screen.png")));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
					
					Color backgroundColor = Color.rgb(63, 56, 81);
					gc.setFill(backgroundColor);
					gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

					// draw level tiles here
					for (Tiles levelTile : levelTiles) {
						gc.drawImage(levelTile.getImage(), levelTile.getX(), levelTile.getY(), levelTile.getWidth(),
								levelTile.getHeight());
					}

					// draw diamonds here
					for (Diamond diamond : diamonds) {
						gc.drawImage(diamond.getImage(), diamond.getX(), diamond.getY(), diamond.getWidth(),
								diamond.getHeight());
					}

					// draw enemies here
					for (Character enemy : enemies) {
						gc.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
					}
					
					//draw health
					for (Tiles tile : uiImages) {
						gc.drawImage(tile.getImage(), tile.getX(), tile.getY(), tile.getWidth(),
								tile.getHeight());
					}

					gc.drawImage(king.getImage(), king.getX(), king.getY(), king.getWidth(), king.getHeight());
					
				} 
				else {
						gc.drawImage(winOrLose.get(0), 0, 0);
				}
			}
		});
	}

	/**
	 * This is a useful method to update character positions, text box information,
	 * etc. If your other classes have their own update methods you should call
	 * those here.
	 */
	public void update() {
		if (king.isAlive()) {
			king.update();
		}
		for (Character enemy : enemies) {
			enemy.update();
		}
		// update diamonds
		for (Diamond diamond : diamonds) {
			diamond.update();
		}

		kingsAndPigs.checkCollision(levelTiles, king, enemies, winOrLose, uiImages);
	}

	/**
	 * This method gets called automatically whenever someone clicks the x to close
	 * the window or when Platform.exit() is used in your program to end the
	 * application. This is a good place to turn off any music or save any data.
	 */
	public void stop() {

	}

}
