package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;

import lr_in_the_well.alexis_puska.domain.level.Teleporter;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class TeleporterPanel extends IdentifiablePanel {

    private static final long serialVersionUID = -4090876979915495722L;
    private static final String DESTINATION = "Destination : ";
    
    private Teleporter teleporter;

    private JLabel destinationLabel;

    private JTextField destinationTextField;

    public TeleporterPanel(JPanel parent, DrawPanel drawPanel, LevelService levelService, String name,
            Teleporter teleporter) {
        super(parent, drawPanel, levelService, name);
        this.teleporter = teleporter;
        destinationLabel = new JLabel(DESTINATION, JLabel.TRAILING);
        destinationTextField = new JTextField();
        destinationLabel.setLabelFor(destinationTextField);
        this.add(destinationLabel);
        this.add(destinationTextField);
        idField.setText(Integer.toString(teleporter.getId()));
        destinationTextField.setText(Integer.toString(teleporter.getToId()));
        SpringUtilities.makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
        addListeners();
        this.parent.updateUI();
    }

    public void updateTeleporter() {
        levelService.updateTeleporter(teleporter);
    }

    public void addListeners() {
        destinationTextField.addCaretListener(new CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField) e.getSource();
                if (text.getText() != null && !text.getText().isEmpty()) {
                    teleporter.setToId(Integer.valueOf(text.getText()));
                    levelService.updateTeleporter(teleporter);
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
    }
}