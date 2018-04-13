package lr_in_the_well.alexis_puska.view.properties;

import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import lr_in_the_well.alexis_puska.domain.level.Decor;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class DecorPanel extends IdentifiablePanel {

    private static final long serialVersionUID = -4090876979915495722L;
    private Decor decor;

    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel spriteLabel;
    private JLabel backgroundLabel;

    private SpinnerNumberModel xModel;
    private SpinnerNumberModel yModel;
    private SpinnerNumberModel spriteModel;
    private SpinnerNumberModel backgroundModel;

    private JSpinner xSpinner;
    private JSpinner ySpinner;
    private JSpinner spriteSpinner;
    private JSpinner backgroundSpinner;

    public DecorPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService,
            String name, Decor decor) {
        super(message, parent, drawPanel, levelService, name);
        this.decor = decor;
        xLabel = new JLabel(message.getString("properties.decor.x"), JLabel.TRAILING);
        xModel = new SpinnerNumberModel();
        xSpinner = new JSpinner();
        xModel.setMinimum(0);
        xModel.setMaximum(420);
        xSpinner.setModel(xModel);
        xLabel.setLabelFor(xSpinner);
        yLabel = new JLabel(message.getString("properties.decor.y"), JLabel.TRAILING);
        yModel = new SpinnerNumberModel();
        ySpinner = new JSpinner();
        yModel.setMinimum(0);
        yModel.setMaximum(500);
        ySpinner.setModel(yModel);
        yLabel.setLabelFor(ySpinner);
        spriteLabel = new JLabel(message.getString("properties.decor.sprite"), JLabel.TRAILING);
        spriteModel = new SpinnerNumberModel();
        spriteSpinner = new JSpinner();
        spriteModel.setMinimum(0);
        spriteModel.setMaximum(100);
        spriteSpinner.setModel(spriteModel);
        spriteLabel.setLabelFor(spriteSpinner);
        backgroundLabel = new JLabel(message.getString("properties.decor.decorId"), JLabel.TRAILING);
        backgroundModel = new SpinnerNumberModel();
        backgroundSpinner = new JSpinner();
        backgroundModel.setMinimum(0);
        backgroundModel.setMaximum(7);
        backgroundSpinner.setModel(backgroundModel);
        backgroundLabel.setLabelFor(backgroundSpinner);
        this.add(xLabel);
        this.add(xSpinner);
        this.add(yLabel);
        this.add(ySpinner);
        this.add(spriteLabel);
        this.add(spriteSpinner);
        this.add(backgroundLabel);
        this.add(backgroundSpinner);
        SpringUtilities.makeCompactGrid(this, 5, 2, 2, 2, 2, 2);
        this.parent.updateUI();
    }

    public void updateDecor() {
        levelService.updateDecor(decor);
    }

}
