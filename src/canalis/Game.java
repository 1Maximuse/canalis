package canalis;

import java.awt.EventQueue;

import canalis.objects.Clock;
import canalis.scene.SceneGame;
import canalis.scene.SceneGame.Difficulty;
import canalis.scene.SceneMainMenu;
import canalis.scene.SceneResult;
import canalis.scene.SceneSettings;

public class Game implements Runnable {

	public static final int PADDING = 150;
	public static int GRID_SIZE;
	private Display display;
	
	private SceneGame sceneGame;
	private SceneResult sceneResult;
	private Clock clock;
	
	@Override
	public void run() {
		Assets.loadTextures();
		display = new Display(1024, 768, this);
		GRID_SIZE = Math.min((display.getWidth() - 2*PADDING) / 7, (display.getHeight() - 2*PADDING) / 3);
		
		clock = new Clock(0, 100, display);
		
		display.addScene(new SceneMainMenu(this, display));
		display.addScene(sceneGame = new SceneGame(this, display, Difficulty.EASY, clock));
		display.addScene(new SceneSettings(this, display));
		display.addScene(sceneResult = new SceneResult(this, display));
		
		display.setScene(0);
	}

	public void win() {
		sceneResult.randomize();
		sceneResult.setTime(clock.getMinute(), clock.getSecond());
		display.setScene(3);
	}
	
	public Difficulty getDifficulty() {
		return sceneGame.getDifficulty();
	}
	
	public void setDifficulty(Difficulty diff) {
		sceneGame.setDifficulty(diff);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Game());
	}
}
