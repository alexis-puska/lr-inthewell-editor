package lr_in_the_well.alexis_puska.view.properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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

	public EnnemiePanel(JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Ennemie ennemie) {
		super(parent, drawPanel, levelService, name);
		this.ennemie = ennemie;
		typeLabel = new JLabel("test", JLabel.TRAILING);
		typeModel = new SpinnerNumberModel();
		typeSpinner = new JSpinner();
		typeModel.setMinimum(0);
		typeModel.setMaximum(7);
		typeSpinner.setModel(typeModel);
		typeLabel.setLabelFor(typeSpinner);
		this.add(typeLabel);
		this.add(typeSpinner);
		SpringUtilities.makeCompactGrid(this, 1, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
		this.parent.updateUI();
	}
	
	public void updateEnnemie() {
		levelService.updateEnnemie(ennemie);
	}

}
