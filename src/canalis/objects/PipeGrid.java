package canalis.objects;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import canalis.Clickable;
import canalis.Game;
import canalis.LevelGenerator;
import canalis.Renderable;
import canalis.objects.Pipe.Type;

public class PipeGrid extends GameObject implements Renderable, Clickable {
	
	private Pipe[][] pipes;
	private LevelGenerator gen;
	private int distance;
	private int height, width;
	private boolean solved;
	private BufferedImage[] texture = new BufferedImage[2];
	
	public PipeGrid(int x, int y, int height, int width) {
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		pipes = new Pipe[height][width];
		distance = 0;
		solved = false;
		
		Random rand = new Random();
		gen = new LevelGenerator(height, width);
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
				if (tiles[i][j] == 1 && pipes[i][j].getOrientation() != 0) distance++;
				else if (tiles[i][j] == 2 && pipes[i][j].getOrientation() != 1) distance++;
				else if (tiles[i][j] == 3 && pipes[i][j].getOrientation() != 0) distance++;
				else if (tiles[i][j] == 4 && pipes[i][j].getOrientation() != 1) distance++;
				else if (tiles[i][j] == 5 && pipes[i][j].getOrientation() != 2) distance++;
				else if (tiles[i][j] == 6 && pipes[i][j].getOrientation() != 3) distance++;
			}
		}
		texture[0] = Game.getTextureAtlas("pipe_start_strip11.png", 128, 128, 0, 0);
		texture[1] = Game.getTexture("pipe_end.png");
	}
	
	public boolean isSolved() {
		return solved;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture[0], posX-Pipe.SIZE, posY, Pipe.SIZE, Pipe.SIZE, null);
		g.drawImage(texture[1], posX+width*Pipe.SIZE, posY+height*Pipe.SIZE-Pipe.SIZE, Pipe.SIZE, Pipe.SIZE, null);
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
		boolean prevcorrect = false;
		if (pipes[y][x].getType() == Type.STRAIGHT && pipes[y][x].getOrientation() == 0 && gen.getTiles()[y][x] == 1
				|| pipes[y][x].getType() == Type.STRAIGHT && pipes[y][x].getOrientation() == 1 && gen.getTiles()[y][x] == 2
				|| pipes[y][x].getType() == Type.BEND && pipes[y][x].getOrientation() == 0 && gen.getTiles()[y][x] == 3
				|| pipes[y][x].getType() == Type.BEND && pipes[y][x].getOrientation() == 1 && gen.getTiles()[y][x] == 4
				|| pipes[y][x].getType() == Type.BEND && pipes[y][x].getOrientation() == 2 && gen.getTiles()[y][x] == 5
				|| pipes[y][x].getType() == Type.BEND && pipes[y][x].getOrientation() == 3 && gen.getTiles()[y][x] == 6) {
			prevcorrect = true;
		}
		pipes[y][x].onClick(e);
		boolean nowcorrect = false;
		if (pipes[y][x].getType() == Type.STRAIGHT && pipes[y][x].getOrientation() == 0 && gen.getTiles()[y][x] == 1
				|| pipes[y][x].getType() == Type.STRAIGHT && pipes[y][x].getOrientation() == 1 && gen.getTiles()[y][x] == 2
				|| pipes[y][x].getType() == Type.BEND && pipes[y][x].getOrientation() == 0 && gen.getTiles()[y][x] == 3
				|| pipes[y][x].getType() == Type.BEND && pipes[y][x].getOrientation() == 1 && gen.getTiles()[y][x] == 4
				|| pipes[y][x].getType() == Type.BEND && pipes[y][x].getOrientation() == 2 && gen.getTiles()[y][x] == 5
				|| pipes[y][x].getType() == Type.BEND && pipes[y][x].getOrientation() == 3 && gen.getTiles()[y][x] == 6) {
			nowcorrect = true;
		}
		if (prevcorrect && !nowcorrect) distance++;
		else if (!prevcorrect && nowcorrect) distance--;
		if (distance == 0) solved = true;
	}

	@Override
	public boolean isInside(int x, int y) {
		if (x > 100 && x < 100 + width*Pipe.SIZE && y > 100 && y < 100 + height*Pipe.SIZE) return true;
		else return false;
	}

}
