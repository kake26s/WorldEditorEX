package itsu.mcpe.WorldEditorEX.task;

import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import itsu.mcpe.WorldEditorEX.WorldEditorEX;

public class CopyTask extends Task {
	
    private Level level;
    private Player player;
    private Location loc1;
    private Location loc2;
    
    private Map<String, String> data = new HashMap<>();
	
	public CopyTask(Level level, Player player, Location loc1, Location loc2) {
        this.level = level;
        this.player = player;
        this.loc1 = loc1;
        this.loc2 = loc2;
	}

	@Override
	public void onRun(int currentTick) {

    	WorldEditorEX.getInstance().getProvider(player).setEditing(true);
    	WorldEditorEX.getInstance().getProvider(player).setPlayer(player);
    	
        int x1 = Math.max((int) loc1.getX(), (int) loc2.getX());
        int y1 = Math.max((int) loc1.getY(), (int) loc2.getY());
        int z1 = Math.max((int) loc1.getZ(), (int) loc2.getZ());

        int x2 = Math.min((int) loc1.getX(), (int) loc2.getX());
        int y2 = Math.min((int) loc1.getY(), (int) loc2.getY());
        int z2 = Math.min((int) loc1.getZ(), (int) loc2.getZ());

        Vector3 vec = new Vector3();

        for(int x = x2;x <= x1;++x) {
            for(int y = y2;y <= y1;++y) {
                for(int z = z2;z <= z1;++z) {
                    vec.x = x;
                    vec.y = y;
                    vec.z = z;
                    data.put((x - x2) + "," + (y - y2) + "," + (z - z2), level.getBlock(vec).getId() + ":" + level.getBlock(vec).getDamage());
                }
            }
        }
        
        WorldEditorEX.getInstance().getProvider(player).setCopy(data);
        WorldEditorEX.getInstance().getProvider(player).setEditing(false);

        player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.AQUA + "処理が終了しました。");
	}

}
