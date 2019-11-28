package canalis.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import canalis.Clickable;
import canalis.Game;
import canalis.Renderable;

public class Pipe extends GameObject implements Renderable, Clickable {
	
	public static final int SIZE = 80;
	
	private final BufferedImage[] texture = new BufferedImage[5];
	private final Type type;
	private int orientation;
	Random rand = new Random();
	
	public Pipe(Type type, int orientation, int posX, int posY) {
		this.type = type;
		if (type == Type.STRAIGHT && orientation >= 2) orientation %= 2;
		this.orientation = orientation;
		if (type == Type.STRAIGHT) this.orientation %= 2;
		this.posX = posX;
		this.posY = posY;
		if (type == Type.STRAIGHT) {
			texture[0] = Game.getTexture("horizontal/pipe_horizontal.png");
			texture[1] = Game.getTexture("vertical/pipe_vertical.png");
		}
		else if (type == Type.BEND) {
			texture[0] = Game.getTexture("top_right/pipe_corner_top_right.png");
			texture[1] = Game.getTexture("bottom_right/pipe_corner_bottom_right.png");
			texture[2] = Game.getTexture("bottom_left/pipe_corner_bottom_left.png");
			texture[3] = Game.getTexture("top_left/pipe_corner_top_left.png");
		}
		texture[4] = Game.getTextureAtlas("board.png", 128, 128, 2, 1);
	}
	
	public void rotate() {
		orientation++;
		if (type == Type.STRAIGHT) {
			orientation %= 2;
		} else if (type == Type.BEND){
			orientation %= 4;
		}
	}
	
	public int getOrientation() {
		return orientation;
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
		g.drawImage(texture[4], posX, posY, SIZE, SIZE, null);
		g.drawImage(texture[orientation], posX, posY, SIZE, SIZE, null);
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
