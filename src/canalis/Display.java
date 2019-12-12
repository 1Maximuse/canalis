package canalis;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import canalis.scene.Scene;

@SuppressWarnings("serial")
public class Display extends JPanel {
	
	/**
	 *      Scene 0: Main Menu
	 * <br> Scene 1: Game
	 * <br> Scene 2: Settings
	 * <br> Scene 3: Result
	 * <br> Scene 4: GameTimeAttack
	 * <br> Scene 5: Credits
	 */
	private ArrayList<Scene> scenes;
	private int currentScene = 0;
	private final int width;
	private final int height;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Renderable obj : scenes.get(currentScene).getSceneObjects()) {
			obj.render(g);
		}
	}
	
	public int addScene(Scene scene) {
		scenes.add(scene);
		return scenes.size()-1;
	}
	
	public Scene getCurrentScene() {
		return scenes.get(currentScene);
	}
	
	public int getCurrentSceneNumber() {
		return currentScene;
	}

	public void setScene(int scene) {
		this.currentScene = scene;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void mousePressed(int x, int y) {
		for (Renderable renderObject : scenes.get(currentScene).getSceneObjects()) {
			if (renderObject instanceof Clickable) {
				Clickable inputObject = (Clickable)renderObject;
				if (inputObject.isInside(x, y)) {
					inputObject.onClick(x, y);
				}
			}
		}
		repaint();
	}

	public Display(int width, int height, Game game) {
		this.width = width;
		this.height = height;
		currentScene = 0;
		setPreferredSize(new Dimension(width, height));
		scenes = new ArrayList<Scene>();
		
		MouseHandler handler = new MouseHandler(this);
		addMouseListener(handler);
		addMouseMotionListener(handler);
		addMouseWheelListener(handler);
		
		JFrame frame = new JFrame("Canalis");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

}
