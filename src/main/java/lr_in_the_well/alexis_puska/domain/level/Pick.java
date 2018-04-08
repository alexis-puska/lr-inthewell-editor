package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pick extends Identifiable implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private int x;
	private int y;
	private boolean visible;
	private int direction;

	public Pick(int id, int x, int y, boolean visible, int direction) {
		super(id);
		this.x = x;
		this.y = y;
		this.visible = visible;
		this.direction = direction;
	}

}
