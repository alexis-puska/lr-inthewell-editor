package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event extends Identifiable implements Serializable {

	private static final long serialVersionUID = 1088138268216464188L;

	private boolean triggered;
	private int x;
	private int y;
	private int type;
	private int objectId;
	private int vortexId;
	private String animation;
	private int doorId;
	private int platformToMRemove;
	private int plateformToMove;
	private int movePlateformX;
	private int movePlateformY;

	public Event(int id, boolean triggered, int x, int y, int type, int objectId, int vortexId, String animation,
			int doorId, int platformToMRemove, int plateformToMove, int movePlateformX, int movePlateformY) {
		super(id);
		this.triggered = triggered;
		this.x = x;
		this.y = y;
		this.type = type;
		this.objectId = objectId;
		this.vortexId = vortexId;
		this.animation = animation;
		this.doorId = doorId;
		this.platformToMRemove = platformToMRemove;
		this.plateformToMove = plateformToMove;
		this.movePlateformX = movePlateformX;
		this.movePlateformY = movePlateformY;
	}

}
