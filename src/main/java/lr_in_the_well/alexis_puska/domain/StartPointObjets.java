package lr_in_the_well.alexis_puska.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartPointObjets implements Serializable {

	private static final long serialVersionUID = 9116647666198572784L;
	private int x;
	private int y;
}
