package lr_in_the_well.alexis_puska.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import lr_in_the_well.alexis_puska.service.LevelService;

public class IdentifiablePanel extends JPanel {

	private static final long serialVersionUID = 5594850105813021006L;

	protected JPanel parent;
	protected LevelService levelService;
	private Border border;
	private SpringLayout layout;
	
	protected JLabel idLabel;
	protected JTextField idField;
	

	public IdentifiablePanel(JPanel parent, LevelService levelService, String name) {
		this.parent = parent;
		this.levelService = levelService;
		border = BorderFactory.createTitledBorder(name);
		this.layout = new SpringLayout();
		this.setBorder(border);
		this.setLayout(layout);
		idField = new JTextField();
		idField.setEditable(false);
		idLabel = new JLabel("id");
		idLabel.setLabelFor(idField);
		this.add(idLabel);
		this.add(idField);
	}

}
