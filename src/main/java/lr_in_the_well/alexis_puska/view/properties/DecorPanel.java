package lr_in_the_well.alexis_puska.view.properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import lr_in_the_well.alexis_puska.domain.level.Decor;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class DecorPanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private Decor decor;

	private JLabel typeLabel;
	private SpinnerNumberModel typeModel;
	private JSpinner typeSpinner;

	public DecorPanel(JPanel parent, LevelService levelService, String name, Decor decor) {
		super(parent, levelService, name);
		this.decor = decor;
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
	
	public void updateDecor() {
		levelService.updateDecor(decor);
	}

}
