package canalis.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
import canalis.objects.Clock;

public class SceneResult extends Scene {
	
	private final Game game;
	private final Clock clock;
	private String time;
	String[] result = {"You Did It !", "Well Done !", "Good Job !"};
	Random rng;
	int random = 0;
	
	public SceneResult(Game game, Display display, Clock clock) {
		super(display);
		this.game = game;
		this.clock = clock;
		rng = new Random();
	}
	
	public void randomize() {
		random = rng.nextInt(3);
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Algerian", Font.PLAIN, 70));
		g.setColor(Color.BLACK);
		g.drawString(result[random], (display.getWidth()/2)-190, 150);
		g.setFont(new Font("Segoe Print", Font.PLAIN, 40));
		
		g.drawString("Time : " + time, (display.getWidth()/2)-150, 300);
//		g.drawString("Time : ", (display.getWidth()/2)-150, 300);
		
		g.drawImage(Assets.retry, (display.getWidth()/3*1)-(200/2), (display.getHeight()/3*2), 200, 80, null);
		g.drawImage(Assets.toMainMenu, (display.getWidth()/3*2)-(200/2), (display.getHeight()/3*2), 200, 80, null);
		
	}
	
	@Override
	public void onClick(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		 
		if (x >= (display.getWidth()/3*1)-(200/2) && x <= (display.getWidth()/3*1)-(200/2)+200 && 
			y >= (display.getHeight()/3*2) && y <= (display.getHeight()/3*2)+80) {
			
			game.resetPipeGrid();
			display.setScene(1);
			clock.reset();
		}
			
		if (x >= (display.getWidth()/3*2)-(200/2) && x <= (display.getWidth()/3*2)-(200/2)+200 && 
			y >= (display.getHeight()/3*2) && y <= (display.getHeight()/3*2)+80) {

			game.resetPipeGrid();
			display.setScene(0);
		}
			
	}

	public void setTime(int minute, int second) {
		time = String.format("%02d:%02d", minute, second);
	}
	
}
