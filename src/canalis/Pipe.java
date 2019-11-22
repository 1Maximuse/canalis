package canalis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Pipe implements Renderable, Clickable {
	
	public static final int SIZE = 80;
	
	private BufferedImage texture;
	private Type type;
	private int orientation;
	private int posX, posY;
	Random rand = new Random();
	
	public Pipe(Type type, int orientation, int posX, int posY) {
		this.type = type;
		this.orientation = orientation;
		if (type == Type.STRAIGHT) this.orientation %= 2;
		this.posX = posX;
		this.posY = posY;
		if (type == Type.STRAIGHT) texture = Game.getTexture("pipe_straight.png");
		else if (type == Type.BEND) texture = Game.getTexture("pipe_bend.png");
	}
	
	public void rotate() {
		orientation++;
		if (type == Type.STRAIGHT) {
			orientation %= 2;
		} else if (type == Type.BEND){
			orientation %= 4;
		}
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean getAccess(Face face) {
		switch (type) {
		case STRAIGHT:
			if (face == Face.TOP || face == Face.BOTTOM) return orientation == 0;
			else return orientation == 1;
		case BEND:
			if (face == Face.TOP) return (orientation == 0 || orientation == 3);
			else if (face == Face.RIGHT) return (orientation == 0 || orientation == 1);
			else if (face == Face.BOTTOM) return (orientation == 1 || orientation == 2);
			else return (orientation == 2 || orientation == 3);
		default:
			return false;
		}
	}

	@Override
	public void onClick(MouseEvent e) {
		rotate();
	}
	
	@Override
	public boolean isInside(int x, int y) {
		if (x > posX && x < posX + SIZE && y > posY && y < posY + SIZE) return true;
		else return false;
	}
	
	@Override
	public void render(Graphics g) {
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(90 * orientation), texture.getWidth() / 2, texture.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(texture, null), posX, posY, posX + SIZE, posY + SIZE, 0, 0, 512, 512, new Color(220, 220, 220), null);
		g.setColor(Color.GRAY);
		g.drawRect(posX, posY, SIZE, SIZE);
	}
	
	public enum Type {
		STRAIGHT,
		BEND
	}
	
	public enum Face {
		TOP,
		BOTTOM,
		LEFT,
		RIGHT
	}
}
