package lr_in_the_well.alexis_puska.view.properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class RayonPanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private Rayon rayon;

	private JLabel typeLabel;
	private SpinnerNumberModel typeModel;
	private JSpinner typeSpinner;

	public RayonPanel(JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Rayon rayon) {
		super(parent, drawPanel, levelService, name);
		this.rayon = rayon;
		typeLabel = new JLabel("type", JLabel.TRAILING);
		typeModel = new SpinnerNumberModel();
		typeSpinner = new JSpinner();
		typeModel.setMinimum(0);
		typeModel.setMaximum(7);
		typeSpinner.setModel(typeModel);
		typeLabel.setLabelFor(typeSpinner);
		idField.setText(Integer.toString(rayon.getId()));
		typeSpinner.setValue(Integer.valueOf(rayon.getType()));
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
					rayon.setType((Integer) text.getValue());
					levelService.updateRayon(rayon);
					drawPanel.repaint();
					parent.repaint();
				}
			}
		});
	}
}
