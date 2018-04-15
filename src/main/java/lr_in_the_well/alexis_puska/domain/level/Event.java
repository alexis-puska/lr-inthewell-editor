package lr_in_the_well.alexis_puska.domain.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event extends Identifiable implements Serializable {

	private static final long serialVersionUID = 1088138268216464188L;

	private boolean triggered;
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

	public Event(int id, boolean triggered, int x, int y, int type, int objectId, int vortexId, String animation,
			int doorId, int platformToMRemove, int plateformToMove, int movePlateformX, int movePlateformY) {
		super(id);
		this.triggered = triggered;
		this.x = x;
		this.y = y;
		this.type = type;
		this.objectId = objectId;
		this.vortexId = vortexId;
		this.animation = animation;
		this.doorId = doorId;
		this.platformToMRemove = platformToMRemove;
		this.plateformToMove = plateformToMove;
		this.movePlateformX = movePlateformX;
		this.movePlateformY = movePlateformY;
	}

	public Event(int id, int x, int y) {
		super(id);
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Dans le code du jeu il y a les triggers 
	 * $t_timer
	 * $t_pos
	 * $attach
	 * $do
	 * $end
	 * $birth
	 * $death
	 * $exp
	 * $enter
	 * $night
	 * $mirror
	 * $multi
	 * $ninja
	 * Et pour les actions ils y a 
	 * $e_score
	 * $e_spec
$e_ext
$e_bad
$e_kill
$e_tuto
$e_msg
$e_killMsg
$e_pointer
$e_killPt
$e_mc
$e_pmc
$e_music
$e_add
$e_rem
$e_itemLine
$e_goto
$e_hide
$e_hideBorders
$e_ctrigger
$e_portal
$e_setVar
$e_openPortal
$e_darkness
$e_fakelid
	 */

}
