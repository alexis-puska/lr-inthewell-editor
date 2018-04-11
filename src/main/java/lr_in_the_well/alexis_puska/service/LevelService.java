package lr_in_the_well.alexis_puska.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lr_in_the_well.alexis_puska.domain.level.Decor;
import lr_in_the_well.alexis_puska.domain.level.Door;
import lr_in_the_well.alexis_puska.domain.level.Ennemie;
import lr_in_the_well.alexis_puska.domain.level.Event;
import lr_in_the_well.alexis_puska.domain.level.Identifiable;
import lr_in_the_well.alexis_puska.domain.level.Level;
import lr_in_the_well.alexis_puska.domain.level.Lock;
import lr_in_the_well.alexis_puska.domain.level.Pick;
import lr_in_the_well.alexis_puska.domain.level.Platform;
import lr_in_the_well.alexis_puska.domain.level.Rayon;
import lr_in_the_well.alexis_puska.domain.level.StartEffectObjets;
import lr_in_the_well.alexis_puska.domain.level.StartPlayer;
import lr_in_the_well.alexis_puska.domain.level.StartPointObjets;
import lr_in_the_well.alexis_puska.domain.level.Teleporter;
import lr_in_the_well.alexis_puska.domain.level.Vortex;

public class LevelService {

    private int current;
    // private int currentRayonType;
    private Map<Integer, Level> levels;

    public LevelService(Map<Integer, Level> levels) {
        this.current = 0;
        this.levels = levels;
    }

    public List<Level> getLevels() {
        return new ArrayList<Level>(levels.values());
    }

    public Level getCurrentLevel() {
        return levels.get(current);
    }

    public void addPlatform(int x, int y, int d, boolean vertical) {
        Level level = levels.get(current);
        Platform p = new Platform();
        p.setId(getIdFromIdentifiable(level.getPlatform()));
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
        level.getPlatform().add(p);
        levels.put(level.getId(), level);
    }

    public void addTeleporter(int x, int y, int x2, int y2) {
        Level level = levels.get(current);
        Teleporter t = new Teleporter();
        t.setId(getIdFromIdentifiable(level.getPlatform()));
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
        levels.put(level.getId(), level);
    }

    public void addRayon(int x, int y, int x2, int y2) {
        Level level = levels.get(current);
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
        levels.put(level.getId(), level);
    }

    public void addEnnemie(int x, int y, int type) {
        Level level = levels.get(current);
        int nbEnnemies = level.getEnnemies().size();
        level.getEnnemies().add(new Ennemie(nbEnnemies, x, y, type));
        levels.put(level.getId(), level);
    }

    public void addDecor(int x, int y) {

    }

    public void addVortex(int x, int y) {

    }

    public void addPick(int x, int y) {

    }

    public void addLock(int x, int y) {

    }

    public void addDoor(int x, int y) {

    }

    public void addEvent(int x, int y) {

    }

    public void addPlayerSpawn(int x, int y) {
        Level level = levels.get(current);
        level.getStartPlayers().add(new StartPlayer(x, y));
    }

    public void addObjectPoint(int x, int y) {
        Level level = levels.get(current);
        level.getStartPointObjets().add(new StartPointObjets(x, y));
    }

    public void addObjectEffect(int x, int y) {
        Level level = levels.get(current);
        level.getStartEffectObjets().add(new StartEffectObjets(x, y));
    }

    public void deleteElement(int x, int y) {
        Level level = levels.get(current);

        // DECORS
        List<Decor> decorToDelete = new ArrayList<>();
        for (Decor e : level.getDecor()) {
            if (e.getX() == x && e.getY() == y) {
                decorToDelete.add(e);
            }
        }
        level.getDecor().removeAll(decorToDelete);

        // DOORS
        List<Door> doorToDelete = new ArrayList<>();
        for (Door e : level.getDoor()) {
            if (e.getX() == x && e.getY() == y) {
                doorToDelete.add(e);
            }
        }
        level.getDoor().removeAll(doorToDelete);

        // ENNEMIES
        List<Ennemie> ennemieToDelete = new ArrayList<>();
        for (Ennemie e : level.getEnnemies()) {
            if (e.getX() == x && e.getY() == y) {
                ennemieToDelete.add(e);
            }
        }
        level.getEnnemies().removeAll(ennemieToDelete);

        // LOCK
        List<Lock> lockToDelete = new ArrayList<>();
        for (Lock e : level.getLock()) {
            if (e.getX() == x && e.getY() == y) {
                lockToDelete.add(e);
            }
        }
        level.getLock().removeAll(lockToDelete);

        // PICK
        List<Pick> pickToDelete = new ArrayList<>();
        for (Pick e : level.getPick()) {
            if (e.getX() == x && e.getY() == y) {
                pickToDelete.add(e);
            }
        }
        level.getPick().removeAll(pickToDelete);

        // PLATEFORM
        List<Platform> platformToDelete = new ArrayList<>();
        for (Platform e : level.getPlatform()) {
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
        level.getPlatform().removeAll(platformToDelete);

        // RAYON
        List<Rayon> rayonToDelete = new ArrayList<>();
        for (Rayon e : level.getRayon()) {
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
        level.getRayon().removeAll(rayonToDelete);

        // TELEPORTER
        List<Teleporter> teleporterToDelete = new ArrayList<>();
        for (Teleporter e : level.getTeleporter()) {
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
        level.getTeleporter().removeAll(teleporterToDelete);

        // EFFECT OBJET
        List<StartEffectObjets> startEffectObjectToDelete = new ArrayList<>();
        for (StartEffectObjets e : level.getStartEffectObjets()) {
            if (e.getX() == x && e.getY() == y) {
                startEffectObjectToDelete.add(e);
            }
        }
        level.getStartEffectObjets().removeAll(startEffectObjectToDelete);

        // STARTPLAYER
        List<StartPlayer> startPlayerToDelete = new ArrayList<>();
        for (StartPlayer e : level.getStartPlayers()) {
            if (e.getX() == x && e.getY() == y) {
                startPlayerToDelete.add(e);
            }
        }
        level.getStartPlayers().removeAll(startPlayerToDelete);

        // START POINT
        List<StartPointObjets> startPointObjetsToDelete = new ArrayList<>();
        for (StartPointObjets e : level.getStartPointObjets()) {
            if (e.getX() == x && e.getY() == y) {
                startPointObjetsToDelete.add(e);
            }
        }
        level.getStartPointObjets().removeAll(startPointObjetsToDelete);
        // update level
        levels.put(level.getId(), level);
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

    public int getNbLevel() {
        return levels.size();
    }

    public int getCurrentLevelIndex() {
        return current;
    }

    public int incCurrentLevelIndex() {
        current++;
        return current;
    }

    public int decCurrentLevelIndex() {
        current--;
        return current;
    }

    public void setCurrentLevel(int parseInt) {
        current = parseInt;
    }

    public Identifiable getProperties(int x, int y) {
        Level level = levels.get(current);

        for (Decor e : level.getDecor()) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        for (Door e : level.getDoor()) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        for (Ennemie e : level.getEnnemies()) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        for (Lock e : level.getLock()) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        for (Pick e : level.getPick()) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        for (Platform e : level.getPlatform()) {
            if (e.isVertical()) {
                int min = e.getY();
                int max = e.getY() + e.getLength();
                if (e.getX() == x && (min <= y && y <= max)) {

                    return e;
                }
            } else {
                int min = e.getX();
                int max = e.getX() + e.getLength();
                if (e.getY() == y && (min <= x && x <= max)) {

                    return e;
                }
            }
        }
        for (Rayon e : level.getRayon()) {
            if (e.isVertical()) {
                int min = e.getY();
                int max = e.getY() + e.getLength();
                if (e.getX() == x && (min <= y && y <= max)) {
                    return e;
                }
            } else {
                int min = e.getX();
                int max = e.getX() + e.getLength();
                if (e.getY() == y && (min <= x && x <= max)) {
                    return e;
                }
            }
        }
        for (Teleporter e : level.getTeleporter()) {
            if (e.isVertical()) {
                int min = e.getY();
                int max = e.getY() + e.getLength();
                if (e.getX() == x && (min <= y && y <= max)) {
                    return e;
                }
            } else {
                int min = e.getX();
                int max = e.getX() + e.getLength();
                if (e.getY() == y && (min <= x && x <= max)) {
                    return e;
                }
            }
        }
        return null;
    }

    public void updateRayon(Rayon rayon) {
        levels.get(current).getRayon().remove(rayon);
        levels.get(current).getRayon().add(rayon);
    }

    public void updateVortex(Vortex vortex) {
        levels.get(current).getVortex().remove(vortex);
        levels.get(current).getVortex().add(vortex);
    }

    public void updateTeleporter(Teleporter teleporter) {
        levels.get(current).getTeleporter().remove(teleporter);
        levels.get(current).getTeleporter().add(teleporter);
    }

    public void updatePick(Pick pick) {
        levels.get(current).getPick().remove(pick);
        levels.get(current).getPick().add(pick);
    }

    public void updateDoor(Door door) {
        levels.get(current).getDoor().remove(door);
        levels.get(current).getDoor().add(door);
    }

    public void updateLock(Lock lock) {
        levels.get(current).getLock().remove(lock);
        levels.get(current).getLock().add(lock);
    }

    public void updateDecor(Decor decor) {
        levels.get(current).getDecor().remove(decor);
        levels.get(current).getDecor().add(decor);
    }

    public void updateEvent(Event event) {
        levels.get(current).getEvent().remove(event);
        levels.get(current).getEvent().add(event);
    }

    public void updateEnnemie(Ennemie ennemie) {
        levels.get(current).getEnnemies().remove(ennemie);
        levels.get(current).getEnnemies().add(ennemie);
    }

    public void loadLevels(Map<Integer, Level> readJsonFile) {
        this.current = 0;
        this.levels = readJsonFile;
    }
}
