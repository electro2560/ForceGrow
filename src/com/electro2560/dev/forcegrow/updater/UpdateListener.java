package com.electro2560.dev.forcegrow.updater;

import java.beans.ConstructorProperties;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.electro2560.dev.forcegrow.ForceGrow;
import com.electro2560.dev.forcegrow.utils.Perms;
import com.electro2560.dev.forcegrow.utils.Utils;

public class UpdateListener implements Listener {
	private final ForceGrow plugin;

	@ConstructorProperties({ "plugin" })
	public UpdateListener(ForceGrow plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission(Perms.canCheckForUpdates) && Utils.isCheckForUpdates()) {
			UpdateUtil.sendUpdateMessage(e.getPlayer(), plugin);
		}
	}
}
