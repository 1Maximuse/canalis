package canalis;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel {
	
	/**
	 * Scene 0: Main Menu
	 * Scene 1: Game
	 * Scene 2: Settings
	 * Scene 3: Result
	 */
	private ArrayList<Renderable>[] renderObjects;
	private int currentScene = 0;
	private final int width;
	private final int height;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Renderable obj : renderObjects[currentScene]) {
			obj.render(g);
		}
	}
	
	public ArrayList<Renderable> getRenderObjects(int scene) {
		return renderObjects[scene];
	}
	
	public void clearRenderObject(int scene) {
		renderObjects[scene].clear();
	}
	
	public void addRenderObject(Renderable obj, int scene) {
		renderObjects[scene].add(obj);
	}
	
	public int getCurrentScene() {
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

	@SuppressWarnings("unchecked")
	public Display(int width, int height, Game game) {
		this.width = width;
		this.height = height;
		currentScene = 0;
		setPreferredSize(new Dimension(width, height));
		renderObjects = new ArrayList[4];
		for (int i = 0; i < 4; i++) {
			renderObjects[i] = new ArrayList<Renderable>();
		}
		
		MouseHandler handler = new MouseHandler(game);
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
