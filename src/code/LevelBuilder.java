package code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class LevelBuilder {

	
	private int room = 1;
	
	public ArrayList <Tiles> buildLevel() {
		return roomLayout();
	}

	// Returns layout of a room as ArrayList
	public ArrayList <Tiles>  roomLayout() {
		ArrayList <Tiles> layout = new ArrayList <Tiles>();
		
		if (getRoom()==1) {
			layout=room1();
		}else if (getRoom()==2) {
			layout=room2();
		}else if (getRoom()==3) {
			layout=room3();
		}else if (getRoom()==4) {
			layout=room4();
		}
		
//		setRoom(room+1);
		return layout;
	}
	
	// Returns layout of room 1 as ArrayList
	private ArrayList <Tiles>  room1() {
		ArrayList <Tiles> layout = new ArrayList <Tiles>();

		try {
			layout.add(new Tiles(0, 300, 800, 31, new Image(new FileInputStream("src/tile-images/floorTile.png")), true, "floor"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			layout.add(new Tiles(450, 249, 20, 200, new Image(new FileInputStream("src/tile-images/floorTile.png")), true, "wall"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			layout.add(new Tiles(450, 251, 100, 20, new Image(new FileInputStream("src/tile-images/floorTile.png")), true, "floor"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			layout.add(new Tiles(799, 491, 80, 5, null, true, "goal"));	
		
		return layout;
	}
	
	private ArrayList <Tiles>  room2() {
		ArrayList <Tiles> layout = new ArrayList <Tiles>();

		try {
			layout.add(new Tiles(0, 491, 95, 31, new Image(new FileInputStream("src/tile-images/floorTile.png")), true, "floor"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return layout;
	}
	
	private ArrayList <Tiles>  room3() {
		ArrayList <Tiles> layout = new ArrayList <Tiles>();

		try {
			layout.add(new Tiles(0, 491, 95, 31, new Image(new FileInputStream("src/tile-images/floorTile.png")), true, "floor"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return layout;
	}
	
	private ArrayList <Tiles>  room4() {
		ArrayList <Tiles> layout = new ArrayList <Tiles>();

		try {
			layout.add(new Tiles(0, 491, 95, 31, new Image(new FileInputStream("src/tile-images/floorTile.png")), true, "floor"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return layout;
	}
	
	private int getRoom() {
		return room;
	}
	
	private void setRoom(int room) {
		this.room=room;
	}

}
