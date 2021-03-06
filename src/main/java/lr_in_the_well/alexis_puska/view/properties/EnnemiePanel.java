package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lr_in_the_well.alexis_puska.constant.EnnemieTypeEnum;
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
	private JComboBox<EnnemieTypeEnum> typeComboBox;

	public EnnemiePanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService,
			String name, Ennemie ennemie) {
		super(message, parent, drawPanel, levelService, name);
		this.ennemie = ennemie;
		enableLabel = new JLabel(message.getString("properties.ennemie.enable"), JLabel.TRAILING);
		enableCheckBox = new JCheckBox();
		enableLabel.setLabelFor(enableCheckBox);
		typeLabel = new JLabel(message.getString("properties.ennemie.type"), JLabel.TRAILING);
		typeComboBox = new JComboBox<EnnemieTypeEnum>(EnnemieTypeEnum.values());
		typeLabel.setLabelFor(typeComboBox);
		idField.setText(Integer.toString(ennemie.getId()));
		enableCheckBox.setSelected(ennemie.isEnable());
		typeComboBox.setSelectedItem(ennemie.getType());
		this.add(enableLabel);
		this.add(enableCheckBox);
		this.add(typeLabel);
		this.add(typeComboBox);
		SpringUtilities.makeCompactGrid(this, 3, 2, 2, 2, 2, 2);
		addListeners();
		this.parent.updateUI();
	}

	public void addListeners() {
		typeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				EnnemieTypeEnum song = (EnnemieTypeEnum) typeComboBox.getSelectedItem();
				ennemie.setType(song);
				levelService.updateEnnemie(ennemie);
				drawPanel.repaint();
				parent.repaint();
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
