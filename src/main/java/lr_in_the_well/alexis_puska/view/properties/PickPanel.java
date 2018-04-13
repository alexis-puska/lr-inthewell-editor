package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class PickPanel extends IdentifiablePanel {

    private static final long serialVersionUID = -4090876979915495722L;
    private static final String ACTIF = "Actif : ";
    private Pick pick;

    private JLabel actifLabel;
    private JCheckBox actifCheckBox;

    public PickPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Pick pick) {
        super(message, parent, drawPanel, levelService, name);
        this.pick = pick;
        idField.setText(Integer.toString(pick.getId()));

        actifLabel = new JLabel(ACTIF, JLabel.TRAILING);
        actifCheckBox = new JCheckBox();
        actifCheckBox.setToolTipText("Vortex affiché dès l'entrée du niveau");
        actifLabel.setLabelFor(actifCheckBox);
        this.add(actifLabel);
        this.add(actifCheckBox);

        SpringUtilities.makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
        addListeners();
        this.parent.updateUI();
    }

    public void addListeners() {
        actifCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                pick.setVisible(actifCheckBox.isSelected());
                levelService.updatePick(pick);
            }
        });
    }
}
