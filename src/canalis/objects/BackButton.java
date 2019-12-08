package canalis.objects;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import canalis.Assets;
import canalis.Clickable;
import canalis.Display;
import canalis.Renderable;

public class BackButton extends GameObject implements Renderable, Clickable {
	
	private final int link;
	private final Display display;
	
	public BackButton(int x, int y, int link, Display display) {
		this.display = display;
		this.posX = x;
		this.posY = y;
		this.link = link;
	}

	@Override
	public void onClick(MouseEvent e) {
		display.setScene(link);
	}

	@Override
	public boolean isInside(int x, int y) {
		if (x >= posX && x <= posX+50 && y >= posY && y <= posY+50) return true; 
		return false;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.back, posX, posY, 50, 50, null);
	}
}
