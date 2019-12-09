package canalis.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import canalis.Display;
import canalis.Renderable;

public class TextCentered extends GameObject implements Renderable {

	private final Display display;
	private Font font;
	private Color color;
	private String text;
	
	public TextCentered(Display display, int y, Font font, Color color, String text) {
		 this.display = display;
		 this.font = font;
		 this.color = color;
		 this.text = text;
		 this.posX = -1;
		 this.posY = y;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, display.getWidth()/2 - g.getFontMetrics().stringWidth(text)/2, posY);
	}
}
