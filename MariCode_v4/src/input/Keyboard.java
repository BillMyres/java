package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import actions.Jump;
import player.Player;

public class Keyboard implements KeyListener{
	
	public static int lastKey;
	public static boolean holdingWalk = false;

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		lastKey = keyCode;
		//System.out.println(keyCode);
		//65=A
		if(keyCode == 68){//D
			Player.isIdle = false;
			Player.facingRight = true;
			Player.isWalking = true;
			holdingWalk = true;
		}
		if(keyCode == 65){//A
			Player.isIdle = false;
			Player.facingRight = false;
			Player.isWalking = true;
			holdingWalk = true;
		}
		if(keyCode == 40){//DOWN
			Player.y++;
		}
		if(keyCode == 38){//UP
			Player.y--;
		}
		if(keyCode == 32){//SPACE
			
			//Player.isWalking = false;
			if(!Player.isJumping && Player.isGrounded){
				Player.isGrounded = false;
				Player.isIdle = false;
				Jump.jumped = false;
				Player.isJumping = true;
				Player.jumpCount = 1;
				Jump.currentFrame = 1;
			}
			
		}
		if(keyCode == 16){//SHIFT
			Player.isRunning = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == 68 && Player.facingRight){//D
			Player.isWalking = false;
			holdingWalk = false;
			//Player.isIdle = true;
		}
		if(keyCode == 65 && !Player.facingRight){//A
			//Player.facingRight = false;
			Player.isWalking = false;
			holdingWalk = false;
			//Player.isIdle = true;
		}
		if(keyCode == 16){
			Player.isRunning = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
