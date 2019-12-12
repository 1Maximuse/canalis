package canalis.scene;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
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
			Game.GRID_SIZE = Math.min((display.getWidth() - 2*Game.PADDING) / 10, (display.getHeight() - 2*Game.PADDING) / 5);
			pipeGrid = new PipeGrid((display.getWidth() - 9*Game.GRID_SIZE) / 2, (display.getHeight() - 6*Game.GRID_SIZE) / 2, 9, 6, this, display);
			faucet.setPosX((display.getWidth() - 9*Game.GRID_SIZE) / 2);
			faucet.setPosY((display.getHeight() - 6*Game.GRID_SIZE) / 2);
			break;
		case HARD:
			Game.GRID_SIZE = Math.min((display.getWidth() - 2*Game.PADDING) / 13, (display.getHeight() - 2*Game.PADDING) / 8);
			pipeGrid = new PipeGrid((display.getWidth() - 12*Game.GRID_SIZE) / 2, (display.getHeight() - 9*Game.GRID_SIZE) / 2, 12, 9, this, display);
			faucet.setPosX((display.getWidth() - 12*Game.GRID_SIZE) / 2);
			faucet.setPosY((display.getHeight() - 9*Game.GRID_SIZE) / 2);
			break;
		}

		addSceneObject(new BackgroundInGame());
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
