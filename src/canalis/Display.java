package canalis;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel {
	
	private ArrayList<Renderable> renderObjects;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Renderable obj : renderObjects) {
			obj.render(g);
		}
	}
	
	public ArrayList<Renderable> getRenderObjects() {
		return renderObjects;
	}
	
	public void addRenderObject(Renderable obj) {
		renderObjects.add(obj);
	}
	
	public Display(int width, int height, Game game) {
		setPreferredSize(new Dimension(width, height));
		renderObjects = new ArrayList<Renderable>();
		
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
