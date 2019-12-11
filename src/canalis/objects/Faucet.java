package canalis.objects;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import canalis.Assets;
import canalis.Clickable;
import canalis.Display;
import canalis.Game;
import canalis.Renderable;
import canalis.scene.SceneGame;

public class Faucet extends GameObject implements Renderable, Clickable {
	
	private final Timer timer;
	private final SceneGame game;
	
	private int rotation;
	
	public Faucet(int x, int y, SceneGame game, Display display) {
		this.game = game;
		this.posX = x;
		this.posY = y;
		rotation = 0;
		timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rotation++;
				if (rotation >= 100) {
					timer.stop();
					rotation = 0;
				}
				display.repaint();
			}
		});
	}
	
	public void setRotating(boolean rotate) {
		if (rotate) {
			timer.restart();
		} else {
			timer.stop();
			rotation = 0;
		}
	}

	@Override
	public void onClick(int x, int y) {
		game.tryFlow();
	}

	@Override
	public boolean isInside(int x, int y) {
		if (x >= posX-Game.GRID_SIZE && y >= posY+Game.GRID_SIZE && x <= posX-Game.GRID_SIZE+Game.GRID_SIZE && y <= posY+Game.GRID_SIZE+Game.GRID_SIZE) return true;
		return false;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.faucet[rotation % 8], posX-Game.GRID_SIZE, posY+Game.GRID_SIZE, Game.GRID_SIZE, Game.GRID_SIZE, null);
	}
}
