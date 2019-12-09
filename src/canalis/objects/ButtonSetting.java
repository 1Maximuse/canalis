package canalis.objects;

import canalis.Assets;
import canalis.Display;
import canalis.scene.SceneSettings;
import canalis.scene.SceneGame.Difficulty;

public class ButtonSetting extends Button {
	
	private final SceneSettings settings;
	private final Difficulty diff;

	public ButtonSetting(Display display, int x, int y, Difficulty diff, SceneSettings settings) {
		super(display, x, y, 200, 80, diff == Difficulty.EASY ? Assets.beginnerButton : diff == Difficulty.MEDIUM ? Assets.intermediateButton : Assets.masterButton);
		this.diff = diff;
		this.settings = settings;
	}

	@Override
	public void onClick(int x, int y) {
		settings.setDifficulty(diff);
	}
}
