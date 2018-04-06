package lr_in_the_well.alexis_puska;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.constant.Constante;
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
	private DrawPanel drawPanel;
	private JButton nextLevel;
	private JButton previousLevel;
	private JButton addLevel;
	private JButton delLevel;
	private JTextField currentLevelIndex;

	private LevelFile levelFile;

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
		levelFile = fileService.readJsonFile(in);
		LOG.info("Nb level in file : " + levelFile.getLevel().size());
		this.getContentPane().setLayout(new BorderLayout());
		buildDrawElement();
		buildNavigationPanelButton();
		buildEnnemiePanelButton();
		buildElementPanelButton();
		buildParameterPanelButton();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(800, 600);
	}

	private void buildDrawElement() {
		JPanel panel = new JPanel();
		panel.setSize(800, 600);
		panel.setBackground(Color.LIGHT_GRAY);
		drawPanel = new DrawPanel(spriteService, levelFile.getLevel().get(levelSelected));
		panel.add(drawPanel);
		drawPanel.setSize(Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);
		drawPanel.setVisible(true);
		drawPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LOG.info("clic on position : " + e.getX() + " " + e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				LOG.info("mouse pressed : " + e.getX() + " " + e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				LOG.info("mouse release : " + e.getX() + " " + e.getY());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LOG.info("mouse entered : " + e.getX() + " " + e.getY());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				LOG.info("mouse exited : " + e.getX() + " " + e.getY());
			}
		});
		this.getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void buildEnnemiePanelButton() {
		GridLayout layout = new GridLayout();
		layout.setColumns(Constante.NB_COLUMN_NAVIGATION);
		layout.setRows(Constante.NB_ROW_NAVIGATION);
		JPanel panelNavigation = new JPanel();
		panelNavigation.setLayout(layout);
		panelNavigation.setBorder(BorderFactory.createTitledBorder("Ennemies"));
		JButton nextLevel = new JButton("+");
		panelNavigation.add(nextLevel);
		this.getContentPane().add(panelNavigation, BorderLayout.EAST);
	}

	private void buildNavigationPanelButton() {
		GridLayout layout = new GridLayout();
		layout.setColumns(Constante.NB_COLUMN_NAVIGATION);
		layout.setRows(Constante.NB_ROW_NAVIGATION);
		JPanel panelNavigation = new JPanel();
		panelNavigation.setBorder(BorderFactory.createTitledBorder("Navigation"));
		panelNavigation.setLayout(layout);

		nextLevel = new JButton("+");
		previousLevel = new JButton("-");
		addLevel = new JButton("add");
		delLevel = new JButton("del");
		currentLevelIndex = new JTextField();

		nextLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextLevel();
			}
		});
		previousLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				previousLevel();
			}
		});
		addLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextLevel();
			}
		});
		delLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextLevel();
			}
		});
		
		
		currentLevelIndex.setText(Integer.toString(levelSelected));
		JLabel cur = new JLabel("current");
		
		
		JLabel l = new JLabel("&&&");
		panelNavigation.add(l);
 
        JSpinner spinner = new JSpinner();
        l.setLabelFor(spinner);
        panelNavigation.add(spinner);
 
		
		panelNavigation.add(cur);
		panelNavigation.add(previousLevel);
		panelNavigation.add(currentLevelIndex);
		panelNavigation.add(nextLevel);
		panelNavigation.add(addLevel);
		panelNavigation.add(delLevel);
		
		
		cur.setLabelFor(currentLevelIndex);
		
		this.getContentPane().add(panelNavigation, BorderLayout.NORTH);
	}

	private void buildElementPanelButton() {
		GridLayout layout = new GridLayout();
		layout.setColumns(Constante.NB_COLUMN_NAVIGATION);
		layout.setRows(Constante.NB_ROW_NAVIGATION);
		JPanel panelNavigation = new JPanel();
		panelNavigation.setLayout(layout);
		panelNavigation.setBorder(BorderFactory.createTitledBorder("Elements"));
		JButton nextLevel = new JButton("+");
		panelNavigation.add(nextLevel);
		this.getContentPane().add(panelNavigation, BorderLayout.WEST);
	}

	private void buildParameterPanelButton() {
		GridLayout layout = new GridLayout();
		layout.setColumns(Constante.NB_COLUMN_NAVIGATION);
		layout.setRows(Constante.NB_ROW_NAVIGATION);
		JPanel panelNavigation = new JPanel();
		panelNavigation.setLayout(layout);
		panelNavigation.setBorder(BorderFactory.createTitledBorder("Parameters"));
		JButton nextLevel = new JButton("+");
		panelNavigation.add(nextLevel);
		this.getContentPane().add(panelNavigation, BorderLayout.SOUTH);
	}

	private void nextLevel() {
		levelSelected++;
		currentLevelIndex.setText(Integer.toString(levelSelected));
		drawPanel.updateLevel(levelFile.getLevel().get(levelSelected));
	}

	private void previousLevel() {
		levelSelected--;
		currentLevelIndex.setText(Integer.toString(levelSelected));
		drawPanel.updateLevel(levelFile.getLevel().get(levelSelected));
	}
}
