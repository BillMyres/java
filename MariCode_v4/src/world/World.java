package world;

import java.util.ArrayList;

import gui.Tile;

public class World {
	int sizeX, sizeY;
	ArrayList<Tile> tiles;
	public World(int x, int y, ArrayList<Tile> tiles){
		sizeX = x;
		sizeY = y;
		this.tiles = tiles;
	}
}
