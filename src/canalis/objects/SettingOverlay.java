package canalis.objects;

import java.awt.Color;
import java.awt.Graphics;

import canalis.Renderable;
import canalis.scene.SceneGame.Difficulty;

public class SettingOverlay extends GameObject implements Renderable {
	
	private Difficulty diff;
	
	public SettingOverlay(int x, int y, Difficulty diff) {
		this.posX = x;
		this.posY = y;
		this.diff = diff;
	}
	
	public void setDifficulty(Difficulty diff) {
		this.diff = diff;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		switch (diff) {
		case EASY:
			g.fillRect(posX, posY-120, 220, 100);
			break;
		case MEDIUM:
			g.fillRect(posX, posY, 220, 100);
			break;
		case HARD:
			g.fillRect(posX, posY+120, 220, 100);
			break;
		}
	}
}
