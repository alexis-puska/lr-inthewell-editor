package lr_in_the_well.alexis_puska.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lr_in_the_well.alexis_puska.domain.level.Decor;
import lr_in_the_well.alexis_puska.domain.level.Door;
import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.domain.level.Event;
import lr_in_the_well.alexis_puska.domain.level.Identifiable;
import lr_in_the_well.alexis_puska.domain.level.Level;
import lr_in_the_well.alexis_puska.domain.level.LevelFile;
import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.domain.level.StartEffectObjets;
import lr_in_the_well.alexis_puska.domain.level.StartPlayer;
import lr_in_the_well.alexis_puska.domain.level.StartPointObjets;
import lr_in_the_well.alexis_puska.domain.level.Teleporter;
import lr_in_the_well.alexis_puska.domain.level.Type;
import lr_in_the_well.alexis_puska.domain.level.Vortex;

public class LevelService {

	private int currentTypeIndex;
	private int currentLevelIndex;
	private Type currentType;
	private Level currentLevel;
	private Map<Integer, Type> typeMap;
	private Map<Integer, Level> levelMap;
	private LevelFile levelFile;

	public LevelService() {
		this.currentTypeIndex = 0;
		this.currentLevelIndex = 0;
	}

	public void putLevelFile(LevelFile levelFile) {
		this.currentTypeIndex = 0;
		this.currentLevelIndex = 0;
		this.levelFile = levelFile;
		// init maps
		this.typeMap = new HashMap<>();
		this.levelMap = new HashMap<>();
		for (Type type : levelFile.getType()) {
			typeMap.put(type.getId(), type);
		}
		// init current object
		currentType = typeMap.get(currentTypeIndex);
		loadCurrentType();
	}

	public LevelFile getLevelFile() {
		saveCurrentLevel();
		saveCurrentType();
		levelFile.setType(new ArrayList<Type>(typeMap.values()));
		return levelFile;
	}

	// LEVEL
	public Level getCurrentLevel() {
		return currentLevel;
	}

	public int getNbLevel() {
		return levelMap.size();
	}

	public int getCurrentLevelIndex() {
		return currentLevelIndex;
	}

	public void setCurrentLevelIndex(int currentLevelIndex) {
		saveCurrentLevel();
		this.currentLevelIndex = currentLevelIndex;
		loadCurrentLevel();
	}

	private void saveCurrentLevel() {
		if (currentLevel != null) {
			this.levelMap.put(currentLevelIndex, currentLevel);
		}
	}

	private void loadCurrentLevel() {
		currentLevel = this.levelMap.get(currentLevelIndex);
	}

	public void deleteLevel() {
		if (this.currentLevel != null) {
			this.levelMap.remove(currentLevel.getId());
		}
		this.currentLevel = null;
	}

	public void createLevel() {
		this.currentLevel = new Level(this.currentLevelIndex);
	}

	// Type
	public Type getCurrentType() {
		return currentType;
	}

	public int getNbType() {
		return typeMap.size();
	}

	public int getCurrentTypeIndex() {
		return currentTypeIndex;
	}

	public void setCurrentTypeIndex(int current) {
		saveCurrentType();
		this.currentTypeIndex = current;
		loadCurrentType();
	}

	private void saveCurrentType() {
		saveCurrentLevel();
		currentType.setLevel(new ArrayList<Level>(this.levelMap.values()));
		typeMap.put(currentTypeIndex, currentType);
	}

	private void loadCurrentType() {
		currentLevelIndex = 0;
		currentType = typeMap.get(currentTypeIndex);
		levelMap = new HashMap<>();
		currentLevel = levelMap.get(currentLevelIndex);
		for (Level level : currentType.getLevel()) {
			levelMap.put(level.getId(), level);
		}
		loadCurrentLevel();
	}

	public void setHorizontalPlatformId(int id) {
		if (currentLevel != null) {
			this.currentLevel.setHorizontalPlateform(id);
			this.saveCurrentLevel();
		}
	}

	public int getHorizontalPlatformId() {
		if (currentLevel != null) {
			return currentLevel.getHorizontalPlateform();
		} else {
			return 0;
		}
	}

	public void setVerticalPlatformId(int id) {
		if (currentLevel != null) {
			this.currentLevel.setVerticalPlateform(id);
			this.saveCurrentLevel();
		}
	}

	public int getVerticalPlatformId() {
		if (currentLevel != null) {
			return currentLevel.getVerticalPlateform();
		} else {
			return 0;
		}
	}

	public void setBackgroundId(int id) {
		if (currentLevel != null) {
			this.currentLevel.setBackground(id);
			this.saveCurrentLevel();
		}
	}

	public int getBackgroundId() {
		if (currentLevel != null) {
			return currentLevel.getBackground();
		} else {
			return 1;
		}
	}

	public void setShowPlatform(boolean show) {
		if (currentLevel != null) {
			this.currentLevel.setShowPlatform(show);
			this.saveCurrentLevel();
		}
	}

	public boolean isShowPlatform() {
		if (currentLevel != null) {
			return currentLevel.isShowPlatform();
		} else {
			return true;
		}
	}

	public void setNextLevelId(int id) {
		if (currentLevel != null) {
			this.currentLevel.setNext(id);
			this.saveCurrentLevel();
		}
	}

	public int getgetNextLevelId() {
		if (currentLevel != null) {
			return currentLevel.getNext();
		} else {
			return 0;
		}
	}

	public void addPlatform(int x, int y, int d, boolean vertical) {

		Platform p = new Platform();
		p.setId(getIdFromIdentifiable(currentLevel.getPlatform()));
		if (vertical) {
			p.setVertical(true);
			p.setVisible(true);
			if (d > 24) {
				d = 24;
			}
			int l = d - y;
			if (l < 0) {
				l *= -1;
			}
			l++;
			if (d > y) {
				p.setY(y);
			} else {
				p.setY(d);
			}
			
			if(l>20) {
				l = 20;
			}
			
			p.setLength(l);
			p.setX(x);
		} else {
			p.setVertical(false);
			p.setVisible(true);
			if (d > 19) {
				d = 19;
			}
			int l = d - x;
			if (l < 0) {
				l *= -1;
			}
			l++;
			if (d > x) {
				p.setX(x);
			} else {
				p.setX(d);
			}
			p.setLength(l);
			p.setY(y);
		}
		currentLevel.getPlatform().add(p);
	}

	public void addTeleporter(int x, int y, int x2, int y2) {
		Level level = currentLevel;
		Teleporter t = new Teleporter();
		t.setId(getIdFromIdentifiable(level.getTeleporter()));
		if (x2 > 19) {
			x2 = 19;
		}
		if (y2 > 24) {
			y2 = 24;
		}
		int lx = x2 - x;
		int ly = y2 - y;
		boolean vertical = false;
		if (lx < 0) {
			lx *= -1;
		}
		if (ly < 0) {
			ly *= -1;
		}
		if (lx > ly) {
			vertical = false;
		} else {
			vertical = true;
		}
		if (vertical) {
			t.setVertical(true);
			ly++;
			if (y2 > y) {
				t.setY(y);
			} else {
				t.setY(y2);
			}
			t.setLength(ly);
			t.setX(x);
		} else {
			t.setVertical(false);
			lx++;
			if (x2 > x) {
				t.setX(x);
			} else {
				t.setX(x2);
			}
			t.setLength(lx);
			t.setY(y);
		}
		level.getTeleporter().add(t);
	}

	public void addRayon(int x, int y, int x2, int y2) {
		Level level = currentLevel;
		Rayon r = new Rayon();
		r.setId(getIdFromIdentifiable(level.getRayon()));
		if (x2 > 19) {
			x2 = 19;
		}
		if (y2 > 24) {
			y2 = 24;
		}
		int lx = x2 - x;
		int ly = y2 - y;
		boolean vertical = false;
		if (lx < 0) {
			lx *= -1;
		}
		if (ly < 0) {
			ly *= -1;
		}
		if (lx > ly) {
			vertical = false;
		} else {
			vertical = true;
		}
		if (vertical) {
			r.setVertical(true);
			ly++;
			if (y2 > y) {
				r.setY(y);
			} else {
				r.setY(y2);
			}
			r.setLength(ly);
			r.setX(x);
		} else {
			r.setVertical(false);
			lx++;
			if (x2 > x) {
				r.setX(x);
			} else {
				r.setX(x2);
			}
			r.setLength(lx);
			r.setY(y);
		}
		level.getRayon().add(r);
	}

	public void addEnnemie(int x, int y, int type) {
		Level level = currentLevel;
		int nbEnnemies = level.getEnnemies().size();
		level.getEnnemies().add(new Ennemie(nbEnnemies, x, y, type));
	}

	public void addDecor(int x, int y) {
		saveCurrentLevel();
	}

	public void addVortex(int x, int y) {
		saveCurrentLevel();
	}

	public void addPick(int x, int y) {
		saveCurrentLevel();
	}

	public void addLock(int x, int y) {
		saveCurrentLevel();
	}

	public void addDoor(int x, int y) {
		saveCurrentLevel();
	}

	public void addEvent(int x, int y) {
		saveCurrentLevel();
	}

	public void addPlayerSpawn(int x, int y) {
		currentLevel.getStartPlayers().add(new StartPlayer(x, y));
		saveCurrentLevel();
	}

	public void addObjectPoint(int x, int y) {
		currentLevel.getStartPointObjets().add(new StartPointObjets(x, y));
		saveCurrentLevel();
	}

	public void addObjectEffect(int x, int y) {
		currentLevel.getStartEffectObjets().add(new StartEffectObjets(x, y));
		saveCurrentLevel();
	}

	public void deleteElement(int x, int y) {
		// DECORS
		List<Decor> decorToDelete = new ArrayList<>();
		for (Decor e : currentLevel.getDecor()) {
			if (e.getX() == x && e.getY() == y) {
				decorToDelete.add(e);
			}
		}
		currentLevel.getDecor().removeAll(decorToDelete);

		// DOORS
		List<Door> doorToDelete = new ArrayList<>();
		for (Door e : currentLevel.getDoor()) {
			if (e.getX() == x && e.getY() == y) {
				doorToDelete.add(e);
			}
		}
		currentLevel.getDoor().removeAll(doorToDelete);

		// ENNEMIES
		List<Ennemie> ennemieToDelete = new ArrayList<>();
		for (Ennemie e : currentLevel.getEnnemies()) {
			if (e.getX() == x && e.getY() == y) {
				ennemieToDelete.add(e);
			}
		}
		currentLevel.getEnnemies().removeAll(ennemieToDelete);

		// LOCK
		List<Lock> lockToDelete = new ArrayList<>();
		for (Lock e : currentLevel.getLock()) {
			if (e.getX() == x && e.getY() == y) {
				lockToDelete.add(e);
			}
		}
		currentLevel.getLock().removeAll(lockToDelete);

		// PICK
		List<Pick> pickToDelete = new ArrayList<>();
		for (Pick e : currentLevel.getPick()) {
			if (e.getX() == x && e.getY() == y) {
				pickToDelete.add(e);
			}
		}
		currentLevel.getPick().removeAll(pickToDelete);

		// PLATEFORM
		List<Platform> platformToDelete = new ArrayList<>();
		for (Platform e : currentLevel.getPlatform()) {
			if (e.isVertical()) {
				int min = e.getY();
				int max = e.getY() + e.getLength();
				if (e.getX() == x && (min <= y && y < max)) {

					platformToDelete.add(e);
				}
			} else {
				int min = e.getX();
				int max = e.getX() + e.getLength();
				if (e.getY() == y && (min <= x && x < max)) {

					platformToDelete.add(e);
				}
			}
		}
		currentLevel.getPlatform().removeAll(platformToDelete);

		// RAYON
		List<Rayon> rayonToDelete = new ArrayList<>();
		for (Rayon e : currentLevel.getRayon()) {
			if (e.isVertical()) {
				int min = e.getY();
				int max = e.getY() + e.getLength();
				if (e.getX() == x && (min <= y && y < max)) {
					rayonToDelete.add(e);
				}
			} else {
				int min = e.getX();
				int max = e.getX() + e.getLength();
				if (e.getY() == y && (min <= x && x < max)) {
					rayonToDelete.add(e);
				}
			}
		}
		currentLevel.getRayon().removeAll(rayonToDelete);

		// TELEPORTER
		List<Teleporter> teleporterToDelete = new ArrayList<>();
		for (Teleporter e : currentLevel.getTeleporter()) {
			if (e.isVertical()) {
				int min = e.getY();
				int max = e.getY() + e.getLength();
				if (e.getX() == x && (min <= y && y < max)) {
					teleporterToDelete.add(e);
				}
			} else {
				int min = e.getX();
				int max = e.getX() + e.getLength();
				if (e.getY() == y && (min <= x && x < max)) {
					teleporterToDelete.add(e);
				}
			}
		}
		currentLevel.getTeleporter().removeAll(teleporterToDelete);

		// EFFECT OBJET
		List<StartEffectObjets> startEffectObjectToDelete = new ArrayList<>();
		for (StartEffectObjets e : currentLevel.getStartEffectObjets()) {
			if (e.getX() == x && e.getY() == y) {
				startEffectObjectToDelete.add(e);
			}
		}
		currentLevel.getStartEffectObjets().removeAll(startEffectObjectToDelete);

		// STARTPLAYER
		List<StartPlayer> startPlayerToDelete = new ArrayList<>();
		for (StartPlayer e : currentLevel.getStartPlayers()) {
			if (e.getX() == x && e.getY() == y) {
				startPlayerToDelete.add(e);
			}
		}
		currentLevel.getStartPlayers().removeAll(startPlayerToDelete);

		// START POINT
		List<StartPointObjets> startPointObjetsToDelete = new ArrayList<>();
		for (StartPointObjets e : currentLevel.getStartPointObjets()) {
			if (e.getX() == x && e.getY() == y) {
				startPointObjetsToDelete.add(e);
			}
		}
		currentLevel.getStartPointObjets().removeAll(startPointObjetsToDelete);
		saveCurrentLevel();
	}

	public int getIdFromIdentifiable(List<? extends Identifiable> list) {
		int max = 0;
		int freeId = -1;
		for (Identifiable pl : list) {
			if (pl.getId() > max) {
				max = pl.getId();
			}
		}
		Boolean[] id = new Boolean[max + 1];
		for (Identifiable pl : list) {
			id[pl.getId()] = true;
		}
		for (int i = 0; i < max; i++) {
			if (id[i] == null) {
				freeId = i;
				break;
			}
		}
		if (freeId == -1) {
			freeId = list.size();
		}
		return freeId;
	}

	public Identifiable getProperties(int x, int y) {
		for (Decor e : currentLevel.getDecor()) {
			if (e.getX() == x && e.getY() == y) {
				return e;
			}
		}
		for (Door e : currentLevel.getDoor()) {
			if (e.getX() == x && e.getY() == y) {
				return e;
			}
		}
		for (Ennemie e : currentLevel.getEnnemies()) {
			if (e.getX() == x && e.getY() == y) {
				return e;
			}
		}
		for (Lock e : currentLevel.getLock()) {
			if (e.getX() == x && e.getY() == y) {
				return e;
			}
		}
		for (Pick e : currentLevel.getPick()) {
			if (e.getX() == x && e.getY() == y) {
				return e;
			}
		}
		for (Platform e : currentLevel.getPlatform()) {
			if (e.isVertical()) {
				int min = e.getY();
				int max = e.getY() + e.getLength();
				if (e.getX() == x && (min <= y && y < max)) {

					return e;
				}
			} else {
				int min = e.getX();
				int max = e.getX() + e.getLength();
				if (e.getY() == y && (min <= x && x < max)) {

					return e;
				}
			}
		}
		for (Rayon e : currentLevel.getRayon()) {
			if (e.isVertical()) {
				int min = e.getY();
				int max = e.getY() + e.getLength();
				if (e.getX() == x && (min <= y && y < max)) {
					return e;
				}
			} else {
				int min = e.getX();
				int max = e.getX() + e.getLength();
				if (e.getY() == y && (min <= x && x < max)) {
					return e;
				}
			}
		}
		for (Teleporter e : currentLevel.getTeleporter()) {
			if (e.isVertical()) {
				int min = e.getY();
				int max = e.getY() + e.getLength();
				if (e.getX() == x && (min <= y && y < max)) {
					return e;
				}
			} else {
				int min = e.getX();
				int max = e.getX() + e.getLength();
				if (e.getY() == y && (min <= x && x < max)) {
					return e;
				}
			}
		}
		return null;
	}

	public void updateRayon(Rayon rayon) {
		currentLevel.getRayon().remove(rayon);
		currentLevel.getRayon().add(rayon);
		saveCurrentLevel();
	}

	public void updateVortex(Vortex vortex) {
		currentLevel.getVortex().remove(vortex);
		currentLevel.getVortex().add(vortex);
		saveCurrentLevel();
	}

	public void updateTeleporter(Teleporter teleporter) {
		currentLevel.getTeleporter().remove(teleporter);
		currentLevel.getTeleporter().add(teleporter);
		saveCurrentLevel();
	}

	public void updatePick(Pick pick) {
		currentLevel.getPick().remove(pick);
		currentLevel.getPick().add(pick);
		saveCurrentLevel();
	}

	public void updateDoor(Door door) {
		currentLevel.getDoor().remove(door);
		currentLevel.getDoor().add(door);
		saveCurrentLevel();
	}

	public void updateLock(Lock lock) {
		currentLevel.getLock().remove(lock);
		currentLevel.getLock().add(lock);
		saveCurrentLevel();
	}

	public void updateDecor(Decor decor) {
		currentLevel.getDecor().remove(decor);
		currentLevel.getDecor().add(decor);
		saveCurrentLevel();
	}

	public void updateEvent(Event event) {
		currentLevel.getEvent().remove(event);
		currentLevel.getEvent().add(event);
		saveCurrentLevel();
	}

	public void updateEnnemie(Ennemie ennemie) {
		currentLevel.getEnnemies().remove(ennemie);
		currentLevel.getEnnemies().add(ennemie);
		saveCurrentLevel();
	}

}
