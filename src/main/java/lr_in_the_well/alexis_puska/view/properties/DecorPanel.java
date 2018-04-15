package lr_in_the_well.alexis_puska.view.properties;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	private JLabel backgroundIdLabel;
	private JLabel displayedLabel;
	private JLabel backLabel;

	private SpinnerNumberModel xModel;
	private SpinnerNumberModel yModel;
	private SpinnerNumberModel backgroundIdModel;

	private JSpinner xSpinner;
	private JSpinner ySpinner;
	private JSpinner backgroundIdSpinner;
	private JCheckBox displayCheckBox;
	private JCheckBox backCheckBox;

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

		backgroundIdLabel = new JLabel(message.getString("properties.decor.decorId"), JLabel.TRAILING);
		backgroundIdModel = new SpinnerNumberModel();
		backgroundIdSpinner = new JSpinner();
		backgroundIdModel.setMinimum(0);
		backgroundIdModel.setMaximum(9);
		backgroundIdSpinner.setModel(backgroundIdModel);
		backgroundIdLabel.setLabelFor(backgroundIdSpinner);
		
		displayedLabel = new JLabel(message.getString("properties.decor.display"), JLabel.TRAILING);
		displayCheckBox = new JCheckBox();
		displayedLabel.setLabelFor(displayCheckBox);

		backLabel = new JLabel(message.getString("properties.decor.back"), JLabel.TRAILING);
		backCheckBox = new JCheckBox();
		backLabel.setLabelFor(backCheckBox);
		
		this.add(xLabel);
		this.add(xSpinner);
		this.add(yLabel);
		this.add(ySpinner);
		this.add(backgroundIdLabel);
		this.add(backgroundIdSpinner);
		this.add(displayedLabel);
		this.add(displayCheckBox);
		this.add(backLabel);
		this.add(backCheckBox);


		idField.setText(Integer.toString(decor.getId()));
		xSpinner.setValue(Integer.valueOf(decor.getX()));
		ySpinner.setValue(Integer.valueOf(decor.getY()));
		backgroundIdSpinner.setValue(Integer.valueOf(decor.getIndexAnim()));
		displayCheckBox.setSelected(decor.isDisplay());
		backCheckBox.setSelected(decor.isBack());

		SpringUtilities.makeCompactGrid(this, 6, 2, 2, 2, 2, 2);
		addListeners();
		this.parent.updateUI();
	}

	public void updateDecor() {
		levelService.updateDecor(decor);
	}

	public void addListeners() {
		xSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					decor.setX((Integer) text.getValue());
					levelService.updateDecor(decor);
					drawPanel.repaint();
					parent.repaint();
				}
			}
		});
		ySpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					decor.setY((Integer) text.getValue());
					levelService.updateDecor(decor);
					drawPanel.repaint();
					parent.repaint();
				}
			}
		});
		backgroundIdSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					decor.setIndexAnim((Integer) text.getValue());
					levelService.updateDecor(decor);
					drawPanel.repaint();
					parent.repaint();
				}
			}
		});
		displayCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				decor.setDisplay(displayCheckBox.isSelected());
				levelService.updateDecor(decor);
				drawPanel.repaint();
			}
		});
		backCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				decor.setBack(backCheckBox.isSelected());
				levelService.updateDecor(decor);
				drawPanel.repaint();
			}
		});
	}

}
