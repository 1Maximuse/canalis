package canalis.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import canalis.Clickable;
import canalis.Display;
import canalis.Renderable;

public abstract class Button extends GameObject implements Renderable, Clickable {
	
	protected final Display display;
	protected final BufferedImage texture;
	
	public Button(Display display, int x, int y, int width, int height, BufferedImage texture) {
		this.display = display;
		this.texture = texture;
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public final boolean isInside(int x, int y) {
		if (x >= posX && x <= posX+width && y >= posY && y <= posY+height) return true;
		return false;
	}

	@Override
	public final void render(Graphics g) {
		g.drawImage(texture, posX, posY, width, height, null);
	}
	
	@Override
	public abstract void onClick(int x, int y);
}
