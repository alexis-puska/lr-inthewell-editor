package lr_in_the_well.alexis_puska.view.properties.renderer;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import lr_in_the_well.alexis_puska.domain.level.Decor;
import lr_in_the_well.alexis_puska.domain.level.Door;
import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.domain.level.Identifiable;
import lr_in_the_well.alexis_puska.domain.level.Item;
import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.domain.level.Teleporter;
import lr_in_the_well.alexis_puska.domain.level.Vortex;
import lr_in_the_well.alexis_puska.domain.level.event.Event;

public class IdentifiableComboBoxRenderer extends JLabel implements ListCellRenderer<Identifiable> {

	private static final long serialVersionUID = 6026263763623162494L;
	private ResourceBundle message;

	public IdentifiableComboBoxRenderer() {
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	public IdentifiableComboBoxRenderer(ResourceBundle message) {
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		this.message = message;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Identifiable> list, Identifiable value, int index,
			boolean isSelected, boolean cellHasFocus) {
		String cellText = "";
		if (value.getClass().equals(Item.class)) {
			cellText += message.getString("identifiable.item") + value.getId();
		} else if (value.getClass().equals(Decor.class)) {
			cellText += message.getString("identifiable.decor") + value.getId();
		} else if (value.getClass().equals(Door.class)) {
			cellText += message.getString("identifiable.door") + value.getId();
		} else if (value.getClass().equals(Ennemie.class)) {
			cellText += message.getString("identifiable.ennemie") + value.getId();
		} else if (value.getClass().equals(Event.class)) {
			cellText += message.getString("identifiable.event") + value.getId();
		} else if (value.getClass().equals(Lock.class)) {
			cellText += message.getString("identifiable.lock") + value.getId();
		} else if (value.getClass().equals(Pick.class)) {
			cellText += message.getString("identifiable.pick") + value.getId();
		} else if (value.getClass().equals(Platform.class)) {
			cellText += message.getString("identifiable.platform") + value.getId();
		} else if (value.getClass().equals(Rayon.class)) {
			cellText += message.getString("identifiable.rayon") + value.getId();
		} else if (value.getClass().equals(Teleporter.class)) {
			cellText += message.getString("identifiable.teleporter") + value.getId();
		} else if (value.getClass().equals(Vortex.class)) {
			cellText += message.getString("identifiable.vortex") + value.getId();
		}
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		this.setText(cellText);
		return this;
	}

}