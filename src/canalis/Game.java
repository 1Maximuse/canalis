package canalis;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Game implements Runnable {

	private boolean isRunning;
	private Display display;
	
	PipeGrid grid;
	
	private void update(double deltaTime) {
		
	}
	
	private void render() {
		display.repaint();
	}
	
	public Game() {
		isRunning = true;
		display = new Display(640, 480, this);
		
		grid = new PipeGrid(3, 5);
		display.addRenderObject(grid);
	}
	
	public void mouseClicked(MouseEvent e) {
		ArrayList<Renderable> renderObjects = display.getRenderObjects();
		for (Renderable renderObject : renderObjects) {
			if (renderObject instanceof Clickable) {
				Clickable inputObject = (Clickable)renderObject;
				if (inputObject.isInside(e.getX(), e.getY())) {
					inputObject.onClick(e);
				}
			}
		}
	}
	

	@Override
	public void run() {
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		
		long oldTime = System.nanoTime();
		long fpsTime = 0;
		int fps = 0;
		
		while (isRunning) {
			long currTime = System.nanoTime();
			long updateLength = currTime - oldTime;
			oldTime = currTime;
			double deltaTime = updateLength / (double)OPTIMAL_TIME;
			
			fpsTime += updateLength;
			fps++;
			
			if (fpsTime >= 1000000000) {
				System.out.println("FPS: " + fps);
				fpsTime = 0;
				fps = 0;
			}
			
			update(deltaTime);
			render();
			
			try {
				Thread.sleep((OPTIMAL_TIME - System.nanoTime() + oldTime) / 1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				
			}
		}
	}
	
	public static BufferedImage getTexture(String path) {
		try {
			return ImageIO.read(Game.class.getResource("/textures/" + path));
		} catch (IOException e) {
			System.err.println("Cannot get image texture for " + path + "!");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	public static void main(String[] args) {
		Thread game = new Thread(new Game());
		game.start();
	}
}
