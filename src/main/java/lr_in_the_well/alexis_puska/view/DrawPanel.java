package lr_in_the_well.alexis_puska.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import lr_in_the_well.alexis_puska.domain.level.Level;
import lr_in_the_well.alexis_puska.service.SpriteService;

public class DrawPanel extends Canvas {

    private static final long serialVersionUID = -617780220828076518L;
    public final static int SCREEN_SIZE_X = 420;
    public final static int SCREEN_SIZE_Y = 500;

    private Level level;
    private SpriteService spriteService;

    public DrawPanel(SpriteService spriteService, Level level) {
        super();
        this.spriteService = spriteService;
        this.level = level;
    }

    public void updateLevel(Level level) {
        this.level = level;
        this.repaint();
    }

    private void drawGrid(Graphics2D g2) {
        int x, y;
        g2.setColor(Color.RED);
        x = 0;
        while (x < SCREEN_SIZE_X) {
            g2.drawLine(x, 0, x, SCREEN_SIZE_Y);
            x += 20;
        }
        y = 0;
        while (y < SCREEN_SIZE_Y) {
            y += 20;
            g2.drawLine(0, y, SCREEN_SIZE_X, y);
        }
    }

    private void drawBackground(Graphics2D g2) {
        BufferedImage bf = spriteService.getSprite("level_background", level.getBackground());
        int x = 0;
        int y = 0;
        while (x < SCREEN_SIZE_X) {
            while (y < SCREEN_SIZE_Y) {
                g2.drawImage(bf, null, x, y);
                y += bf.getHeight();
            }
            x += bf.getWidth();
            y = 0;
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        drawGrid(g2);
    }

}