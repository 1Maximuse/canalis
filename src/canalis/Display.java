package canalis;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel {
	
	private ArrayList<Renderable> renderObjects[];
	private int scene = 0;
	private final int width;
	private final int height;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Renderable obj : renderObjects[scene]) {
			obj.render(g);
		}
	}
	
	public ArrayList<Renderable> getRenderObjects() {
		return renderObjects[scene];
	}
	
	public void clearRenderObject() {
		renderObjects[scene].clear();
	}
	
	public void addRenderObject(Renderable obj) {
		renderObjects[scene].add(obj);
	}
	
	public int getScene() {
		return scene;
	}

	public void setScene(int scene) {
		this.scene = scene;
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
		setPreferredSize(new Dimension(width, height));
		renderObjects = new ArrayList[5];
//		Scene 0 : Main Menu
		renderObjects[0] = new ArrayList<Renderable>();
//		Scene 1 : Game (Tulisan Play)
		renderObjects[1] = new ArrayList<Renderable>();
//		Scene 2 : Setting (Gambar Cog)
		renderObjects[2] = new ArrayList<Renderable>();
//		Scene 3 : ?
		renderObjects[3] = new ArrayList<Renderable>();
//		Scene 4 : ?
		renderObjects[4] = new ArrayList<Renderable>();
		
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
