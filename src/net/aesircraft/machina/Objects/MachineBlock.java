package net.aesircraft.machina.Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import net.aesircraft.machina.Machina;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MachineBlock {
    
    private Block block;
    private ManaLocation mloc;
    private String MachineType;
    private int power;
    private String owner;
    
    public MachineBlock(Block block){
	this.block=block;
	mloc=new ManaLocation(block.getLocation());
    }
    
    public boolean isEngine(){
	return MachineType.contains("engine");
    }
    
    public void tic(){
	//DO STUFF HERE
    }
    
    public boolean withdrawPower(int amount){
	Block b1=block.getRelative(BlockFace.UP);
	MachineBlock mb=new MachineBlock(b1);
	if (mb.isMachine()){
	    if (mb.isEngine()){
		if (mb.takePower(amount)){
		    power=power+amount;
		    saveInfo();
		    return true;
		}		
	    }	    
	}
	return false;
    }
    
    public boolean takePower(int amount){
	if (amount>power){
	    return false;
	}
	power=power-amount;
	saveInfo();
	return true;
    }
    
    public boolean isMachine(){
	return !(getFile(true)==null);
    }
    
    public void newBlock(String MachineType,Player player){
	this.MachineType=MachineType.toLowerCase();
	owner=player.getName();
    }
    
    private void saveInfo(){
	YamlConfiguration config=getYaml();
	config.set("Machine", MachineType);
	config.set("Power",power);
	config.set(owner, mloc);
	save(config);
    }
    
    private File getFile(boolean exists) {
	File dir = Machina.getStatic().getDataFolder();
	File dir2 = new File(dir, "MachineBlocks");
	File file = new File(dir2, mloc.getString() + ".mbf");
	if (!dir.exists()) {
	    dir.mkdir();
	}
	if (!dir2.exists()) {
	    dir2.mkdir();
	}
	if (!file.exists()) {
	    if (exists){
		return null;
	    }
	    try {
		file.createNewFile();
	    } catch (IOException ex) {
		Machina.logger.log(Level.SEVERE, "[Machina] Failed to write machine file!");
	    }
	}
	return file;
    }
    
     private YamlConfiguration getYaml() {
	YamlConfiguration config = new YamlConfiguration();
	try {
	    config.load(getFile(false));
	} catch (FileNotFoundException ex) {
	    Machina.logger.log(Level.SEVERE, "[Machina] Failed to find machine file!");
	} catch (IOException ex) {
	    Machina.logger.log(Level.SEVERE, "[Machina] Failed to load machine file!");
	} catch (InvalidConfigurationException ex) {
	    Machina.logger.log(Level.SEVERE, "[Machina] Failed to understand machine file!");
	}
	return config;
    }

    private void save(YamlConfiguration config) {
	try {
	    config.save(getFile(false));
	} catch (IOException ex) {
	    Machina.logger.log(Level.SEVERE, "[Machina] Failed to write machine file!");
	}
    }
}
