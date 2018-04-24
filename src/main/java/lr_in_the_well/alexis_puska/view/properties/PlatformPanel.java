package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class PlatformPanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private DrawPanel drawPanel;
	private Platform platform;
	private JLabel displayLabel;
	private JCheckBox displayCheckBox;
	private JLabel enableLabel;
	private JCheckBox enableCheckBox;

	public PlatformPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService,
			String name, Platform platform) {
		super(message, parent, drawPanel, levelService, name);
		this.platform = platform;
		this.drawPanel = drawPanel;
		enableLabel = new JLabel(message.getString("properties.platform.enable"), JLabel.TRAILING);
		enableCheckBox = new JCheckBox();
		enableLabel.setLabelFor(enableCheckBox);
		displayLabel = new JLabel(message.getString("properties.plateform.display"), JLabel.TRAILING);
		displayCheckBox = new JCheckBox();
		displayLabel.setLabelFor(displayCheckBox);
		idField.setText(Integer.toString(platform.getId()));
		displayCheckBox.setSelected(platform.isDisplayed());
		enableCheckBox.setSelected(platform.isEnable());
		this.add(enableLabel);
		this.add(enableCheckBox);
		this.add(displayLabel);
		this.add(displayCheckBox);
		SpringUtilities.makeCompactGrid(this, 3, 2, 2, 2, 2, 2);
		addListeners();
		this.parent.updateUI();
		
	}

	private void addListeners() {
		displayCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				platform.setDisplayed(displayCheckBox.isSelected());
				levelService.updatePlatform(platform);
				drawPanel.repaint();
			}
		});
		enableCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				platform.setEnable(enableCheckBox.isSelected());
				levelService.updatePlatform(platform);
				drawPanel.repaint();
			}
		});
	}
}
