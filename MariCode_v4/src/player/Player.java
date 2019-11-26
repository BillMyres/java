package player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import activate.Gravity;
import game.Game;
import gui.Sprite;

public class Player {
	public static boolean loadPlayer = false;
	static boolean playerLoaded = false;
	
	static BufferedImage walkBIG;
	static ArrayList<Sprite> walk = new ArrayList<Sprite>();
	
	static BufferedImage idleBIG;
	static ArrayList<Sprite> idle = new ArrayList<Sprite>();
	
	static BufferedImage jumpBIG;
	static ArrayList<Sprite> jump = new ArrayList<Sprite>();
	
	public static float x = 64, y = 350;
	
	public static int sizeY = 32;
	public static int sizeX = 32;
	
	public static float velx = 0;
	public static float vely = 0;
	public static float velxMax = 2;
	public static float velyMax = 5;
	public static float velSpeed = 3;
	
	static int walkCount =1;
	static int idleCount =1;
	public static int jumpCount =1;
	public static int framesPassed =0;
	
	public static boolean facingRight = true;
	public static boolean flip = false;
	
	public static float Friction = 100;
	
	public static boolean isIdle = true;
	public static boolean isWalking = false;
	public static boolean isRunning = false;
	public static boolean isJumping = false;
	public static boolean isGrounded= false;
	public static boolean isFalling = true;
	
	static boolean walkLoaded = false, idleLoaded = false, jumpLoaded = false;
	
	public static void tick(double delta){
		
	///	if(vely < 0){
	//		Player.isJumping = true;
	//	}
		if(Player.isJumping){
			isFalling = true;
			
		}
		if(isGrounded){
			isFalling = false;
		}
		//IF walkAnim IS THERE
		if(walkBIG == null || idleBIG == null){
			try {//SET IT
				walkBIG = ImageIO.read(new File("C:\\Users\\Thomas\\Desktop\\workspace\\MariCode_v4\\src\\walkAnim.png"));} catch (IOException e) {e.printStackTrace();}
			try {//SET IT
				idleBIG = ImageIO.read(new File("C:\\Users\\Thomas\\Desktop\\workspace\\MariCode_v4\\src\\idle2Anim.png"));} catch (IOException e) {e.printStackTrace();}
			try {//SET IT
				jumpBIG = ImageIO.read(new File("C:\\Users\\Thomas\\Desktop\\workspace\\MariCode_v4\\src\\jumpAnim.png"));} catch (IOException e) {e.printStackTrace();}
		}
		//END 
		
		//IF WE WANT TO LOAD THE PLAYER BUT HE HAS NOT LOADED YET --WALKING
		if(loadPlayer && playerLoaded == false){
			//LOAD WALKING||||||||||||||||||||||||||||||
				int size = walkBIG.getHeight();
				//FOR THE 16 WALKING FRAMES
				for(int i =0; i < 16; i++){
					//ADD SPRITE TO SPRITE VAR
					walk.add(new Sprite(walkBIG.getSubimage(0 + (i*size), 0, size, size), false));
				}//PLAYERS LOADED NOW
				walkLoaded = true;
			//END |||||||||||||||||||||||||||||||||||||
				
			//LOAD IDLE |||||||||||||||||||||||||||||||
				//FOR THE 16 WALKING FRAMES
				for(int i =0; i < 7; i++){
					//ADD SPRITE TO SPRITE VAR
					idle.add(new Sprite(idleBIG.getSubimage(0 + (i*size), 0, size, size), false));
				}//PLAYERS LOADED NOW
				idleLoaded = true;
			//END ||||||||||||||||||||||||||||||||||||
				
			//LOAD IDLE |||||||||||||||||||||||||||||||
				//FOR THE 16 WALKING FRAMES
				for(int i =0; i < 5; i++){
					//ADD SPRITE TO SPRITE VAR
					jump.add(new Sprite(jumpBIG.getSubimage(0 + (i*size), 0, size, size), false));
				}//PLAYERS LOADED NOW
				jumpLoaded = true;
			//END ||||||||||||||||||||||||||||||||||||
		}
		//END
		//CHECK LOADED
		if(walkLoaded && idleLoaded && jumpLoaded){
			playerLoaded = true;
			Game.ready = true;
		}
		//END
		//IMAGE FLIPPING
		if(facingRight == false){
			if(sizeX >= 0){
				//sizeX = sizeX * (-1);
				//x += 128;//ADD HALF THE SIZE OF IMAGE
		}}else{
			if(sizeX <= 0){
				//sizeX = sizeX * (-1);
				//x -= 128;//ADD HALF THE SIZE OF IMAGE
			}
		}
		//END
		//IF JUMPING
		if(isJumping){
			isIdle = false;
			//isWalking = false;
			//isRunning = false;
		}
		
		//END
		//WALKING !
		if(isWalking && facingRight){
			if(isRunning){
				x+=4*delta;
			}else{
				x+=(2.5*(Player.velx))*delta;
			}
		}
		if(isWalking && !facingRight){
			if(isRunning){
				x-=4*delta;
			}else{
				x-=(2.5*(Player.velx))*delta;
			}
		}
		//END 
		
		
	}
	
	public static int offsety =0;
	public static void render(Graphics g, double delta){
		Gravity.tick(delta, g);
		//System.out.println(isJumping);
		if(loadPlayer && playerLoaded == true){
			actions.Idle.checkIsIdle(idle, g);
			actions.Jump.checkIsJumping(jump, g, delta);
			actions.Velocity.tick(delta);
			actions.Walk.checkIsWalking(walk, g, delta);
			
		}
		
		
	}
	
	
	
	
	
}
