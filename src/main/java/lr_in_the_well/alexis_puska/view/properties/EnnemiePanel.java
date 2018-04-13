package lr_in_the_well.alexis_puska.view.properties;

import java.util.ResourceBundle;

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

	private JLabel typeLabel;
	private SpinnerNumberModel typeModel;
	private JSpinner typeSpinner;

	public EnnemiePanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Ennemie ennemie) {
		super(message, parent, drawPanel, levelService, name);
		this.ennemie = ennemie;
		typeLabel = new JLabel(message.getString("properties.ennemie.type"), JLabel.TRAILING);
		typeModel = new SpinnerNumberModel();
		typeSpinner = new JSpinner();
		typeModel.setMinimum(0);
		typeModel.setMaximum(16);
		typeSpinner.setModel(typeModel);
		typeLabel.setLabelFor(typeSpinner);
		idField.setText(Integer.toString(ennemie.getId()));
		typeSpinner.setValue(Integer.valueOf(ennemie.getType()));
		this.add(typeLabel);
		this.add(typeSpinner);
		SpringUtilities.makeCompactGrid(this, 2, 2, 2, 2, 2, 2);
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
	}
}
