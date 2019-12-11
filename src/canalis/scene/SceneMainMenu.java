package canalis.scene;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
import canalis.objects.Background;
import canalis.objects.ButtonChangeScene;
import canalis.objects.Logo;

public class SceneMainMenu extends Scene {
	
	public SceneMainMenu(Game game, Display display) {
		super(game, display);
		addSceneObject(new Background(display));
		addSceneObject(new Logo(display));
		addSceneObject(new ButtonChangeScene(display, (display.getWidth()/2)-(200/2), (display.getHeight()/2)+50, 200, 80, 1, Assets.playButton));
		addSceneObject(new ButtonChangeScene(display, 0, (display.getHeight()/2)+50, 200, 80, 4, Assets.playButton));
		addSceneObject(new ButtonChangeScene(display, (display.getWidth()-55), 10, 50, 50, 2, Assets.options));
	}
}
