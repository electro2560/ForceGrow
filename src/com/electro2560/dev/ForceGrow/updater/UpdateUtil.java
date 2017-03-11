package com.electro2560.dev.ForceGrow.updater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.electro2560.dev.ForceGrow.utils.Utils;

public class UpdateUtil {
	public static final String PREFIX = "§a§l[ForceGrow] §a";
	private static final String URL = "http://dev.electro2560.com/plugins/ForceGrow/info.json";

	public static void sendUpdateMessage(final Player p, final Plugin plugin) {
		new BukkitRunnable() {
			public void run() {
				final String message = UpdateUtil.getUpdateMessage(false);
				if (message != null) {
					new BukkitRunnable() {
						public void run() {
							if (p != null) p.sendMessage(UpdateUtil.PREFIX + message);
						}
					}.runTask(plugin);
				}
			}
		}.runTaskAsynchronously(plugin);
	}

	private static String getUpdateMessage(boolean console) {
		console = true;
		String newestString = getNewestVersion();
		if (newestString == null) {
			if (console) return "Could not check for updates, check your connection.";
			else return null;
		}
		
		Version current;
		
		try {
			current = new Version(Utils.getVersion());
		} catch (IllegalArgumentException e) {
			return "You are using a debug/custom version, consider updating.";
		}
		
		Version newest = new Version(newestString);
		
		if (current.compareTo(newest) < 0) {
			String updates = "\n";
			for (String s : getChanges()) {
				s = s.replace('&', '§');
				updates += " * " + s + "\n";
			}
			return "There is a newer version available: " + newest.toString() + updates;
		} else if (console && current.compareTo(newest) != 0) return "You are running a newer version than is released!";
		
		return null;
	}

	public static String getNewestVersion() {
		try {
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setUseCaches(true);
			connection.addRequestProperty("User-Agent", "ForceGrow " + Utils.getVersion());
			connection.setDoOutput(true);
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String content = "";
			String input;
			while ((input = br.readLine()) != null) content = content + input;
			br.close();
			JSONParser parser = new JSONParser();
			JSONObject statistics;
			try {
				statistics = (JSONObject) parser.parse(content);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
			return (String) statistics.get("version");
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) { }
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> getChanges() {
		try {
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setUseCaches(true);
			connection.addRequestProperty("User-Agent", "ForceGrow " + Utils.getVersion());
			connection.setDoOutput(true);
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String content = "";
			String input;
			while ((input = br.readLine()) != null) content += input;
			br.close();
			JSONParser parser = new JSONParser();
			JSONObject statistics;
			
			try {
				statistics = (JSONObject) parser.parse(content);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
			
			return (ArrayList<String>) statistics.get("message");
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) { }
		
		return null;
	}
}
