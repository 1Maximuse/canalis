package canalis;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import canalis.objects.Faucet;
import canalis.objects.Pipe;
import canalis.objects.PipeGrid;

public class Game implements Runnable {

	private Display display;
	
	PipeGrid grid;
	Faucet faucet;
	
	@Override
	public void run() {
		display = new Display(640, 480, this);
		grid = new PipeGrid(100, 100, 3, 5);
		faucet = new Faucet(100 - Pipe.SIZE, 100 - Pipe.SIZE - 10, display);
		
		display.addRenderObject(grid);
		display.addRenderObject(faucet);
	}
	
	public void mousePressed(MouseEvent e) {
		ArrayList<Renderable> renderObjects = display.getRenderObjects();
		for (Renderable renderObject : renderObjects) {
			if (renderObject instanceof Clickable) {
				Clickable inputObject = (Clickable)renderObject;
				if (inputObject.isInside(e.getX(), e.getY())) {
					inputObject.onClick(e);
				}
			}
		}
		display.repaint();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Game());
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

	public static BufferedImage getTextureAtlas(String path, int sizeX, int sizeY, int posX, int posY) {
		try {
			return ImageIO.read(Game.class.getResource("/textures/" + path)).getSubimage(posX*sizeX, posY*sizeY, sizeX, sizeY);
		} catch (Exception e) {
			System.err.println("Cannot get image texture for " + path + "!");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
