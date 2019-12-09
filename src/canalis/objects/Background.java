package canalis.objects;

import java.awt.Graphics;

import canalis.Assets;
import canalis.Display;
import canalis.Renderable;

public class Background implements Renderable {
	
	private final Display display;
	
	public Background(Display display) {
		this.display = display;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.background, (int)((Assets.background.getWidth() - display.getWidth()) / -2.0), (int)((Assets.background.getHeight() - display.getHeight()) / -2.0), null);
	}
}
