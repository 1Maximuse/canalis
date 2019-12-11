package canalis.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
import canalis.Renderable;
import canalis.scene.SceneGame.Difficulty;

public class Clock extends GameObject implements Renderable {

	private final Timer timer;
	private int minute;
	private int second;
	private boolean reversed;
	private Display display;
	private Game game;
	
	public Clock(int x, int y, Game game, Display display) {
		this.posX = x;
		this.posY = y;
		this.game = game;
		this.display = display;
		minute = second = 0;
		reversed = false;
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (reversed) {
					second--;
					if (second < 0) {
						minute--;
						if (minute < 0) game.winTA();
						second = 59;
					}
				} else {
					second++;
					if (second >= 60) {
						minute++;
						second = 0;
					}
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
	
	public void unpause() {
		timer.start();
	}
	
	public void pause() {
		timer.stop();
	}
	
	public void addSeconds(int secs) {
		second += secs;
		if (second >= 60) {
			second -= 60;
			minute++;
		}
	}
	
	public void reset() {
		minute = 0;
		second = 0;
		reversed = false;
		timer.restart();
	}
	
	public void resetReverse() {
		reversed = true;
		if (game.getDifficulty() == Difficulty.EASY) {
			minute = 0;
			second = 30;
		}
		else if (game.getDifficulty() == Difficulty.MEDIUM){
			minute = 1;
			second = 0;
		}
		else {
			minute = 1;
			second = 30;
		}
		timer.restart();
	}
	
	@Override
	public void render(Graphics g) {
		g.setFont(Assets.fontSecondary.deriveFont(40.0f));
		g.setColor(Color.BLACK);
		if (display.getCurrentSceneNumber() == 1) {
			g.drawString(String.format("Time : %02d:%02d", minute, second), posX, posY);
		}
		else {
			g.drawString(String.format("Time left : %02d:%02d", minute, second), posX, posY);
		}
	}
}
