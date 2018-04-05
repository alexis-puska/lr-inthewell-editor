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
public class Event implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1088138268216464188L;

    private boolean triggered;
    private int id;
    private int x;
    private int y;
    private int type;
    private int objectId;
    private int vortexId;
    private String animation;
    private int doorId;
    private int platformToMRemove;
    private int plateformToMove;
    private int movePlateformX;
    private int movePlateformY;

}
