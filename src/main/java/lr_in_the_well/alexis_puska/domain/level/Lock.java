package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lr_in_the_well.alexis_puska.constant.GameKeyEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lock extends Identifiable implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private boolean enable;
	private int x;
	private int y;
	private GameKeyEnum key;

	public Lock(int id, boolean enable, int x, int y, GameKeyEnum key) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.key = key;
	}

}
