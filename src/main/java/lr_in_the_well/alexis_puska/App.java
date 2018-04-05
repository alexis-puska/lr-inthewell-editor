package lr_in_the_well.alexis_puska;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.domain.level.LevelFile;
import lr_in_the_well.alexis_puska.service.FileService;
import lr_in_the_well.alexis_puska.service.SpriteService;

public class App extends JFrame {

    private final static Logger LOG = Logger.getLogger(App.class);

    private static final long serialVersionUID = -8256444946608935363L;

    private final FileService fileService;
    private final SpriteService spriteService;

    public static void main(String[] args) {
        App app = new App();
        app.Launch();
    }

    public App() {
        LOG.info("Welcome in lr-inthewell-editor App !");
        this.fileService = new FileService();
        this.spriteService = new SpriteService(fileService);
    }

    private void Launch() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("json/json_level_parser.json").getFile());
        LevelFile levelFile = fileService.readJsonFile(file);

        BufferedImage bf = this.spriteService.getSprite("level_background", 1);

        JPanel panel = new JPanel();
        panel.setSize(1800, 1100);
        panel.setBackground(Color.CYAN);
        ImageIcon icon = new ImageIcon(bf);
        JLabel label = new JLabel();
        label.setIcon(icon);
        panel.add(label);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(10, 10);
    }

}
