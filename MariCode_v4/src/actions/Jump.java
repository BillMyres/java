package actions;

import java.awt.Graphics;
import java.util.ArrayList;

import gui.Sprite;
import player.Player;

public class Jump {
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	static int spriteLength =6;
	public static int currentFrame =1;
	static int framesPassed =0, cycleRate =3;
	public static boolean jumped = false;
	
	public static void checkIsJumping(ArrayList<Sprite> sprites, Graphics g, double delta){
		//add 1 per frame
		framesPassed++;
		//set sprite
		Jump.sprites = sprites;
		
		if(Player.isGrounded){
			jumped = false;
		}
		
		if(Player.isJumping){
			Player.isFalling = false;
			///////////////////////////
			//System.out.println(Player.facingRight+" "+jumped);
			if(!jumped){
				Player.vely = -.2f;
			}
			if(Player.facingRight){
				
				//Player.x += ((Player.vel/10)) * delta /2;
				Player.x +=	(Player.velx) * delta;
				//if(Player.isFalling){
					//Player.y += (Player.vely) * delta;
					
				//}
				jumped = true;
			}
			if(!Player.facingRight){
				Player.x -=	(Player.velx) * delta;
				//if(Player.isFalling){
					//Player.y += (Player.vely) * delta;
				//}
				jumped = true;
			}
			
			if(!sprites.isEmpty() && framesPassed % cycleRate == 0){//once every 6 frames
				
				if(checkFrameBounds()){
					//if animation has finished
					Player.isJumping = false;
					if(!Player.isWalking){
						Player.isIdle = true;
					}
				}
				
			}
			if(!sprites.isEmpty()){
				cycleSprite(g);
			}
			
			///////////////////////////
		}else{
			if(!Player.isGrounded){
				Player.isFalling = true;
			}
		}
	}
	
	public static boolean checkFrameBounds(){
		if(currentFrame < spriteLength){
			currentFrame++;
		}
		if(currentFrame >= spriteLength){
			return true;
		}
		return false;
	}
	
	public static void cycleSprite(Graphics g){
		int showFrame =0;
		//for each sprite
		for(Sprite s : sprites){
			showFrame++;
			if(showFrame == currentFrame){
				s.Active = true;//set sprite Active
			}else{
				s.Active = false;
			}
			//draw frame if active
			if(s.Active){
				if(Player.facingRight){
				g.drawImage(s.b, (int)Player.x, (int)Player.y, Player.sizeX*4, Player.sizeY*4, null);
				}else{
					g.drawImage(s.b, (int)Player.x + ((int)Player.sizeX*4), (int)Player.y, -Player.sizeX*4, Player.sizeY*4, null);
				}
			}
		}
	}
}
