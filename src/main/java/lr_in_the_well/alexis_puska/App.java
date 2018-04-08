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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.constant.ActionEnum;
import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.domain.level.Decor;
import lr_in_the_well.alexis_puska.domain.level.Door;
import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.domain.level.Identifiable;
import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.domain.level.Teleporter;
import lr_in_the_well.alexis_puska.service.FileService;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.service.SpriteService;
import lr_in_the_well.alexis_puska.view.BackgroundDrawPanel;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.PlatformDrawPanel;

public class App extends JFrame {

	private final static Logger LOG = Logger.getLogger(App.class);

	private static final long serialVersionUID = -8256444946608935363L;

	// services
	private final FileService fileService;
	private final SpriteService spriteService;
	private final LevelService levelService;

	private int xFirst;
	private int yFirst;
	private ActionEnum action;

	// West Panel
	private JPanel westPanel;
	private GridLayout westLayout;

	// EastPanel
	private JPanel eastPanel;
	private GridLayout eastLayout;
	private JPanel platformPanel;
	private Border platformBorder;
	private PlatformDrawPanel platformDrawPanel;
	private JPanel backgroundPanel;
	private Border backgroundBorder;
	private BackgroundDrawPanel backgroundDrawPanel;

	// draw
	private JPanel panelDraw;
	private DrawPanel drawPanel;

	// navigations
	private JPanel panelNavigation;
	private Border borderNavigation;
	private GridLayout layoutNavigation;
	private JLabel labelNextLevel;
	private JButton nextLevel;
	private JButton previousLevel;
	private JButton addLevel;
	private JButton delLevel;
	private JTextField currentLevelIndex;
	private JButton chooseFile;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter fileChooserFilter;

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
	private JButton poireButton;
	private JButton blobButton;

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
		this.levelService = new LevelService(
				fileService.readJsonFile(this.getClass().getResourceAsStream("/json/json_level_parser.json")));
		this.action = ActionEnum.SELECT;
	}

	private void Launch() {
		LOG.info("Nb level in file : " + levelService.getNbLevel());
		this.getContentPane().setLayout(new BorderLayout());
		initComponent();
		initListeners();
		buildWestPanel();
		buildEastPanel();
		buildDrawElement();
		buildNavigationPanelButton();
		buildElementPanelButton();
		buildEnnemiePanelButton();
		buildParameterPanelButton();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(1400, 900);
	}

	private void buildDrawElement() {
		panelDraw.setSize(800, 600);
		panelDraw.setBackground(Color.LIGHT_GRAY);
		panelDraw.add(drawPanel);
		drawPanel.setSize(Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);
		drawPanel.setVisible(true);
		this.getContentPane().add(panelDraw, BorderLayout.CENTER);
	}

	private void buildWestPanel() {
		westLayout.setRows(2);
		westPanel.setLayout(westLayout);
		this.getContentPane().add(westPanel, BorderLayout.WEST);
	}

	private void buildEastPanel() {
		platformDrawPanel.setSize(300, Constante.SCREEN_SIZE_Y);
		platformDrawPanel.setVisible(true);
		platformPanel.setBorder(platformBorder);
		platformPanel.add(platformDrawPanel);

		backgroundDrawPanel.setSize(300, Constante.SCREEN_SIZE_Y);
		backgroundDrawPanel.setVisible(true);
		backgroundPanel.setBorder(backgroundBorder);
		backgroundPanel.add(backgroundDrawPanel);

		eastLayout.setColumns(2);
		eastPanel.setLayout(eastLayout);
		eastPanel.add(platformPanel);
		eastPanel.add(backgroundPanel);

		this.getContentPane().add(eastPanel, BorderLayout.EAST);
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
		panelEnnemies.add(poireButton);
		panelEnnemies.add(blobButton);
		westPanel.add(panelEnnemies);
	}

	private void buildNavigationPanelButton() {
		panelNavigation.setBorder(borderNavigation);
		panelNavigation.setLayout(layoutNavigation);
		currentLevelIndex.setText(Integer.toString(levelService.getCurrentLevelIndex()));
		panelNavigation.add(previousLevel);
		panelNavigation.add(labelNextLevel);
		panelNavigation.add(currentLevelIndex);
		panelNavigation.add(nextLevel);
		panelNavigation.add(addLevel);
		panelNavigation.add(delLevel);
		panelNavigation.add(chooseFile);
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
		westPanel.add(panelElement);
	}

	private void buildParameterPanelButton() {
		panelParameters.setLayout(layoutParameters);
		panelParameters.setBorder(borderParameters);
		panelParameters.add(backgroundId);

		this.getContentPane().add(panelParameters, BorderLayout.SOUTH);
	}

	private void nextLevel() {
		currentLevelIndex.setText(Integer.toString(levelService.incCurrentLevelIndex()));
		drawPanel.repaint();
	}

	private void previousLevel() {
		currentLevelIndex.setText(Integer.toString(levelService.decCurrentLevelIndex()));
		drawPanel.repaint();
	}

	private void initComponent() {

		fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooserFilter = new FileNameExtensionFilter("Hammerfest JSON level file", "json");
		fileChooser.setFileFilter(fileChooserFilter);

		// westPanel
		westPanel = new JPanel();
		westLayout = new GridLayout();

		// EastPanel
		eastPanel = new JPanel();
		eastLayout = new GridLayout();
		platformPanel = new JPanel();
		platformBorder = BorderFactory.createTitledBorder("Platform");
		platformDrawPanel = new PlatformDrawPanel(spriteService);
		backgroundPanel = new JPanel();
		backgroundBorder = BorderFactory.createTitledBorder("Background");
		backgroundDrawPanel = new BackgroundDrawPanel(spriteService);

		// draw
		panelDraw = new JPanel();
		drawPanel = new DrawPanel(spriteService, levelService);

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
		chooseFile = new JButton("Choose file");
		currentLevelIndex = new JTextField();
		labelNextLevel = new JLabel("Image and Text", JLabel.CENTER);
		labelNextLevel.setLabelFor(currentLevelIndex);
		labelNextLevel.setVerticalTextPosition(JLabel.CENTER);
		labelNextLevel.setHorizontalTextPosition(JLabel.LEFT);

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
		poireButton = new JButton("poire");
		blobButton = new JButton("blob");
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

		/**********************
		 *
		 * DRAW
		 * 
		 ***********************/
		drawPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				click(e.getX(), e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pressed(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				release(e.getX(), e.getY());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		/**********************
		 *
		 * NAVIGATION
		 * 
		 ***********************/
		currentLevelIndex.addCaretListener(new CaretListener() {
			public void caretUpdate(javax.swing.event.CaretEvent e) {
				JTextField text = (JTextField) e.getSource();
				if (text.getText() != null && !text.getText().isEmpty()) {
					LOG.info("ChangeLevel : " + text.getText());
					levelService.setCurrentLevel(Integer.parseInt(text.getText()));
					drawPanel.repaint();
				}
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
		chooseFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fileChooser.showOpenDialog(panelNavigation);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + fileChooser.getSelectedFile().getName());
				}
			}
		});

		/**********************
		 *
		 * ENNEMIES
		 * 
		 ***********************/
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
		poireButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_POIRE;
			}
		});
		blobButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_BLOB;
			}
		});

		/**********************
		 *
		 * ELEMENT
		 * 
		 ***********************/
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
			break;
		case ADD_DECOR:
			this.addDecor(caseX, caseY);
			break;
		case ADD_VORTEX:
			this.addVortex(caseX, caseY);
			break;
		case ADD_PICK:
			this.addPick(caseX, caseY);
			break;
		case ADD_EVENT:
			this.addEvent(caseX, caseY);
			break;
		case ADD_PLAYER_SPAWN:
			this.addPlayerSpawn(caseX, caseY);
			break;
		case ADD_OBJECT_POINT:
			this.addObjectPoint(caseX, caseY);
			break;
		case ADD_OBJECT_EFFECT:
			this.addObjectEffect(caseX, caseY);
			break;
		case ADD_CERISE:
			this.addEnnemie(caseX, caseY, 0);
			break;
		case ADD_ORANGE:
			this.addEnnemie(caseX, caseY, 1);
			break;
		case ADD_POMME:
			this.addEnnemie(caseX, caseY, 2);
			break;
		case ADD_BANANE:
			this.addEnnemie(caseX, caseY, 3);
			break;
		case ADD_LITCHI:
			this.addEnnemie(caseX, caseY, 8);
			break;
		case ADD_FRAISE:
			this.addEnnemie(caseX, caseY, 9);
			break;
		case ADD_FRAMBOISE:
			this.addEnnemie(caseX, caseY, 14);
			break;
		case ADD_CITRON:
			this.addEnnemie(caseX, caseY, 4);
			break;
		case ADD_ABRICOT:
			this.addEnnemie(caseX, caseY, 7);
			break;
		case ADD_ABRICOT_NAIN:
			this.addEnnemie(caseX, caseY, 15);
			break;
		case ADD_ANANAS:
			this.addEnnemie(caseX, caseY, 12);
			break;
		case ADD_KIWI:
			this.addEnnemie(caseX, caseY, 10);
			break;
		case ADD_PASTEQUE:
			this.addEnnemie(caseX, caseY, 11);
			break;
		case ADD_PRUNE:
			this.addEnnemie(caseX, caseY, 5);
			break;
		case ADD_SCIE:
			this.addEnnemie(caseX, caseY, 16);
			break;
		case ADD_POIRE:
			this.addEnnemie(caseX, caseY, 6);
			break;
		case ADD_BLOB:
			this.addEnnemie(caseX, caseY, 13);
			break;
		}
	}

	private void pressed(int x, int y) {
		int caseX = x / Constante.GRID_SIZE;
		int caseY = y / Constante.GRID_SIZE;
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
		case ADD_DECOR:
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
		case ADD_BLOB:
		case ADD_POIRE:
			break;
		}
	}

	private void click(int x, int y) {
		int caseX = x / Constante.GRID_SIZE;
		int caseY = y / Constante.GRID_SIZE;
		switch (action) {
		case SELECT:
			selectElement(caseX, caseY);
			break;
		case DELETE:
			deleteElement(caseX, caseY);
			break;
		case DRAW_VERTICAL_PLATFORM:
		case DRAW_HORIZONTAL_PLATFORM:
		case ADD_VORTEX:
		case ADD_DECOR:
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
		case ADD_BLOB:
		case ADD_POIRE:
			break;
		}
	}

	private void addRayon(int x, int y, int x2, int y2) {
		levelService.addRayon(x, y, x2, y2);
		drawPanel.repaint();
	}

	private void addTeleporter(int x, int y, int x2, int y2) {
		levelService.addTeleporter(x, y, x2, y2);
		drawPanel.repaint();
	}

	private void addHorizontalPlatform(int x, int y, int x2) {
		levelService.addPlatform(x, y, x2, false);
		drawPanel.repaint();
	}

	private void addVerticalPlatform(int x, int y, int y2) {
		levelService.addPlatform(x, y, y2, true);
		drawPanel.repaint();
	}

	private void addEnnemie(int x, int y, int type) {
		levelService.addEnnemie(x, y, type);
		drawPanel.repaint();
	}

	private void addDecor(int x, int y) {
		levelService.addDecor(x, y);
		drawPanel.repaint();
	}

	private void addVortex(int x, int y) {
		levelService.addVortex(x, y);
		drawPanel.repaint();
	}

	private void addPick(int x, int y) {
		levelService.addPick(x, y);
		drawPanel.repaint();
	}

	private void addEvent(int x, int y) {
		levelService.addEvent(x, y);
		drawPanel.repaint();
	}

	private void addPlayerSpawn(int x, int y) {
		levelService.addPlayerSpawn(x, y);
		drawPanel.repaint();
	}

	private void addObjectPoint(int x, int y) {
		levelService.addObjectPoint(x, y);
		drawPanel.repaint();
	}

	private void addObjectEffect(int x, int y) {
		levelService.addObjectEffect(x, y);
		drawPanel.repaint();
	}

	private void deleteElement(int x, int y) {
		levelService.deleteElement(x, y);
		drawPanel.repaint();
	}

	private void selectElement(int x, int y) {
		Identifiable obj = levelService.getProperties(x, y);
		if (obj != null) {
			if (obj.getClass().equals(Ennemie.class)) {
				treatEnnemieProperties((Ennemie) obj);
			} else if (obj.getClass().equals(Decor.class)) {
				treatDecorProperties((Decor) obj);
			} else if (obj.getClass().equals(Door.class)) {
				treatDoorProperties((Door) obj);
			} else if (obj.getClass().equals(Lock.class)) {
				treatLockProperties((Lock) obj);
			} else if (obj.getClass().equals(Pick.class)) {
				treatPickProperties((Pick) obj);
			} else if (obj.getClass().equals(Platform.class)) {
				treatPlatformProperties((Platform) obj);
			} else if (obj.getClass().equals(Rayon.class)) {
				treatRayonProperties((Rayon) obj);
			} else if (obj.getClass().equals(Teleporter.class)) {
				treatTeleporterProperties((Teleporter) obj);
			}
		}
	}

	private void treatEnnemieProperties(Ennemie ennemie) {
		LOG.info("build ennemie parameters panel ! : " + ennemie.getId());
	}

	private void treatDecorProperties(Decor decor) {
		LOG.info("build decor parameters panel ! : " + decor.getId());
	}

	private void treatDoorProperties(Door door) {
		LOG.info("build door parameters panel ! : " + door.getId());
	}

	private void treatLockProperties(Lock lock) {
		LOG.info("build lock parameters panel ! : " + lock.getId());
	}

	private void treatPickProperties(Pick pick) {
		LOG.info("build pick parameters panel ! : " + pick.getId());
	}

	private void treatPlatformProperties(Platform platform) {
		LOG.info("build platform parameters panel ! : " + platform.getId());
	}

	private void treatRayonProperties(Rayon rayon) {
		LOG.info("build rayon parameters panel ! : " + rayon.getId());
	}

	private void treatTeleporterProperties(Teleporter teleporter) {
		LOG.info("build teleporter parameters panel ! : " + teleporter.getId());
	}
}
