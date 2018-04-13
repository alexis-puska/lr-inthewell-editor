package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lr_in_the_well.alexis_puska.domain.level.Door;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class DoorPanel extends IdentifiablePanel {

    private static final long serialVersionUID = -4090876979915495722L;
    private Door door;

    private JLabel typeLabel;
    private SpinnerNumberModel typeModel;
    private JSpinner typeSpinner;

    private JLabel toLevelLabel;
    private SpinnerNumberModel toLevelModel;
    private JSpinner toLevelSpinner;

    private JLabel requieredKeyLabel;
    private SpinnerNumberModel requieredKeyModel;
    private JSpinner requieredKeySpinner;

    private JLabel lockedLabel;
    private JCheckBox lockedCheckBox;

    public DoorPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Door door) {
        super(message, parent, drawPanel, levelService, name);
        this.door = door;
        typeLabel = new JLabel("Type :", JLabel.TRAILING);
        typeModel = new SpinnerNumberModel();
        typeSpinner = new JSpinner();
        typeModel.setMinimum(0);
        typeModel.setMaximum(7);
        typeSpinner.setModel(typeModel);
        typeLabel.setLabelFor(typeSpinner);

        toLevelLabel = new JLabel("To Level :", JLabel.TRAILING);
        toLevelModel = new SpinnerNumberModel();
        toLevelSpinner = new JSpinner();
        toLevelSpinner.setModel(toLevelModel);
        toLevelLabel.setLabelFor(toLevelSpinner);

        requieredKeyLabel = new JLabel("Requiered Key :", JLabel.TRAILING);
        requieredKeyModel = new SpinnerNumberModel();
        requieredKeySpinner = new JSpinner();
        requieredKeySpinner.setModel(requieredKeyModel);
        requieredKeyLabel.setLabelFor(requieredKeySpinner);

        lockedLabel = new JLabel("Locked :", JLabel.TRAILING);
        lockedCheckBox = new JCheckBox();
        lockedLabel.setLabelFor(lockedCheckBox);
        this.add(typeLabel);
        this.add(typeSpinner);
        this.add(toLevelLabel);
        this.add(toLevelSpinner);
        this.add(requieredKeyLabel);
        this.add(requieredKeySpinner);
        this.add(lockedLabel);
        this.add(lockedCheckBox);
        SpringUtilities.makeCompactGrid(this, 5, 2, // rows, cols
                6, 6, // initX, initY
                6, 6); // xPad, yPad
        this.parent.updateUI();
    }

    public void addListeners() {
        typeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner text = (JSpinner) e.getSource();
                if (text.getValue() != null) {
                    door.setType((Integer) text.getValue());
                    levelService.updateDoor(door);
                    drawPanel.repaint();
                    parent.repaint();
                }
            }
        });
        toLevelSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner text = (JSpinner) e.getSource();
                if (text.getValue() != null) {
                    door.setToLevel((Integer) text.getValue());
                    levelService.updateDoor(door);
                    drawPanel.repaint();
                    parent.repaint();
                }
            }
        });
        requieredKeySpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner text = (JSpinner) e.getSource();
                if (text.getValue() != null) {
                    door.setRequieredKey((Integer) text.getValue());
                    levelService.updateDoor(door);
                    drawPanel.repaint();
                    parent.repaint();
                }
            }
        });
        lockedCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                door.setLocked(lockedCheckBox.isSelected());
                levelService.updateDoor(door);
            }
        });
    }

}
