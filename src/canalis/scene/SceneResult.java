package canalis.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import canalis.Assets;
import canalis.Display;

public class SceneResult extends Scene {
	String[] result = {"You Did It !", "Well Done !", "Good Job !"};
	Random rng;
	int random = 0;
	
	public SceneResult(Display display) {
		super(display);
		rng = new Random();
	}

	@Override
	public void render(Graphics g) {
		random = rng.nextInt(3);
		g.setFont(new Font("Algerian", Font.PLAIN, 70));
		g.setColor(Color.BLACK);
		g.drawString(result[random], (display.getWidth()/2)-190, 150);
		g.setFont(new Font("Segoe Print", Font.PLAIN, 40));
		
//		g.drawString("Time : " + time, (display.getWidth()/2)-150, 300);
		g.drawString("Time : ", (display.getWidth()/2)-150, 300);
		
		g.drawImage(Assets.retry, (display.getWidth()/3*1)-(200/2), (display.getHeight()/3*2), 200, 80, null);
		g.drawImage(Assets.toMainMenu, (display.getWidth()/3*2)-(200/2), (display.getHeight()/3*2), 200, 80, null);
		
	}
	
	@Override
	public void onClick(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		 
		if (x >= (display.getWidth()/3*1)-(200/2) && x <= (display.getWidth()/3*1)-(200/2)+200 && 
			y >= (display.getHeight()/3*2) && y <= (display.getHeight()/3*2)+80)
			display.setScene(1);
		if (x >= (display.getWidth()/3*2)-(200/2) && x <= (display.getWidth()/3*2)-(200/2)+200 && 
			y >= (display.getHeight()/3*2) && y <= (display.getHeight()/3*2)+80)
			display.setScene(0);
			
	}
	
}
