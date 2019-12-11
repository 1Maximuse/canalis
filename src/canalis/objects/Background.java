package canalis.objects;

import java.awt.Graphics;

import canalis.Assets;
import canalis.Display;
import canalis.Renderable;

public class Background implements Renderable {
	
	private static final int SIZE = 300;
	private final Display display;
	
	public Background(Display display) {
		this.display = display;
	}
	
	@Override
	public void render(Graphics g) {
		for (int i = 0; i < display.getHeight() / SIZE + 1; i++) {
			for (int j = 0; j < display.getWidth() / SIZE + 1; j++) {
				g.drawImage(Assets.background, SIZE*j, SIZE*i, SIZE, SIZE, null);
			}
		}
	}
}
