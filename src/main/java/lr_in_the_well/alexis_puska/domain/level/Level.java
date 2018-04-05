package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Level implements Serializable {

    private static final long serialVersionUID = -3619458033448491606L;

    private int id;
    private int next;
    private boolean showPlatform;
    private int background;
    private int verticalPlateform;
    private int horizontalPlateform;
    private List<Decor> decor;
    private List<Event> event;
    private List<Door> door;
    private List<Lock> lock;
    private List<Pick> pick;
    private List<Platform> platform;
    private List<Rayon> rayon;
    private List<Teleporter> teleporter;
    private List<Vortex> vortex;
    private List<Ennemie> ennemies;
    private List<StartPlayer> startPlayers;
    private List<StartEffectObjets> startEffectObjets;
    private List<StartPointObjets> startPointObjets;

    public Level(int id, boolean showPlatform, int background, int verticalPlateform, int horizontalPlateform) {
        super();
        this.id = id;
        this.showPlatform = showPlatform;
        this.background = background;
        this.verticalPlateform = verticalPlateform;
        this.horizontalPlateform = horizontalPlateform;
        this.next = 0;
        this.decor = new ArrayList<>();
        this.event = new ArrayList<>();
        this.door = new ArrayList<>();
        this.lock = new ArrayList<>();
        this.pick = new ArrayList<>();
        this.platform = new ArrayList<>();
        this.rayon = new ArrayList<>();
        this.teleporter = new ArrayList<>();
        this.vortex = new ArrayList<>();
        this.ennemies = new ArrayList<>();
        this.startPlayers = new ArrayList<>();
        this.startEffectObjets = new ArrayList<>();
        this.startPointObjets = new ArrayList<>();
    }

}
