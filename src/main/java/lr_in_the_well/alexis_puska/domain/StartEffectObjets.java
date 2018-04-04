package lr_in_the_well.alexis_puska.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartEffectObjets implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private int x;
	private int y;
}
