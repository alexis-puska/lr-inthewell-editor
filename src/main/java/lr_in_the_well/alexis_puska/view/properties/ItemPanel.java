package lr_in_the_well.alexis_puska.view.properties;

import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lr_in_the_well.alexis_puska.domain.level.Item;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class ItemPanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private Item item;

	private JLabel typeLabel;
	private SpinnerNumberModel typeModel;
	private JSpinner typeSpinner;

	public ItemPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name,
			Item item) {
		super(message, parent, drawPanel, levelService, name);
		this.item = item;
		idField.setText(Integer.toString(item.getId()));

		typeLabel = new JLabel(message.getString("properties.item.type"), JLabel.TRAILING);
		typeModel = new SpinnerNumberModel();
		typeSpinner = new JSpinner();
		typeModel.setMinimum(0);
		typeModel.setMaximum(352);
		typeSpinner.setModel(typeModel);
		typeLabel.setLabelFor(typeSpinner);
		typeSpinner.setValue(Integer.valueOf(item.getItemId()));
		this.add(typeLabel);
		this.add(typeSpinner);

		SpringUtilities.makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
		addListeners();
		this.parent.updateUI();
	}

	public void addListeners() {
		typeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					item.setItemId((Integer) text.getValue());
					levelService.updateItem(item);
					drawPanel.repaint();
					parent.repaint();
				}
			}
		});

	}

}
