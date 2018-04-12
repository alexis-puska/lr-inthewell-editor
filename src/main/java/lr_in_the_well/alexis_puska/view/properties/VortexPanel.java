package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;

import lr_in_the_well.alexis_puska.domain.level.Vortex;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class VortexPanel extends IdentifiablePanel {

    private static final long serialVersionUID = -4090876979915495722L;
    private static final String DESTINATION = "Destination : ";
    private static final String ACTIF = "Actif : ";
    private Vortex vortex;

    private JLabel destinationLabel;
    private JTextField destinationTextField;
    private JLabel actifLabel;
    private JCheckBox actifCheckBox;

    public VortexPanel(JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Vortex vortex) {
        super(parent, drawPanel, levelService, name);
        this.vortex = vortex;
        destinationLabel = new JLabel(DESTINATION, JLabel.TRAILING);
        destinationTextField = new JTextField();
        destinationLabel.setLabelFor(destinationTextField);
        actifLabel = new JLabel(ACTIF, JLabel.TRAILING);
        actifCheckBox = new JCheckBox();
        actifLabel.setLabelFor(actifCheckBox);
        this.add(actifLabel);
        this.add(actifCheckBox);
        this.add(destinationLabel);
        this.add(destinationTextField);
        idField.setText(Integer.toString(vortex.getId()));
        destinationTextField.setText(Integer.toString(vortex.getDestination()));
        SpringUtilities.makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
        addListeners();
        this.parent.updateUI();
    }

    public void updateRayon() {
        levelService.updateVortex(vortex);
    }

    public void addListeners() {
        destinationTextField.addCaretListener(new CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField) e.getSource();
                if (text.getText() != null && !text.getText().isEmpty()) {
                    vortex.setDestination(Integer.valueOf(text.getText()));
                    levelService.updateVortex(vortex);
                }
            }
        });
        destinationTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        actifCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                vortex.setEnable(actifCheckBox.isSelected());
                levelService.updateVortex(vortex);
            }
        });
    }
}
