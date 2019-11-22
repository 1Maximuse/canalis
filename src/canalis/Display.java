package canalis;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel implements MouseListener {
	
	private Game game;
	private ArrayList<Renderable> renderObjects;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
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
		this.game = game;
		
		addMouseListener(this);
		
		JFrame frame = new JFrame("Canalis");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
