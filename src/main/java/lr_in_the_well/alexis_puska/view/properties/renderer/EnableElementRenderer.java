package lr_in_the_well.alexis_puska.view.properties.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import lr_in_the_well.alexis_puska.domain.level.event.EnableElement;

public class EnableElementRenderer extends JLabel implements ListCellRenderer<EnableElement> {

    private static final long serialVersionUID = -7756260545095706601L;

    public EnableElementRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends EnableElement> list, EnableElement enableElement,
            int index, boolean isSelected, boolean cellHasFocus) {
        if (enableElement.getElementType() == null) {
            setIcon(new ImageIcon(getClass().getResource("/icon/warn.png")));
            setText("NOT CONFIGURED");
        } else {
            setIcon(new ImageIcon(getClass().getResource("/icon/ok.png")));
            setText(enableElement.getElementType().name() + " " + enableElement.getId() + " "
                    + enableElement.isNewState());
        }
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setForeground(list.getForeground());
            if (enableElement.isNewState()) {
                setBackground(new Color(210, 255, 210));
            } else {
                setBackground(new Color(255, 210, 210));
            }
        }
        return this;
    }
}