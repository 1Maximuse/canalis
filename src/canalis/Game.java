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
import canalis.scene.MainMenu;
import canalis.scene.Setting;

public class Game implements Runnable {

	private Display display;
	PipeGrid pipegrid;
	Faucet faucet;
	MainMenu mainmenu;
	Setting setting;
	
	@Override
	public void run() {
		display = new Display(640, 480, this);
		
		display.setScene(0);
		mainmenu = new MainMenu(display);
		display.addRenderObject(mainmenu);
		
		display.setScene(1);
		pipegrid = new PipeGrid(100, 100, 3, 5);
		faucet = new Faucet(100 - Pipe.SIZE, 100 - Pipe.SIZE - 10, display);
		display.addRenderObject(pipegrid);
		display.addRenderObject(faucet);
		
		display.setScene(2);
		setting = new Setting(display, this);
		display.addRenderObject(setting);
		
		display.setScene(0);
	}
	
	public void setPipeGrid(int x, int y, int width, int height) {
		display.setScene(1);
		display.clearRenderObject();
		this.pipegrid = new PipeGrid(100, 100, width, height);
		display.addRenderObject(pipegrid);
		display.addRenderObject(faucet);
		display.setScene(0);
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
