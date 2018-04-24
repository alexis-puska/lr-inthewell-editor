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

import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class EnnemiePanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private Ennemie ennemie;

	private JLabel enableLabel;
	private JCheckBox enableCheckBox;
	private JLabel typeLabel;
	private SpinnerNumberModel typeModel;
	private JSpinner typeSpinner;

	public EnnemiePanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Ennemie ennemie) {
		super(message, parent, drawPanel, levelService, name);
		this.ennemie = ennemie;
		enableLabel = new JLabel(message.getString("properties.ennemie.enable"), JLabel.TRAILING);
		enableCheckBox = new JCheckBox();
		enableLabel.setLabelFor(enableCheckBox);
		typeLabel = new JLabel(message.getString("properties.ennemie.type"), JLabel.TRAILING);
		typeModel = new SpinnerNumberModel();
		typeSpinner = new JSpinner();
		typeModel.setMinimum(0);
		typeModel.setMaximum(16);
		typeSpinner.setModel(typeModel);
		typeLabel.setLabelFor(typeSpinner);
		idField.setText(Integer.toString(ennemie.getId()));
		typeSpinner.setValue(Integer.valueOf(ennemie.getType()));
		enableCheckBox.setSelected(ennemie.isEnable());
		this.add(enableLabel);
		this.add(enableCheckBox);
		this.add(typeLabel);
		this.add(typeSpinner);
		SpringUtilities.makeCompactGrid(this, 3, 2, 2, 2, 2, 2);
		addListeners();
		this.parent.updateUI();
	}

	public void addListeners() {
		typeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					ennemie.setType((Integer) text.getValue());
					levelService.updateEnnemie(ennemie);
					drawPanel.repaint();
					parent.repaint();
				}
			}
		});
		enableCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ennemie.setEnable(enableCheckBox.isSelected());
				levelService.updateEnnemie(ennemie);
				drawPanel.repaint();
			}
		});
	}
}
