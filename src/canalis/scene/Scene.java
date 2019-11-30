package canalis.scene;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import canalis.Clickable;
import canalis.Display;
import canalis.Renderable;

public abstract class Scene implements Renderable, Clickable {
	
	protected final Display display;

	public Scene(Display display) {
		 this.display = display;
	}

	@Override
	public final boolean isInside(int x, int y) {
		return true;
	}

	@Override
	public abstract void onClick(MouseEvent e);

	@Override
	public abstract void render(Graphics g);
}
