package canalis.objects;

import java.awt.Graphics;

import canalis.Assets;
import canalis.Display;
import canalis.Renderable;

public class BackgroundInGame implements Renderable {
	
	private final Display display;
	
	public BackgroundInGame(Display display) {
		this.display = display;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.backgroundInGame, 0, -100, 1000, 1000, null);
	}
}
