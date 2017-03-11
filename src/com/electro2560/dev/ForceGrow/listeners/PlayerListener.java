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
import com.electro2560.dev.ForceGrow.utils.Utils;

public class PlayerListener implements Listener{

	final ForceGrow m = ForceGrow.get();
	final FileConfiguration f = m.getConfig();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteractWithBlock(PlayerInteractEvent event){
		Player p = event.getPlayer();
		Block b = event.getClickedBlock();
		
		if(b == null) return;
		
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		
		Material t = b.getType();
		
		ItemStack i = p.getItemInHand();
		if(i == null) return;
		
		if(i.getType() != Material.INK_SACK || i.getData().getData() != 15) return;
		
		switch(t){
		case SUGAR_CANE_BLOCK:
			int size = Utils.getHeightSize(b);
			
			//Too large to grow
			if(size >= 4) return;
			
			if(i.getAmount() - 1 <= 1) p.getInventory().setItemInHand(null);
			else i.setAmount(i.getAmount() - 1);
			
			if(!Utils.getChance(f.getString("chances.sugarCane"))) return;
			
			GrowUtils.growReeds(b);
			
			break;
		case PUMPKIN_STEM:
			if(b.getData() != 7) return;
			
			if(i.getAmount() - 1 <= 1) p.getInventory().setItemInHand(null);
			else i.setAmount(i.getAmount() - 1);
			
			if(!Utils.getChance(f.getString("chances.pumpkin"))) return;
			
			GrowUtils.growPumpkin(b);
			break;
		case MELON_STEM:
			if(b.getData() != 7) return;
			
			if(i.getAmount() - 1 <= 1) p.getInventory().setItemInHand(null);
			else i.setAmount(i.getAmount() - 1);
			
			if(!Utils.getChance(f.getString("chances.melon"))) return;
			
			GrowUtils.growMelon(b);
			break;
		case CACTUS:
			int s = Utils.getHeightSize(b);
			
			//Too large to grow
			if(s >= 4) return;
			
			if(i.getAmount() - 1 <= 1) p.getInventory().setItemInHand(null);
			else i.setAmount(i.getAmount() - 1);
			
			if(!Utils.getChance(f.getString("chances.cactus"))) return;
			
			GrowUtils.growCactus(b);
			break;
		case NETHER_WARTS:
			if(b.getData() == 3) return;
			
			if(i.getAmount() - 1 <= 1) p.getInventory().setItemInHand(null);
			else i.setAmount(i.getAmount() - 1);
			
			if(!Utils.getChance(f.getString("chances.netherWart"))) return;
			
			GrowUtils.growNetherWart(b);
			break;
		default: break;
		}
		
	}
	
}
