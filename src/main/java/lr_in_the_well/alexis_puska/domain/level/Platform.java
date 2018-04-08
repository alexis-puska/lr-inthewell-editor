package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Platform extends Identifiable implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private int x;
	private int y;
	private boolean vertical;
	private boolean visible;
	private int length;
	private int surfaceId;

	public Platform(int id, int x, int y, boolean vertical, boolean visible, int length, int surfaceId) {
		super(id);
		this.x = x;
		this.y = y;
		this.vertical = vertical;
		this.visible = visible;
		this.length = length;
		this.surfaceId = surfaceId;
	}

}
