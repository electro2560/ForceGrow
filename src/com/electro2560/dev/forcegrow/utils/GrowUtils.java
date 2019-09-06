package com.electro2560.dev.forcegrow.utils;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;

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
				
				c.setType(Material.MELON);
				return;
			}
			
		}while(faces.size() < 4);
	}
	
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
				return;
			}
		}while(faces.size() < 4);
	}
	
	public static void growNetherWart(Block b){
		Ageable a = (Ageable) b.getBlockData();
		
		int age = a.getAge();
		
		age += Utils.getRndInt(1, 2);
		
		if(age >= a.getMaximumAge()) a.setAge(a.getMaximumAge());
		else a.setAge(age);
		
		b.setBlockData(a);
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
			
			current.setType(Material.SUGAR_CANE);
			return;
		}
	}
	
	public static boolean fullyGrown(Block b) {
		Ageable a = (Ageable) b.getBlockData();
		
		if(a.getAge() >= a.getMaximumAge()) return true;
		
		return false;
	}
	
}
