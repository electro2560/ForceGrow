package com.electro2560.dev.ForceGrow.utils;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Pumpkin;

public class GrowUtils {

	public static void growMelon(Block b){
		HashSet<BlockFace> faces = new HashSet<BlockFace>();
		
		do{
			BlockFace f = Utils.randomFace();
			if(!faces.contains(f)){
				faces.add(f);
				Block r = b.getRelative(f).getRelative(BlockFace.DOWN);
				if(r == null) continue;
				if(r.isLiquid() || !r.getType().isSolid() || r.getType() == Material.AIR) continue;
				
				Block c = b.getRelative(f);
				if(c == null || c.getType() != Material.AIR) continue;
				
				c.setType(Material.MELON_BLOCK);
				return;
			}
			
		}while(faces.size() < 4);
	}
	
	@SuppressWarnings("deprecation")
	public static void growPumpkin(Block b){
		HashSet<BlockFace> faces = new HashSet<BlockFace>();
		
		do{
			BlockFace f = Utils.randomFace();
			if(!faces.contains(f)){
				faces.add(f);
				Block r = b.getRelative(f).getRelative(BlockFace.DOWN);
				if(r == null) continue;
				if(r.isLiquid() || !r.getType().isSolid() || r.getType() == Material.AIR) continue;
				
				Block c = b.getRelative(f);
				if(c == null || c.getType() != Material.AIR) continue;
				
				c.setType(Material.PUMPKIN);
				
				//Set the direction of the pumpkin to be random
				Pumpkin p = (Pumpkin) c.getState().getData();

				p.setFacingDirection(Utils.randomFace());
				c.setData(p.getData());
				return;
			}
			
		}while(faces.size() < 4);
	}
	
	@SuppressWarnings("deprecation")
	public static void growNetherWart(Block b){
		byte age = b.getData();
		
		age += Utils.getRndInt(1, 2);
		
		if(age >= 3) b.setData((byte) 3);
		else b.setData(age);
	}

	public static void growCactus(final Block b){
		Block current = b;
		
		for (int i = 0; i < 4; i++) {
			current = current.getRelative(BlockFace.UP);
			
			if(current.getType() != Material.AIR) continue;
			
			current.setType(Material.CACTUS);
			return;
		}
	}
	
	public static void growReeds(final Block b){
		Block current = b;
		
		for (int i = 0; i < 4; i++) {
			current = current.getRelative(BlockFace.UP);
			
			if(current.getType() != Material.AIR) continue;
			
			current.setType(Material.SUGAR_CANE_BLOCK);
			return;
		}
	}
	
}
