package lr_in_the_well.alexis_puska;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.domain.level.LevelFile;
import lr_in_the_well.alexis_puska.service.FileService;
import lr_in_the_well.alexis_puska.service.SpriteService;
import lr_in_the_well.alexis_puska.view.DrawPanel;

public class App extends JFrame {

    private final static Logger LOG = Logger.getLogger(App.class);

    private static final long serialVersionUID = -8256444946608935363L;

    private final FileService fileService;
    private final SpriteService spriteService;
    private int levelSelected;

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
        levelSelected = 0;
        InputStream in = this.getClass().getResourceAsStream("/json/json_level_parser.json");
        LevelFile levelFile = fileService.readJsonFile(in);

        LOG.info("Nb level in file : " + levelFile.getLevel().size());

        JPanel panel = new JPanel();
        panel.setSize(800, 600);
        panel.setBackground(Color.LIGHT_GRAY);
        DrawPanel g2D = new DrawPanel(spriteService, levelFile.getLevel().get(levelSelected));
        JButton but = new JButton("ici");
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                levelSelected++;
                g2D.updateLevel(levelFile.getLevel().get(levelSelected));
            }
        });
        panel.add(g2D);
        g2D.setSize(420, 500);
        g2D.setVisible(true);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.getContentPane().add(but, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800, 600);
    }
}
