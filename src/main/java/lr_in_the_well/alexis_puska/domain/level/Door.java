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
public class Door implements Serializable {

    private static final long serialVersionUID = -3148349064427411770L;
    int type;
    boolean locked;
    int toLevel;
    int requieredKey;
}
