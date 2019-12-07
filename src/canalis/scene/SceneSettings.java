package canalis.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import canalis.Assets;
import canalis.Display;
import canalis.Game;

public class SceneSettings extends Scene {
	
	private final Game game;
	private int selectedx = (display.getWidth()/2)-(200/2)-10;
	private int selectedy = (display.getHeight()/2)-120-10;
	
	public SceneSettings(Display display, Game game) {
		super(display);
		this.game = game;
		
	}

	@Override
	public void render(Graphics g) {
		int bgPadding = 100;
		g.drawImage(Assets.background, -bgPadding, -bgPadding, 2*bgPadding + display.getWidth(), 2*bgPadding + display.getHeight(), null);
		
		g.setFont(new Font("Algerian", Font.PLAIN, 70));
		g.setColor(Color.BLACK);
		g.drawString("Difficulty", (display.getWidth()/2)-190, 150);
		g.setColor(Color.YELLOW);
		g.fillRect(selectedx, selectedy, 220, 100);
		g.drawImage(Assets.beginnerButton, (display.getWidth()/2)-(200/2), (display.getHeight()/2)-120, 200, 80, null);
		g.drawImage(Assets.intermediateButton, (display.getWidth()/2)-(200/2), (display.getHeight()/2), 200, 80, null);
		g.drawImage(Assets.masterButton, (display.getWidth()/2)-(200/2), (display.getHeight()/2)+120, 200, 80, null);
		g.drawImage(Assets.back, 5, 10, 50, 50, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		 
		if (x >= 5 && x <= 5+50 && y >= 10 && y <= 10+50)
			display.setScene(0);
		if (x >= (display.getWidth()/2)-(200/2) && x <= (display.getWidth()/2)-(200/2)+200 &&
			y >= (display.getHeight()/2)-100 && y <= (display.getHeight()/2)-100+60) {
			game.setPipeGrid(6, 4); 
			selectedy = (display.getHeight()/2)-120-10;
		}
		if (x >= (display.getWidth()/2)-(200/2) && x <= (display.getWidth()/2)-(200/2)+200 &&
			y >= (display.getHeight()/2) && y <= (display.getHeight()/2)+60) {
			game.setPipeGrid(8, 5);
			selectedy = (display.getHeight()/2)-10;
		}
		if (x >= (display.getWidth()/2)-(200/2) && x <= (display.getWidth()/2)-(200/2)+200 &&
			y >= (display.getHeight()/2)+100 && y <= (display.getHeight()/2)+100+60) {
			game.setPipeGrid(10, 7);
			selectedy = (display.getHeight()/2)+120-10;
			}
	}
}