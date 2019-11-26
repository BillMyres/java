package actions;

import input.Keyboard;
import player.Player;

public class Velocity {
	public static void tick(double delta){
		
		if(Player.isGrounded && !Player.isJumping){
			Player.vely = 0;
		}
		
		if(Player.velx != 0.0f){
			
			if(Player.velx < 0.0f){
				Player.velx = 0.0f;
			}
			
			if(Player.velx > Player.velxMax){//limit speed
				Player.velx = Player.velxMax;
			}
			
			if(Player.velx < -Player.velxMax){
				Player.velx = -Player.velxMax;
			}
			
			Player.isWalking = true;
		}else if(!Keyboard.holdingWalk){
			Player.isWalking = false;
		}
		
		if(Player.vely != 0.0f){
			
			if(Player.vely > Player.velyMax){
				Player.vely = Player.velyMax;
			}
			
			if(Player.vely < -Player.velyMax){
				Player.vely = -Player.velyMax;
			}
			
			if(Player.vely < 0){
				Player.y += (Player.vely * 15) * delta;
			}else{
				Player.y += (Player.vely * 5) * delta;
			}
		}
		
		if(Player.isWalking && Keyboard.holdingWalk){
			Player.velx += (Player.velSpeed / Player.Friction) * delta;
		}else{
			if(Player.velx > 0){
				Player.velx -= (Player.velSpeed / Player.Friction) * delta;
			}
		}
		
		if(Player.isFalling){
			Player.vely += (Player.velSpeed/35) * delta;
		}
	}
}
