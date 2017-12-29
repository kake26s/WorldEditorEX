package itsu.mcpe.WorldEditorEX;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import itsu.mcpe.WorldEditorEX.task.CopyTask;
import itsu.mcpe.WorldEditorEX.task.ExportTask;
import itsu.mcpe.WorldEditorEX.task.ImportTask;
import itsu.mcpe.WorldEditorEX.task.PasteTask;
import itsu.mcpe.WorldEditorEX.task.ReplaceTask;
import itsu.mcpe.WorldEditorEX.task.SetBlockTask;

public class WorldEditorEX extends PluginBase {

    private static WorldEditorEX instance;
    private Map<String, ServiceProvider> data = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info(TextFormat.GREEN + "起動しました。");
        getLogger().info(TextFormat.YELLOW + "このプラグインはJupiterのライセンス（GPL v3.0）に則ります。");
        getLogger().info(TextFormat.YELLOW + "開発: Itsu(itsu020402) 二次配布はおやめください。改造については可とします。");

        new File("plugins/WorldEditorEX/").mkdirs();
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        instance = this;
    }

    @SuppressWarnings({ "deprecation" })
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {

        Player player = null;
        Block block = null;

        Location loc1 = null;
        Location loc2 = null;
        Level level = null;

        int id = 0;
        int meta = 0;

        switch(command.getName()) {

            case "set":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                }

                player = (Player) sender;

                try {
                    if(args[0].contains(":")) {
                        String[] data = args[0].split(":");
                        id = Integer.parseInt(data[0]);
                        meta = Integer.parseInt(data[1]);

                    } else {
                        id = Integer.parseInt(args[0]);
                    }

                    block = Block.get(id, meta);
                    level = player.getLevel();
                    loc1 = getProvider(player).getLocation1();
                    loc2 = getProvider(player).getLocation2();

                    if(!getProvider(player).isEditing()) {
                        if(block != null && level != null && loc1 != null && loc2 != null) {
                            getServer().broadcastMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.YELLOW + sender.getName() + "がワールドの変更を開始します。");
                            getServer().getScheduler().scheduleTask(new SetBlockTask(block, level, (Player) sender, loc1, loc2));

                        } else {
                            sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "設定されていない項目があります。");
                            return true;
                        }

                    } else {
                        sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "別の処理を行っています。");
                        return true;
                    }

                } catch (IndexOutOfBoundsException e) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ブロックIDを入力してください。");
                    return true;

                } catch (NumberFormatException e) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ブロックIDには数値を入力してください。");
                    return true;
                }

                return true;

            case "cut":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                }

                player = (Player) sender;

                try {

                    block = Block.get(0, 0);
                    level = player.getLevel();
                    loc1 = getProvider(player).getLocation1();
                    loc2 = getProvider(player).getLocation2();

                    if(!getProvider(player).isEditing()) {
                        if(block != null && level != null && loc1 != null && loc2 != null) {
                            getServer().broadcastMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.YELLOW + sender.getName() + "がワールドの変更を開始します。");
                            getServer().getScheduler().scheduleTask(new SetBlockTask(block, level, (Player) sender, loc1, loc2));

                        } else {
                            sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "設定されていない項目があります。");
                            return true;
                        }

                        return true;

                    } else {
                        sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "別の処理を行っています。");
                        return true;
                    }

                } catch (IndexOutOfBoundsException e) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ブロックIDを入力してください。");
                    return true;

                }

            case "replace":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                }

                player = (Player) sender;
                int id2 = 0;
                int meta2 = 0;
                Block to;

                try {
                    if(args[0].contains(":")) {
                        String[] data = args[0].split(":");
                        id = Integer.parseInt(data[0]);
                        meta = Integer.parseInt(data[1]);

                    } else {
                        id = Integer.parseInt(args[0]);
                    }

                    if(args[1].contains(":")) {
                        String[] data = args[1].split(":");
                        id2 = Integer.parseInt(data[0]);
                        meta2 = Integer.parseInt(data[1]);

                    } else {
                        id2 = Integer.parseInt(args[1]);
                    }

                    block = Block.get(id, meta);
                    to = Block.get(id2, meta2);
                    level = player.getLevel();
                    loc1 = getProvider(player).getLocation1();
                    loc2 = getProvider(player).getLocation2();

                    if(!getProvider(player).isEditing()) {
                        if(block != null && level != null && loc1 != null && loc2 != null) {
                            getServer().broadcastMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.YELLOW + sender.getName() + "がワールドの変更を開始します。");
                            getServer().getScheduler().scheduleTask(new ReplaceTask(to, block, level, (Player) sender, loc1, loc2));

                        } else {
                            sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "設定されていない項目があります。");
                            return true;
                        }

                    } else {
                        sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "別の処理を行っています。");
                        return true;
                    }

                } catch (IndexOutOfBoundsException e) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ブロックIDを入力してください。");
                    return true;

                } catch (NumberFormatException e) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ブロックIDには数値を入力してください。");
                    return true;
                }

                return true;

            case "copy":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                }

                player = (Player) sender;

                level = player.getLevel();
                loc1 = getProvider(player).getLocation1();
                loc2 = getProvider(player).getLocation2();

                if(!getProvider(player).isEditing()) {
                    if(level != null && loc1 != null && loc2 != null) {
                        getServer().broadcastMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.YELLOW + sender.getName() + "がワールドの変更を開始します。");
                        getServer().getScheduler().scheduleTask(new CopyTask(level, (Player) sender, loc1, loc2));

                    } else {
                        sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "設定されていない項目があります。");
                        return true;
                    }

                } else {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "別の処理を行っています。");
                    return true;
                }

                return true;

            case "paste":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                }

                player = (Player) sender;

                level = player.getLevel();
                loc1 = getProvider(player).getLocation1();
                loc2 = getProvider(player).getLocation2();

                if(!getProvider(player).isEditing()) {
                    if(level != null && loc1 != null && loc2 != null && getProvider(player).getCopy() != null) {
                        getServer().broadcastMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.YELLOW + sender.getName() + "がワールドの変更を開始します。");
                        getServer().getScheduler().scheduleTask(new PasteTask(level, player, player.getLocation(), getProvider(player).getCopy()));

                    } else {
                        sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "設定されていない項目があります。");
                        return true;
                    }

                } else {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "別の処理を行っています。");
                    return true;
                }

                return true;

            case "export":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                }

                try {
                    player = (Player) sender;
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.YELLOW + "エクスポート中...");
                    getServer().getScheduler().scheduleTask(new ExportTask(new File("plugins/WorldEditorEX/" + args[0] + ".json"), new Gson().toJson(getProvider(player).getCopy()).getBytes()));
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.AQUA + "出力が完了しました。");
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "エクスポート名を入力してください。");
                    return true;
                }

            case "import":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                }

                player = (Player) sender;
                sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.YELLOW + "インポート中...");
                getServer().getScheduler().scheduleTask(new ImportTask(player, new File("plugins/WorldEditorEX/" + args[0] + ".json")));
                return true;

            case "xyz":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                } else {
                    player = (Player) sender;
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RESET + "現在の座標: (" + player.getX() + ", " + player.getY() + ", " + player.getZ() + ")");
                    return true;
                }

            case "bid":
                if(sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RED + "ゲーム内から実行してください。");
                    return true;
                } else {
                    player = (Player) sender;
                    sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RESET + "アイテムID: " + player.getInventory().getItemInHand().getId() + ":" + player.getInventory().getItemInHand().getDamage());
                    return true;
                }
                
            case "objects":
                File[] files = new File("plugins/WorldEditorEX/").listFiles();
                sender.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.YELLOW + "インポート可能ファイル: ");
                for(File file : files) {
                	if(!file.isDirectory() && file.getName().endsWith(".json")) {
                		sender.sendMessage(TextFormat.LIGHT_PURPLE + file.getName().replaceAll(".json", ""));
                	}
                }
                return true;
        }

        return true;
    }

    public static WorldEditorEX getInstance() {
        return instance;
    }

    public void addPlayer(Player player) {
        if(!data.containsKey(player.getName())) {
            data.put(player.getName(), new ServiceProvider());
        }
    }

    public void removePlayer(Player player) {
        if(data.containsKey(player.getName())) {
            data.remove(player.getName());
        }
    }

    public ServiceProvider getProvider(Player player) {
        return data.get(player.getName());
    }

}
