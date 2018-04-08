package lr_in_the_well.alexis_puska.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.service.SpriteService;

public class DrawPanel extends Canvas {

	private static final long serialVersionUID = -617780220828076518L;

	private LevelService levelService;
	private SpriteService spriteService;

	public DrawPanel(SpriteService spriteService, LevelService levelService) {
		super();
		this.spriteService = spriteService;
		this.levelService = levelService;
	}

	/**
	 * Draw the level if exists, draw message if level doesn't exists
	 * 
	 * @param g2
	 *            graphics
	 */
	public void paint(Graphics g) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			bs = getBufferStrategy();
		}
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		if (levelService.getCurrentLevel() != null) {
			drawBackground(g2);
			drawPlatform(g2);
			drawEnnemies(g2);
			drawGrid(g2);
		} else {
			Font font = new Font("Serif", Font.PLAIN, 20);
			g2.setFont(font);
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);
			g2.setColor(Color.RED);
			g2.drawString("NO LEVEL FOUND", 130, 250);
		}
		g2.dispose();
		bs.show();
	}

	/**
	 * Draw the level
	 * 
	 * @param g2
	 *            graphics2D
	 */
	private void drawGrid(Graphics2D g2) {
		int x, y;
		g2.setColor(Color.RED);
		x = 0;
		while (x < Constante.SCREEN_SIZE_X) {
			g2.drawLine(x, 0, x, Constante.SCREEN_SIZE_Y);
			x += Constante.GRID_SIZE;
		}
		y = 0;
		while (y < Constante.SCREEN_SIZE_Y) {
			y += Constante.GRID_SIZE;
			g2.drawLine(0, y, Constante.SCREEN_SIZE_X, y);
		}
	}

	/**
	 * Draw the background of the level
	 * 
	 * @param g2
	 */
	private void drawBackground(Graphics2D g2) {
		BufferedImage bf = spriteService.getSprite("level_background", levelService.getCurrentLevel().getBackground());
		int x = 0;
		int y = 0;
		while (x < Constante.SCREEN_SIZE_X) {
			while (y < Constante.SCREEN_SIZE_Y) {
				g2.drawImage(bf, null, x, y);
				y += bf.getHeight();
			}
			x += bf.getWidth();
			y = 0;
		}
	}

	/**
	 * draw platform of the level
	 * 
	 * @param g2
	 */
	private void drawPlatform(Graphics2D g2) {
		BufferedImage bf = spriteService.getSprite("platform", levelService.getCurrentLevel().getHorizontalPlateform());
		BufferedImage bfv = spriteService.getSprite("platform", levelService.getCurrentLevel().getVerticalPlateform());
		for (Platform platform : levelService.getCurrentLevel().getPlatform()) {

			if (platform.isVertical()) {

				AffineTransform backup = g2.getTransform();
				AffineTransform trans = new AffineTransform();
				trans.rotate((Math.PI / 2), platform.getX() * Constante.GRID_SIZE,
						platform.getY() * Constante.GRID_SIZE); // the points to rotate around (the center in my
				// example, your left side for your problem)
				trans.translate(-Constante.GRID_SIZE, -Constante.GRID_SIZE);
				g2.transform(trans);
				g2.drawImage(bfv.getSubimage(0, 0, platform.getLength() * Constante.GRID_SIZE, Constante.GRID_SIZE),
						platform.getX() * Constante.GRID_SIZE + Constante.GRID_SIZE,
						platform.getY() * Constante.GRID_SIZE, null);

				g2.setTransform(backup); // restore previous transform
			} else {
				g2.drawImage(bf.getSubimage(0, 0, platform.getLength() * Constante.GRID_SIZE, Constante.GRID_SIZE),
						platform.getX() * Constante.GRID_SIZE, platform.getY() * Constante.GRID_SIZE, null);
			}
		}
	}

	/**
	 * draw Ennemies of the level
	 * 
	 * @param g2
	 */
	private void drawEnnemies(Graphics2D g2) {
		BufferedImage bf = spriteService.getSprite("cerise", 0);
		for (Ennemie ennemie : levelService.getCurrentLevel().getEnnemies()) {
			switch (ennemie.getType()) {
			case 0:
				bf = spriteService.getSprite("cerise", 0);
				break;
			case 1:
				bf = spriteService.getSprite("orange", 0);
				break;
			case 2:
				bf = spriteService.getSprite("pomme", 0);
				break;
			case 3:
				bf = spriteService.getSprite("banane", 0);
				break;
			case 4:
				bf = spriteService.getSprite("citron", 0);
				break;
			case 5:
				bf = spriteService.getSprite("bombinos", 0);
				break;
			case 6:
				bf = spriteService.getSprite("poire", 0);
				break;
			case 7:
				bf = spriteService.getSprite("abricot", 0);
				break;
			case 8:
				bf = spriteService.getSprite("litchi", 0);
				break;
			case 9:
				bf = spriteService.getSprite("fraise", 0);
				break;
			case 10:
				bf = spriteService.getSprite("kiwi", 0);
				break;
			case 11:
				bf = spriteService.getSprite("pasteque", 0);
				break;
			case 12:
				bf = spriteService.getSprite("ananas", 0);
				break;
			case 13:
				bf = spriteService.getSprite("blob", 0);
				break;
			case 14:
				bf = spriteService.getSprite("framboise", 0);
				break;
			case 15:
				bf = spriteService.getSprite("nainbricot", 0);
				break;
			case 16:
				bf = spriteService.getSprite("scie", 0);
				break;
			}
			int x = (ennemie.getX() * Constante.GRID_SIZE) + (Constante.GRID_SIZE / 2) - (bf.getWidth() / 2);
			int y = ((ennemie.getY() + 1) * Constante.GRID_SIZE) - bf.getHeight();
			g2.drawImage(bf, x, y, null);
		}
	}

}