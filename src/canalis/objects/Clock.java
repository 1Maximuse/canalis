package canalis.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import canalis.Display;
import canalis.Renderable;

public class Clock extends GameObject implements Renderable {

	private final Timer timer;
	private int minute;
	private int second;
	
	public Clock(int x, int y, Display display) {
		this.posX = x;
		this.posY = y;
		minute = second = 0;
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				second++;
				if (second >= 60) {
					minute++;
					second = 0;
				}
				display.repaint();
			}
		});
		timer.restart();
	}
	
	public int getMinute() {
		return minute;
	}
	
	public int getSecond() {
		return second;
	}
	
	public void pause() {
		timer.stop();
	}
	
	public void reset() {
		minute = 0;
		second = 0;
		timer.restart();
	}
	
	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Segoe Print", Font.PLAIN, 40));
		g.setColor(Color.BLACK);
		g.drawString(String.format("%02d:%02d", minute, second), posX, posY);
	}
}
