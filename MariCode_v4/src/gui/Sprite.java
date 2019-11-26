package gui;

import java.awt.image.BufferedImage;

public class Sprite {
	public BufferedImage b;
	public boolean Active;
	public Sprite(BufferedImage b, boolean Active){
		this.b = b;
		this.Active = Active;
	}
}
