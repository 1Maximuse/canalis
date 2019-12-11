package canalis.scene;

import canalis.Display;
import canalis.Game;
import canalis.objects.Clock;

public class SceneGameTimeAttack extends SceneGame {
	private int solved;
	
	public SceneGameTimeAttack(Game game, Display display, Difficulty diff, Clock clock) {
		super(game, display, diff, clock);
		solved = 0;
	}
	
	@Override
	public void newGrid(boolean reset) {
		super.newGrid(false);
		if (reset) {
			clock.resetReverse();
			solved = 0;
		}
	}

	@Override
	public void stopFlow() {
		faucet.setRotating(false);
		
		switch (currentDifficulty) {
		case EASY:
			clock.addSeconds(10);
			break;
		case MEDIUM:
			clock.addSeconds(15);
			break;
		case HARD:
			clock.addSeconds(25);
			break;
		}
		

		solved++;
		newGrid(false);
		clock.unpause();
	}
	
	public int getSolved() {
		return solved;
	}
}
