package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lr_in_the_well.alexis_puska.constant.GameKeyEnum;

@Getter
@Setter
@NoArgsConstructor
public class Door extends Identifiable implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private boolean enable;
	private int x;
	private int y;
	private int type;
	private boolean locked;
	private int toLevel;
	private GameKeyEnum key;
	private int lockId;

	public Door(int id, boolean enable, int x, int y, int type, boolean locked, int toLevel, GameKeyEnum key, int lockId) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.type = type;
		this.locked = locked;
		this.toLevel = toLevel;
		this.key = key;
		this.lockId = lockId;
	}
}
