package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vortex implements Serializable {

	private static final long serialVersionUID = 3701911571588077040L;
	private int id;
	private int x;
	private int y;
	private double zoomX;
	private double zoomY;
	private boolean enable;
	private int destination;
	private int animationType;
}
