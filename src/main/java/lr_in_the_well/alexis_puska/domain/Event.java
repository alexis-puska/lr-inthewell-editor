package lr_in_the_well.alexis_puska.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088138268216464188L;
	
	private boolean triggered;
	private int type;
	private int objectId;
	private int vortexId;
	private String animation;
	private int doorsId;
	private int plateformToRemove;
	private int plateformToMove;
	private int movePlateformX;
	private int movePlateformY;
}
