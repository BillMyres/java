package world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gui.Tile;
import player.Player;

public class WorldChoice {
	static int width = 960;
	static int height= 540;
	
	static int tileSize = 32;
	
	public static boolean testWorld = false;
	public static boolean loadedTestWorld = false;
	
	
	static World worldToLoad;
	public static ArrayList<Tile> tiles;
	
	
	public static void tick(){
		if(tiles != null){
			
			//System.out.println(tiles.size());
		}
		if(testWorld && loadedTestWorld == false){
			tiles = new ArrayList<Tile>();
			
			
			for(int i = 0; i < width / 30; i++){
				tiles.add(new Tile(i, (height/tileSize)));
				
				if(i>10){
					//tiles.add(new Tile(i, (height/tileSize) - (2)));
					tiles.add(new Tile(i, (height/tileSize) - (1)));
				}
			}
			
			if(tiles.isEmpty()){
				//System.out.println("could not make tiles.");
			}else{	
				worldToLoad = new World(width, height, tiles);
			}
			
			loadedTestWorld = true;
		}
	}
	
	public static void render(Graphics g){
		//System.out.println();
		
		if(testWorld && loadedTestWorld){
			for(Tile tile : worldToLoad.tiles){
				//System.out.println(tileSize);
				g.setColor(Color.darkGray);
				g.fillRect(tile.x * 32, tile.y*32, tileSize, tileSize);
			}
			Player.loadPlayer = true;
		}
	}
}
