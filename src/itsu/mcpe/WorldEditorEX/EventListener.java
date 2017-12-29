package itsu.mcpe.WorldEditorEX;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerLoginEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

public class EventListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(e.getPlayer().isOp()) {
			if(e.getPlayer().getInventory().getItemInHand().getId() == Item.WOODEN_AXE) {
				WorldEditorEX.getInstance().getProvider(e.getPlayer()).setLocation1(e.getBlock().getLocation(), e.getBlock().getLevel());
				e.getPlayer().sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RESET + "地点1を設定しました。(ブロックID: " + e.getBlock().getId() + ":" + e.getBlock().getDamage() + ")");
				e.getPlayer().sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RESET + "座標: (" + e.getBlock().getX() + ", " + e.getBlock().getY() + ", " + e.getBlock().getZ() + ")");
				calculateBlockCount(e.getPlayer());
				e.setCancelled();
			}
		}
	}
	
	@EventHandler
	public void onTouch(PlayerInteractEvent e) {
		if(e.getPlayer().isOp()) {
			if(e.getPlayer().getInventory().getItemInHand().getId() == Item.WOODEN_AXE) {
				if(e.getBlock().getId() == Block.AIR) return;
				WorldEditorEX.getInstance().getProvider(e.getPlayer()).setLocation2(e.getBlock().getLocation(), e.getBlock().getLevel());
				e.getPlayer().sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RESET + "地点2を設定しました。(ブロックID: " + e.getBlock().getId() + ":" + e.getBlock().getDamage() + ")");
				e.getPlayer().sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RESET + "座標: (" + e.getBlock().getX() + ", " + e.getBlock().getY() + ", " + e.getBlock().getZ() + ")");
				calculateBlockCount(e.getPlayer());
				e.setCancelled();
			}
		}
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		WorldEditorEX.getInstance().addPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		WorldEditorEX.getInstance().removePlayer(event.getPlayer());
	}
	
	private void calculateBlockCount(Player player) {
		try {
	        int x1 = Math.max((int) WorldEditorEX.getInstance().getProvider(player).getLocation1().getX(), (int) WorldEditorEX.getInstance().getProvider(player).getLocation2().getX());
	        int y1 = Math.max((int) WorldEditorEX.getInstance().getProvider(player).getLocation1().getY(), (int) WorldEditorEX.getInstance().getProvider(player).getLocation2().getY());
	        int z1 = Math.max((int) WorldEditorEX.getInstance().getProvider(player).getLocation1().getZ(), (int) WorldEditorEX.getInstance().getProvider(player).getLocation2().getZ());
	
	        int x2 = Math.min((int) WorldEditorEX.getInstance().getProvider(player).getLocation1().getX(), (int) WorldEditorEX.getInstance().getProvider(player).getLocation2().getX());
	        int y2 = Math.min((int) WorldEditorEX.getInstance().getProvider(player).getLocation1().getY(), (int) WorldEditorEX.getInstance().getProvider(player).getLocation2().getY());
	        int z2 = Math.min((int) WorldEditorEX.getInstance().getProvider(player).getLocation1().getZ(), (int) WorldEditorEX.getInstance().getProvider(player).getLocation2().getZ());
	        
	        int result = (x1 - x2 + 1) * (y1 - y2 + 1) * (z1 - z2 + 1);
	        
	        player.sendMessage(TextFormat.GREEN + "[WorldEditorEX] " + TextFormat.RESET + "選択ブロック数: " + result);
		} catch(NullPointerException e) {}
	}

}
