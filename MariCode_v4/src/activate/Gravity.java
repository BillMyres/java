package activate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import gui.Tile;
import player.Player;
import world.WorldChoice;

public class Gravity {
	
	static Rectangle playerBase;
	public static int inBlock = 0;
	
	static float speed = 8f;
	
	public static void tick(double delta, Graphics g){
		playerBase = new Rectangle((int)Player.x + 48, (int)Player.y + (128-10), 32, 10);
		g.setColor(Color.orange);
		g.fillRect(playerBase.x, playerBase.y, playerBase.width, playerBase.height);
		//System.out.println(playerBase.x+", "+playerBase.y);
		boolean up = false, down = false;
		if(Game.ready){
			//inBlock = 0;
			boolean Ground = true;
			for(Tile t : WorldChoice.tiles){
				///System.out.println(playerBase.y);
				//System.out.println(t.y);
				int x = t.x*32;
				int y = t.y*32;
				if(playerBase.intersects(new Rectangle(x, y, 32, 32))){
					//System.out.println("Touching!!!!");
					inBlock = 1;
					Player.offsety = 0;
					

					if(((t.y*32)) - (Player.y+128) < 12 && ((t.y*32)) - (Player.y+128) > -12
							&& (Player.x + 64) - (t.x*32) < 32
							&& (Player.x + 64) - (t.x*32) > -32){
						
						Player.isGrounded = true;
						//Ground = true;
						
					}else{
						//Ground = false;
					}
					
					Player.y = (t.y*32) - 128;
					up = true;
					
				}else{
					inBlock = 0;
					
					if((Player.x + 64) - (t.x*32) < 52
					&& (Player.x + 64) - (t.x*32) > -52
					&&((t.y*32)) - (Player.y+128) < 2 && ((t.y*32)) - (Player.y+128) > -2){
						//System.out.println((Player.x + 64) - (t.x*32));
						Player.isGrounded = true;
						Player.y = (t.y*32) - 128;
						return;
						//Ground = true;
						//System.out.println("true");
					}else{
						
						Player.isGrounded = false;
						//Player.y = (t.y*32) - 128;
						//Ground = false;
						
					}
				}
				if(!Player.isGrounded){
					down = true;
					
				}
				//Player.y +=1;
				
			}
			//
			if(Ground){
				Player.isGrounded = false;
			}else{
				Player.isGrounded = true;
			}
		}
		if(up){
			//Player.y --;
			if(!Player.isFalling){
				//Player.y -= (((speed*2) * (Player.vely-9))) * delta;
				//Player.vely -= .5 * delta;
			}
			//Player.isGrounded = true;
		}
		if(down){
			//Player.y+= (((speed*1.3) * (Player.vely))) * delta;
			if(Player.facingRight){
				Player.x += (Player.velx) * delta /4;
			}else{
				Player.x += (Player.velx) * delta /4;
			}
		}
		
	}
}
