package lr_in_the_well.alexis_puska.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DrawPanel extends Canvas {

    private static final long serialVersionUID = -617780220828076518L;
    BufferedImage bf;
    boolean draw;

    public DrawPanel(BufferedImage bf) {
        super();
        this.bf = bf;
        this.draw = false;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.BLACK);
        g2.drawString("Draw a rectangle", 100, 100);
        g2.drawRect(100, 200, 50, 50);
        if (draw) {
            g2.drawImage(bf, null, 0, 0);
            draw = false;
        } else {
            draw = true;
        }
    }

}