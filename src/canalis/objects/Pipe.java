package canalis.objects;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import canalis.Assets;
import canalis.Clickable;
import canalis.Game;
import canalis.Renderable;

public class Pipe extends GameObject implements Renderable, Clickable {
	
	private final Type type;
	private int orientation;
	private int flowPos;
	private Face flowFrom;
	Random rand = new Random();
	
	public Pipe(Type type, int orientation, int posX, int posY) {
		this.type = type;
		if (type == Type.STRAIGHT && orientation >= 2) orientation %= 2;
		this.orientation = orientation;
		if (type == Type.STRAIGHT) this.orientation %= 2;
		this.posX = posX;
		this.posY = posY;
		flowPos = -1;
		flowFrom = null;
	}

	public void setFlowing(int i, Face from) {
		flowPos = i;
		flowFrom = from;
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
			else if (face == Face.LEFT || face == Face.RIGHT) return orientation == 1;
		case BEND:
			if (face == Face.TOP) return (orientation == 0 || orientation == 3);
			else if (face == Face.RIGHT) return (orientation == 0 || orientation == 1);
			else if (face == Face.BOTTOM) return (orientation == 1 || orientation == 2);
			else if (face == Face.LEFT) return (orientation == 2 || orientation == 3);
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
		if (x > posX && x < posX + Game.GRID_SIZE && y > posY && y < posY + Game.GRID_SIZE) return true;
		else return false;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.board, posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
		if (getType() == Type.STRAIGHT) {
			switch (getOrientation()) {
			case 0:
				if (flowPos > -1) {
					if (flowFrom == Face.BOTTOM) g.drawImage(Assets.waterVerticalBottom[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
					else if (flowFrom == Face.TOP) g.drawImage(Assets.waterVerticalTop[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				}
				g.drawImage(Assets.pipeVertical, posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				break;
			case 1:
				if (flowPos > -1) {
					if (flowFrom == Face.LEFT) g.drawImage(Assets.waterHorizontalLeft[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
					else if (flowFrom == Face.RIGHT) g.drawImage(Assets.waterHorizontalRight[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				}
				g.drawImage(Assets.pipeHorizontal, posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				break;
			}
		} else if (getType() == Type.BEND) {
			switch (getOrientation()) {
			case 0:
				if (flowPos > -1) {
					if (flowFrom == Face.RIGHT) g.drawImage(Assets.waterTopRightRight[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
					else if (flowFrom == Face.TOP) g.drawImage(Assets.waterTopRightTop[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				}
				g.drawImage(Assets.pipeTopRight, posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				break;
			case 1:
				if (flowPos > -1) {
					if (flowFrom == Face.RIGHT) g.drawImage(Assets.waterBottomRightRight[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
					else if (flowFrom == Face.BOTTOM) g.drawImage(Assets.waterBottomRightBottom[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				}
				g.drawImage(Assets.pipeBottomRight, posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				break;
			case 2:
				if (flowPos > -1) {
					if (flowFrom == Face.LEFT) g.drawImage(Assets.waterBottomLeftLeft[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
					else if (flowFrom == Face.BOTTOM) g.drawImage(Assets.waterBottomLeftBottom[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				}
				g.drawImage(Assets.pipeBottomLeft, posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				break;
			case 3:
				if (flowPos > -1) {
					if (flowFrom == Face.LEFT) g.drawImage(Assets.waterTopLeftLeft[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
					else if (flowFrom == Face.TOP) g.drawImage(Assets.waterTopLeftTop[flowPos], posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				}
				g.drawImage(Assets.pipeTopLeft, posX, posY, Game.GRID_SIZE, Game.GRID_SIZE, null);
				break;
			}
		}
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
