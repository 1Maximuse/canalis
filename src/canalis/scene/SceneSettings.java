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
		g.drawString("Game Size", (display.getWidth()/2)-180, 100);
		
		g.drawImage(Assets.playButton, (display.getWidth()/2)-(200/2), (display.getHeight()/2)-100, 200, 60, null);
		g.drawImage(Assets.playButton, (display.getWidth()/2)-(200/2), (display.getHeight()/2), 200, 60, null);
		g.drawImage(Assets.playButton, (display.getWidth()/2)-(200/2), (display.getHeight()/2)+100, 200, 60, null);
		g.drawImage(Assets.options, 5, 10, 50, 50, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		 
		if (x >= 5 && x <= 5+50 && y >= 10 && y <= 10+50)
			display.setScene(0);
		if (x >= (display.getWidth()/2)-(200/2) && x <= (display.getWidth()/2)-(200/2)+200 &&
			y >= (display.getHeight()/2)-100 && y <= (display.getHeight()/2)-100+60) {
			game.setPipeGrid(100, 100, 3, 5);
			display.setScene(0);
		}
		if (x >= (display.getWidth()/2)-(200/2) && x <= (display.getWidth()/2)-(200/2)+200 &&
			y >= (display.getHeight()/2) && y <= (display.getHeight()/2)+60) {
			game.setPipeGrid(100, 100, 5, 7);
			display.setScene(0);
		}
		if (x >= (display.getWidth()/2)-(200/2) && x <= (display.getWidth()/2)-(200/2)+200 &&
			y >= (display.getHeight()/2)+100 && y <= (display.getHeight()/2)+100+60) {
			game.setPipeGrid(100, 100, 7, 10);
			display.setScene(0);
			}
	}
}