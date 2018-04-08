package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vortex extends Identifiable implements Serializable {

	private static final long serialVersionUID = 3701911571588077040L;
	private int x;
	private int y;
	private double zoomX;
	private double zoomY;
	private boolean enable;
	private int destination;
	private int animationType;

	public Vortex(int id, int x, int y, double zoomX, double zoomY, boolean enable, int destination,
			int animationType) {
		super(id);
		this.x = x;
		this.y = y;
		this.zoomX = zoomX;
		this.zoomY = zoomY;
		this.enable = enable;
		this.destination = destination;
		this.animationType = animationType;
	}

}
