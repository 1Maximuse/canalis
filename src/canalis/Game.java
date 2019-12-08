package canalis;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canalis.objects.BackButton;
import canalis.objects.Clock;
import canalis.objects.Faucet;
import canalis.objects.PipeGrid;
import canalis.scene.SceneMainMenu;
import canalis.scene.SceneResult;
import canalis.scene.SceneSettings;

public class Game implements Runnable {

	public static final int PADDING = 150;
	public static int GRID_SIZE;
	private Display display;
	private PipeGrid pipeGrid;
	private Faucet faucet;
	private SceneMainMenu mainmenu;
	private SceneSettings setting;
	private SceneResult result;
	private Clock clock;
	private int gridX, gridY;
	
	public void tryFlow() {
		pipeGrid.startFlow();
	}
	
	public void startFlow() {
		faucet.setRotating(true);
		clock.pause();
	}
	
	public void stopFlow() {
		faucet.setRotating(false);
		result.randomize();
		result.setTime(clock.getMinute(), clock.getSecond());
		display.setScene(3);
	}
	
	@Override
	public void run() {
		Assets.loadTextures();
		display = new Display(1024, 768, this);
		GRID_SIZE = Math.min((display.getWidth() - 2*PADDING) / 7, (display.getHeight() - 2*PADDING) / 3);
		
		clock = new Clock(0, 100, display);
		
		mainmenu = new SceneMainMenu(display, clock);
		display.addRenderObject(mainmenu, 0);
		
		gridX = 6;
		gridY = 4;
		pipeGrid = new PipeGrid((display.getWidth() - 6*GRID_SIZE) / 2, (display.getHeight() - 4*GRID_SIZE) / 2, 6, 4, this, display);
		faucet = new Faucet((display.getWidth() - 8*GRID_SIZE) / 2, (display.getHeight() - 2*GRID_SIZE) / 2, this, display);
		display.addRenderObject(pipeGrid, 1);
		display.addRenderObject(faucet, 1);
		display.addRenderObject(new BackButton(0, 5, 0, display), 1);
		display.addRenderObject(clock, 1);
		
		setting = new SceneSettings(display, this);
		display.addRenderObject(setting, 2);
		display.addRenderObject(new BackButton(0, 5, 0, display), 2);
		
		result = new SceneResult(this, display, clock);
		display.addRenderObject(result, 3);
		
		display.setScene(0);
	}
	
	public int getGridX() {
		return gridX;
	}
	
	public int getGridY() {
		return gridY;
	}
	
	public void setPipeGrid(int width, int height) {
		gridX = width;
		gridY = height;
		display.clearRenderObject(1);
		GRID_SIZE = Math.min((display.getWidth() - 2*PADDING) / (width + 1), (display.getHeight() - 2*PADDING) / (height-1));
		this.pipeGrid = new PipeGrid((display.getWidth() - width*GRID_SIZE) / 2, (display.getHeight() - height*GRID_SIZE) / 2, width, height, this, display);
		faucet.setPosX((display.getWidth() - (width+2)*GRID_SIZE) / 2);
		faucet.setPosY((display.getHeight() - (height-2)*GRID_SIZE) / 2);
		display.addRenderObject(pipeGrid, 1);
		display.addRenderObject(faucet, 1);
		display.addRenderObject(new BackButton(0, 5, 0, display), 1);
		display.addRenderObject(clock, 1);
		clock.reset();
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
