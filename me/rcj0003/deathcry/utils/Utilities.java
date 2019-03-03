package me.rcj0003.deathcry.utils;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Utilities {
	public static class ConfigUtilities {
		public static void updateConfig(Plugin p) {
			p.reloadConfig();
			p.getConfig().set("version", p.getConfig().getDefaults().get("version"));

			Set<String> n = p.getConfig().getKeys(true);
			for (String s : p.getConfig().getDefaults().getKeys(true))
				if (!n.contains(s))
					p.getConfig().set(s, p.getConfig().getDefaults().get(s));

			p.saveConfig();
		}

		public static void makeConfiguration(Plugin p) { // Do work to create the configuration.
			try {
				if (!p.getDataFolder().exists())
					p.getDataFolder().mkdirs();
				if (!new File(p.getDataFolder(), "config.yml").exists())
					p.saveDefaultConfig();
			} catch (Exception e) {
				Bukkit.getConsoleSender().sendMessage("An error occurred while loading the config.");
				e.printStackTrace();
			}
		}

		public static FileConfiguration getConfiguration(Plugin p, String name) throws Exception {
			File f = new File(p.getName() + File.separator + name);
			if (!f.exists())
				throw new Exception();
			return YamlConfiguration.loadConfiguration(f);
		}

		public static FileConfiguration getConfiguration(String name) throws Exception {
			File f = new File(name.replace("/", File.separator));
			if (!f.exists())
				throw new Exception();
			return YamlConfiguration.loadConfiguration(f);
		}
	}
	
	public static class TimeUtilities {
		public static long getTimeAfter(int days, int hours, int minutes, int seconds, int milliseconds) {
			return System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000) + (hours * 60 * 60 * 1000) + (minutes * 60 * 1000) + (seconds * 1000) + milliseconds;
		}
		
		public static long timeRemainingUntil(long time) {
			return Math.max(Math.min(time - System.currentTimeMillis(), Long.MAX_VALUE), 0);
		}

		public static String millisecondsToString(long ms) {
			int days = 0, hours = 0, mins = 0, seconds = 0;
			while (ms >= 24 * 60 * 60 * 1000) {
				ms -= 24 * 60 * 60 * 1000;
				days++;
			}
			while (ms >= 60 * 60 * 1000) {
				ms -= 60 * 60 * 1000;
				hours++;
			}
			while (ms >= 60 * 1000) {
				ms -= 60 * 1000;
				mins++;
			}
			while (ms >= 1000) {
				ms -= 1000;
				seconds++;
			}
			String s = days > 0 ? days + " days" : "";
			s += hours > 0 ? (s.length() > 0 ? ", " : "") + hours + " hours" : "";
			s += mins > 0 ? (s.length() > 0 ? ", " : "") + mins + " minutes" : "";
			s += (s.length() > 0 ? ", " : "") + seconds + " seconds";
			return s;
		}
	}

	public static class IntUtilities {
		public static int containIntWithinRange(int value, int min, int max) {
			return Math.max(Math.min(max, value), min);
		}
	}

	public static class StringUtilities {
		public static String convertColorCodes(String string) {
			return string.replace((char) 38, (char) 167);
		}
	}
}