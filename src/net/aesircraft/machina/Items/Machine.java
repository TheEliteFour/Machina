package net.aesircraft.machina.Items;

import net.aesircraft.machina.Data.Config;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

public class Machine extends GenericCubeCustomBlock {

    public Machine(Plugin plugin) {
	super(plugin, "Machine Block", Config.getMachineTexture(), Config.getMachineTextureSize());
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("ABA", "BCB", "ABA");
	r.setIngredient('A', MaterialData.redstone);
	r.setIngredient('B', MaterialData.ironIngot);
	r.setIngredient('C', MaterialData.diamond);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
