package canalis;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import canalis.Pipe.Type;

public class PipeGrid implements Renderable, Clickable {
	
	private Pipe[][] pipes;
	private int height, width;
	
	public PipeGrid(int height, int width) {
		this.width = width;
		this.height = height;
		pipes = new Pipe[height][width];
		
		Random rand = new Random();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pipes[i][j] = new Pipe(Type.values()[rand.nextInt(2)], rand.nextInt(4), 100+j*Pipe.SIZE, 100+i*Pipe.SIZE);
			}
		}
	}

	@Override
	public void render(Graphics g) {
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
