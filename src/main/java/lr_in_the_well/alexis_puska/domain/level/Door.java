package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Door extends Identifiable implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private int x;
	private int y;
	private int type;
	private boolean locked;
	private int toLevel;
	private int requieredKey;
	private int lockId;

	public Door(int id, int x, int y, int type, boolean locked, int toLevel, int requieredKey, int lockId) {
		super(id);
		this.x = x;
		this.y = y;
		this.type = type;
		this.locked = locked;
		this.toLevel = toLevel;
		this.requieredKey = requieredKey;
		this.lockId = lockId;
	}
}
