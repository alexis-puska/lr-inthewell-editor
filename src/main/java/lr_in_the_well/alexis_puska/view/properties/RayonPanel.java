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

import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.service.LevelService;
import lr_in_the_well.alexis_puska.utils.SpringUtilities;
import lr_in_the_well.alexis_puska.view.DrawPanel;
import lr_in_the_well.alexis_puska.view.IdentifiablePanel;

public class RayonPanel extends IdentifiablePanel {

	private static final long serialVersionUID = -4090876979915495722L;
	private Rayon rayon;

	private JLabel enableLabel;
	private JCheckBox enableCheckBox;
	
	private JLabel typeLabel;
	private SpinnerNumberModel typeModel;
	private JSpinner typeSpinner;
	private JLabel verticalLabel;
	private JCheckBox verticalCheckBox;

	public RayonPanel(ResourceBundle message, JPanel parent, DrawPanel drawPanel, LevelService levelService, String name, Rayon rayon) {
		super(message, parent, drawPanel, levelService, name);
		this.rayon = rayon;
		enableLabel = new JLabel(message.getString("properties.rayon.enable"), JLabel.TRAILING);
		enableCheckBox = new JCheckBox();
		enableLabel.setLabelFor(enableCheckBox);
		typeLabel = new JLabel(message.getString("properties.rayon.type"), JLabel.TRAILING);
		typeModel = new SpinnerNumberModel();
		typeSpinner = new JSpinner();
		typeModel.setMinimum(0);
		typeModel.setMaximum(7);
		typeSpinner.setModel(typeModel);
		typeLabel.setLabelFor(typeSpinner);
		verticalLabel = new JLabel(message.getString("properties.rayon.vertical"), JLabel.TRAILING);
		verticalCheckBox = new JCheckBox();
		verticalLabel.setLabelFor(verticalCheckBox);
		
		idField.setText(Integer.toString(rayon.getId()));
		typeSpinner.setValue(Integer.valueOf(rayon.getType()));
		verticalCheckBox.setSelected(rayon.isVertical());
		enableCheckBox.setSelected(rayon.isEnable());
		
		this.add(enableLabel);
		this.add(enableCheckBox);
		this.add(typeLabel);
		this.add(typeSpinner);
		this.add(verticalLabel);
		this.add(verticalCheckBox);
		SpringUtilities.makeCompactGrid(this, 4, 2, 2, 2, 2, 2);
		addListeners();
		this.parent.updateUI();
	}

	public void addListeners() {
		typeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner text = (JSpinner) e.getSource();
				if (text.getValue() != null) {
					rayon.setType((Integer) text.getValue());
					levelService.updateRayon(rayon);
					drawPanel.repaint();
					parent.repaint();
				}
			}
		});
		verticalCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				rayon.setVertical(verticalCheckBox.isSelected());
				levelService.updateRayon(rayon);
				drawPanel.repaint();
				parent.repaint();
			}
		});
		enableCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				rayon.setEnable(enableCheckBox.isSelected());
				levelService.updateRayon(rayon);
				drawPanel.repaint();
			}
		});
	}
}
