package canalis.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import canalis.Clickable;
import canalis.Display;
import canalis.Game;
import canalis.Renderable;
import canalis.objects.PipeGrid;

public class Setting implements Renderable, Clickable {
	private BufferedImage[] texture = new BufferedImage[5];
	
	private final Display display;
	private final Game game;
	
	
	public Setting(Display display, Game game) {
		this.display = display;
		this.game = game;
		texture[0] = Game.getTexture("main_menu/GameBackground.png");
		texture[1] = Game.getTexture("main_menu/play.png");
		texture[2] = Game.getTexture("main_menu/play.png");
		texture[3] = Game.getTexture("main_menu/play.png");
//		texture 4 harusnya tombol back
		texture[4] = Game.getTexture("main_menu/cog.png");
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture[0], display.getWidth()-(2507/2), display.getHeight()-(1112/2), 2507, 1112, null);
		
		g.setFont(new Font("Algerian", Font.PLAIN, 70));
		g.setColor(Color.BLACK);
		g.drawString("Game Size", (display.getWidth()/2)-180, 100);
		
		g.drawImage(texture[1], (display.getWidth()/2)-(200/2), (display.getHeight()/2)-100, 200, 60, null);
		g.drawImage(texture[2], (display.getWidth()/2)-(200/2), (display.getHeight()/2), 200, 60, null);
		g.drawImage(texture[3], (display.getWidth()/2)-(200/2), (display.getHeight()/2)+100, 200, 60, null);
		g.drawImage(texture[4], 5, 10, 50, 50, null);
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

	@Override
	public boolean isInside(int x, int y) {
		return true;
	}
}