package canalis;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import canalis.Pipe.Type;

public class PipeGrid implements Renderable, Clickable {
	
	private Pipe[][] pipes;
	private int height, width;
	private BufferedImage[] texture = new BufferedImage[2];
	
	public PipeGrid(int height, int width) {
		this.width = width;
		this.height = height;
		pipes = new Pipe[height][width];
		
		Random rand = new Random();
		LevelGenerator gen = new LevelGenerator(height, width);
		int[][] tiles = gen.getTiles();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Type pipe = null;
				if (tiles[i][j] == 1 || tiles[i][j] == 2) {
					pipe = Type.STRAIGHT;
				} else if (tiles[i][j] >= 3 && tiles[i][j] <= 6) {
					pipe = Type.BEND;
				} else {
					pipe = Type.values()[rand.nextInt(2)];
				}
				pipes[i][j] = new Pipe(pipe, rand.nextInt(4), 100+j*Pipe.SIZE, 100+i*Pipe.SIZE);
			}
		}
		texture[0] = Game.getTextureAtlas("pipe_start_strip11.png", 128, 128, 0, 0);
		texture[1] = Game.getTexture("pipe_end.png");
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture[0], 100-Pipe.SIZE, 100, Pipe.SIZE, Pipe.SIZE, null);
		g.drawImage(texture[1], 100+width*Pipe.SIZE, 100+height*Pipe.SIZE-Pipe.SIZE, Pipe.SIZE, Pipe.SIZE, null);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pipes[i][j].render(g);
			}
		}
	}

	@Override
	public void onClick(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		x -= 100;
		y -= 100;
		x /= Pipe.SIZE;
		y /= Pipe.SIZE;
		pipes[y][x].onClick(e);
	}

	@Override
	public boolean isInside(int x, int y) {
		if (x > 100 && x < 100 + width*Pipe.SIZE && y > 100 && y < 100 + height*Pipe.SIZE) return true;
		else return false;
	}

}
