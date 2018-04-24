package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
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
    
    private Teleporter teleporter;

    private JLabel enableLabel;
	private JCheckBox enableCheckBox;
    
    private JLabel destinationLabel;
    private JTextField destinationTextField;

    public TeleporterPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name,
            Teleporter teleporter) {
        super(message, parent, drawPanel, levelService, name);
        this.teleporter = teleporter;
        enableLabel = new JLabel(message.getString("properties.teleporter.enable"), JLabel.TRAILING);
		enableCheckBox = new JCheckBox();
		enableLabel.setLabelFor(enableCheckBox);
        destinationLabel = new JLabel(message.getString("properties.teleporter.destination"), JLabel.TRAILING);
        destinationTextField = new JTextField();
        destinationLabel.setLabelFor(destinationTextField);
        destinationTextField.setToolTipText(message.getString("properties.teleporter.destination.description"));
        destinationLabel.setToolTipText(message.getString("properties.teleporter.destination.description"));
        this.add(enableLabel);
		this.add(enableCheckBox);
        this.add(destinationLabel);
        this.add(destinationTextField);
        idField.setText(Integer.toString(teleporter.getId()));
        destinationTextField.setText(Integer.toString(teleporter.getToId()));
        enableCheckBox.setSelected(teleporter.isEnable());
        SpringUtilities.makeCompactGrid(this, 3, 2, 6, 6, 6, 6);
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
        enableCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				teleporter.setEnable(enableCheckBox.isSelected());
				levelService.updateTeleporter(teleporter);
				drawPanel.repaint();
			}
		});
    }
}
