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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.constant.ActionEnum;
import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.service.FileService;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.service.SpriteService;
import lr_in_the_well.alexis_puska.view.DrawPanel;

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

	private void buildWestPanel() {
		westLayout.setRows(2);
		westPanel.setLayout(westLayout);
		this.getContentPane().add(westPanel, BorderLayout.WEST);
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

		/**********************
		 *
		 * NAVIGATION
		 * 
		 ***********************/
		currentLevelIndex.addCaretListener(new CaretListener() {
			public void caretUpdate(javax.swing.event.CaretEvent e) {
				JTextField text = (JTextField) e.getSource();
				if (text.getText() != null && !text.getText().isEmpty()) {
					levelService.setCurrentLevel(Integer.parseInt(text.getText()));
					drawPanel.repaint();
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
		case ADD_BLOB:
		case ADD_POIRE:
			break;
		}
	}

	private void click(int x, int y) {
		int caseX = x / Constante.GRID_SIZE;
		int caseY = y / Constante.GRID_SIZE;
		LOG.info("x : " + caseX + " y : " + caseY);

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

	private void selectElement(int x, int y) {
		// TODO Auto-generated method stub

	}

	private void addRayon(int xFirst2, int yFirst2, int caseX, int caseY) {

	}

	private void addTeleporter(int xFirst2, int yFirst2, int caseX, int caseY) {

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

	private void deleteElement(int x, int y) {
		levelService.deleteElement(x, y);
		drawPanel.repaint();
	}
}
