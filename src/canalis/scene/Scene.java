package canalis.scene;

import java.util.ArrayList;

import canalis.Display;
import canalis.Game;
import canalis.Renderable;

public abstract class Scene {
	
	protected final Game game;
	protected final Display display;
	private ArrayList<Renderable> sceneObjects;
	
	public Scene(Game game, Display display) {
		this.game = game;
		this.display = display;
		sceneObjects = new ArrayList<Renderable>();
	}
	
	public ArrayList<Renderable> getSceneObjects() {
		return sceneObjects;
	}
	
	public void clearSceneObjects() {
		sceneObjects.clear();
	}
	
	public void addSceneObject(Renderable object) {
		sceneObjects.add(object);
	}
}
