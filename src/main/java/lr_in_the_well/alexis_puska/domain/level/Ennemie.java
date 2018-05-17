package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lr_in_the_well.alexis_puska.constant.EnnemieTypeEnum;

@Getter
@Setter
@NoArgsConstructor
public class Ennemie extends Identifiable implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private boolean enable;
	private int x;
	private int y;
	private EnnemieTypeEnum type;

	public Ennemie(int id, boolean enable, int x, int y, EnnemieTypeEnum type) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.type = type;
	}

}
