package canalis.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import canalis.Clickable;
import canalis.Game;
import canalis.Renderable;
import canalis.Display;

public class MainMenu implements Renderable, Clickable {
	private BufferedImage[] texture = new BufferedImage[5];
	
	private final Display display;
	
	public MainMenu(Display display) {
		this.display = display;
		texture[0] = Game.getTexture("main_menu/GameBackground.png");
		texture[1] = Game.getTexture("main_menu/Logo.png");
		texture[2] = Game.getTexture("main_menu/cog.png");
		texture[3] = Game.getTexture("main_menu/play.png");
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(texture[0], display.getWidth()-(2507/2), display.getHeight()-(1112/2), 2507, 1112, null);
		g.drawImage(texture[1], (display.getWidth()/2)-(400/2), (display.getHeight()/2)-300, 400, 400, null);
		g.drawImage(texture[2], (display.getWidth()-55), 10, 50, 50, null);
		g.drawImage(texture[3], (display.getWidth()/2)-(200/2), (display.getHeight()/2)+50, 200, 60, null);	
	}

	@Override
	public void onClick(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		 
		if (x >= (display.getWidth()/2)-(200/2) && x <= (display.getWidth()/2)-(200/2)+200 &&
				y >= (display.getHeight()/2)+50 && y <= (display.getHeight()/2)+50+60)
			display.setScene(1);
		if (x >= (display.getWidth()-55) && x <= (display.getWidth()-55)+50 && y >= 10 && y <= 10+50)
			display.setScene(2);
		
	}

	@Override
	public boolean isInside(int x, int y) {
		return true;
	}
}
