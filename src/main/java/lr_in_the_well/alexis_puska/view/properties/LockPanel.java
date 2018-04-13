package lr_in_the_well.alexis_puska.view.properties;

import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class LockPanel extends IdentifiablePanel {

    private static final long serialVersionUID = -4090876979915495722L;
    private Lock lock;

    private JLabel typeLabel;
    private SpinnerNumberModel typeModel;
    private JSpinner typeSpinner;

    public LockPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Lock lock) {
        super(message, parent, drawPanel, levelService, name);
        this.lock = lock;
        typeLabel = new JLabel("test", JLabel.TRAILING);
        typeModel = new SpinnerNumberModel();
        typeSpinner = new JSpinner();
        typeSpinner.setModel(typeModel);
        typeLabel.setLabelFor(typeSpinner);

        this.add(typeLabel);
        this.add(typeSpinner);

        SpringUtilities.makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
        this.parent.updateUI();
    }

    public void updateLock() {
        levelService.updateLock(lock);
    }

}
