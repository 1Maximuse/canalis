package canalis;

import java.awt.event.MouseEvent;

public interface Clickable {
	
	public void onClick(MouseEvent e);
	
	public boolean isInside(int x, int y);
}
