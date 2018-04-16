package lr_in_the_well.alexis_puska.domain.level.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lr_in_the_well.alexis_puska.constant.EnabledElementEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnableElement {
    private int id;
    private EnabledElementEnum elementType;
    private boolean newState;
}
