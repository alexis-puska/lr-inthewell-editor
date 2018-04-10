package lr_in_the_well.alexis_puska.view.properties;

import javax.swing.JPanel;

import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class PlatformPanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;

	public PlatformPanel(JPanel parent, LevelService levelService, String name, Platform platform) {
		super(parent, levelService, name);
		idField.setText(Integer.toString(platform.getId()));
		SpringUtilities.makeCompactGrid(this, 1, 2, 6, 6, 6, 6);
		this.parent.updateUI();
	}
}
