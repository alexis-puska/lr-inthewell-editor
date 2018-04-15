package lr_in_the_well.alexis_puska;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;

import lr_in_the_well.alexis_puska.constant.ActionEnum;
import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.domain.level.Decor;
import lr_in_the_well.alexis_puska.domain.level.Door;
import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.domain.level.Event;
import lr_in_the_well.alexis_puska.domain.level.Identifiable;
import lr_in_the_well.alexis_puska.domain.level.Item;
import lr_in_the_well.alexis_puska.domain.level.LevelFile;
import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.domain.level.Teleporter;
import lr_in_the_well.alexis_puska.domain.level.Vortex;
import lr_in_the_well.alexis_puska.service.FileService;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.service.SpriteService;
import lr_in_the_well.alexis_puska.view.BackgroundDrawPanel;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.PlatformDrawPanel;
import lr_in_the_well.alexis_puska.view.properties.DecorPanel;
import lr_in_the_well.alexis_puska.view.properties.DoorPanel;
import lr_in_the_well.alexis_puska.view.properties.EnnemiePanel;
import lr_in_the_well.alexis_puska.view.properties.EventPanel;
import lr_in_the_well.alexis_puska.view.properties.ItemPanel;
import lr_in_the_well.alexis_puska.view.properties.LockPanel;
import lr_in_the_well.alexis_puska.view.properties.PickPanel;
import lr_in_the_well.alexis_puska.view.properties.PlatformPanel;
import lr_in_the_well.alexis_puska.view.properties.RayonPanel;
import lr_in_the_well.alexis_puska.view.properties.TeleporterPanel;
import lr_in_the_well.alexis_puska.view.properties.VortexPanel;

public class App extends JFrame {

	private final static Logger LOG = Logger.getLogger(App.class);

	private static final long serialVersionUID = -8256444946608935363L;
	private static final int OFFSET = 10;

	// services
	private final FileService fileService;
	private final SpriteService spriteService;
	private final LevelService levelService;

	// traduction
	private final Locale locale;
	private final ResourceBundle message;

	private String absolutePathFile;
	private int xFirst;
	private int yFirst;
	private ActionEnum action;

	/****************
	 * WEST PANEL
	 ***************/
	private JPanel westPanel;
	private GridLayout westLayout;

	/****************
	 * EAST PANEL
	 ***************/
	private JPanel eastPanel;
	private GridLayout eastLayout;
	private JPanel platformPanel;
	private Border platformBorder;
	private PlatformDrawPanel platformDrawPanel;
	private JPanel backgroundPanel;
	private Border backgroundBorder;
	private BackgroundDrawPanel backgroundDrawPanel;

	/****************
	 * DRAW
	 ***************/
	private JPanel centerPanel;
	private BorderLayout drawLayout;
	private DrawPanel drawPanel;

	/****************
	 * properties
	 ***************/
	private JPanel identifiablePropertiesPanel;

	/****************
	 * NAVIGATION
	 ***************/
	private JPanel panelNavigation;
	private Border borderNavigation;
	private GridLayout layoutNavigation;
	// currentLevelType
	private JPanel currentTypePanel;
	private GridLayout currentTypeLayout;
	private Border currentTypeBorder;
	private JLabel currentTypeLevel;
	private SpinnerNumberModel currentTypeLevelIndexModel;
	private JSpinner currentTypeLevelIndex;
	// currentLevelIndex
	private JPanel currentLevelPanel;
	private GridLayout currentLevelLayout;
	private Border currentLevelBorder;
	private JButton addLevel;
	private JButton delLevel;
	private SpinnerNumberModel currentLevelIndexModel;
	private JSpinner currentLevelIndex;
	// file
	private JPanel filePanel;
	private GridLayout fileLayout;
	private Border fileBorder;
	private JButton openSaveFileChooser;
	private JFileChooser saveFileChooser;
	private JButton openLoadFileChooser;
	private JFileChooser loadFileChooser;
	private FileNameExtensionFilter loadFileChooserFilter;
	private FileNameExtensionFilter saveFileChooserFilter;

	/****************
	 * ENNEMIES
	 ***************/
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

	/****************
	 * ELEMENTS
	 ***************/
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
	private JButton doorButton;
	private JButton lockButton;
	private JButton eventButton;
	private JButton startButton;
	private JButton pointButton;
	private JButton effectButton;
	private JButton decorButton;
	private JButton itemButton;

	/********************
	 * LEVEL PROPERTIES
	 *******************/
	private JPanel panelParameters;
	private GridLayout layoutParameters;
	private Border borderParameters;
	private JPanel verticalPlatformIndexPanel;
	private GridLayout verticalPlatformLayout;
	private Border verticalPlatformIndexBorder;
	private SpinnerNumberModel verticalPlatformIndexModel;
	private JSpinner verticalPlatformIndexSpinner;
	private JPanel backgroundIndexPanel;
	private GridLayout backgroundLayout;
	private Border backgroundIndexBorder;
	private SpinnerNumberModel backgroundIndexModel;
	private JSpinner backgroundIndexSpinner;
	private JPanel horizontalPlatformIndexPanel;
	private GridLayout horizontalPlatformLayout;
	private Border horizontalPlatformIndexBorder;
	private SpinnerNumberModel horizontalPlatformIndexModel;
	private JSpinner horizontalPlatformIndexSpinner;

	private JPanel nextLevelIndexPanel;
	private GridLayout nextLevelIndexLayout;
	private Border nextLevelIndexBorder;
	private JTextField nextLevelIndexTextField;

	private JPanel showPlatformLevelPanel;
	private GridLayout showPlatformLevelLayout;
	private Border showPlatformLevelBorder;
	private JCheckBox showPlatformLevelCheckBox;

	public static void main(String[] args) {
		String lang = "fr";
		App app;
		if (args != null && args.length > 0) {
			if (args[0].equals("fr") || args[0].equals("en")) {
				lang = args[0];
			}
		}
		app = new App(lang);
		app.Launch();
	}

	public App(String lang) {
		LOG.info("Welcome in lr-inthewell-editor App !");
		this.fileService = new FileService();
		this.spriteService = new SpriteService(fileService);
		this.levelService = new LevelService();
		this.locale = Locale.forLanguageTag(lang);
		this.message = ResourceBundle.getBundle("i18n/Message", locale);

		LevelFile levelFile = new LevelFile();
		levelFile.setType(new ArrayList<>());
		for (int i = 0; i <= Constante.MAX_TYPE_ID; i++) {
			lr_in_the_well.alexis_puska.domain.level.Type type = new lr_in_the_well.alexis_puska.domain.level.Type();
			type.setId(i);
			type.setLevel(new ArrayList<>());
			levelFile.getType().add(type);
		}
		this.levelService.putLevelFile(levelFile);
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
		this.setSize(Constante.APP_SIZE_X, Constante.APP_SIZE_Y);
	}

	/*************************************************************************************
	 * 
	 * --- BUILD FUNCTION ---
	 * 
	 *************************************************************************************/
	private void buildDrawElement() {
		drawPanel.setSize(Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);
		drawPanel.setVisible(true);
		centerPanel.setBackground(Color.LIGHT_GRAY);
		centerPanel.setLayout(drawLayout);
		centerPanel.add(drawPanel, BorderLayout.CENTER);
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
	}

	private void buildWestPanel() {
		westLayout.setRows(2);
		westPanel.setLayout(westLayout);
		this.getContentPane().add(westPanel, BorderLayout.WEST);
	}

	private void buildEastPanel() {
		platformDrawPanel.setSize(Constante.PANEL_PLATFORM_BACKGROUD_WIDTH, Constante.SCREEN_SIZE_Y);
		platformDrawPanel.setVisible(true);
		platformPanel.setBorder(platformBorder);
		platformPanel.add(platformDrawPanel);
		backgroundDrawPanel.setSize(Constante.PANEL_PLATFORM_BACKGROUD_WIDTH, Constante.SCREEN_SIZE_Y);
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
		currentLevelIndexModel.setMinimum(Constante.MIN_LEVEL_ID);
		currentLevelIndex.setModel(currentLevelIndexModel);
		currentLevelPanel.setLayout(currentLevelLayout);
		currentLevelPanel.setBorder(currentLevelBorder);
		currentLevelPanel.add(currentLevelIndex);
		currentLevelPanel.add(addLevel);
		currentLevelPanel.add(delLevel);
		currentTypeLevelIndexModel.setMinimum(Constante.MIN_TYPE_ID);
		currentTypeLevelIndexModel.setMaximum(Constante.MAX_TYPE_ID);
		currentTypeLevelIndex.setModel(currentTypeLevelIndexModel);
		currentTypePanel.setLayout(currentTypeLayout);
		currentTypePanel.setBorder(currentTypeBorder);
		currentTypePanel.add(currentTypeLevel);
		currentTypePanel.add(currentTypeLevelIndex);
		filePanel.setLayout(fileLayout);
		filePanel.setBorder(fileBorder);
		filePanel.add(openLoadFileChooser);
		filePanel.add(openSaveFileChooser);
		panelNavigation.setBorder(borderNavigation);
		panelNavigation.setLayout(layoutNavigation);
		panelNavigation.add(filePanel);
		panelNavigation.add(currentTypePanel);
		panelNavigation.add(currentLevelPanel);
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
		panelElement.add(doorButton);
		panelElement.add(lockButton);
		panelElement.add(eventButton);
		panelElement.add(startButton);
		panelElement.add(pointButton);
		panelElement.add(effectButton);
		panelElement.add(decorButton);
		panelElement.add(itemButton);
		westPanel.add(panelElement);
	}

	private void buildParameterPanelButton() {
		verticalPlatformIndexModel.setMinimum(Constante.MIN_PLATFORM_ID);
		verticalPlatformIndexModel.setMaximum(Constante.MAX_PLATFORM_ID);
		verticalPlatformIndexSpinner.setModel(verticalPlatformIndexModel);
		verticalPlatformIndexPanel.setBorder(verticalPlatformIndexBorder);
		verticalPlatformIndexPanel.setLayout(verticalPlatformLayout);
		verticalPlatformIndexPanel.add(verticalPlatformIndexSpinner);
		horizontalPlatformIndexModel.setMinimum(Constante.MIN_PLATFORM_ID);
		horizontalPlatformIndexModel.setMaximum(Constante.MAX_PLATFORM_ID);
		horizontalPlatformIndexSpinner.setModel(horizontalPlatformIndexModel);
		horizontalPlatformIndexPanel.setBorder(horizontalPlatformIndexBorder);
		horizontalPlatformIndexPanel.setLayout(horizontalPlatformLayout);
		horizontalPlatformIndexPanel.add(horizontalPlatformIndexSpinner);
		backgroundIndexModel.setMinimum(Constante.MIN_BACKGROUND_ID);
		backgroundIndexModel.setMaximum(Constante.MAX_BACKGROUND_ID);
		backgroundIndexSpinner.setModel(backgroundIndexModel);
		backgroundIndexPanel.setBorder(backgroundIndexBorder);
		backgroundIndexPanel.setLayout(backgroundLayout);
		backgroundIndexPanel.add(backgroundIndexSpinner);
		nextLevelIndexPanel.setBorder(nextLevelIndexBorder);
		nextLevelIndexPanel.setLayout(nextLevelIndexLayout);
		nextLevelIndexPanel.add(nextLevelIndexTextField);
		showPlatformLevelPanel.setBorder(showPlatformLevelBorder);
		showPlatformLevelPanel.setLayout(showPlatformLevelLayout);
		showPlatformLevelPanel.add(showPlatformLevelCheckBox);
		panelParameters.setLayout(layoutParameters);
		panelParameters.setBorder(borderParameters);
		panelParameters.add(verticalPlatformIndexPanel);
		panelParameters.add(horizontalPlatformIndexPanel);
		panelParameters.add(backgroundIndexPanel);
		panelParameters.add(nextLevelIndexPanel);
		panelParameters.add(showPlatformLevelPanel);
		this.getContentPane().add(panelParameters, BorderLayout.SOUTH);
	}

	/*************************************************************************************
	 *
	 * --- INIT COMPONENT ---
	 * 
	 *************************************************************************************/
	private void initComponent() {

		// westPanel
		westPanel = new JPanel();
		westLayout = new GridLayout();

		// EastPanel
		eastPanel = new JPanel();
		eastLayout = new GridLayout();
		platformPanel = new JPanel();
		platformBorder = BorderFactory.createTitledBorder(message.getString("platform.border"));
		platformDrawPanel = new PlatformDrawPanel(spriteService);
		backgroundPanel = new JPanel();
		backgroundBorder = BorderFactory.createTitledBorder(message.getString("background.border"));
		backgroundDrawPanel = new BackgroundDrawPanel(spriteService);

		// draw
		centerPanel = new JPanel();
		drawLayout = new BorderLayout();
		drawPanel = new DrawPanel(spriteService, levelService);

		// navigation
		panelNavigation = new JPanel();
		borderNavigation = BorderFactory.createTitledBorder(message.getString("navigation.border"));
		layoutNavigation = new GridLayout();
		layoutNavigation.setColumns(Constante.NB_COLUMN_NAVIGATION);
		layoutNavigation.setRows(Constante.NB_ROW_NAVIGATION);

		currentLevelPanel = new JPanel();
		currentLevelLayout = new GridLayout();
		currentLevelBorder = BorderFactory.createTitledBorder(message.getString("currentLevel.border"));
		addLevel = new JButton(message.getString("currentLevel.button.add"));
		delLevel = new JButton(message.getString("currentLevel.button.delete"));
		currentLevelIndexModel = new SpinnerNumberModel();
		currentLevelIndex = new JSpinner();

		currentTypePanel = new JPanel();
		currentTypeLayout = new GridLayout();
		currentTypeBorder = BorderFactory.createTitledBorder(message.getString("type.border"));
		currentTypeLevel = new JLabel(message.getString("currentLevel.label"));
		currentTypeLevel.setToolTipText(message.getString("currentLevel.tooltip"));
		currentTypeLevelIndexModel = new SpinnerNumberModel();
		currentTypeLevelIndex = new JSpinner();
		currentTypeLevelIndex.setToolTipText(message.getString("currentLevel.tooltip"));

		// file
		filePanel = new JPanel();
		fileLayout = new GridLayout();
		fileBorder = BorderFactory.createTitledBorder(message.getString("file.border"));

		openLoadFileChooser = new JButton(message.getString("file.open"));
		openSaveFileChooser = new JButton(message.getString("file.save"));
		loadFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		loadFileChooserFilter = new FileNameExtensionFilter(message.getString("file.description"), "json");
		loadFileChooser.setFileFilter(loadFileChooserFilter);
		saveFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		saveFileChooserFilter = new FileNameExtensionFilter(message.getString("file.description"), "json");
		saveFileChooser.setFileFilter(saveFileChooserFilter);

		// ennemies
		panelEnnemies = new JPanel();
		borderEnnemies = BorderFactory.createTitledBorder(message.getString("ennemie.border"));
		layoutEnnemies = new GridLayout();
		layoutEnnemies.setColumns(Constante.NB_COLUMN_ENNEMIE);
		layoutEnnemies.setRows(Constante.NB_ROW_ENNEMIE);
		ceriseButton = new JButton(message.getString("ennemie.type.cerise"));
		orangeButton = new JButton(message.getString("ennemie.type.orange"));
		pommeButton = new JButton(message.getString("ennemie.type.pomme"));
		litchiButton = new JButton(message.getString("ennemie.type.litchi"));
		fraiseButton = new JButton(message.getString("ennemie.type.fraise"));
		framboiseButton = new JButton(message.getString("ennemie.type.framboise"));
		citronButton = new JButton(message.getString("ennemie.type.citron"));
		abricotButton = new JButton(message.getString("ennemie.type.abricot"));
		abricotnainsButton = new JButton(message.getString("ennemie.type.nainBricot"));
		annanasButton = new JButton(message.getString("ennemie.type.annanas"));
		kiwiButton = new JButton(message.getString("ennemie.type.kiwi"));
		pastequeButton = new JButton(message.getString("ennemie.type.pasteque"));
		pruneButton = new JButton(message.getString("ennemie.type.prune"));
		scieButton = new JButton(message.getString("ennemie.type.scie"));
		poireButton = new JButton(message.getString("ennemie.type.poire"));
		blobButton = new JButton(message.getString("ennemie.type.blob"));
		bananeButton = new JButton(message.getString("ennemie.type.banane"));

		// element
		panelElement = new JPanel();
		borderElement = BorderFactory.createTitledBorder(message.getString("element.border"));
		layoutElement = new GridLayout();
		layoutElement.setColumns(Constante.COL_ELEMENT_PANEL);
		layoutElement.setRows(Constante.ROW_ELEMENT_PANEL);
		selectButton = new JButton(message.getString("element.button.select"));
		deleteButton = new JButton(message.getString("element.button.delete"));
		verticalPlatformButton = new JButton(message.getString("element.button.vpf"));
		horizontalPlatformButton = new JButton(message.getString("element.button.hpf"));
		vortexButton = new JButton(message.getString("element.button.vortex"));
		teleporterButton = new JButton(message.getString("element.button.teleporter"));
		rayonButton = new JButton(message.getString("element.button.rayon"));
		pickButton = new JButton(message.getString("element.button.pick"));
		doorButton = new JButton(message.getString("element.button.door"));
		lockButton = new JButton(message.getString("element.button.lock"));
		eventButton = new JButton(message.getString("element.button.event"));
		startButton = new JButton(message.getString("element.button.startPlayer"));
		pointButton = new JButton(message.getString("element.button.point"));
		effectButton = new JButton(message.getString("element.button.effect"));
		decorButton = new JButton(message.getString("element.button.decor"));
		itemButton = new JButton(message.getString("element.button.item"));

		// properties
		panelParameters = new JPanel();
		layoutParameters = new GridLayout();
		layoutParameters.setColumns(Constante.NB_COLUMN_PARAMETER);
		layoutParameters.setRows(Constante.NB_ROW_PARAMETER);
		borderParameters = BorderFactory.createTitledBorder("Properties");

		verticalPlatformIndexPanel = new JPanel();
		verticalPlatformLayout = new GridLayout();
		verticalPlatformIndexBorder = BorderFactory
				.createTitledBorder(message.getString("properties.border.platformVertical"));
		verticalPlatformIndexModel = new SpinnerNumberModel();
		verticalPlatformIndexSpinner = new JSpinner();
		backgroundIndexPanel = new JPanel();
		backgroundLayout = new GridLayout();
		backgroundIndexBorder = BorderFactory.createTitledBorder(message.getString("properties.border.background"));
		backgroundIndexModel = new SpinnerNumberModel();
		backgroundIndexSpinner = new JSpinner();
		horizontalPlatformIndexPanel = new JPanel();
		horizontalPlatformLayout = new GridLayout();
		horizontalPlatformIndexBorder = BorderFactory
				.createTitledBorder(message.getString("properties.border.platformHorizontal"));
		horizontalPlatformIndexModel = new SpinnerNumberModel();
		horizontalPlatformIndexSpinner = new JSpinner();
		nextLevelIndexPanel = new JPanel();
		nextLevelIndexLayout = new GridLayout();
		nextLevelIndexBorder = BorderFactory.createTitledBorder(message.getString("properties.border.nextLevel"));
		nextLevelIndexTextField = new JTextField();
		showPlatformLevelPanel = new JPanel();
		showPlatformLevelLayout = new GridLayout();
		showPlatformLevelBorder = BorderFactory.createTitledBorder(message.getString("properties.border.showPlatform"));
		showPlatformLevelCheckBox = new JCheckBox();

	}

	private void initListeners() {

		/*************************************************************************************
		 *
		 * --- DRAW ---
		 * 
		 *************************************************************************************/
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

		/*************************************************************************************
		 *
		 * --- NAVIGATION ---
		 * 
		 *************************************************************************************/
		currentLevelIndex.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					LOG.info("ChangeLevel : " + (Integer) text.getValue());
					levelService.setCurrentLevelIndex((Integer) text.getValue());
					loadPropertiesLevel();
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
					JSpinner text = (JSpinner) e.getSource();
					if (text.getValue() != null) {
						LOG.info("ChangeLevel : " + (Integer) text.getValue());
						levelService.setCurrentLevelIndex((Integer) text.getValue());
						loadPropertiesLevel();
						drawPanel.repaint();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		currentTypeLevelIndex.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					LOG.info("Change Type : " + (Integer) text.getValue());
					levelService.setCurrentTypeIndex((Integer) text.getValue());
					currentLevelIndex.setValue((Integer) levelService.getCurrentLevelIndex());
					centerPanel.updateUI();
					loadPropertiesLevel();
					drawPanel.repaint();
				}
			}
		});
		currentTypeLevelIndex.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					e.consume();
					JSpinner text = (JSpinner) e.getSource();
					if (text.getValue() != null) {
						LOG.info("Change Type : " + (Integer) text.getValue());
						levelService.setCurrentTypeIndex((Integer) text.getValue());
						currentLevelIndex.setValue((Integer) levelService.getCurrentLevelIndex());
						centerPanel.updateUI();
						loadPropertiesLevel();
						drawPanel.repaint();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		addLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				levelService.createLevel();
				loadPropertiesLevel();
				repaint();
			}
		});
		delLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				levelService.deleteLevel();
				loadPropertiesLevel();
				repaint();
			}
		});
		openLoadFileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = loadFileChooser.showOpenDialog(panelNavigation);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					absolutePathFile = loadFileChooser.getSelectedFile().getAbsolutePath();
					try {
						levelService.putLevelFile(
								fileService.readJsonFile(new FileInputStream(new File(absolutePathFile))));
						currentLevelIndex.setValue((Integer) levelService.getCurrentLevelIndex());
						currentTypeLevelIndex.setValue((Integer) levelService.getCurrentTypeIndex());
						centerPanel.updateUI();
						loadPropertiesLevel();
						repaint();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(
							"You chose to open this file: " + loadFileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		openSaveFileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = saveFileChooser.showSaveDialog(panelNavigation);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					LevelFile levelFile = levelService.getLevelFile();
					absolutePathFile = saveFileChooser.getSelectedFile().getAbsolutePath();
					if (!absolutePathFile.endsWith(".json")) {
						absolutePathFile += ".json";
					}
					fileService.writeJson(levelFile, new File(absolutePathFile));
					centerPanel.updateUI();
					loadPropertiesLevel();
					repaint();
					System.out.println("You chose to open this file: " + absolutePathFile);
				}
			}
		});
		/*************************************************************************************
		 *
		 * --- PROPERTIES LEVEL ---
		 * 
		 *************************************************************************************/

		horizontalPlatformIndexSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					LOG.info("setHorizontalPlatformId : " + (Integer) text.getValue());
					levelService.setHorizontalPlatformId((Integer) text.getValue());
					drawPanel.repaint();
				}
			}
		});
		horizontalPlatformIndexSpinner.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					e.consume();
					JSpinner text = (JSpinner) e.getSource();
					if (text.getValue() != null) {
						LOG.info("setHorizontalPlatformId : " + (Integer) text.getValue());
						levelService.setHorizontalPlatformId((Integer) text.getValue());
						drawPanel.repaint();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		verticalPlatformIndexSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					LOG.info("setVerticalPlatformId : " + (Integer) text.getValue());
					levelService.setVerticalPlatformId((Integer) text.getValue());
					drawPanel.repaint();
				}
			}
		});
		verticalPlatformIndexSpinner.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					e.consume();
					JSpinner text = (JSpinner) e.getSource();
					if (text.getValue() != null) {
						LOG.info("setVerticalPlatformId : " + (Integer) text.getValue());
						levelService.setVerticalPlatformId((Integer) text.getValue());
						drawPanel.repaint();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		backgroundIndexSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					LOG.info("setBackgroundId : " + (Integer) text.getValue());
					levelService.setBackgroundId((Integer) text.getValue());
					drawPanel.repaint();
				}
			}
		});
		backgroundIndexSpinner.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
					e.consume();
					JSpinner text = (JSpinner) e.getSource();
					if (text.getValue() != null) {
						LOG.info("setBackgroundId : " + (Integer) text.getValue());
						levelService.setBackgroundId((Integer) text.getValue());
						drawPanel.repaint();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		nextLevelIndexTextField.addCaretListener(new CaretListener() {
			public void caretUpdate(javax.swing.event.CaretEvent e) {
				JTextField text = (JTextField) e.getSource();
				if (text.getText() != null && !text.getText().isEmpty()) {
					levelService.setNextLevelId(Integer.parseInt(text.getText()));
					drawPanel.repaint();
				}
				LOG.info("NextLevel change : " + text.getText());
			}
		});
		nextLevelIndexTextField.addKeyListener(new KeyListener() {
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

		showPlatformLevelCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("Checked? " + showPlatformLevelCheckBox.isSelected());
				levelService.setShowPlatform(showPlatformLevelCheckBox.isSelected());
			}
		});

		/*************************************************************************************
		 *
		 * --- ENNEMIE ---
		 * 
		 *************************************************************************************/
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

		/*************************************************************************************
		 *
		 * --- ELEMENT ---
		 * 
		 *************************************************************************************/
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
		lockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_LOCK;
			}
		});
		doorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_DOOR;
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
		decorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_DECOR;
			}
		});
		itemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				action = ActionEnum.ADD_ITEM;
			}
		});
	}

	/*************************************************************************************
	 * 
	 * --- DRAW EVENT ---
	 * 
	 *************************************************************************************/

	private void release(int x, int y) {
		int caseX = (x- OFFSET) / Constante.GRID_SIZE ;
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
		case ADD_ITEM:
			this.addItem(caseX, caseY);
			break;
		case ADD_DECOR:
			this.addDecor(x, y);
			break;
		case ADD_LOCK:
			this.addLock(caseX, caseY);
			break;
		case ADD_DOOR:
			this.addDoor(caseX, caseY);
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
		int caseX = (x-OFFSET )/ Constante.GRID_SIZE;
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
		case ADD_ITEM:
		case ADD_VORTEX:
		case ADD_PICK:
		case ADD_DOOR:
		case ADD_LOCK:
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

		switch (action) {
		case SELECT:
			selectElement(x - OFFSET, y);
			break;
		case DELETE:
			deleteElement(x - OFFSET, y);
			break;
		case DRAW_VERTICAL_PLATFORM:
		case DRAW_HORIZONTAL_PLATFORM:
		case ADD_VORTEX:
		case ADD_ITEM:
		case ADD_DECOR:
		case ADD_TELEPORTER:
		case ADD_RAYON:
		case ADD_PICK:
		case ADD_DOOR:
		case ADD_LOCK:
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

	/*************************************************************************************
	 * 
	 * --- TREAT FUNCTION ---
	 * 
	 *************************************************************************************/

	private void addRayon(int x, int y, int x2, int y2) {
		levelService.addRayon(x, y, x2, y2);
		repaint();
	}

	private void addTeleporter(int x, int y, int x2, int y2) {
		levelService.addTeleporter(x, y, x2, y2);
		repaint();
	}

	private void addHorizontalPlatform(int x, int y, int x2) {
		levelService.addPlatform(x, y, x2, false);
		repaint();
	}

	private void addVerticalPlatform(int x, int y, int y2) {
		levelService.addPlatform(x, y, y2, true);
		repaint();
	}

	private void addEnnemie(int x, int y, int type) {
		levelService.addEnnemie(x, y, type);
		repaint();
	}

	private void addDecor(int x, int y) {
		levelService.addDecor(x, y);
		repaint();
	}

	private void addVortex(int x, int y) {
		levelService.addVortex(x, y);
		repaint();
	}

	private void addPick(int x, int y) {
		levelService.addPick(x, y);
		repaint();
	}

	private void addDoor(int x, int y) {
		levelService.addDoor(x, y);
		repaint();
	}

	private void addLock(int x, int y) {
		levelService.addLock(x, y);
		repaint();
	}

	private void addEvent(int x, int y) {
		levelService.addEvent(x, y);
		repaint();
	}

	private void addPlayerSpawn(int x, int y) {
		levelService.addPlayerSpawn(x, y);
		repaint();
	}

	private void addObjectPoint(int x, int y) {
		levelService.addObjectPoint(x, y);
		repaint();
	}

	private void addObjectEffect(int x, int y) {
		levelService.addObjectEffect(x, y);
		repaint();
	}

	private void addItem(int x, int y) {
		levelService.addItem(x, y);
		repaint();
	}

	private void deleteElement(int x, int y) {
		levelService.deleteElement(x, y);
		repaint();
	}

	private void selectElement(int x, int y) {
		Identifiable obj = levelService.getProperties(x, y);
		if (identifiablePropertiesPanel != null) {
			centerPanel.remove(identifiablePropertiesPanel);
		}
		if (obj != null) {
			if (obj.getClass().equals(Item.class)) {
				treatItemProperties((Item) obj);
			} else if (obj.getClass().equals(Decor.class)) {
				treatDecorProperties((Decor) obj);
			} else if (obj.getClass().equals(Door.class)) {
				treatDoorProperties((Door) obj);
			} else if (obj.getClass().equals(Ennemie.class)) {
				treatEnnemieProperties((Ennemie) obj);
			} else if (obj.getClass().equals(Event.class)) {
				treatEventProperties((Event) obj);
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
			} else if (obj.getClass().equals(Vortex.class)) {
				treatVortexProperties((Vortex) obj);
			}
		} else {
			centerPanel.updateUI();
		}
	}

	private void treatItemProperties(Item item) {
		identifiablePropertiesPanel = new ItemPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.item.border"), item);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatEventProperties(Event event) {
		identifiablePropertiesPanel = new EventPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.event.border"), event);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatVortexProperties(Vortex vortex) {
		identifiablePropertiesPanel = new VortexPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.vortex.border"), vortex);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatEnnemieProperties(Ennemie ennemie) {
		identifiablePropertiesPanel = new EnnemiePanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.ennemie.border"), ennemie);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatDecorProperties(Decor decor) {
		identifiablePropertiesPanel = new DecorPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.decor.border"), decor);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatDoorProperties(Door door) {
		identifiablePropertiesPanel = new DoorPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.door.border"), door);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatLockProperties(Lock lock) {
		identifiablePropertiesPanel = new LockPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.lock.border"), lock);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatPickProperties(Pick pick) {
		identifiablePropertiesPanel = new PickPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.pick.border"), pick);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatPlatformProperties(Platform platform) {
		identifiablePropertiesPanel = new PlatformPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.platform.border"), platform);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatRayonProperties(Rayon rayon) {
		identifiablePropertiesPanel = new RayonPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.rayon.border"), rayon);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	private void treatTeleporterProperties(Teleporter teleporter) {
		identifiablePropertiesPanel = new TeleporterPanel(message, centerPanel, drawPanel, levelService,
				message.getString("properties.teleporter.border"), teleporter);
		centerPanel.add(identifiablePropertiesPanel, BorderLayout.SOUTH);
	}

	public void repaint() {
		this.drawPanel.repaint();
	}

	public void loadPropertiesLevel() {
		verticalPlatformIndexSpinner.setValue((Integer) levelService.getVerticalPlatformId());
		horizontalPlatformIndexSpinner.setValue((Integer) levelService.getHorizontalPlatformId());
		backgroundIndexSpinner.setValue((Integer) levelService.getBackgroundId());
		nextLevelIndexTextField.setText(Integer.toString(levelService.getgetNextLevelId()));
		showPlatformLevelCheckBox.setSelected(levelService.isShowPlatform());
	}
}
