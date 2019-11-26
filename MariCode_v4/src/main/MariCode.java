package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import activate.Gravity;
import game.Game;
import input.Keyboard;
import input.Mouse;
import player.Player;
import world.WorldChoice;

public class MariCode{
	
	static int FPS = 60;
	static boolean running = false;
	
	static JFrame window;
	static BufferedImage canvas;

	static long lastLoopTime = System.nanoTime();
	final static long Optimal_Time = 1000000000 / FPS;
	
	static long lastFpsTime;
	static int tFPS;
	static int lFPS;
	
	public static void main(String args[]){
		//JFRAME SETTINGS
		window = new JFrame();
		window.setTitle("MariCode v4777");
		window.setSize(960, 540);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		Mouse.frame = window;
		window.addMouseListener(new Mouse());
		window.addKeyListener(new Keyboard());
		//END
		running = true;
		//GAMELOOP
		while(running){
			//FPS TIMER VARIABLES
			long then = System.nanoTime();
			long updateLength = then - lastLoopTime;
			lastLoopTime = then;
			double delta = updateLength / ((double)Optimal_Time);//movement multiplier
			//END
			////////////////////////////////////////
			Game.topInset = window.getInsets().top;//TITLE BAR HEIGHT
			Game.leftInset = window.getInsets().left;//LEFT BAR WIDTH
			Game.window = window;
			
			Systemout();
			render(delta);
			
			WorldChoice.tick();
			Player.tick(delta);
						
			
			////////////////////////////////////////
			//FPS COUNTER
			lastFpsTime += updateLength;
			tFPS++;
			if(lastFpsTime >= 1000000000){
				//System.out.println("FPS: "+tFPS);
				//if(tFPS > lFPS){
					lFPS = tFPS;
				//}
				lastFpsTime =0;
				tFPS =0;
			}
			
			//END
			//SLEEP TIMER
			long now = System.nanoTime();
			try {if(((then - now)/1000000) + (Optimal_Time) / 1000000 > 0){Thread.sleep(((then - now)/1000000) + (Optimal_Time) /1000000);}} catch (InterruptedException e) {e.printStackTrace();}
			//END
		}
		//END GAMELOOP
	}
	
	public static void render(double delta){
		//render
		Graphics g = window.getGraphics();
		
		canvas = new BufferedImage(window.getWidth(), window.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D b = (Graphics2D) canvas.getGraphics();
		//CLEAR BACKGROUND / PRIVIOUS FRAME
		b.setColor(Color.lightGray);
		b.fillRect(0, 0, window.getWidth(), window.getHeight());
		
		
		Game.render(b);
		
		/////////////////////////////
		//b.fillRect(100, 100, 32, 32);
		WorldChoice.render(b);
		Player.render(b, delta);
		////////////////////////////
		b.dispose();
		
		g.drawImage(canvas, 0, 0, null);
		g.dispose();
	}
	
	public static void Systemout(){
		int mx = MouseInfo.getPointerInfo().getLocation().x - window.getX();
		int my = MouseInfo.getPointerInfo().getLocation().y - window.getY();
		System.out.println("////////////////////");
		System.out.println("MariCode v4 : Cekeh"+"   vel: "+(Player.velx)+", "+(Player.vely)+"   isJumping:"+Player.isJumping);
		System.out.println("FPS: "+lFPS+"    Player POS: "+Player.x+", "+Player.y+"   jumped:"+actions.Jump.jumped);
		System.out.println("inBlock: "+Gravity.inBlock+"   Mouse x:"+mx+" y:"+my+"   isGrounded: "+Player.isGrounded+"   isFalling:"+Player.isFalling);
		System.out.println("////////////////////");
	}
	
}
