package canalis.objects;

import java.awt.Graphics;

import canalis.Assets;
import canalis.Display;
import canalis.Renderable;

public class Logo implements Renderable {
	
	private final Display display;
	
	public Logo(Display display) {
		this.display = display;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.logo, (int)((display.getWidth() - (Assets.logo.getWidth() / 2)) / 2.0), 50, Assets.logo.getWidth() / 2, Assets.logo.getHeight() / 2, null);
	}
}
