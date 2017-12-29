package itsu.mcpe.WorldEditorEX.task;

import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import itsu.mcpe.WorldEditorEX.WorldEditorEX;

public class PasteTask extends Task {
    private Level level;
    private Player player;
    private Location loc;
    
    private Map<String, String> data;
	
	public PasteTask(Level level, Player player, Location loc, Map<String, String> data) {
        this.level = level;
        this.player = player;
        this.loc = loc;
        this.data = data;
	}
	
	@Override
	public void onRun(int currentTick) {

    	WorldEditorEX.getInstance().getProvider(player).setEditing(true);
    	WorldEditorEX.getInstance().getProvider(player).setPlayer(player);
    	
    	Vector3 vec = new Vector3();

        for(String str : data.keySet()) {
        	double x = Integer.parseInt(str.split(",")[0]) + loc.getX();
        	double y = Integer.parseInt(str.split(",")[1]) + loc.getY();
        	double z = Integer.parseInt(str.split(",")[2]) + loc.getZ();
        	
        	int id = Integer.parseInt(data.get(str).split(":")[0]);
        	int meta = Integer.parseInt(data.get(str).split(":")[1]);
        	
        	Block block = Block.get(id, meta);
        	
        	vec.x = x;
        	vec.y = y;
        	vec.z = z;
        	
        	level.setBlock(vec, block);
        }
        
        WorldEditorEX.getInstance().getProvider(player).setCopy(data);
        WorldEditorEX.getInstance().getProvider(player).setEditing(false);

        player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.AQUA + "処理が終了しました。");
	}
}
