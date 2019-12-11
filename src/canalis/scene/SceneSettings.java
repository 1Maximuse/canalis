package canalis.scene;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
import canalis.objects.Background;
import canalis.objects.ButtonChangeScene;
import canalis.objects.ButtonSetting;
import canalis.objects.SettingOverlay;
import canalis.objects.TextCentered;
import canalis.scene.SceneGame.Difficulty;

public class SceneSettings extends Scene {
	
	private Difficulty diff;
	private SettingOverlay overlay;
	
	public SceneSettings(Game game, Display display) {
		super(game, display);
		diff = game.getDifficulty();
		addSceneObject(new Background(display));
		addSceneObject(new ButtonChangeScene(display, 10, 10, 50, 50, 0, Assets.buttonBack));
		addSceneObject(overlay = new SettingOverlay((display.getWidth()/2)-(200/2)-10, (display.getHeight()/2)-10, diff));
		addSceneObject(new ButtonSetting(display, (display.getWidth()/2)-(200/2), (display.getHeight()/2)-120, Difficulty.EASY, this));
		addSceneObject(new ButtonSetting(display, (display.getWidth()/2)-(200/2), (display.getHeight()/2), Difficulty.MEDIUM, this));
		addSceneObject(new ButtonSetting(display, (display.getWidth()/2)-(200/2), (display.getHeight()/2)+120, Difficulty.HARD, this));
		addSceneObject(new TextCentered(display, 150, Assets.font.deriveFont(60.0f), Assets.colorMain, "Select Difficulty"));		
		addSceneObject(new ButtonChangeScene(display, (display.getWidth()-220), (display.getHeight()-100), 200, 80, 5, Assets.buttonCredits));
	}
	
	public void setDifficulty(Difficulty diff) {
		this.diff = diff;
		overlay.setDifficulty(diff);
		game.setDifficulty(diff);
	}
}