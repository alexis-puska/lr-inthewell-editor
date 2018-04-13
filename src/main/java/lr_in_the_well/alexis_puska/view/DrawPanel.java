package lr_in_the_well.alexis_puska.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;

import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.domain.level.Door;
import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.domain.level.Event;
import lr_in_the_well.alexis_puska.domain.level.Item;
import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.domain.level.StartEffectObjets;
import lr_in_the_well.alexis_puska.domain.level.StartPlayer;
import lr_in_the_well.alexis_puska.domain.level.StartPointObjets;
import lr_in_the_well.alexis_puska.domain.level.Teleporter;
import lr_in_the_well.alexis_puska.domain.level.Vortex;
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
			drawTeleporter(g2);
			drawPlatform(g2);
			drawRayon(g2);
			drawVortex(g2);
			drawEnnemies(g2);
			drawStartPlayer(g2);
			drawEffectObject(g2);
			drawPontObject(g2);
			drawItem(g2);
			drawEvent(g2);
			drawGrid(g2);
			drawLock(g2);
			drawDoors(g2);
			drawPick(g2);
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

	private void drawStartPlayer(Graphics2D g2) {
		List<StartPlayer> sp = levelService.getCurrentLevel().getStartPlayers();
		if (sp != null && !sp.isEmpty()) {
			Stroke savedStrock = g2.getStroke();
			g2.setColor(Color.GREEN);
			Font font = new Font("Arial", Font.PLAIN, 20);
			g2.setFont(font);
			g2.setStroke(new BasicStroke(2));
			for (StartPlayer s : sp) {
				g2.drawString("S", s.getX() * Constante.GRID_SIZE + 2,
						(s.getY() * Constante.GRID_SIZE) + Constante.GRID_SIZE);
			}
			g2.setStroke(savedStrock);
		}
	}

	private void drawEffectObject(Graphics2D g2) {
		List<StartEffectObjets> sp = levelService.getCurrentLevel().getStartEffectObjets();
		if (sp != null && !sp.isEmpty()) {
			Stroke savedStrock = g2.getStroke();
			g2.setColor(Color.CYAN);
			Font font = new Font("Arial", Font.PLAIN, 20);
			g2.setFont(font);
			g2.setStroke(new BasicStroke(2));
			for (StartEffectObjets s : sp) {
				g2.drawString("E", s.getX() * Constante.GRID_SIZE + 2,
						(s.getY() * Constante.GRID_SIZE) + Constante.GRID_SIZE);
			}
			g2.setStroke(savedStrock);
		}
	}

	private void drawPontObject(Graphics2D g2) {
		List<StartPointObjets> sp = levelService.getCurrentLevel().getStartPointObjets();
		if (sp != null && !sp.isEmpty()) {
			Stroke savedStrock = g2.getStroke();
			g2.setColor(Color.YELLOW);
			Font font = new Font("Arial", Font.PLAIN, 20);
			g2.setFont(font);
			g2.setStroke(new BasicStroke(2));
			for (StartPointObjets s : sp) {
				g2.drawString("P", s.getX() * Constante.GRID_SIZE + 2,
						(s.getY() * Constante.GRID_SIZE) + Constante.GRID_SIZE);
			}
			g2.setStroke(savedStrock);
		}
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
		Stroke savedStrock = g2.getStroke();
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(0, 0, Constante.SCREEN_SIZE_X, 0);
		g2.drawLine(0, 0, 0, Constante.SCREEN_SIZE_Y);
		g2.drawLine(Constante.SCREEN_SIZE_X, 0, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);
		g2.drawLine(0, Constante.SCREEN_SIZE_Y, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);

		g2.setStroke(savedStrock);
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

	private void drawItem(Graphics2D g2) {
		for (Item item : levelService.getCurrentLevel().getItems()) {
			BufferedImage bf = spriteService.getSprite("objects", item.getItemId());
			g2.drawImage(bf, null, item.getX() * Constante.GRID_SIZE - (bf.getWidth() / 2) + 10,
					((item.getY() * Constante.GRID_SIZE) + Constante.GRID_SIZE) - bf.getHeight());
		}
	}

	private void drawLock(Graphics2D g2) {
		for (Lock lock : levelService.getCurrentLevel().getLock()) {
			BufferedImage bf = spriteService.getSprite("serrure", 1);
			g2.drawImage(bf, null, lock.getX() * Constante.GRID_SIZE - (bf.getWidth() / 2) + 10,
					((lock.getY() * Constante.GRID_SIZE) + Constante.GRID_SIZE) - bf.getHeight());
		}
	}

	private void drawDoors(Graphics2D g2) {
		for (Door door : levelService.getCurrentLevel().getDoor()) {
			BufferedImage bf = spriteService.getSprite("doors", door.getType());
			g2.drawImage(bf, null, door.getX() * Constante.GRID_SIZE - (bf.getWidth() / 2) + 10,
					((door.getY() * Constante.GRID_SIZE) + Constante.GRID_SIZE) - bf.getHeight());
		}
	}

	private void drawPick(Graphics2D g2) {
		for (Pick pick : levelService.getCurrentLevel().getPick()) {
			BufferedImage bf = spriteService.getSprite("pick", 1);
			AffineTransform backup = g2.getTransform();
			AffineTransform trans = new AffineTransform();
			if (pick.getDirection() == 1) {
				trans.rotate((Math.PI / 2), pick.getX() * Constante.GRID_SIZE, pick.getY() * Constante.GRID_SIZE);
				trans.translate(0,-Constante.GRID_SIZE);
			} else if (pick.getDirection() == 2) {
				trans.rotate((Math.PI), pick.getX() * Constante.GRID_SIZE, pick.getY() * Constante.GRID_SIZE);
				trans.translate(-Constante.GRID_SIZE, -Constante.GRID_SIZE);
			} else if (pick.getDirection() == 3) {
				trans.rotate(3*(Math.PI / 2), pick.getX() * Constante.GRID_SIZE, pick.getY() * Constante.GRID_SIZE);
				trans.translate(-Constante.GRID_SIZE,0);
			}
			g2.transform(trans);
			g2.drawImage(bf, pick.getX() * Constante.GRID_SIZE, pick.getY() * Constante.GRID_SIZE,
					null);
			g2.setTransform(backup); // restore previous transform
		}
	}

	private void drawEvent(Graphics2D g2) {
		Stroke savedStrock = g2.getStroke();
		g2.setColor(Color.PINK);
		Font font = new Font("Arial", Font.PLAIN, 20);
		g2.setFont(font);
		g2.setStroke(new BasicStroke(2));
		for (Event event : levelService.getCurrentLevel().getEvent()) {
			g2.drawString("Ev", event.getX() * Constante.GRID_SIZE,
					(event.getY() * Constante.GRID_SIZE) + Constante.GRID_SIZE);
		}
		g2.setStroke(savedStrock);
	}

	private void drawVortex(Graphics2D g2) {
		for (Vortex vortex : levelService.getCurrentLevel().getVortex()) {
			BufferedImage bf = spriteService.getSprite("vortex", 0);
			g2.drawImage(bf, null, vortex.getX() * Constante.GRID_SIZE - (bf.getWidth() / 2) + 10,
					((vortex.getY() * Constante.GRID_SIZE) + Constante.GRID_SIZE) - bf.getHeight());
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
	 * draw rayon of the level
	 * 
	 * @param g2
	 */
	private void drawRayon(Graphics2D g2) {
		for (Rayon rayon : levelService.getCurrentLevel().getRayon()) {
			BufferedImage bf = spriteService.getSprite("rayon", rayon.getType() * 2);
			if (rayon.isVertical()) {
				for (int i = rayon.getY(); i < rayon.getY() + rayon.getLength(); i++) {
					g2.drawImage(bf, rayon.getX() * Constante.GRID_SIZE, i * Constante.GRID_SIZE, null);
				}
			} else {
				for (int i = 0; i < rayon.getLength(); i++) {
					AffineTransform backup = g2.getTransform();
					AffineTransform trans = new AffineTransform();
					trans.rotate((Math.PI / 2), (rayon.getX() + i) * Constante.GRID_SIZE,
							rayon.getY() * Constante.GRID_SIZE);
					trans.translate(0, -Constante.GRID_SIZE);
					g2.transform(trans);
					g2.drawImage(bf, (rayon.getX() + i) * Constante.GRID_SIZE, rayon.getY() * Constante.GRID_SIZE,
							null);
					g2.setTransform(backup); // restore previous transform
				}
			}
		}
	}

	/**
	 * draw rayon of the level
	 * 
	 * @param g2
	 */
	private void drawTeleporter(Graphics2D g2) {
		for (Teleporter teleporter : levelService.getCurrentLevel().getTeleporter()) {
			BufferedImage bf = spriteService.getSprite("teleporter", 0);
			if (teleporter.isVertical()) {
				for (int i = teleporter.getY(); i < teleporter.getY() + teleporter.getLength(); i++) {
					g2.drawImage(bf.getSubimage(0, 0, Constante.GRID_SIZE, Constante.GRID_SIZE),
							teleporter.getX() * Constante.GRID_SIZE, i * Constante.GRID_SIZE, null);
				}
			} else {
				for (int i = 0; i < teleporter.getLength(); i++) {
					AffineTransform backup = g2.getTransform();
					AffineTransform trans = new AffineTransform();
					trans.rotate((Math.PI / 2), (teleporter.getX() + i) * Constante.GRID_SIZE,
							teleporter.getY() * Constante.GRID_SIZE);
					trans.translate(0, -Constante.GRID_SIZE);
					g2.transform(trans);
					g2.drawImage(bf.getSubimage(0, 0, Constante.GRID_SIZE, Constante.GRID_SIZE),
							(teleporter.getX() + i) * Constante.GRID_SIZE, teleporter.getY() * Constante.GRID_SIZE,
							null);
					g2.setTransform(backup); // restore previous transform
				}
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