package lr_in_the_well.alexis_puska;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.CaretListener;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.constant.ActionEnum;
import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.domain.level.Level;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.service.FileService;
import lr_in_the_well.alexis_puska.service.SpriteService;
import lr_in_the_well.alexis_puska.view.DrawPanel;

public class App extends JFrame {

	private final static Logger LOG = Logger.getLogger(App.class);

	private static final long serialVersionUID = -8256444946608935363L;

	// services
	private final FileService fileService;
	private final SpriteService spriteService;

	private int levelSelected;
	private int xFirst;
	private int yFirst;
	private ActionEnum action;

	// levels
	private Map<Integer, Level> levels;

	// draw
	private JPanel panelDraw;
	private DrawPanel drawPanel;

	// navigations
	private JPanel panelNavigation;
	private Border borderNavigation;
	private GridLayout layoutNavigation;
	private JButton nextLevel;
	private JButton previousLevel;
	private JButton addLevel;
	private JButton delLevel;
	private JTextField currentLevelIndex;

	// ennemies component

	private JPanel panelEnnemies;
	private Border borderEnnemies;
	private GridLayout layoutEnnemies;
	private JButton ceriseButton;
	private JButton orangeButton;
	private JButton pommeButton;
	private JButton bananeButton;
	private JButton litchiButton;
	private JButton fraiseButton;
	private JButton framboiseButton;
	private JButton citronButton;
	private JButton abricotButton;
	private JButton abricotnainsButton;
	private JButton annanasButton;
	private JButton kiwiButton;
	private JButton pastequeButton;
	private JButton pruneButton;
	private JButton scieButton;

	// elements component
	private JPanel panelElement;
	private Border borderElement;
	private GridLayout layoutElement;
	private JButton selectButton;
	private JButton deleteButton;
	private JButton verticalPlatformButton;
	private JButton horizontalPlatformButton;
	private JButton vortexButton;
	private JButton teleporterButton;
	private JButton rayonButton;
	private JButton pickButton;
	private JButton eventButton;
	private JButton startButton;
	private JButton pointButton;
	private JButton effectButton;

	// properties
	private JPanel panelParameters;
	private GridLayout layoutParameters;
	private Border borderParameters;
	private JTextField backgroundId;

	public static void main(String[] args) {
		App app = new App();
		app.Launch();
	}

	public App() {
		LOG.info("Welcome in lr-inthewell-editor App !");
		this.fileService = new FileService();
		this.spriteService = new SpriteService(fileService);
		this.action = ActionEnum.SELECT;
	}

	private void Launch() {
		levelSelected = 0;
		InputStream in = this.getClass().getResourceAsStream("/json/json_level_parser.json");
		levels = fileService.readJsonFile(in);
		LOG.info("Nb level in file : " + levels.size());
		this.getContentPane().setLayout(new BorderLayout());
		initComponent();
		initListeners();
		buildDrawElement();
		buildNavigationPanelButton();
		buildElementPanelButton();
		buildEnnemiePanelButton();
		buildParameterPanelButton();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(1200, 900);
	}

	private void buildDrawElement() {

		panelDraw.setSize(800, 600);
		panelDraw.setBackground(Color.LIGHT_GRAY);

		panelDraw.add(drawPanel);
		drawPanel.setSize(Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);
		drawPanel.setVisible(true);
		this.getContentPane().add(panelDraw, BorderLayout.CENTER);
	}

	private void buildEnnemiePanelButton() {
		panelEnnemies.setBorder(borderEnnemies);
		panelEnnemies.setLayout(layoutEnnemies);
		panelEnnemies.add(ceriseButton);
		panelEnnemies.add(orangeButton);
		panelEnnemies.add(pommeButton);
		panelEnnemies.add(bananeButton);
		panelEnnemies.add(litchiButton);
		panelEnnemies.add(fraiseButton);
		panelEnnemies.add(framboiseButton);
		panelEnnemies.add(citronButton);
		panelEnnemies.add(abricotButton);
		panelEnnemies.add(abricotnainsButton);
		panelEnnemies.add(annanasButton);
		panelEnnemies.add(kiwiButton);
		panelEnnemies.add(pastequeButton);
		panelEnnemies.add(pruneButton);
		panelEnnemies.add(scieButton);
		this.getContentPane().add(panelEnnemies, BorderLayout.EAST);
	}

	private void buildNavigationPanelButton() {
		panelNavigation.setBorder(borderNavigation);
		panelNavigation.setLayout(layoutNavigation);
		currentLevelIndex.setText(Integer.toString(levelSelected));
		panelNavigation.add(previousLevel);
		panelNavigation.add(currentLevelIndex);
		panelNavigation.add(nextLevel);
		panelNavigation.add(addLevel);
		panelNavigation.add(delLevel);
		this.getContentPane().add(panelNavigation, BorderLayout.NORTH);
	}

	private void buildElementPanelButton() {
		panelElement.setBorder(borderElement);
		panelElement.setLayout(layoutElement);
		panelElement.add(selectButton);
		panelElement.add(deleteButton);
		panelElement.add(verticalPlatformButton);
		panelElement.add(horizontalPlatformButton);
		panelElement.add(vortexButton);
		panelElement.add(teleporterButton);
		panelElement.add(rayonButton);
		panelElement.add(pickButton);
		panelElement.add(eventButton);
		panelElement.add(startButton);
		panelElement.add(pointButton);
		panelElement.add(effectButton);
		this.getContentPane().add(panelElement, BorderLayout.WEST);
	}

	private void buildParameterPanelButton() {
		panelParameters.setLayout(layoutParameters);
		panelParameters.setBorder(borderParameters);
		panelParameters.add(backgroundId);

		this.getContentPane().add(panelParameters, BorderLayout.SOUTH);
	}

	private void nextLevel() {
		levelSelected++;
		currentLevelIndex.setText(Integer.toString(levelSelected));
		drawPanel.updateLevel(levels.get(levelSelected));
	}

	private void previousLevel() {
		levelSelected--;
		currentLevelIndex.setText(Integer.toString(levelSelected));
		drawPanel.updateLevel(levels.get(levelSelected));
	}

	private void initComponent() {
		// draw
		panelDraw = new JPanel();
		drawPanel = new DrawPanel(spriteService, levels.get(levelSelected));

		// navigation
		panelNavigation = new JPanel();
		borderNavigation = BorderFactory.createTitledBorder("Navigation");
		layoutNavigation = new GridLayout();
		layoutNavigation.setColumns(Constante.NB_COLUMN_NAVIGATION);
		layoutNavigation.setRows(Constante.NB_ROW_NAVIGATION);
		nextLevel = new JButton("Next");
		previousLevel = new JButton("Previous");
		addLevel = new JButton("Add");
		delLevel = new JButton("Delete");
		currentLevelIndex = new JTextField();

		// ennemies
		panelEnnemies = new JPanel();
		borderEnnemies = BorderFactory.createTitledBorder("Ennemies");
		layoutEnnemies = new GridLayout();
		layoutEnnemies.setColumns(Constante.NB_COLUMN_ENNEMIE);
		layoutEnnemies.setRows(Constante.NB_ROW_ENNEMIE);
		ceriseButton = new JButton("cerise");
		orangeButton = new JButton("orange");
		pommeButton = new JButton("pomme");
		litchiButton = new JButton("litchi");
		fraiseButton = new JButton("fraise");
		framboiseButton = new JButton("framboise");
		citronButton = new JButton("citron");
		abricotButton = new JButton("abricot");
		abricotnainsButton = new JButton("abricot nain");
		annanasButton = new JButton("annanas");
		kiwiButton = new JButton("kiwi");
		pastequeButton = new JButton("pastèque");
		pruneButton = new JButton("prune");
		scieButton = new JButton("scie");
		bananeButton = new JButton("Banane");

		// element
		panelElement = new JPanel();
		borderElement = BorderFactory.createTitledBorder("Element");
		layoutElement = new GridLayout();
		layoutElement.setColumns(Constante.COL_ELEMENT_PANEL);
		layoutElement.setRows(Constante.ROW_ELEMENT_PANEL);
		selectButton = new JButton("Select");
		deleteButton = new JButton("Delete");
		verticalPlatformButton = new JButton("VPF");
		horizontalPlatformButton = new JButton("HPF");
		vortexButton = new JButton("Vortex");
		teleporterButton = new JButton("Teleporter");
		rayonButton = new JButton("Rayon");
		pickButton = new JButton("Pick");
		eventButton = new JButton("Event");
		startButton = new JButton("player spawn");
		pointButton = new JButton("point spawn");
		effectButton = new JButton("effect spawn");

		// properties
		panelParameters = new JPanel();
		layoutParameters = new GridLayout();
		layoutParameters.setColumns(Constante.NB_COLUMN_PARAMETER);
		layoutParameters.setRows(Constante.NB_ROW_PARAMETER);
		borderParameters = BorderFactory.createTitledBorder("Properties");
		backgroundId = new JTextField();
	}

	private void initListeners() {

		drawPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				click(e.getX(), e.getY());
				LOG.info("clic on position : " + e.getX() + " " + e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pressed(e.getX(), e.getY());
				LOG.info("mouse pressed : " + e.getX() + " " + e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				release(e.getX(), e.getY());
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

		// navigation
		currentLevelIndex.addCaretListener(new CaretListener() {
			public void caretUpdate(javax.swing.event.CaretEvent e) {
				JTextField text = (JTextField) e.getSource();
				if (text.getText() != null && !text.getText().isEmpty()) {
					levelSelected = Integer.parseInt(text.getText());
					drawPanel.updateLevel(levels.get(levelSelected));
				}
				LOG.info("ChangeLevel : " + text.getText());
			}
		});
		currentLevelIndex.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
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
		ceriseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_CERISE;
			}
		});
		orangeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_ORANGE;
			}
		});
		pommeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_POMME;
			}
		});
		bananeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_BANANE;
			}
		});
		litchiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_LITCHI;
			}
		});
		fraiseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_FRAISE;
			}
		});
		framboiseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_FRAMBOISE;
			}
		});
		citronButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_CITRON;
			}
		});
		abricotButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_ABRICOT;
			}
		});
		abricotnainsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_ABRICOT_NAIN;
			}
		});
		annanasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_ANANAS;
			}
		});
		kiwiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_KIWI;
			}
		});
		pastequeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_PASTEQUE;
			}
		});
		pruneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_PRUNE;
			}
		});
		scieButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_SCIE;
			}
		});
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.SELECT;
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.DELETE;
			}
		});
		verticalPlatformButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOG.info("draw VPF");
				action = ActionEnum.DRAW_VERTICAL_PLATFORM;
			}
		});
		horizontalPlatformButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.DRAW_HORIZONTAL_PLATFORM;
			}
		});
		vortexButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_VORTEX;
			}
		});
		teleporterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_TELEPORTER;
			}
		});
		rayonButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_RAYON;
			}
		});
		pickButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_PICK;
			}
		});
		eventButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_EVENT;
			}
		});
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_PLAYER_SPAWN;
			}
		});
		pointButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_OBJECT_POINT;
			}
		});
		effectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_OBJECT_EFFECT;
			}
		});

	}

	private void release(int x, int y) {
		int caseX = x / Constante.GRID_SIZE;
		int caseY = y / Constante.GRID_SIZE;
		LOG.info("x : " + caseX + " y : " + caseY);
		switch (action) {
		case DRAW_VERTICAL_PLATFORM:
			addVerticalPlatform(xFirst, yFirst, caseY);
			break;
		case DRAW_HORIZONTAL_PLATFORM:
			addHorizontalPlatform(xFirst, yFirst, caseX);
			break;
		case ADD_TELEPORTER:
			addTeleporter(xFirst, yFirst, caseX, caseY);
			break;
		case ADD_RAYON:
			addRayon(xFirst, yFirst, caseX, caseY);
			break;
		case SELECT:
		case DELETE:
		case ADD_VORTEX:
		case ADD_PICK:
		case ADD_EVENT:
		case ADD_PLAYER_SPAWN:
		case ADD_OBJECT_POINT:
		case ADD_OBJECT_EFFECT:
		case ADD_CERISE:
		case ADD_ORANGE:
		case ADD_POMME:
		case ADD_BANANE:
		case ADD_LITCHI:
		case ADD_FRAISE:
		case ADD_FRAMBOISE:
		case ADD_CITRON:
		case ADD_ABRICOT:
		case ADD_ABRICOT_NAIN:
		case ADD_ANANAS:
		case ADD_KIWI:
		case ADD_PASTEQUE:
		case ADD_PRUNE:
		case ADD_SCIE:

		}
	}

	private void pressed(int x, int y) {
		int caseX = x / Constante.GRID_SIZE;
		int caseY = y / Constante.GRID_SIZE;
		LOG.info("x : " + caseX + " y : " + caseY);
		switch (action) {

		case DRAW_VERTICAL_PLATFORM:
		case DRAW_HORIZONTAL_PLATFORM:
		case ADD_TELEPORTER:
		case ADD_RAYON:
			xFirst = caseX;
			yFirst = caseY;
			break;
		case SELECT:
		case DELETE:
		case ADD_VORTEX:
		case ADD_PICK:
		case ADD_EVENT:
		case ADD_PLAYER_SPAWN:
		case ADD_OBJECT_POINT:
		case ADD_OBJECT_EFFECT:
		case ADD_CERISE:
		case ADD_ORANGE:
		case ADD_POMME:
		case ADD_BANANE:
		case ADD_LITCHI:
		case ADD_FRAISE:
		case ADD_FRAMBOISE:
		case ADD_CITRON:
		case ADD_ABRICOT:
		case ADD_ABRICOT_NAIN:
		case ADD_ANANAS:
		case ADD_KIWI:
		case ADD_PASTEQUE:
		case ADD_PRUNE:
		case ADD_SCIE:

		}
	}

	private void click(int x, int y) {
		int caseX = x / Constante.GRID_SIZE;
		int caseY = y / Constante.GRID_SIZE;
		LOG.info("x : " + caseX + " y : " + caseY);

		switch (action) {
		case SELECT:
		case DELETE:
		case DRAW_VERTICAL_PLATFORM:
		case DRAW_HORIZONTAL_PLATFORM:
		case ADD_VORTEX:
		case ADD_TELEPORTER:
		case ADD_RAYON:
		case ADD_PICK:
		case ADD_EVENT:
		case ADD_PLAYER_SPAWN:
		case ADD_OBJECT_POINT:
		case ADD_OBJECT_EFFECT:
		case ADD_CERISE:
		case ADD_ORANGE:
		case ADD_POMME:
		case ADD_BANANE:
		case ADD_LITCHI:
		case ADD_FRAISE:
		case ADD_FRAMBOISE:
		case ADD_CITRON:
		case ADD_ABRICOT:
		case ADD_ABRICOT_NAIN:
		case ADD_ANANAS:
		case ADD_KIWI:
		case ADD_PASTEQUE:
		case ADD_PRUNE:
		case ADD_SCIE:

		}
	}

	private void addRayon(int xFirst2, int yFirst2, int caseX, int caseY) {

	}

	private void addTeleporter(int xFirst2, int yFirst2, int caseX, int caseY) {

	}

	private void addHorizontalPlatform(int x, int y, int x2) {
		Level level = levels.get(levelSelected);
		int nbPlatform = level.getPlatform().size();
		Platform p = new Platform();
		p.setId(nbPlatform);
		p.setVertical(false);
		p.setVisible(true);
		int l = x2 - x;
		if (l < 0) {
			l *=-1;
		}
		l++;
		if (x2 > x) {
			p.setX(x);
		} else {
			p.setX(x2);
		}
		p.setLength(l);
		p.setY(y);
		level.getPlatform().add(p);
		levels.put(level.getId(), level);
		drawPanel.repaint();
	}

	private void addVerticalPlatform(int x, int y, int y2) {
		Level level = levels.get(levelSelected);
		int nbPlatform = level.getPlatform().size();
		Platform p = new Platform();
		p.setId(nbPlatform);
		p.setVertical(true);
		p.setVisible(true);
		int l = y2 - y;
		if (l < 0) {
			l *=-1;
		}
		l++;
		if (y2 > y) {
			p.setY(y);
		} else {
			p.setY(y2);
		}
		p.setLength(l);
		p.setX(x);
		level.getPlatform().add(p);
		levels.put(level.getId(), level);
		drawPanel.repaint();
	}

}
