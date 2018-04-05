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
public class Teleporter implements Serializable {

    private static final long serialVersionUID = 8337468415269378236L;
    private int id;
    private int x;
    private int y;
    private int length;
    private boolean vertical;
    private int toId;
}
