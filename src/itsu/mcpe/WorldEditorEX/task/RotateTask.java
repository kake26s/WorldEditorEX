package itsu.mcpe.WorldEditorEX.task;

import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import itsu.mcpe.WorldEditorEX.WorldEditorEX;

public class RotateTask extends Task {
    private Player player;
    
    private Map<String, String> data;
	
	public RotateTask(Player player, Map<String, String> data) {
        this.player = player;
        this.data = data;
	}
	
	@Override
	public void onRun(int currentTick) {

    	WorldEditorEX.getInstance().getProvider(player).setEditing(true);
    	WorldEditorEX.getInstance().getProvider(player).setPlayer(player);
    	
        Map<String, String> newCopy = new HashMap<>();;

        for(String str : data.keySet()) {
        	String[] old = str.split(",");
        	String newData = old[2] + "," + old[1] + "," + old[0];
        	
        	newCopy.put(newData, data.get(str));
        }
        
        WorldEditorEX.getInstance().getProvider(player).setCopy(newCopy);
        WorldEditorEX.getInstance().getProvider(player).setEditing(false);
        WorldEditorEX.getInstance().getProvider(player).getTask("rotate").cancel();

        player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.AQUA + "処理が終了しました。");
	}
}
