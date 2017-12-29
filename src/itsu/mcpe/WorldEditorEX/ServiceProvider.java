package itsu.mcpe.WorldEditorEX;

import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;

public class ServiceProvider {

    private Location loc1;
    private Location loc2;

    private Level lev;

    private Player player;
    
    private Map<String, String> data = new HashMap<>();

    private boolean isEditing = false;

    public void setLocation1(Location loc, Level level) {
        loc1 = loc;
        lev = level;
    }

    public void setLocation2(Location loc, Level level) {
        loc2 = loc;
        lev = level;
    }

    public void setPlayer(Player p) {
        player = p;
    }

    public void setEditing(boolean b) {
        isEditing = b;
    }
    
    public void setCopy(Map<String, String> data) {
        this.data = data;
    }
    
    public Map<String, String> getCopy() {
        return this.data;
    }

    public Location getLocation1() {
        return loc1;
    }

    public Location getLocation2() {
        return loc2;
    }

    public Level getLevel() {
        return lev;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void reset() {
        loc1 = null;
        loc2 = null;
        lev = null;
    }

}
