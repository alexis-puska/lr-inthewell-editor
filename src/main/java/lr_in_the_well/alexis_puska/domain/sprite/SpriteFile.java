package lr_in_the_well.alexis_puska.domain.sprite;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpriteFile implements Serializable {

    private static final long serialVersionUID = -8772069448741457644L;

    private String file;
    private List<Sprite> area;
}
