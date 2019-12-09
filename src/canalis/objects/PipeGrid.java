package canalis.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import canalis.Assets;
import canalis.Clickable;
import canalis.Display;
import canalis.Game;
import canalis.LevelGenerator;
import canalis.Renderable;
import canalis.objects.Pipe.Face;
import canalis.objects.Pipe.Type;
import canalis.scene.SceneGame;

public class PipeGrid extends GameObject implements Renderable, Clickable {
	
	private SceneGame game;
	private Pipe[][] pipes;
	private LevelGenerator gen;
	private final Display display;
	private ArrayList<Point> order;
	private int height, width;
	private int flowCounter;
	private int flowLimit;
	private int startPos;
	private final Timer flowTimer = new Timer(20, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			doFlow();
			flowCounter++;
			if (flowCounter >= flowLimit) {
				stopFlow();
			}
		}
	});
	
	public PipeGrid(int x, int y, int width, int height, SceneGame game, Display display) {
		this.display = display;
		this.game = game;
		flowCounter = 0;
		flowLimit = -1;
		startPos = 0;
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		pipes = new Pipe[height][width];
		
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
				pipes[i][j] = new Pipe(pipe, rand.nextInt(4), posX+j*Game.GRID_SIZE, posY+i*Game.GRID_SIZE);
			}
		}
	}
	
	private void doFlow() {
		Face from = null;
		if (flowCounter < 11) {
			startPos = flowCounter;
			display.repaint();
			return;
		}
		int curr = (flowCounter-11) / 11;
		if (curr == 0) {
			from = Face.LEFT;
		} else {
			int dx = order.get(curr).x - order.get(curr-1).x;
			int dy = order.get(curr).y - order.get(curr-1).y;
			if (dx == 0 && dy == 1) from = Face.TOP;
			else if (dx == 0 && dy == -1) from = Face.BOTTOM;
			else if (dx == 1 && dy == 0) from = Face.LEFT;
			else if (dx == -1 && dy == 0) from = Face.RIGHT;
		}
		pipes[order.get(curr).y][order.get(curr).x].setFlowing(flowCounter % 11, from);
//		System.out.println("flowing" + flowCounter);
		display.repaint();
	}

	public void startFlow() {
//		System.out.println("trying...");
		order = gen.getOrder(pipes);
		if (order != null) {
			game.startFlow();
			flowLimit = (order.size() + 1) * 11;
//			System.out.println("valid!" + order.size());
			flowTimer.restart();
		}
	}
	
	public void stopFlow() {
//		System.out.println("stopped");
		flowTimer.stop();
		game.stopFlow();
		flowCounter = 0;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.pipeStart[startPos], posX-Game.GRID_SIZE, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
		g.drawImage(Assets.pipeEnd, posX+width*Game.GRID_SIZE, posY+height*Game.GRID_SIZE-Game.GRID_SIZE, Game.GRID_SIZE, Game.GRID_SIZE, null);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pipes[i][j].render(g);
			}
		}
	}

	@Override
	public void onClick(int x, int y) {
		x -= posX;
		y -= posY;
		x /= Game.GRID_SIZE;
		y /= Game.GRID_SIZE;
		pipes[y][x].onClick(x, y);
	}

	@Override
	public boolean isInside(int x, int y) {
		if (x > posX && x < posX + width*Game.GRID_SIZE && y > posY && y < posY + height*Game.GRID_SIZE) return true;
		else return false;
	}

}
