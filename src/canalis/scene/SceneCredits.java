package canalis.scene;

import canalis.Assets;
import canalis.Display;
import canalis.Game;
import canalis.objects.Background;
import canalis.objects.ButtonChangeScene;
import canalis.objects.Logo;
import canalis.objects.TextCentered;

public class SceneCredits extends Scene {

	public SceneCredits(Game game, Display display) {
		super(game, display);
		addSceneObject(new Background(display));
		addSceneObject(new Logo(display));
		addSceneObject(new TextCentered(display, 100, Assets.font.deriveFont(60.0f), Assets.colorMain, "Credits"));
		addSceneObject(new TextCentered(display, 400, Assets.font.deriveFont(60.0f), Assets.colorMain, "A game by 'RIFT'"));
		
		addSceneObject(new TextCentered(display, 570, Assets.font.deriveFont(60.0f), Assets.colorMain, "Our Team :"));
		addSceneObject(new TextCentered(display, 640, Assets.font.deriveFont(60.0f), Assets.colorMain, "Emmanuel Maximus Yohanes"));
		addSceneObject(new TextCentered(display, 710, Assets.font.deriveFont(60.0f), Assets.colorMain, "Martin William"));
		
		addSceneObject(new ButtonChangeScene(display, 10, 10, 50, 50, 2, Assets.buttonBack));
	}
}
