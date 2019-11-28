package canalis.objects;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import canalis.Clickable;
import canalis.Display;
import canalis.Game;
import canalis.Renderable;

public class Faucet extends GameObject implements Renderable, Clickable {
	
	private final BufferedImage[] texture;
	private final Timer timer;
	
	private boolean rotating;
	private int rotation;
	
	public Faucet(int x, int y, Display display) {
		rotating = false;
		texture = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			texture[i] = Game.getTextureAtlas("items/wheel.png", 128, 128, i, 0);
		}
		this.posX = x;
		this.posY = y;
		rotation = 0;
		timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rotation++;
				if (rotation >= texture.length) {
					rotation -= texture.length;
					if (!rotating) timer.stop();
				}
				display.repaint();
			}
		});
	}

	@Override
	public void onClick(MouseEvent e) {
		rotating = !rotating;
		if (rotating) timer.restart();
	}

	@Override
	public boolean isInside(int x, int y) {
		if (x >= posX && y >= posY && x <= posX + Pipe.SIZE && y <= posY + Pipe.SIZE) return true;
		return false;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture[rotation], posX, posY, Pipe.SIZE, Pipe.SIZE, null);
	}
}
