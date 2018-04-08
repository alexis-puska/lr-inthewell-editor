package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rayon extends Identifiable implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private int x;
	private int y;
	private int length;
	private int type;
	private boolean vertical;

	public Rayon(int id, int x, int y, int length, int type, boolean vertical) {
		super(id);
		this.x = x;
		this.y = y;
		this.length = length;
		this.type = type;
		this.vertical = vertical;
	}

}
