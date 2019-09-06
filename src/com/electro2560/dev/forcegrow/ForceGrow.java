package com.electro2560.dev.forcegrow;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.electro2560.dev.forcegrow.listeners.PlayerListener;
import com.electro2560.dev.forcegrow.updater.UpdateListener;
import com.electro2560.dev.forcegrow.utils.Utils;

public class ForceGrow extends JavaPlugin{

	private PluginManager pm = Bukkit.getServer().getPluginManager();
	
	private static ForceGrow instance;
	
	public void onEnable(){
		instance = this;
		
		if(!new File(getDataFolder() + File.separator + "config.yml").exists()) saveDefaultConfig();
		
		pm.registerEvents(new PlayerListener(), instance);
		pm.registerEvents(new UpdateListener(instance), instance);
		
		if(getConfig().getBoolean("useMetrics", true)) Utils.startMetrics();
	}
	
	public void onDisable(){
		instance = null;
	}
	
	public static ForceGrow get(){
		return instance;
	}
	
}
