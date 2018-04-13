package lr_in_the_well.alexis_puska.view.properties;

import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class LockPanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private Lock lock;

	private JLabel requieredKeyLabel;
	private SpinnerNumberModel requieredKeyModel;
	private JSpinner requieredKeySpinner;

	public LockPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name,
			Lock lock) {
		super(message, parent, drawPanel, levelService, name);
		this.lock = lock;
		requieredKeyLabel = new JLabel(message.getString("properties.lock.requiredKey"), JLabel.TRAILING);
		requieredKeyModel = new SpinnerNumberModel();
		requieredKeySpinner = new JSpinner();
		requieredKeySpinner.setModel(requieredKeyModel);
		requieredKeyLabel.setLabelFor(requieredKeySpinner);

		requieredKeyLabel.setToolTipText(message.getString("properties.lock.requiredKey.description"));
		requieredKeyLabel.setToolTipText(message.getString("properties.lock.requiredKey.description"));
		requieredKeyLabel.setToolTipText(message.getString("properties.lock.serrure.description"));
		requieredKeyLabel.setToolTipText(message.getString("properties.lock.serrure.description"));

		this.add(requieredKeyLabel);
		this.add(requieredKeySpinner);

		addListeners();
		SpringUtilities.makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
		this.parent.updateUI();
	}

	public void addListeners() {
		requieredKeySpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					lock.setRequieredKeyId((Integer) text.getValue());
					levelService.updateLock(lock);
					drawPanel.repaint();
					parent.repaint();
				}
			}
		});
	}

	public void updateLock() {
		levelService.updateLock(lock);
	}

}
