package canalis.scene;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
import canalis.objects.Background;
import canalis.objects.BackgroundInGame;
import canalis.objects.ButtonChangeScene;
import canalis.objects.Clock;
import canalis.objects.Faucet;
import canalis.objects.PipeGrid;

public class SceneGame extends Scene {
	
	protected Faucet faucet;
	protected ButtonChangeScene back;
	protected PipeGrid pipeGrid;
	protected Clock clock;
	protected Difficulty currentDifficulty;

	public SceneGame(Game game, Display display, Difficulty diff, Clock clock) {
		super(game, display);
		currentDifficulty = diff;
		this.clock = clock;
		faucet = new Faucet((display.getWidth() - 8*Game.GRID_SIZE) / 2, (display.getHeight() - 2*Game.GRID_SIZE) / 2, this, display);
		back = new ButtonChangeScene(display, 10, 10, 50, 50, 0, Assets.buttonBack);
		newGrid(true);
	}
	
	public void setDifficulty(Difficulty diff) {
		this.currentDifficulty = diff;
		newGrid(true);
	}
	
	public Difficulty getDifficulty() {
		return currentDifficulty;
	}
	
	public void newGrid(boolean reset) {
		clearSceneObjects();
		switch (currentDifficulty) {
		case EASY:
			Game.GRID_SIZE = Math.min((display.getWidth() - 2*Game.PADDING) / 7, (display.getHeight() - 2*Game.PADDING) / 3);
			pipeGrid = new PipeGrid((display.getWidth() - 6*Game.GRID_SIZE) / 2, (display.getHeight() - 4*Game.GRID_SIZE) / 2, 6, 4, this, display);
			faucet.setPosX((display.getWidth() - 6*Game.GRID_SIZE) / 2);
			faucet.setPosY((display.getHeight() - 4*Game.GRID_SIZE) / 2);
			break;
		case MEDIUM:
			Game.GRID_SIZE = Math.min((display.getWidth() - 2*Game.PADDING) / 9, (display.getHeight() - 2*Game.PADDING) / 4);
			pipeGrid = new PipeGrid((display.getWidth() - 8*Game.GRID_SIZE) / 2, (display.getHeight() - 5*Game.GRID_SIZE) / 2, 8, 5, this, display);
			faucet.setPosX((display.getWidth() - 8*Game.GRID_SIZE) / 2);
			faucet.setPosY((display.getHeight() - 5*Game.GRID_SIZE) / 2);
			break;
		case HARD:
			Game.GRID_SIZE = Math.min((display.getWidth() - 2*Game.PADDING) / 11, (display.getHeight() - 2*Game.PADDING) / 6);
			pipeGrid = new PipeGrid((display.getWidth() - 10*Game.GRID_SIZE) / 2, (display.getHeight() - 7*Game.GRID_SIZE) / 2, 10, 7, this, display);
			faucet.setPosX((display.getWidth() - 10*Game.GRID_SIZE) / 2);
			faucet.setPosY((display.getHeight() - 7*Game.GRID_SIZE) / 2);
			break;
		}

		addSceneObject(new BackgroundInGame(display));
		addSceneObject(pipeGrid);
		addSceneObject(faucet);
		addSceneObject(back);
		addSceneObject(clock);
		if (reset) clock.reset();
	}
	
	public void tryFlow() {
		pipeGrid.startFlow();
	}
	
	public void startFlow() {
		faucet.setRotating(true);
		clock.pause();
	}
	
	public void stopFlow() {
		faucet.setRotating(false);
		game.win();
	}
	
	public enum Difficulty {
		EASY, MEDIUM, HARD
	}
}
