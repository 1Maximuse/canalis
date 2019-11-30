package canalis;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canalis.objects.Faucet;
import canalis.objects.PipeGrid;

public class Game implements Runnable {

	public static int GRID_SIZE;
	private Display display;
	
	PipeGrid grid;
	Faucet faucet;
	
	public void tryFlow() {
		grid.startFlow();
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
		grid = new PipeGrid(GRID_SIZE, GRID_SIZE, 3, 5, this, display);
		faucet = new Faucet(0, 0, this, display);
		
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
}
