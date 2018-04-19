package lr_in_the_well.alexis_puska.view.properties;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.domain.level.event.EnableElement;
import lr_in_the_well.alexis_puska.domain.level.event.Event;
import lr_in_the_well.alexis_puska.domain.level.event.Message;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.properties.renderer.EnableElementRenderer;
import lr_in_the_well.alexis_puska.view.properties.renderer.MessageRenderer;

public class EventPanel extends JPanel {

    private static final long serialVersionUID = -4090876979915495722L;
    private Event event;

    private ResourceBundle message;
    private DrawPanel drawPanel;
    private LevelService levelService;
    
    private int lastEnableElementSelectedIndex;
    private int lastMessageSelectedIndex;

    /*********************************
     * MAIN PANEL
     *********************************/

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

    private JPanel actionPanel;
    private Border actionBorder;
    private BorderLayout actionLayout;

    // common
    private JPanel commonActionPanel;
    private Border commonActionBorder;
    private SpringLayout commonActionLayout;
    private JLabel songLabel;
    private JComboBox<String> songComboBox;
    private JLabel soundLabel;
    private JComboBox<String> soundComboBox;
    private JLabel darknessLabel;
    private JTextField darknessTextField;
    private JLabel iceLabel;
    private JTextField iceTextField;

    private JPanel centerActionPanel;
    private GridLayout centerActionLayout;

    // EnableElement
    private JPanel EnableElementPanel;
    private Border EnableElementBorder;
    private BorderLayout EnableElementLayout;
    private JList<EnableElement> enableElementList;
    private JScrollPane enableElementListScollPane;
    private EnableElementRenderer enableElementRenderer;
    private DefaultListModel<EnableElement> enableElementListModel;
    private JPanel enableElementButtonPanel;
    private GridLayout enableElementButtonLayout;
    private JButton addEnableElement;
    private JButton delEnableElement;
    // editElement
    private JPanel enableElementEditPanel;
    private Border enableElementEditBorder;
    private SpringLayout enableElementEditLayout;
    private JLabel enableElementIdLabel;
    private JTextField enableElementIdTextField;
    private JLabel enableElementTypeLabel;
    private JComboBox<String> enableElementTypeComboBox;
    private JLabel enableElementStatusLabel;
    private JCheckBox enableElementStatusCheckBox;

    // Message
    private JPanel messagePanel;
    private Border messageBorder;
    private BorderLayout messageLayout;
    private JList<Message> messageList;
    private JScrollPane messageListScollPane;
    private MessageRenderer messageRenderer;
    private DefaultListModel<Message> messageListModel;
    private JPanel messageButtonPanel;
    private GridLayout messageButtonLayout;
    private JButton addmessage;
    private JButton delmessage;
    // edit message
    private JPanel messageEditPanel;
    private Border messageEditBorder;
    private SpringLayout messageEditLayout;
    private JLabel timeoutLabel;
    private JTextField timeoutTextField;
    private JLabel espagnolLabel;
    private JTextField espagnolextField;
    private JLabel englishLabel;
    private JTextField englishTextField;
    private JLabel frenchLabel;
    private JTextField frenchTextField;

    public EventPanel(ResourceBundle message, DrawPanel drawPanel, LevelService levelService, String name,
            Event event) {
        this.message = message;
        this.drawPanel = drawPanel;
        this.event = event;
        this.levelService = levelService;
        this.lastEnableElementSelectedIndex = -1;
        this.lastMessageSelectedIndex = -1;

        initComponent();
        buildTriggerPanel();
        buildActionPanel();
        initValue();
        initListeners();
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
        triggerBorder = BorderFactory.createTitledBorder(message.getString("properties.event.trigger.border"));

        triggeredPanel = new JPanel();
        triggeredBorder = BorderFactory.createTitledBorder(message.getString("properties.event.trigger.once.border"));
        triggeredLayout = new SpringLayout();
        onlyOnceLabel = new JLabel(message.getString("properties.event.trigger.once.label"));
        onlyOnceCheckBox = new JCheckBox();

        nearTriggerPanel = new JPanel();
        nearTriggerBorder = BorderFactory.createTitledBorder(message.getString("properties.event.trigger.near.border"));
        nearTriggerLayout = new SpringLayout();
        onNearestLabel = new JLabel(message.getString("properties.event.trigger.near.label"));
        onNearestCheckBox = new JCheckBox();
        xLabel = new JLabel(message.getString("properties.event.trigger.near.x"));
        xTextField = new JSpinner();
        yLabel = new JLabel(message.getString("properties.event.trigger.near.y"));
        yTextField = new JSpinner();
        dLabel = new JLabel(message.getString("properties.event.trigger.near.d"));
        dTextField = new JSpinner();

        countDownPanel = new JPanel();
        countDownBorder = BorderFactory
                .createTitledBorder(message.getString("properties.event.trigger.countdown.border"));
        countDouwnLayout = new SpringLayout();
        countDownLabel = new JLabel(message.getString("properties.event.trigger.countdown.label"));
        countDownCheckBox = new JCheckBox();
        countDownValueLabel = new JLabel(message.getString("properties.event.trigger.countdown.value"));
        countDownValueTextField = new JSpinner();

        conditionPanel = new JPanel();
        conditionBorder = BorderFactory
                .createTitledBorder(message.getString("properties.event.trigger.condition.border"));
        conditionLayout = new SpringLayout();
        onBirthLabel = new JLabel(message.getString("properties.event.trigger.condition.onBirth"));
        onBirthCheckBox = new JCheckBox();
        onDeathLabel = new JLabel(message.getString("properties.event.trigger.condition.onDeath"));
        onDeathCheckBox = new JCheckBox();
        onLevelEnterLabel = new JLabel(message.getString("properties.event.trigger.condition.onLevelEnter"));
        onLevelEnterCheckBox = new JCheckBox();

        optionPanel = new JPanel();
        optionBorder = BorderFactory.createTitledBorder(message.getString("properties.event.trigger.option.border"));
        optionLayout = new SpringLayout();
        mirrorLabel = new JLabel(message.getString("properties.event.trigger.option.mirror"));
        mirrorCheckBox = new JCheckBox();
        nightmareLabel = new JLabel(message.getString("properties.event.trigger.option.nightmare"));
        nightmareCheckBox = new JCheckBox();
        timeAttackLabel = new JLabel(message.getString("properties.event.trigger.option.timeAttack"));
        timeAttackCheckBox = new JCheckBox();
        multiLabel = new JLabel(message.getString("properties.event.trigger.option.multi"));
        multiCheckBox = new JCheckBox();
        ninjaLabel = new JLabel(message.getString("properties.event.trigger.option.ninja"));
        ninjaCheckBox = new JCheckBox();

        /***********************************
         * ACTION - CENTER
         ***********************************/

        actionPanel = new JPanel();
        actionBorder = BorderFactory.createTitledBorder(message.getString("properties.event.action.border"));
        actionLayout = new BorderLayout();

        // common
        commonActionPanel = new JPanel();
        commonActionBorder = BorderFactory
                .createTitledBorder(message.getString("properties.event.action.common.border"));
        commonActionLayout = new SpringLayout();

        songLabel = new JLabel(message.getString("properties.event.action.common.music"));
        songComboBox = new JComboBox<>(Constante.MUSIQUE_LIST);
        songLabel.setToolTipText(message.getString("properties.event.action.common.music.tooltip"));
        songComboBox.setToolTipText(message.getString("properties.event.action.common.music.tooltip"));

        soundLabel = new JLabel(message.getString("properties.event.action.common.sound"));
        soundComboBox = new JComboBox<>(Constante.SOUND_LIST);
        soundLabel.setToolTipText(message.getString("properties.event.action.common.sound.tooltip"));
        soundComboBox.setToolTipText(message.getString("properties.event.action.common.sound.tooltip"));

        darknessLabel = new JLabel(message.getString("properties.event.action.common.darkness"));
        darknessTextField = new JTextField();
        darknessLabel.setToolTipText(message.getString("properties.event.action.common.darkness.tooltip"));
        darknessTextField.setToolTipText(message.getString("properties.event.action.common.darkness.tooltip"));

        iceLabel = new JLabel(message.getString("properties.event.action.common.ice"));
        iceTextField = new JTextField();
        iceLabel.setToolTipText(message.getString("properties.event.action.common.ice.tooltip"));
        iceTextField.setToolTipText(message.getString("properties.event.action.common.ice.tooltip"));

        // CENTER
        centerActionPanel = new JPanel();
        centerActionLayout = new GridLayout();

        // EnableElement
        EnableElementPanel = new JPanel();
        EnableElementBorder = BorderFactory
                .createTitledBorder(message.getString("properties.event.action.enableElement.border"));
        EnableElementLayout = new BorderLayout();
        enableElementList = new JList<>();
        enableElementListScollPane = new JScrollPane();
        enableElementListModel = new DefaultListModel<>();
        enableElementRenderer = new EnableElementRenderer();
        enableElementButtonPanel = new JPanel();
        enableElementButtonLayout = new GridLayout();

        addEnableElement = new JButton(message.getString("properties.event.action.enableElement.add"));
        delEnableElement = new JButton(message.getString("properties.event.action.enableElement.delete"));

        // Message
        messagePanel = new JPanel();
        messageBorder = BorderFactory.createTitledBorder(message.getString("properties.event.action.message.border"));
        messageLayout = new BorderLayout();
        messageList = new JList<>();
        messageListScollPane = new JScrollPane();
        messageRenderer = new MessageRenderer();
        messageListModel = new DefaultListModel<>();
        messageButtonPanel = new JPanel();
        messageButtonLayout = new GridLayout();
        addmessage = new JButton(message.getString("properties.event.action.message.add"));
        delmessage = new JButton(message.getString("properties.event.action.message.delete"));

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
        commonActionPanel.add(songComboBox);
        commonActionPanel.add(soundLabel);
        commonActionPanel.add(soundComboBox);
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
        enableElementList.setModel(enableElementListModel);
        enableElementList.setCellRenderer(enableElementRenderer);
        enableElementListScollPane.setViewportView(enableElementList);
        EnableElementPanel.add(enableElementListScollPane, BorderLayout.CENTER);
        EnableElementPanel.add(enableElementButtonPanel, BorderLayout.SOUTH);

        // Message
        messagePanel.setBorder(messageBorder);
        messagePanel.setLayout(messageLayout);
        messageButtonLayout.setRows(1);
        messageButtonLayout.setColumns(2);
        messageButtonPanel.setLayout(messageButtonLayout);
        messageButtonPanel.add(addmessage);
        messageButtonPanel.add(delmessage);
        messageList.setModel(messageListModel);
        messageList.setCellRenderer(messageRenderer);
        messageListScollPane.setViewportView(messageList);
        messagePanel.add(messageListScollPane, BorderLayout.CENTER);
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
        if (enableElementEditPanel != null) {
            EnableElementPanel.remove(enableElementEditPanel);
        }
        enableElementEditPanel = new JPanel();
        enableElementEditBorder = BorderFactory
                .createTitledBorder(message.getString("properties.event.action.enableElement.edit.border"));
        enableElementEditLayout = new SpringLayout();
        enableElementIdLabel = new JLabel(message.getString("properties.event.action.enableElement.edit.id"));
        enableElementIdTextField = new JTextField();
        enableElementTypeLabel = new JLabel(message.getString("properties.event.action.enableElement.edit.type"));
        enableElementTypeComboBox = new JComboBox<>();
        enableElementStatusLabel = new JLabel(message.getString("properties.event.action.enableElement.edit.status"));
        enableElementStatusCheckBox = new JCheckBox();
        enableElementEditPanel.setLayout(enableElementEditLayout);
        enableElementEditPanel.setBorder(enableElementEditBorder);
        enableElementEditPanel.add(enableElementIdLabel);
        enableElementEditPanel.add(enableElementIdTextField);
        enableElementEditPanel.add(enableElementTypeLabel);
        enableElementEditPanel.add(enableElementTypeLabel);
        enableElementEditPanel.add(enableElementStatusLabel);
        enableElementEditPanel.add(enableElementStatusCheckBox);
        SpringUtilities.makeCompactGrid(enableElementEditPanel, 3, 2, 6, 6, 6, 6);
        initListenersEnableElement();
        EnableElementPanel.revalidate();
        EnableElementPanel.repaint();
    }

    public void buildMessageEditPanel() {
        if (messageEditPanel != null) {
            messagePanel.remove(messageEditPanel);
        }
        messageEditPanel = new JPanel();
        messageEditBorder = BorderFactory
                .createTitledBorder(message.getString("properties.event.action.message.edit.border"));
        messageEditLayout = new SpringLayout();
        timeoutLabel = new JLabel();
        timeoutTextField = new JTextField(message.getString("properties.event.action.message.edit.timeout"));
        espagnolLabel = new JLabel();
        espagnolextField = new JTextField(message.getString("properties.event.action.message.edit.es"));
        englishLabel = new JLabel();
        englishTextField = new JTextField(message.getString("properties.event.action.message.edit.en"));
        frenchLabel = new JLabel();
        frenchTextField = new JTextField(message.getString("properties.event.action.message.edit.fr"));
        messageEditPanel.setLayout(messageEditLayout);
        messageEditPanel.setBorder(messageEditBorder);
        messageEditPanel.add(timeoutLabel);
        messageEditPanel.add(timeoutTextField);
        messageEditPanel.add(frenchLabel);
        messageEditPanel.add(frenchTextField);
        messageEditPanel.add(englishLabel);
        messageEditPanel.add(englishTextField);
        messageEditPanel.add(espagnolLabel);
        messageEditPanel.add(espagnolextField);
        SpringUtilities.makeCompactGrid(messageEditPanel, 4, 2, 6, 6, 6, 6);
        initListenersMessage();
        messagePanel.revalidate();
        messagePanel.repaint();
    }

    private void initValue() {
        /*********************************
         * TRIGGER : left panel
         *********************************/
        onlyOnceCheckBox.setSelected(event.isOnlyOnce());

        onNearestCheckBox.setSelected(event.isNear());
        xTextField.setValue((Integer) event.getX());
        yTextField.setValue((Integer) event.getY());
        dTextField.setValue((Integer) event.getD());
        //
        countDownCheckBox.setSelected(event.isTime());
        countDownValueTextField.setValue((Integer) event.getTimeout());

        onBirthCheckBox.setSelected(event.isOnBirth());
        onDeathCheckBox.setSelected(event.isOnDeath());
        onLevelEnterCheckBox.setSelected(event.isOnLevelEnter());

        mirrorCheckBox.setSelected(event.isMirror());
        nightmareCheckBox.setSelected(event.isNightmare());
        timeAttackCheckBox.setSelected(event.isTimeAttackeOption());
        multiCheckBox.setSelected(event.isMultiOption());
        ninjaCheckBox.setSelected(event.isNinja());

        /*********************************
         * ACTION : center panel
         *********************************/

        songComboBox.setSelectedItem(event.getSong());
        soundComboBox.setSelectedItem(event.getSound());
        darknessTextField.setText(Integer.toString(event.getDarknessValue()));
        iceTextField.setText(Integer.toString(event.getIceValue()));
        if (event.getEnableElement() != null) {
            for (EnableElement e : event.getEnableElement()) {
                enableElementListModel.addElement(e);
            }
        }
        if (event.getMessage() != null) {
            for (Message e : event.getMessage()) {
                messageListModel.addElement(e);
            }
        }
    }

    private void initListeners() {

        /*********************************
         * TRIGGER : left panel
         *********************************/
        // onlyOnceCheckBox;
        //
        // onNearestCheckBox;
        // xTextField;
        // yTextField;
        // dTextField;
        //
        // countDownCheckBox;
        // countDownValueTextField;
        //
        // onBirthCheckBox;
        // onDeathCheckBox;
        // onLevelEnterCheckBox;
        //
        // mirrorCheckBox;
        // nightmareCheckBox;
        // timeAttackCheckBox;
        // multiCheckBox;
        // ninjaCheckBox;

        /*********************************
         * ACTION : center panel
         *********************************/

        // songComboBox;
        // soundComboBox;
        // darknessTextField;
        // iceTextField;
        //
        enableElementList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (enableElementList.getSelectedIndex() != -1 && lastEnableElementSelectedIndex == enableElementList.getSelectedIndex()) {
                    enableElementList.clearSelection();
                    lastEnableElementSelectedIndex = -1;
                } else {
                    
                    lastEnableElementSelectedIndex = enableElementList.getSelectedIndex();
                }
            }
        });
        addEnableElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableElementListModel.addElement(new EnableElement());
            }
        });
        delEnableElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableElementListModel.remove(enableElementList.getSelectedIndex());
            }
        });
        messageList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (messageList.getSelectedIndex() != -1 && lastMessageSelectedIndex == messageList.getSelectedIndex()) {
                    messageList.clearSelection();
                    lastMessageSelectedIndex = -1;
                } else {
                    
                    lastMessageSelectedIndex = messageList.getSelectedIndex();
                }
            }
        });
        addmessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageListModel.addElement(new Message());
            }
        });
        delmessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageListModel.remove(messageList.getSelectedIndex());
            }
        });
    }

    private void initListenersEnableElement() {
        // enableElementIdTextField;
        // enableElementTypeComboBox;
        // enableElementStatusCheckBox;
    }

    private void initListenersMessage() {
        // timeoutTextField;
        // espagnolextField;
        // englishTextField;
        // frenchTextField;
    }
}
