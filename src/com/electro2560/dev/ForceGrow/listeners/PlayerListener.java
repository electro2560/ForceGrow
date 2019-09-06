package com.electro2560.dev.ForceGrow.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.electro2560.dev.ForceGrow.ForceGrow;
import com.electro2560.dev.ForceGrow.utils.GrowUtils;
import com.electro2560.dev.ForceGrow.utils.Perms;
import com.electro2560.dev.ForceGrow.utils.Utils;

public class PlayerListener implements Listener{

	static FileConfiguration config = ForceGrow.get().getConfig();
	
	@EventHandler
	public void onInteractWithBlock(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Block b = event.getClickedBlock();
		
		if(b == null) return;
		
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		
		Material t = b.getType();
		
		ItemStack i = player.getInventory().getItemInMainHand();
		if(i == null) return;
		
		//Ensure the player is holding bone meal
		if(i.getType() != Material.BONE_MEAL) return;
		
		switch(t){
		case SUGAR_CANE:
			if(!player.hasPermission(Perms.canForceSugarCane)) return;
			
			int size = Utils.getHeightSize(b);
			
			//Too large to grow again
			if(size >= 3) return;
			
			decrementItemInHand(player);
			
			if(!Utils.getChance(config.getString("chances.sugarCane"))) return;
			
			GrowUtils.growReeds(b);
			break;
		case PUMPKIN_STEM:
			if(!player.hasPermission(Perms.canForcePumpkin)) return;
			
			//Stem must be fully grown
			if(!GrowUtils.fullyGrown(b)) return;
			
			decrementItemInHand(player);
			
			if(!Utils.getChance(config.getString("chances.pumpkin"))) return;
			
			GrowUtils.growPumpkin(b);
			break;
		case MELON_STEM:
			if(!player.hasPermission(Perms.canForceMelon)) return;
			
			//Stem must be fully grown
			if(!GrowUtils.fullyGrown(b)) return;
			
			decrementItemInHand(player);
			
			if(!Utils.getChance(config.getString("chances.melon"))) return;
			
			GrowUtils.growMelon(b);
			break;
		case CACTUS:
			if(!player.hasPermission(Perms.canForceCactus)) return;
			
			int s = Utils.getHeightSize(b);
			
			//Too large to grow again
			if(s >= 3) return;
			
			decrementItemInHand(player);
			
			if(!Utils.getChance(config.getString("chances.cactus"))) return;
			
			GrowUtils.growCactus(b);
			break;
		case NETHER_WART:
			if(!player.hasPermission(Perms.canForceNetherWart)) return;
			
			if(GrowUtils.fullyGrown(b)) return;

			decrementItemInHand(player);
			
			if(!Utils.getChance(config.getString("chances.netherWart"))) return;
			
			GrowUtils.growNetherWart(b);
			break;
		default: break;
		
		}
	}
	
	private void decrementItemInHand(Player player){
		ItemStack i = player.getInventory().getItemInMainHand();
		
		if(i.getAmount() - 1 <= 1) player.getInventory().setItemInMainHand(null);
		else i.setAmount(i.getAmount() - 1);
	}
	
}
