package canalis.scene;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
import canalis.objects.ButtonChangeScene;
import canalis.objects.ButtonSetting;
import canalis.objects.SettingOverlay;
import canalis.scene.SceneGame.Difficulty;

public class SceneSettings extends Scene {
	
	private Difficulty diff;
	private SettingOverlay overlay;
	
	public SceneSettings(Game game, Display display) {
		super(game, display);
		diff = game.getDifficulty();
		addSceneObject(new ButtonChangeScene(display, 10, 10, 50, 50, 0, Assets.back));
		addSceneObject(overlay = new SettingOverlay((display.getWidth()/2)-(200/2)-10, (display.getHeight()/2)-10, diff));
		addSceneObject(new ButtonSetting(display, (display.getWidth()/2)-(200/2), (display.getHeight()/2)-120, Difficulty.EASY, this));
		addSceneObject(new ButtonSetting(display, (display.getWidth()/2)-(200/2), display.getHeight()/2, Difficulty.MEDIUM, this));
		addSceneObject(new ButtonSetting(display, (display.getWidth()/2)-(200/2), (display.getHeight()/2)+120, Difficulty.HARD, this));
	}
	
	public void setDifficulty(Difficulty diff) {
		this.diff = diff;
		overlay.setDifficulty(diff);
		game.setDifficulty(diff);
	}
}