package lr_in_the_well.alexis_puska.view.properties;

import javax.swing.JPanel;

import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class PickPanel extends IdentifiablePanel {

    private static final long serialVersionUID = -4090876979915495722L;

    public PickPanel(JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Pick pick) {
        super(parent, drawPanel, levelService, name);
        idField.setText(Integer.toString(pick.getId()));
        SpringUtilities.makeCompactGrid(this, 1, 2, 6, 6, 6, 6);
        this.parent.updateUI();
    }

}
