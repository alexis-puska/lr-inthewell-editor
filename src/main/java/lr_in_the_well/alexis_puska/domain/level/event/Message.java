package lr_in_the_well.alexis_puska.domain.level.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	int timeout;
	String en;
	String fr;
	String es;
}
