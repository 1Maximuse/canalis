package canalis;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canalis.objects.Faucet;
import canalis.objects.Pipe;
import canalis.objects.PipeGrid;
import canalis.scene.MainMenu;
import canalis.scene.Setting;

public class Game implements Runnable {

	public static int GRID_SIZE;
	private Display display;
	PipeGrid pipegrid;
	Faucet faucet;
	MainMenu mainmenu;
	Setting setting;
	
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
		pipegrid = new PipeGrid(GRID_SIZE, GRID_SIZE, 3, 5, this, display);
		faucet = new Faucet(0, 0, this, display);
		display.setScene(0);
		mainmenu = new MainMenu(display);
		display.addRenderObject(mainmenu);
		
		display.setScene(1);
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
}
