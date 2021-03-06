package lr_in_the_well.alexis_puska.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lr_in_the_well.alexis_puska.constant.Constante;
import lr_in_the_well.alexis_puska.constant.EnnemieTypeEnum;
import lr_in_the_well.alexis_puska.constant.RayonTypeEnum;
import lr_in_the_well.alexis_puska.domain.StartDimension;
import lr_in_the_well.alexis_puska.domain.level.Decor;
import lr_in_the_well.alexis_puska.domain.level.Door;
import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.domain.level.Identifiable;
import lr_in_the_well.alexis_puska.domain.level.Item;
import lr_in_the_well.alexis_puska.domain.level.Level;
import lr_in_the_well.alexis_puska.domain.level.LevelFile;
import lr_in_the_well.alexis_puska.domain.level.LevelName;
import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.domain.level.Position;
import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.domain.level.Teleporter;
import lr_in_the_well.alexis_puska.domain.level.Type;
import lr_in_the_well.alexis_puska.domain.level.Vortex;
import lr_in_the_well.alexis_puska.domain.level.event.Event;

public class LevelService {

	private int previousHorizontalPlatformIndex;
	private int previousVerticalPlatformIndex;
	private int previousBackgroundIndex;

	private int currentTypeIndex;
	private int currentLevelIndex;
	private Type currentType;
	private Level currentLevel;
	private Map<Integer, Type> typeMap;
	private Map<Integer, Level> levelMap;
	private LevelFile levelFile;

	// printLevel
	private List<Integer> treatedLevels;
	private List<String> treatedDimensions;
	private List<Integer> treatPath;

	public LevelService() {
		this.currentTypeIndex = 0;
		this.currentLevelIndex = 0;
		this.previousHorizontalPlatformIndex = 0;
		this.previousVerticalPlatformIndex = 0;
		this.previousBackgroundIndex = 1;
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
		if (this.currentLevel == null) {
			this.currentLevel = new Level(this.currentLevelIndex);
			this.currentLevel.setBackground(previousBackgroundIndex);
			this.currentLevel.setVerticalPlateform(previousVerticalPlatformIndex);
			this.currentLevel.setHorizontalPlateform(previousHorizontalPlatformIndex);
		}
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
			if (level.getItems() == null) {
				level.setItems(new ArrayList<>());
			}
			levelMap.put(level.getId(), level);
		}
		loadCurrentLevel();
	}

	public void setHorizontalPlatformId(int id) {
		if (currentLevel != null) {
			previousHorizontalPlatformIndex = id;
			this.currentLevel.setHorizontalPlateform(id);
			this.saveCurrentLevel();
		}
	}

	public int getHorizontalPlatformId() {
		if (currentLevel != null) {
			return currentLevel.getHorizontalPlateform();
		} else {
			return previousHorizontalPlatformIndex;
		}
	}

	public void setVerticalPlatformId(int id) {
		if (currentLevel != null) {
			this.previousVerticalPlatformIndex = id;
			this.currentLevel.setVerticalPlateform(id);
			this.saveCurrentLevel();
		}
	}

	public int getVerticalPlatformId() {
		if (currentLevel != null) {
			return currentLevel.getVerticalPlateform();
		} else {
			return previousVerticalPlatformIndex;
		}
	}

	public void setBackgroundId(int id) {
		if (currentLevel != null) {
			this.previousBackgroundIndex = id;
			this.currentLevel.setBackground(id);
			this.saveCurrentLevel();
		}
	}

	public String getLevelName(String lang) {

		if (this.currentLevel != null) {
			if (this.currentLevel.getName() != null) {
				for (LevelName ln : this.currentLevel.getName()) {
					if (ln.getLang().equals(lang)) {
						return ln.getValue();
					}
				}
			} else {
				this.currentLevel.setName(new ArrayList<>());
				this.currentLevel.getName()
						.add(new LevelName(getIdFromIdentifiable(this.currentLevel.getName()), "es", ""));
				this.currentLevel.getName()
						.add(new LevelName(getIdFromIdentifiable(this.currentLevel.getName()), "fr", ""));
				this.currentLevel.getName()
						.add(new LevelName(getIdFromIdentifiable(this.currentLevel.getName()), "en", ""));
			}
		}
		return "";
	}

	public void setLevelName(String lang, String name) {
		LevelName tmp = null;
		for (LevelName ln : this.currentLevel.getName()) {
			if (ln.getLang().equals(lang)) {
				tmp = ln;
				break;
			}
		}
		if (tmp != null) {
			this.currentLevel.getName().remove(tmp);
			tmp.setValue(name);
			this.currentLevel.getName().add(tmp);
		} else {
			tmp = new LevelName(getIdFromIdentifiable(this.currentLevel.getName()), lang, name);
			this.currentLevel.getName().add(tmp);
		}
		this.saveCurrentLevel();
	}

	public int getBackgroundId() {
		if (currentLevel != null) {
			return currentLevel.getBackground();
		} else {
			return previousBackgroundIndex;
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

	public int getNextLevelId() {
		if (currentLevel != null) {
			return currentLevel.getNext();
		} else {
			return 0;
		}
	}

	public void addPlatform(int x, int y, int d, boolean vertical) {
		if (currentLevel != null) {
			Platform p = new Platform();
			p.setId(getIdFromIdentifiable(currentLevel.getPlatform()));
			p.setEnable(true);
			if (vertical) {
				p.setVertical(true);
				p.setDisplayed(true);
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

				if (l > 20) {
					l = 20;
				}

				p.setLength(l);
				p.setX(x);
			} else {
				p.setVertical(false);
				p.setDisplayed(true);
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
			saveCurrentLevel();
		}
	}

	public void addTeleporter(int x, int y, int x2, int y2) {
		if (currentLevel != null) {
			Level level = currentLevel;
			Teleporter t = new Teleporter();
			t.setEnable(true);
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
			saveCurrentLevel();
		}
	}

	public void addRayon(int x, int y, int x2, int y2) {
		if (currentLevel != null) {
			Level level = currentLevel;
			Rayon r = new Rayon();
			r.setEnable(true);
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
			r.setType(RayonTypeEnum.BLACK);
			level.getRayon().add(r);
			saveCurrentLevel();
		}
	}

	public void addEnnemie(int x, int y, EnnemieTypeEnum type) {
		if (currentLevel != null) {
			currentLevel.getEnnemies()
					.add(new Ennemie(getIdFromIdentifiable(currentLevel.getEnnemies()), true, x, y, type));
			saveCurrentLevel();
		}
	}

	public void addDecor(int x, int y) {
		if (currentLevel != null) {
			currentLevel.getDecor()
					.add(new Decor(getIdFromIdentifiable(currentLevel.getDecor()), true, x, y, false, 0));
			saveCurrentLevel();
		}
	}

	public void addVortex(int x, int y) {
		if (currentLevel != null) {
			currentLevel.getVortex()
					.add(new Vortex(getIdFromIdentifiable(currentLevel.getVortex()), true, x, y, 1.0, 1.0, 0));
			saveCurrentLevel();
		}
	}

	public void addPick(int x, int y) {
		if (currentLevel != null) {
			currentLevel.getPick().add(new Pick(getIdFromIdentifiable(currentLevel.getPick()), true, x, y, 0));
			saveCurrentLevel();
		}
	}

	public void addLock(int x, int y) {
		if (currentLevel != null) {
			currentLevel.getLock().add(new Lock(getIdFromIdentifiable(currentLevel.getLock()), false, x, y, null));
			saveCurrentLevel();
		}
	}

	public void addDoor(int x, int y) {
		if (currentLevel != null) {
			currentLevel.getDoor()
					.add(new Door(getIdFromIdentifiable(currentLevel.getDoor()), true, x, y, 0, true, 0, null, 0));
			saveCurrentLevel();
		}
	}

	public void addEvent(int x, int y) {
		if (currentLevel != null) {
			currentLevel.getEvent().add(new Event(getIdFromIdentifiable(currentLevel.getEvent()), x, y));
			saveCurrentLevel();
		}
	}

	public void addPlayerSpawn(int x, int y) {
		if (currentLevel != null) {
			currentLevel.setStartPlayers(new Position(x, y));
			saveCurrentLevel();
		}
	}

	public void addObjectPoint(int x, int y) {
		if (currentLevel != null) {
			currentLevel.setStartPointObjets(new Position(x, y));
			saveCurrentLevel();
		}
	}

	public void addObjectEffect(int x, int y) {
		if (currentLevel != null) {
			currentLevel.setStartEffectObjets(new Position(x, y));
			saveCurrentLevel();
		}
	}

	public void addItem(int x, int y) {
		if (currentLevel != null) {
			if (x > 19) {
				x = 19;
			}
			if (y > 24) {
				y = 24;
			}
			currentLevel.getItems().add(new Item(getIdFromIdentifiable(currentLevel.getItems()), true, x, y, 0));
			saveCurrentLevel();
		}
	}

	public void deleteElement(int x, int y) {
		int caseX = x / Constante.GRID_SIZE;
		int caseY = y / Constante.GRID_SIZE;
		// DECORS
		if (currentLevel != null) {
			List<Decor> decorToDelete = new ArrayList<>();
			for (Decor e : currentLevel.getDecor()) {
				if (e.getX() >= ((x + 10) - 5) && e.getX() <= ((x + 10) + 5) && e.getY() >= (y - 5)
						&& e.getY() <= (y + 5)) {
					decorToDelete.add(e);
				}
			}
			currentLevel.getDecor().removeAll(decorToDelete);

			// DOORS
			List<Door> doorToDelete = new ArrayList<>();
			for (Door e : currentLevel.getDoor()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					doorToDelete.add(e);
				}
			}
			currentLevel.getDoor().removeAll(doorToDelete);

			// ENNEMIES
			List<Ennemie> ennemieToDelete = new ArrayList<>();
			for (Ennemie e : currentLevel.getEnnemies()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					ennemieToDelete.add(e);
				}
			}
			currentLevel.getEnnemies().removeAll(ennemieToDelete);

			// LOCK
			List<Lock> lockToDelete = new ArrayList<>();
			for (Lock e : currentLevel.getLock()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					lockToDelete.add(e);
				}
			}
			currentLevel.getLock().removeAll(lockToDelete);

			// PICK
			List<Pick> pickToDelete = new ArrayList<>();
			for (Pick e : currentLevel.getPick()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					pickToDelete.add(e);
				}
			}
			currentLevel.getPick().removeAll(pickToDelete);

			// VORTEX
			List<Vortex> vortexToDelete = new ArrayList<>();
			for (Vortex e : currentLevel.getVortex()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					vortexToDelete.add(e);
				}
			}
			currentLevel.getVortex().removeAll(vortexToDelete);

			// PLATEFORM
			List<Platform> platformToDelete = new ArrayList<>();
			for (Platform e : currentLevel.getPlatform()) {
				if (e.isVertical()) {
					int min = e.getY();
					int max = e.getY() + e.getLength();
					if (e.getX() == caseX && (min <= caseY && caseY < max)) {

						platformToDelete.add(e);
					}
				} else {
					int min = e.getX();
					int max = e.getX() + e.getLength();
					if (e.getY() == caseY && (min <= caseX && caseX < max)) {

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
					if (e.getX() == caseX && (min <= caseY && caseY < max)) {
						rayonToDelete.add(e);
					}
				} else {
					int min = e.getX();
					int max = e.getX() + e.getLength();
					if (e.getY() == caseY && (min <= caseX && caseX < max)) {
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
					if (e.getX() == caseX && (min <= caseY && caseY < max)) {
						teleporterToDelete.add(e);
					}
				} else {
					int min = e.getX();
					int max = e.getX() + e.getLength();
					if (e.getY() == caseY && (min <= caseX && caseX < max)) {
						teleporterToDelete.add(e);
					}
				}
			}
			currentLevel.getTeleporter().removeAll(teleporterToDelete);

			// EFFECT OBJET
			if (currentLevel.getStartEffectObjets() != null && currentLevel.getStartEffectObjets().getX() == caseX
					&& currentLevel.getStartEffectObjets().getY() == caseY) {
				currentLevel.setStartEffectObjets(null);
			}

			// STARTPLAYER
			if (currentLevel.getStartPlayers() != null && currentLevel.getStartPlayers().getX() == caseX
					&& currentLevel.getStartPlayers().getY() == caseY) {
				currentLevel.setStartPlayers(null);
			}

			// START POINT
			if (currentLevel.getStartPointObjets() != null && currentLevel.getStartPointObjets().getX() == caseX
					&& currentLevel.getStartPointObjets().getY() == caseY) {
				currentLevel.setStartPointObjets(null);
			}

			// ITEMS
			List<Item> itemsToDelete = new ArrayList<>();
			for (Item e : currentLevel.getItems()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					itemsToDelete.add(e);
				}
			}
			currentLevel.getItems().removeAll(itemsToDelete);

			// EVENT
			List<Event> eventsToDelete = new ArrayList<>();
			for (Event e : currentLevel.getEvent()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					eventsToDelete.add(e);
				}
			}
			currentLevel.getEvent().removeAll(eventsToDelete);
			saveCurrentLevel();
		}

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

	public List<Identifiable> getProperties(int x, int y) {
		List<Identifiable> identified = new ArrayList<>();
		int caseX = x / Constante.GRID_SIZE;
		int caseY = y / Constante.GRID_SIZE;
		if (currentLevel != null) {
			for (Decor e : currentLevel.getDecor()) {
				if (e.getX() >= ((x + 10) - 5) && e.getX() <= ((x + 10) + 5) && e.getY() >= (y - 5)
						&& e.getY() <= (y + 5)) {
					identified.add(e);
				}
			}
			for (Door e : currentLevel.getDoor()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					identified.add(e);
				}
			}
			for (Ennemie e : currentLevel.getEnnemies()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					identified.add(e);
				}
			}
			for (Lock e : currentLevel.getLock()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					identified.add(e);
				}
			}
			for (Pick e : currentLevel.getPick()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					identified.add(e);
				}
			}
			for (Platform e : currentLevel.getPlatform()) {
				if (e.isVertical()) {
					int min = e.getY();
					int max = e.getY() + e.getLength();
					if (e.getX() == caseX && (min <= caseY && caseY < max)) {
						identified.add(e);
					}
				} else {
					int min = e.getX();
					int max = e.getX() + e.getLength();
					if (e.getY() == caseY && (min <= caseX && caseX < max)) {
						identified.add(e);
					}
				}
			}
			for (Rayon e : currentLevel.getRayon()) {
				if (e.isVertical()) {
					int min = e.getY();
					int max = e.getY() + e.getLength();
					if (e.getX() == caseX && (min <= caseY && caseY < max)) {
						identified.add(e);
					}
				} else {
					int min = e.getX();
					int max = e.getX() + e.getLength();
					if (e.getY() == caseY && (min <= caseX && caseX < max)) {
						identified.add(e);
					}
				}
			}
			for (Teleporter e : currentLevel.getTeleporter()) {
				if (e.isVertical()) {
					int min = e.getY();
					int max = e.getY() + e.getLength();
					if (e.getX() == caseX && (min <= caseY && caseY < max)) {
						identified.add(e);
					}
				} else {
					int min = e.getX();
					int max = e.getX() + e.getLength();
					if (e.getY() == caseY && (min <= caseX && caseX < max)) {
						identified.add(e);
					}
				}
			}
			for (Vortex e : currentLevel.getVortex()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					identified.add(e);
				}
			}
			for (Item e : currentLevel.getItems()) {
				if (e.getX() == caseX && e.getY() == caseY) {
					identified.add(e);
				}
			}
			for (Event e : currentLevel.getEvent()) {
				if ((int)e.getX() == caseX && (int)e.getY() == caseY) {
					identified.add(e);
				}
			}
		}
		return identified;
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

	public void updatePlatform(Platform platform) {
		currentLevel.getPlatform().remove(platform);
		currentLevel.getPlatform().add(platform);
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

	public void updateItem(Item item) {
		currentLevel.getItems().remove(item);
		currentLevel.getItems().add(item);
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

	/***************************************************************
	 * 
	 * --- print level id and represent the map of level ----
	 * 
	 ***************************************************************/
	public String printTextMap() {
		// print map
		treatedLevels = new ArrayList<>();
		treatedDimensions = new ArrayList<>();
		treatPath = new ArrayList<>();
		List<StartDimension> dimensions = new ArrayList<>();

		String tmp = "";
		Level level = levelMap.get(0);
		int next = level.getNext();
		if (level != null) {
			while (true) {
				if (level != null && next != -1 && levelMap.containsKey(next)) {
					dimensions = searchPath(dimensions, level);
					tmp += level.getId();
					treatedLevels.add(level.getId());
					tmp += "->";

					next = level.getNext();
					level = levelMap.get(next);
				} else {
					tmp += next + "END";
					treatedDimensions.add(tmp);
					break;
				}
			}
		}
		printDimension(dimensions);
		String map = "";
		for (String s : treatedDimensions) {
			map += s;
			map += System.lineSeparator();
		}
		return map;
	}

	private void printDimension(List<StartDimension> startDimension) {
		String map = "";
		List<StartDimension> dim = new ArrayList<>();
		Level level;
		int next = 0;

		for (StartDimension di : startDimension) {
			int iter = 0;
			map = di.getOriginLevel() + "-> " + di.getDestination();
			level = levelMap.get(di.getDestination());
			next = level.getNext();
			if (level != null) {
				dim = searchPath(dim, level);
			}

			while (true) {
				if (level != null && !treatedLevels.contains(next)) {
					if (iter > 0) {
						map += "->" + next;
					}
				} else {
					map += "->" + next;
					treatedDimensions.add(map);
					break;
				}
				treatedLevels.add(level.getId());
				level = levelMap.get(level.getNext());
				next = level.getNext();
				iter++;
			}

		}

		if (!dim.isEmpty()) {
			printDimension(dim);
		}
	}

	private List<StartDimension> searchPath(List<StartDimension> dim, Level level) {
		if (!treatPath.contains(level.getId())) {
			if (level.getVortex() != null && !level.getVortex().isEmpty()) {
				for (Vortex d : level.getVortex()) {
					dim.add(new StartDimension(1, level.getId(), d.getDestination()));
					treatPath.add(level.getId());
				}
			}
			if (level.getDoor() != null && !level.getDoor().isEmpty()) {
				for (Door d : level.getDoor()) {
					dim.add(new StartDimension(0, level.getId(), d.getToLevel()));
					treatPath.add(level.getId());
				}
			}
		}
		return dim;
	}
}
