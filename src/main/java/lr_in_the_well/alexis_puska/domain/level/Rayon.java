package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rayon implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private int id;
	private int x;
	private int y;
	private int length;
	private int type;
	private boolean vertical;
}
