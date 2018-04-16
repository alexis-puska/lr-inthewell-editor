package lr_in_the_well.alexis_puska.view.properties;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.domain.level.event.EnableElement;
import lr_in_the_well.alexis_puska.domain.level.event.Event;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;

public class EventPanel extends JPanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private Event event;

	private ResourceBundle message;
	private DrawPanel drawPanel;
	private LevelService levelService;

	/*********************************
	 * MAIN PANEL
	 *********************************/
	private JPanel parent;

	private BorderLayout mainLayout;
	protected JLabel idLabel;
	protected JSpinner idField;

	/*********************************
	 * TRIGGER : left panel
	 *********************************/
	private JPanel triggerPanel;
	private Border triggerBorder;
	private SpringLayout triggerLayout;

	private JPanel triggeredPanel;
	private Border triggeredBorder;
	private SpringLayout triggeredLayout;
	private JLabel onlyOnceLabel;
	private JCheckBox onlyOnceCheckBox;

	private JPanel nearTriggerPanel;
	private Border nearTriggerBorder;
	private SpringLayout nearTriggerLayout;
	private JLabel onNearestLabel;
	private JCheckBox onNearestCheckBox;
	private JLabel xLabel;
	private JSpinner xTextField;
	private JLabel yLabel;
	private JSpinner yTextField;
	private JLabel dLabel;
	private JSpinner dTextField;

	private JPanel countDownPanel;
	private Border countDownBorder;
	private SpringLayout countDouwnLayout;
	private JLabel countDownLabel;
	private JCheckBox countDownCheckBox;
	private JLabel countDownValueLabel;
	private JSpinner countDownValueTextField;

	private JPanel conditionPanel;
	private Border conditionBorder;
	private SpringLayout conditionLayout;
	private JLabel onBirthLabel;
	private JCheckBox onBirthCheckBox;
	private JLabel onDeathLabel;
	private JCheckBox onDeathCheckBox;
	private JLabel onLevelEnterLabel;
	private JCheckBox onLevelEnterCheckBox;

	private JPanel optionPanel;
	private Border optionBorder;
	private SpringLayout optionLayout;
	private JLabel mirrorLabel;
	private JCheckBox mirrorCheckBox;
	private JLabel nightmareLabel;
	private JCheckBox nightmareCheckBox;
	private JLabel timeAttackLabel;
	private JCheckBox timeAttackCheckBox;
	private JLabel multiLabel;
	private JCheckBox multiCheckBox;
	private JLabel ninjaLabel;
	private JCheckBox ninjaCheckBox;

	/*********************************
	 * ACTION : center panel
	 *********************************/

	JPanel actionPanel;
	Border actionBorder;
	BorderLayout actionLayout;

	// common
	JPanel commonActionPanel;
	Border commonActionBorder;
	SpringLayout commonActionLayout;
	JLabel songLabel;
	JTextField songTextField;
	JLabel soundLabel;
	JTextField soundTextField;
	JLabel darknessLabel;
	JTextField darknessTextField;
	JLabel iceLabel;
	JTextField iceTextField;

	JPanel centerActionPanel;
	GridLayout centerActionLayout;

	// EnableElement
	JPanel EnableElementPanel;
	Border EnableElementBorder;
	BorderLayout EnableElementLayout;
	JList<EnableElement> enableElementList;
	JPanel enableElementButtonPanel;
	GridLayout enableElementButtonLayout;
	JButton addEnableElement;
	JButton delEnableElement;
	// editElement
	JPanel enableElementEditPanel;
	Border enableElementEditBorder;
	SpringLayout enableElementEditLayout;
	JLabel enableElementIdLabel;
	JTextField enableElementIdTextField;
	JLabel enableElementTypeLabel;
	JComboBox<String> enableElementTypeComboBox;
	JLabel enableElementStatusLabel;
	JCheckBox enableElementStatusCheckBox;

	// Message
	JPanel messagePanel;
	Border messageBorder;
	BorderLayout messageLayout;
	JList<EnableElement> messageList;
	JPanel messageButtonPanel;
	GridLayout messageButtonLayout;
	JButton addmessage;
	JButton delmessage;
	// edit message
	JPanel messageEditPanel;
	Border messageEditBorder;
	SpringLayout messageEditLayout;
	JLabel timeoutLabel;
	JTextField timeoutTextField;
	JLabel espagnolLabel;
	JTextField espagnolextField;
	JLabel englishLabel;
	JTextField englishTextField;
	JLabel frenchLabel;
	JTextField frenchTextField;

	/*
	 * private List<EnableElement> enableElement; private String song; private
	 * String sound; private int darknessValue; private int iceValue;
	 */

	public EventPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService,
			String name, Event event) {
		this.message = message;
		this.parent = parent;
		this.event = event;
		this.levelService = levelService;
		this.drawPanel = drawPanel;

		initComponent();
		buildTriggerPanel();
		buildActionPanel();
	}

	public void updateRayon() {
		levelService.updateEvent(event);
	}

	public void initComponent() {
		/***********************************
		 * MAIN
		 ***********************************/

		mainLayout = new BorderLayout();
		this.setLayout(mainLayout);

		/***********************************
		 * TRIGGER - WEST
		 ***********************************/
		triggerPanel = new JPanel();
		triggerLayout = new SpringLayout();
		triggerBorder = BorderFactory.createTitledBorder("TRIGGER");

		triggeredPanel = new JPanel();
		triggeredBorder = BorderFactory.createTitledBorder("Déclanchement :");
		triggeredLayout = new SpringLayout();
		onlyOnceLabel = new JLabel("Une seule fois :");
		onlyOnceCheckBox = new JCheckBox();

		nearTriggerPanel = new JPanel();
		nearTriggerBorder = BorderFactory.createTitledBorder("Proximité :");
		nearTriggerLayout = new SpringLayout();
		onNearestLabel = new JLabel("à proximité :");
		onNearestCheckBox = new JCheckBox();
		xLabel = new JLabel("x :");
		xTextField = new JSpinner();
		yLabel = new JLabel("y :");
		yTextField = new JSpinner();
		dLabel = new JLabel("d :");
		dTextField = new JSpinner();

		countDownPanel = new JPanel();
		countDownBorder = BorderFactory.createTitledBorder("timer");
		countDouwnLayout = new SpringLayout();
		countDownLabel = new JLabel("Sur décompte :");
		countDownCheckBox = new JCheckBox();
		countDownValueLabel = new JLabel("Décompte :");
		countDownValueTextField = new JSpinner();

		conditionPanel = new JPanel();
		conditionBorder = BorderFactory.createTitledBorder("condition");
		conditionLayout = new SpringLayout();
		onBirthLabel = new JLabel("On birth :");
		onBirthCheckBox = new JCheckBox();
		onDeathLabel = new JLabel("On death :");
		onDeathCheckBox = new JCheckBox();
		onLevelEnterLabel = new JLabel("On enter level:");
		onLevelEnterCheckBox = new JCheckBox();

		optionPanel = new JPanel();
		optionBorder = BorderFactory.createTitledBorder("option");
		optionLayout = new SpringLayout();
		mirrorLabel = new JLabel("Mirror :");
		mirrorCheckBox = new JCheckBox();
		nightmareLabel = new JLabel("Nightmare :");
		nightmareCheckBox = new JCheckBox();
		timeAttackLabel = new JLabel("Time attack :");
		timeAttackCheckBox = new JCheckBox();
		multiLabel = new JLabel("Mulit :");
		multiCheckBox = new JCheckBox();
		ninjaLabel = new JLabel("Ninja :");
		ninjaCheckBox = new JCheckBox();

		/***********************************
		 * ACTION - CENTER
		 ***********************************/

		actionPanel = new JPanel();
		actionBorder = BorderFactory.createTitledBorder("ACTION");
		actionLayout = new BorderLayout();

		// common
		commonActionPanel = new JPanel();
		commonActionBorder = BorderFactory.createTitledBorder("Common");
		commonActionLayout = new SpringLayout();
		songLabel = new JLabel("Song :");
		songTextField = new JTextField();
		soundLabel = new JLabel("Son :");
		soundTextField = new JTextField();
		darknessLabel = new JLabel("Obscurite:");
		darknessTextField = new JTextField();
		iceLabel = new JLabel("Ice :");
		iceTextField = new JTextField();

		// CENTER
		centerActionPanel = new JPanel();
		centerActionLayout = new GridLayout();

		// EnableElement
		EnableElementPanel = new JPanel();
		EnableElementBorder = BorderFactory.createTitledBorder("Status element");
		EnableElementLayout = new BorderLayout();
		enableElementList = new JList<>();
		enableElementButtonPanel = new JPanel();
		enableElementButtonLayout = new GridLayout();

		addEnableElement = new JButton("ajouter");
		delEnableElement = new JButton("supprimer");

		// Message
		messagePanel = new JPanel();
		messageBorder = BorderFactory.createTitledBorder("Message");
		messageLayout = new BorderLayout();
		messageList = new JList<>();
		messageButtonPanel = new JPanel();
		messageButtonLayout = new GridLayout();
		addmessage = new JButton("ajouter");
		delmessage = new JButton("supprimer");

	}

	public void buildTriggerPanel() {

		triggeredPanel.setBorder(triggeredBorder);
		triggeredPanel.setLayout(triggeredLayout);
		triggeredPanel.add(onlyOnceLabel);
		triggeredPanel.add(onlyOnceCheckBox);
		SpringUtilities.makeCompactGrid(triggeredPanel, 1, 2, 6, 6, 6, 6);

		nearTriggerPanel.setBorder(nearTriggerBorder);
		nearTriggerPanel.setLayout(nearTriggerLayout);
		nearTriggerPanel.add(onNearestLabel);
		nearTriggerPanel.add(onNearestCheckBox);
		nearTriggerPanel.add(xLabel);
		nearTriggerPanel.add(xTextField);
		nearTriggerPanel.add(yLabel);
		nearTriggerPanel.add(yTextField);
		nearTriggerPanel.add(dLabel);
		nearTriggerPanel.add(dTextField);
		SpringUtilities.makeCompactGrid(nearTriggerPanel, 4, 2, 6, 6, 6, 6);

		countDownPanel.setBorder(countDownBorder);
		countDownPanel.setLayout(countDouwnLayout);
		countDownPanel.add(countDownLabel);
		countDownPanel.add(countDownCheckBox);
		countDownPanel.add(countDownValueLabel);
		countDownPanel.add(countDownValueTextField);
		SpringUtilities.makeCompactGrid(countDownPanel, 2, 2, 6, 6, 6, 6);

		conditionPanel.setBorder(conditionBorder);
		conditionPanel.setLayout(conditionLayout);
		conditionPanel.add(onBirthLabel);
		conditionPanel.add(onBirthCheckBox);
		conditionPanel.add(onDeathLabel);
		conditionPanel.add(onDeathCheckBox);
		conditionPanel.add(onLevelEnterLabel);
		conditionPanel.add(onLevelEnterCheckBox);
		SpringUtilities.makeCompactGrid(conditionPanel, 3, 2, 6, 6, 6, 6);

		optionPanel.setBorder(optionBorder);
		optionPanel.setLayout(optionLayout);
		optionPanel.add(mirrorLabel);
		optionPanel.add(mirrorCheckBox);
		optionPanel.add(nightmareLabel);
		optionPanel.add(nightmareCheckBox);
		optionPanel.add(timeAttackLabel);
		optionPanel.add(timeAttackCheckBox);
		optionPanel.add(multiLabel);
		optionPanel.add(multiCheckBox);
		optionPanel.add(ninjaLabel);
		optionPanel.add(ninjaCheckBox);
		SpringUtilities.makeCompactGrid(optionPanel, 5, 2, 6, 6, 6, 6);

		triggerPanel.setLayout(triggerLayout);
		triggerPanel.setBorder(triggerBorder);
		triggerPanel.add(triggeredPanel);
		triggerPanel.add(nearTriggerPanel);
		triggerPanel.add(countDownPanel);
		triggerPanel.add(conditionPanel);
		triggerPanel.add(optionPanel);
		triggerPanel.setPreferredSize(new Dimension(Constante.EVENT_FRAME_TRIGGER_PANEL_WIDTH, 700));
		SpringUtilities.makeCompactGrid(triggerPanel, 5, 1, 6, 6, 6, 6);
		this.add(triggerPanel, BorderLayout.WEST);
	}

	public void buildActionPanel() {

		// common
		commonActionPanel.setBorder(commonActionBorder);
		commonActionPanel.setLayout(commonActionLayout);

		commonActionPanel.add(songLabel);
		commonActionPanel.add(songTextField);
		commonActionPanel.add(soundLabel);
		commonActionPanel.add(soundTextField);
		commonActionPanel.add(darknessLabel);
		commonActionPanel.add(darknessTextField);
		commonActionPanel.add(iceLabel);
		commonActionPanel.add(iceTextField);
		SpringUtilities.makeCompactGrid(commonActionPanel, 4, 2, 6, 6, 6, 6);

		// EnableElement
		EnableElementPanel.setBorder(EnableElementBorder);
		EnableElementPanel.setLayout(EnableElementLayout);
		enableElementButtonLayout.setColumns(2);
		enableElementButtonLayout.setRows(1);
		enableElementButtonPanel.setLayout(enableElementButtonLayout);
		enableElementButtonPanel.add(addEnableElement);
		enableElementButtonPanel.add(delEnableElement);
		EnableElementPanel.add(enableElementList, BorderLayout.CENTER);
		EnableElementPanel.add(enableElementButtonPanel, BorderLayout.SOUTH);

		// Message
		messagePanel.setBorder(messageBorder);
		messagePanel.setLayout(messageLayout);
		messageButtonLayout.setRows(1);
		messageButtonLayout.setColumns(2);
		messageButtonPanel.setLayout(messageButtonLayout);
		messageButtonPanel.add(addmessage);
		messageButtonPanel.add(delmessage);
		messagePanel.add(messageList, BorderLayout.CENTER);
		messagePanel.add(messageButtonPanel, BorderLayout.SOUTH);

		centerActionLayout.setColumns(1);
		centerActionLayout.setRows(2);
		centerActionPanel.setLayout(centerActionLayout);
		centerActionPanel.add(EnableElementPanel);
		centerActionPanel.add(messagePanel);

		actionPanel.setBorder(actionBorder);
		actionPanel.setLayout(actionLayout);
		actionPanel.add(commonActionPanel, BorderLayout.NORTH);
		actionPanel.add(centerActionPanel, BorderLayout.CENTER);
		this.add(actionPanel, BorderLayout.CENTER);

	}

	public void buildEnableElementEditPanel() {
		enableElementEditPanel = new JPanel();
		enableElementEditBorder = BorderFactory.createTitledBorder("Edit element");
		enableElementEditLayout = new SpringLayout();
		enableElementIdLabel = new JLabel();
		enableElementIdTextField = new JTextField();
		enableElementTypeLabel = new JLabel();
		enableElementTypeComboBox = new JComboBox<>();
		enableElementStatusLabel = new JLabel();
		enableElementStatusCheckBox = new JCheckBox();
	}

	public void buildMessageEditPanel() {
		messageEditPanel = new JPanel();
		messageEditBorder = BorderFactory.createTitledBorder("Edit message");
		messageEditLayout = new SpringLayout();
		timeoutLabel = new JLabel();
		timeoutTextField = new JTextField();
		espagnolLabel = new JLabel();
		espagnolextField = new JTextField();
		englishLabel = new JLabel();
		englishTextField = new JTextField();
		frenchLabel = new JLabel();
		frenchTextField = new JTextField();
	}

}
