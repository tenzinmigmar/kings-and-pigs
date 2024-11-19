package code;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;


/**
 * Class for tile objects of game
 * @version January 2023
 * @author Tenzin Migmar, Minkila Bara, Kieran Norman
 */
public class Tiles extends Rectangle {
	
	private Image image;
	private boolean solid;
	private String type;
	
	/**
	 * Tiles class for creating all tile objects in the game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param image
	 * @param solid
	 * @param type
	 */
	public Tiles(double x, double y, double width, double height, Image image, boolean solid, String type) {
		super(x, y, width, height);
		this.setImage(image);
		this.solid = solid;
		this.setType(type);
	}
	
	/**
	 * Gets the image of the tile 
	 * @return Image image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * Sets the image of the tile 
	 * @param Image image
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	/**
	 * Checks if the tile is solid/can be walked on
	 * @return boolean isSolid
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * Retrieves the type of the tile
	 * @return String type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the tile 
	 * @param String type 
	 */
	public void setType(String type) {
		this.type = type;
	}
}