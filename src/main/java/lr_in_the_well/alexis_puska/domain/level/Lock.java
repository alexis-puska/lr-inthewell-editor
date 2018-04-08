package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lock extends Identifiable implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private int x;
	private int y;
	private int requieredKeyId;

	public Lock(int id, int x, int y, int requieredKeyId) {
		super(id);
		this.x = x;
		this.y = y;
		this.requieredKeyId = requieredKeyId;
	}

}
