package lr_in_the_well.alexis_puska.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartPlayer implements Serializable {

	private static final long serialVersionUID = -6867679477182974708L;
	private int x;
	private int y;
}
