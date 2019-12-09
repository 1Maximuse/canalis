package canalis.objects;

import java.awt.image.BufferedImage;

import canalis.Display;
import canalis.scene.SceneGame;

public class ButtonChangeScene extends Button {
	
	private int targetScene;
	
	public ButtonChangeScene(Display display, int x, int y, int width, int height, int targetScene, BufferedImage texture) {
		super(display, x, y, width, height, texture);
		this.targetScene = targetScene;
	}

	@Override
	public void onClick(int x, int y) {
		display.setScene(targetScene);
		if (targetScene == 1) {
			SceneGame gameScene = (SceneGame) display.getCurrentScene();
			gameScene.newGrid();
		}
	}
}
