package itsu.mcpe.WorldEditorEX.task;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Utils;
import itsu.mcpe.WorldEditorEX.WorldEditorEX;

public class ImportTask extends Task {
	
    private Player player;
	private File file;
	
	public ImportTask(Player player, File file) {
        this.player = player;
        this.file = file;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRun(int currentTick) {
        try {
            WorldEditorEX.getInstance().getProvider(player).setCopy((Map<String, String>) new Gson().fromJson(Utils.readFile(file), Map.class));
            player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.AQUA + "インポートしました。/pasteでペースト可能です。");
            return;

        } catch (IndexOutOfBoundsException e) {
        	player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ファイル名を入力してください。");
            return;

        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "エラーが発生しました。");
            return;

        } catch (IOException e) {
        	player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "指定されたファイルが見つかりません。");
            return;
        }
	}
}
