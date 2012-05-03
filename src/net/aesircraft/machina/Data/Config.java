package net.aesircraft.machina.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import net.aesircraft.machina.Machina;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

    private static boolean newFile;
    public static boolean userPermissions = false;

    private static File getFile() {
	File dir = Machina.getStatic().getDataFolder();
	File file = new File(dir, "config.yml");
	if (!dir.exists()) {
	    dir.mkdir();
	}
	if (!file.exists()) {
	    try {
		file.createNewFile();
		newFile = true;
	    } catch (IOException ex) {
		Machina.logger.log(Level.SEVERE, "[Machina] Failed to write config file!");
	    }
	}
	return file;
    }

    private static YamlConfiguration getYaml() {
	YamlConfiguration config = new YamlConfiguration();
	try {
	    config.load(getFile());
	} catch (FileNotFoundException ex) {
	    Machina.logger.log(Level.SEVERE, "[Machina] Failed to find config file!");
	} catch (IOException ex) {
	    Machina.logger.log(Level.SEVERE, "[Machina] Failed to load config file!");
	} catch (InvalidConfigurationException ex) {
	    Machina.logger.log(Level.SEVERE, "[Machina] You fudged up the config file!");
	}
	if (newFile) {
	    config.set("Use-Permissions", true);
	    config.set("Machina-Block-Texture", "http://aesircraft.net/items/machina.png");
	    config.set("Machina-Block-Texture-Size", 16);
	    config.set("Machine-Block-Texture", "http://aesircraft.net/items/machine.png");
	    config.set("Machine-Block-Texture-Size", 16);
	    config.set("Iron-Gear-Texture", "http://aesircraft.net/items/gear.png");
	    config.set("Wooden-Cog-Texture", "http://aesircraft.net/items/cog.png");
	    config.set("Sound-Effects-File", "http://aesircraft.net/items/poof.ogg");
	    config.set("Sound-Effects", true);
	    save(config);

	    try {
		config.load(getFile());
	    } catch (FileNotFoundException ex) {
		Machina.logger.log(Level.SEVERE, "[Machina] Failed to find config file!");
	    } catch (IOException ex) {
		Machina.logger.log(Level.SEVERE, "[Machina] Failed to load config file!");
	    } catch (InvalidConfigurationException ex) {
		Machina.logger.log(Level.SEVERE, "[Machina] You fudged up the config file!");
	    }
	}
	return config;
    }

    private static void save(YamlConfiguration config) {
	try {
	    config.save(getFile());
	} catch (IOException ex) {
	    Machina.logger.log(Level.SEVERE, "[Machina] Failed to save config file!");
	}
    }

    public static void writeDefaults() {
	getYaml();
    }

    public static boolean getUsePermissions() {
	if (!userPermissions) {
	    return userPermissions;
	}
	return getYaml().getBoolean("Use-Permissions", true);
    }

    public static String getMachinaTexture() {
	return getYaml().getString("Machina-Block-Texture", "http://aesircraft.net/items/machina.png");
    }

    public static String getMachineTexture() {
	return getYaml().getString("Machine-Block-Texture", "http://aesircraft.net/items/machine.png");
    }

    public static int getMachineTextureSize() {
	return getYaml().getInt("Machine-Block-Texture-Size", 16);
    }

    public static int getMachinaTextureSize() {
	return getYaml().getInt("Machina-Block-Texture-Size", 16);
    }

    public static String getIronGearTexture() {
	return getYaml().getString("Iron-Gear-Texture", "http://aesircraft.net/items/gear.png");
    }

    public static String getWoodenCogTexture() {
	return getYaml().getString("Wooden-Cog-Texture", "http://aesircraft.net/items/cog.png");
    }

    public static boolean useSoundEffects() {
	return getYaml().getBoolean("Sound-Effects", true);
    }

    public static String getSoundEffectsFile() {
	return getYaml().getString("Sound-Effects-File", "http://aesircraft.net/items/machine.ogg");
    }
}
