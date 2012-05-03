package net.aesircraft.machina;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.aesircraft.machina.Data.Config;
import net.aesircraft.machina.Listeners.MachineListener;
import net.aesircraft.machina.Listeners.PlayerListener;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Machina extends JavaPlugin {

    private static Machina instance = null;
    public static final Logger logger = Logger.getLogger("Minecraft");
    public static Permission permission = null;

    private Boolean setupPermissions() {
	RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	if (permissionProvider != null) {
	    permission = permissionProvider.getProvider();
	}
	return (permission != null);
    }

    public static Machina getStatic() {
	return instance;
    }

    private void setStatic() {
	instance = this;
    }

    @Override
    public void onDisable() {
	logger.info("[Machina] Unloaded!");
    }

    @Override
    public void onEnable() {
	setStatic();
	PluginManager pm = getServer().getPluginManager();
	if (!pm.isPluginEnabled("Spout")) {
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
	    logger.log(Level.SEVERE, "    MACHINA - SEVERE USER ERROR!");
	    logger.log(Level.SEVERE, "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "Machina is now disabling it's self.");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "This is happening because you did not");
	    logger.log(Level.SEVERE, "follow instructions and install SpoutPlugin.");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "To prevent errors like this you should");
	    logger.log(Level.SEVERE, "thoroughly read about what you are trying");
	    logger.log(Level.SEVERE, "to install, or just not install it.");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "If you do have SpoutPlugin then read");
	    logger.log(Level.SEVERE, "the rest of your log, completely,");
	    logger.log(Level.SEVERE, "because SpoutPlugin obviously disabled");
	    logger.log(Level.SEVERE, "it's self because you are not using");
	    logger.log(Level.SEVERE, "proper versions.");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "Do not post this error because I will");
	    logger.log(Level.SEVERE, "laugh at you if you do.");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "Have a nice day.");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    logger.log(Level.SEVERE, "");
	    return;
	}

	Config.writeDefaults();
	//Config.upgrade();
	if (Config.getUsePermissions()) {
	    if (pm.isPluginEnabled("Vault")) {
		setupPermissions();
		Config.userPermissions = true;
	    }
	}

	SpoutBinder.bind(this);

	new PlayerListener(this);
	new MachineListener(this);
	logger.info("[Machina] Loaded " + this.getDescription().getName() + " build " + this.getDescription().getVersion() + "!");


    }
}
