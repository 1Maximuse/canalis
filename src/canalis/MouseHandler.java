package canalis;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
	
	private final Display display;
	
	public MouseHandler(Display display) {
		this.display = display;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		display.mousePressed(e.getX(), e.getY());
	}
}
