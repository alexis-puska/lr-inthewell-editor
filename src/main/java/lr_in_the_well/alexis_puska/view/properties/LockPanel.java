package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lr_in_the_well.alexis_puska.constant.GameKeyEnum;
import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class LockPanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private Lock lock;
	private JLabel enableLabel;
	private JCheckBox enableCheckBox;
	private JLabel requieredKeyLabel;
	private JComboBox<GameKeyEnum> requieredKeyComboBox;

	public LockPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name,
			Lock lock) {
		super(message, parent, drawPanel, levelService, name);
		this.lock = lock;
		enableLabel = new JLabel(message.getString("properties.lock.enable"), JLabel.TRAILING);
		enableCheckBox = new JCheckBox();
		enableLabel.setLabelFor(enableCheckBox);
		requieredKeyLabel = new JLabel(message.getString("properties.lock.requiredKey"), JLabel.TRAILING);
		requieredKeyComboBox = new JComboBox<>();
		requieredKeyLabel.setLabelFor(requieredKeyComboBox);
		enableCheckBox.setSelected(lock.isEnable());

		requieredKeyLabel.setToolTipText(message.getString("properties.lock.requiredKey.description"));
		requieredKeyLabel.setToolTipText(message.getString("properties.lock.requiredKey.description"));
		requieredKeyLabel.setToolTipText(message.getString("properties.lock.serrure.description"));
		requieredKeyLabel.setToolTipText(message.getString("properties.lock.serrure.description"));

		idField.setText(Integer.toString(lock.getId()));
		requieredKeyComboBox.setSelectedItem(lock.getRequieredKeyId());
		enableCheckBox.setSelected(lock.isEnable());

		this.add(enableLabel);
		this.add(enableCheckBox);
		this.add(requieredKeyLabel);
		this.add(requieredKeyComboBox);

		addListeners();
		SpringUtilities.makeCompactGrid(this, 3, 2, 6, 6, 6, 6);
		this.parent.updateUI();
	}

	public void addListeners() {
		requieredKeyComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				lock.setRequieredKeyId((GameKeyEnum) requieredKeyComboBox.getSelectedItem());
				levelService.updateLock(lock);
				drawPanel.repaint();
				parent.repaint();
			}
		});
		enableCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				lock.setEnable(enableCheckBox.isSelected());
				levelService.updateLock(lock);
				drawPanel.repaint();
			}
		});
	}

	public void updateLock() {
		levelService.updateLock(lock);
	}

}
