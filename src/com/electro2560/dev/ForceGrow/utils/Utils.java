package com.electro2560.dev.ForceGrow.utils;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.mcstats.MetricsLite;

import com.electro2560.dev.ForceGrow.ForceGrow;

import net.md_5.bungee.api.ChatColor;

public class Utils {
	
	public static final ForceGrow m = ForceGrow.get();
	
	public static void log(String msg){
		Bukkit.getServer().getLogger().info(ChatColor.GREEN + "[ForceGrow] " + msg);
	}
	
	public static boolean getChance(String chance){
		String[] parts = chance.split("/");
		double p1, p2;
		
		try{
			p1 = Double.parseDouble(parts[0]);
		}catch(Exception e){
			log("Failed to generate chance! " + parts[0] + " is not a number!");
			return false;
		}
		try{
			p2 = Double.parseDouble(parts[1]);
		}catch(Exception e){
			log("Failed to generate chance! " + parts[1] + " is not a number!");
			return false;
		}
		
		double c = p1/p2;
		
		if(chance.equals("1/1")) c = 1;
		
		if(c >= Math.random()) return true;
		
		return false;
	}

	public static BlockFace randomFace(){
		double rnd = Math.random() * 100; 
		
		if(rnd <= 25) return BlockFace.NORTH;
		else if(rnd <= 50) return BlockFace.SOUTH;
		else if(rnd <= 75) return BlockFace.EAST;
		else return BlockFace.WEST;
	}
	
	public static int getRndInt(int min, int max){
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public static int getHeightSize(final Block b){
		final Material m = b.getType();
		int a = 1; //Set to 1 to include the clicked block
		
		Block current = b;
		
		while(current.getRelative(BlockFace.DOWN).getType() == m){
			a++;
			current = current.getRelative(BlockFace.DOWN);
		}
		
		//Reset relative locations
		current = b;
		
		while(current.getRelative(BlockFace.UP).getType() == m){
			a++;
			current = current.getRelative(BlockFace.UP);
		}
		
		return a;
	}

	public static boolean isCheckForUpdates() {
		return m.getConfig().getBoolean("checkForUpdates", true);
	}

	public static String getVersion() {
		return m.getDescription().getVersion();
	}
	
	public static void startMetrics(){
		try {
	        MetricsLite metrics = new MetricsLite(ForceGrow.get());
	        metrics.start();
	    } catch (IOException e) {}
		
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(ForceGrow.get());
	}
	
}
