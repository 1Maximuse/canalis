package canalis;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canalis.objects.Faucet;
import canalis.objects.PipeGrid;
import canalis.scene.SceneMainMenu;
import canalis.scene.SceneSettings;

public class Game implements Runnable {

	public static final int PADDING = 100;
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
		Assets.loadTextures();
		display = new Display(1280, 720, this);
		GRID_SIZE = Math.min((display.getWidth() - 2*PADDING) / 7, (display.getHeight() - 2*PADDING) / 3);
		
		mainmenu = new SceneMainMenu(display);
		display.addRenderObject(mainmenu, 0);

		pipeGrid = new PipeGrid((display.getWidth() - 5*GRID_SIZE) / 2, (display.getHeight() - 3*GRID_SIZE) / 2, 5, 3, this, display);
		faucet = new Faucet(0, 0, this, display);
		display.addRenderObject(pipeGrid, 1);
		display.addRenderObject(faucet, 1);
		
		setting = new SceneSettings(display, this);
		display.addRenderObject(setting, 2);
		
		display.setScene(0);
	}
	
	public void setPipeGrid(int width, int height) {
		display.clearRenderObject(1);
		GRID_SIZE = Math.min((display.getWidth() - 2*PADDING) / (width + 2), (display.getHeight() - 2*PADDING) / height);
		this.pipeGrid = new PipeGrid((display.getWidth() - width*GRID_SIZE) / 2, (display.getHeight() - height*GRID_SIZE) / 2, width, height, this, display);
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
