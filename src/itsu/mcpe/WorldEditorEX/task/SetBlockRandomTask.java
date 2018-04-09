package itsu.mcpe.WorldEditorEX.task;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import itsu.mcpe.WorldEditorEX.WorldEditorEX;

import java.util.ArrayList;
import java.util.Random;

public class SetBlockRandomTask extends Task{

    private ArrayList<Block> blocks;
    private Level level;
    private Player player;
    private Location loc1;
    private Location loc2;

    public SetBlockRandomTask(ArrayList<Block> blocks, Level level, Player player, Location loc1, Location loc2) {
        this.blocks = blocks;
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

        Random rnd = new Random();
        int size = blocks.size();

        for (int x = x2; x <= x1; ++x) {
            for (int y = y2; y <= y1; ++y) {
                for (int z = z2; z <= z1; ++z) {
                    vec.x = x;
                    vec.y = y;
                    vec.z = z;
                    int num = rnd.nextInt(size);
                    level.setBlock(vec, blocks.get(num));
                }
            }
        }

        WorldEditorEX.getInstance().getProvider(player).setEditing(false);
        WorldEditorEX.getInstance().getProvider(player).getTask("set").cancel();

        player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.AQUA + "処理が終了しました。");
    }
}
