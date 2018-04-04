package lr_in_the_well.alexis_puska.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teleporter implements Serializable {

	private static final long serialVersionUID = 8337468415269378236L;
	private int id;
	private int x;
	private int y;
	private int length;
	private boolean vertical;
	private int toId;
}
