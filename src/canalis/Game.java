package canalis;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canalis.objects.Faucet;
import canalis.objects.PipeGrid;
import canalis.scene.SceneMainMenu;
import canalis.scene.SceneSettings;

public class Game implements Runnable {

	public static int GRID_SIZE;
	private Display display;
	PipeGrid pipeGrid;
	Faucet faucet;
	SceneMainMenu mainmenu;
	SceneSettings setting;
	
	public void tryFlow() {
		pipeGrid.startFlow();
	}
	
	public void startFlow() {
		faucet.setRotating(true);
	}
	
	public void stopFlow() {
		faucet.setRotating(false);
	}
	
	@Override
	public void run() {
		GRID_SIZE = 1280 / 10;
		Assets.loadTextures();
		display = new Display(1280, 720, this);
		
		mainmenu = new SceneMainMenu(display);
		display.addRenderObject(mainmenu, 0);

		pipeGrid = new PipeGrid(GRID_SIZE, GRID_SIZE, 3, 5, this, display);
		faucet = new Faucet(0, 0, this, display);
		display.addRenderObject(pipeGrid, 1);
		display.addRenderObject(faucet, 1);
		
		setting = new SceneSettings(display, this);
		display.addRenderObject(setting, 2);
		
		display.setScene(0);
	}
	
	public void setPipeGrid(int x, int y, int height, int width) {
		display.clearRenderObject(1);
		GRID_SIZE = 800 / Math.max(width, height);
		this.pipeGrid = new PipeGrid(GRID_SIZE, GRID_SIZE, height, width, this, display);
		display.addRenderObject(pipeGrid, 1);
		display.addRenderObject(faucet, 1);
		display.setScene(0);
	}
	
	public void mousePressed(MouseEvent e) {
		ArrayList<Renderable> renderObjects = display.getRenderObjects(display.getCurrentScene());
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
}
