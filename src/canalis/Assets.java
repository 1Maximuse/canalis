package canalis;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {
	
	public static Color colorMain;
	
	public static Font font;
	public static Font fontSecondary;
	
	public static BufferedImage background;
	public static BufferedImage logo;
	
	public static BufferedImage buttonOptions;
	public static BufferedImage buttonBack;	
	public static BufferedImage buttonPlayCasual;
	public static BufferedImage buttonDifficultyEasy;
	public static BufferedImage buttonDifficultyMedium;
	public static BufferedImage buttonDifficultyHard;
	public static BufferedImage buttonMainMenu;
	public static BufferedImage buttonRetry;
	
	
	public static BufferedImage pipeHorizontal;
	public static BufferedImage pipeVertical;
	public static BufferedImage pipeTopRight;
	public static BufferedImage pipeBottomRight;
	public static BufferedImage pipeBottomLeft;
	public static BufferedImage pipeTopLeft;
	
	public static BufferedImage[] waterHorizontalLeft = new BufferedImage[11];
	public static BufferedImage[] waterHorizontalRight = new BufferedImage[11];
	public static BufferedImage[] waterVerticalBottom = new BufferedImage[11];
	public static BufferedImage[] waterVerticalTop = new BufferedImage[11];
	public static BufferedImage[] waterTopRightTop = new BufferedImage[11];
	public static BufferedImage[] waterTopRightRight = new BufferedImage[11];
	public static BufferedImage[] waterBottomRightBottom = new BufferedImage[11];
	public static BufferedImage[] waterBottomRightRight = new BufferedImage[11];
	public static BufferedImage[] waterBottomLeftBottom = new BufferedImage[11];
	public static BufferedImage[] waterBottomLeftLeft = new BufferedImage[11];
	public static BufferedImage[] waterTopLeftTop = new BufferedImage[11];
	public static BufferedImage[] waterTopLeftLeft = new BufferedImage[11];
	
	public static BufferedImage board;
	
	public static BufferedImage[] pipeStart = new BufferedImage[11];
	public static BufferedImage pipeEnd;
	
	public static BufferedImage[] faucet = new BufferedImage[8];
	
	public static void loadTextures() {
		colorMain = new Color(20, 21, 24);
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(Game.class.getResource("/textures/ui/font.ttf").toURI()));
			fontSecondary = Font.createFont(Font.TRUETYPE_FONT, new File(Game.class.getResource("/textures/ui/font2.ttf").toURI()));
		} catch (Exception e) {
			System.err.println("Cannot get font!");
			e.printStackTrace();
		}
		
		background = getTexture("ui/background.png");
		logo = getTexture("ui/logo.png");
		buttonOptions = getTexture("ui/button_options.png");
		buttonBack = getTexture("ui/button_back.png");
		buttonPlayCasual = getTexture("ui/button_casual.png");
		
		buttonDifficultyEasy = getTexture("ui/button_difficulty1.png");
		buttonDifficultyMedium = getTexture("ui/button_difficulty2.png");
		buttonDifficultyHard = getTexture("ui/button_difficulty3.png");
		
		buttonMainMenu = getTexture("ui/button_mainmenu.png");
		buttonRetry = getTexture("ui/button_retry.png");
		
		
		pipeHorizontal = getTexture("horizontal/pipe_horizontal.png");
		pipeVertical = getTexture("vertical/pipe_vertical.png");
		pipeTopRight = getTexture("top_right/pipe_corner_top_right.png");
		pipeBottomRight = getTexture("bottom_right/pipe_corner_bottom_right.png");
		pipeBottomLeft = getTexture("bottom_left/pipe_corner_bottom_left.png");
		pipeTopLeft = getTexture("top_left/pipe_corner_top_left.png");
		
		for (int i = 0; i < 11; i++) {
			waterHorizontalLeft[i] = getTextureAtlas("horizontal/water_horizontal_left_strip11.png", 128, 128, i, 0);
			waterHorizontalRight[i] = getTextureAtlas("horizontal/water_horizontal_right_strip11.png", 128, 128, i, 0);
			waterVerticalBottom[i] = getTextureAtlas("vertical/water_vertical_bottom_strip11.png", 128, 128, i, 0);
			waterVerticalTop[i] = getTextureAtlas("vertical/water_vertical_top_strip11.png", 128, 128, i, 0);
			waterTopRightTop[i] = getTextureAtlas("top_right/water_corner_top_right_top_strip11.png", 128, 128, i, 0);
			waterTopRightRight[i] = getTextureAtlas("top_right/water_corner_top_right_right_strip11.png", 128, 128, i, 0);
			waterBottomRightBottom[i] = getTextureAtlas("bottom_right/water_corner_bottom_right_bottom_strip11.png", 128, 128, i, 0);
			waterBottomRightRight[i] = getTextureAtlas("bottom_right/water_corner_bottom_right_right_strip11.png", 128, 128, i, 0);
			waterBottomLeftBottom[i] = getTextureAtlas("bottom_left/water_corner_bottom_left_bottom_strip11.png", 128, 128, i, 0);
			waterBottomLeftLeft[i] = getTextureAtlas("bottom_left/water_corner_bottom_left_left_strip11.png", 128, 128, i, 0);
			waterTopLeftTop[i] = getTextureAtlas("top_left/water_corner_top_left_top_strip11.png", 128, 128, i, 0);
			waterTopLeftLeft[i] = getTextureAtlas("top_left/water_corner_top_left_left_strip11.png", 128, 128, i, 0);
		}
		
		board = getTextureAtlas("board.png", 128, 128, 2, 1);
		
		for (int i = 0; i < 11; i++) {
			pipeStart[i] = getTextureAtlas("pipe_start_strip11.png", 128, 128, i, 0);
		}
		pipeEnd = getTexture("pipe_end.png");
		
		for (int i = 0; i < 8; i++) {
			faucet[i] = getTextureAtlas("wheel_strip8.png", 128, 128, i, 0);
		}
	}
	
	private static BufferedImage getTexture(String path) {
		try {
			return ImageIO.read(Game.class.getResource("/textures/" + path));
		} catch (IOException e) {
			System.err.println("Cannot get image texture for " + path + "!");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	private static BufferedImage getTextureAtlas(String path, int sizeX, int sizeY, int posX, int posY) {
		try {
			return ImageIO.read(Game.class.getResource("/textures/" + path)).getSubimage(posX*sizeX, posY*sizeY, sizeX, sizeY);
		} catch (Exception e) {
			System.err.println("Cannot get image texture for " + path + "!");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
