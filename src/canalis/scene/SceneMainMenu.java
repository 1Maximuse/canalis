package canalis.scene;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import canalis.Assets;
import canalis.Display;
import canalis.Game;

public class SceneMainMenu extends Scene {
	
	private final Game game;
	
	public SceneMainMenu(Game game, Display display) {
		super(display);
		this.game = game;
	}

	@Override
	public void render(Graphics g) {
		int bgPadding = 100;
		double bgScale = (double)(display.getWidth() + 2*bgPadding) / Assets.background.getWidth();
		bgScale = Math.max(bgScale, (double)(display.getHeight() + 2*bgPadding) / Assets.background.getHeight());
//		System.out.println(bgScale);
		g.drawImage(Assets.background, (int)(-bgPadding / bgScale), (int)(-bgPadding / bgScale), (int)(Assets.background.getWidth() * bgScale), (int)(Assets.background.getHeight() * bgScale), null);
		g.drawImage(Assets.logo, (display.getWidth()/2)-(display.getWidth()/2/2), -display.getHeight()/12, display.getWidth() / 2, display.getWidth() / 2, null);
		g.drawImage(Assets.options, (display.getWidth()-55), 10, 50, 50, null);
		g.drawImage(Assets.playButton, (display.getWidth()/2)-(200/2), (display.getHeight()/2)+50, 200, 80, null);	
	}

	@Override
	public void onClick(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		 
		if (x >= (display.getWidth()/2)-(200/2) && x <= (display.getWidth()/2)-(200/2)+200 &&
				y >= (display.getHeight()/2)+50 && y <= (display.getHeight()/2)+50+60) {

			game.resetPipeGrid();
			display.setScene(1);
		}
		if (x >= (display.getWidth()-55) && x <= (display.getWidth()-55)+50 && y >= 10 && y <= 10+50)
			display.setScene(2);
		
	}
}
