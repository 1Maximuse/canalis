package canalis.scene;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
import canalis.objects.ButtonChangeScene;

public class SceneMainMenu extends Scene {
	
	public SceneMainMenu(Game game, Display display) {
		super(game, display);
		addSceneObject(new ButtonChangeScene(display, (display.getWidth()/2)-(200/2), (display.getHeight()/2)+50, 200, 80, 1, Assets.playButton));
		addSceneObject(new ButtonChangeScene(display, (display.getWidth()-55), 10, 50, 50, 2, Assets.options));
	}
}
