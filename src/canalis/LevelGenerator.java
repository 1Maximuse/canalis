package canalis;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import canalis.objects.Pipe;
import canalis.objects.Pipe.Face;

public class LevelGenerator {
	
	private int width, height;
	private boolean[][] visited;
	private int[][] path;
	private int[][] tiles;
	
	public LevelGenerator(int height, int width) {
		this.width = width;
		this.height = height;
		visited = new boolean[height][width];
		path = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				visited[i][j] = false;
				path[i][j] = -1;
			}
		}
		generate(0, 0, 0);
		tiles = createTiles(0, 0);
	}
	
	public int[][] getTiles() {
		return tiles;
	}
	
	private int[][] createTiles(int x, int y) {
		int[][] tiles = new int[height][width];
		Face prev = null, next = null;
		int nextX = -1, nextY = -1;
		int curr = path[y][x];
		if (y-1 >= 0) {
			if (path[y-1][x] == curr+1) {
				next = Face.TOP;
				nextX = x; nextY = y-1;
			}
			else if (path[y-1][x] == curr-1) prev = Face.TOP;
		}
		if (x-1 >= 0) {
			if (path[y][x-1] == curr+1) {
				next = Face.LEFT;
				nextX = x-1; nextY = y;
			}
			else if (path[y][x-1] == curr-1) prev = Face.LEFT;
		}
		if (y+1 < height) {
			if (path[y+1][x] == curr+1) {
				next = Face.BOTTOM;
				nextX = x; nextY = y+1;
			}
			else if (path[y+1][x] == curr-1) prev = Face.BOTTOM;
		}
		if (x+1 < width) {
			if (path[y][x+1] == curr+1) {
				next = Face.RIGHT;
				nextX = x+1; nextY = y;
			}
			else if (path[y][x+1] == curr-1) prev = Face.RIGHT;
		}
		if (nextX != -1 && nextY != -1) tiles = createTiles(nextX, nextY);
		if (x == 0 && y == 0) prev = Face.LEFT;
		if (x == width-1 && y == height-1) next = Face.RIGHT;
		if (prev == Face.LEFT && next == Face.RIGHT || prev == Face.RIGHT && next == Face.LEFT) tiles[y][x] = 1;
		else if (prev == Face.TOP && next == Face.BOTTOM || prev == Face.BOTTOM && next == Face.TOP) tiles[y][x] = 2;
		else if (prev == Face.TOP && next == Face.RIGHT || prev == Face.RIGHT && next == Face.TOP) tiles[y][x] = 3;
		else if (prev == Face.RIGHT && next == Face.BOTTOM || prev == Face.BOTTOM && next == Face.RIGHT) tiles[y][x] = 4;
		else if (prev == Face.BOTTOM && next == Face.LEFT || prev == Face.LEFT && next == Face.BOTTOM) tiles[y][x] = 5;
		else if (prev == Face.LEFT && next == Face.TOP || prev == Face.TOP && next == Face.LEFT) tiles[y][x] = 6;
		return tiles;
	}

	private int generate(int x, int y, int depth) {
		visited[y][x] = true;
		if (x == width-1 && y == height-1) {
			path[y][x] = depth;
			return 1;
		}
		int sum = 0;
		ArrayList<int[]> neighbor = new ArrayList<int[]>();
		if (y-1 >= 0 && !visited[y-1][x]) neighbor.add(new int[] {y-1, x});
		if (x-1 >= 0 && !visited[y][x-1]) neighbor.add(new int[] {y, x-1});
		if (y+1 < height && !visited[y+1][x]) neighbor.add(new int[] {y+1, x});
		if (x+1 < width && !visited[y][x+1]) neighbor.add(new int[] {y, x+1});
		Collections.shuffle(neighbor);
		for (int[] next : neighbor) {
			if (generate(next[1], next[0], depth + 1) > 0) {
				sum++;
				break;
			}
		}
		if (sum > 0) {
			path[y][x] = depth;
			return 1;
		}
		return 0;
	}
	
	public ArrayList<Point> getOrder(Pipe[][] pipes) {
		if (!pipes[0][0].getAccess(Face.LEFT)) return null;
		if (!pipes[height-1][width-1].getAccess(Face.RIGHT)) return null;
		ArrayList<Point> p = new ArrayList<Point>();
		int x = 0;
		int y = 0;
		Face prev = Face.LEFT;
		while (x != width-1 || y != height-1)  {
			p.add(new Point(x, y));
			if (prev != Face.TOP && pipes[y][x].getAccess(Face.TOP) && y > 0 && pipes[y-1][x].getAccess(Face.BOTTOM)) {
				prev = Face.BOTTOM;
				y--;
			} else if (prev != Face.RIGHT && pipes[y][x].getAccess(Face.RIGHT) && x < width-1 && pipes[y][x+1].getAccess(Face.LEFT)) {
				prev = Face.LEFT;
				x++;
			} else if (prev != Face.BOTTOM && pipes[y][x].getAccess(Face.BOTTOM) && y < height-1 && pipes[y+1][x].getAccess(Face.TOP)) {
				prev = Face.TOP;
				y++;
			} else if (prev != Face.LEFT && pipes[y][x].getAccess(Face.LEFT) && x > 0 && pipes[y][x-1].getAccess(Face.RIGHT)) {
				prev = Face.RIGHT;
				x--;
			} else {
				return null;
			}
		}
		p.add(new Point(width-1, height-1));
		return p;
	}
}
