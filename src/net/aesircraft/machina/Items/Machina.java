package net.aesircraft.machina.Items;

import net.aesircraft.machina.Data.Config;
import net.aesircraft.machina.SpoutBinder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

public class Machina extends GenericCubeCustomBlock {

    public Machina(Plugin plugin) {
	super(plugin, "Machina Block", Config.getMachinaTexture(), Config.getMachinaTextureSize());
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("ABA", "BCB", "ABA");
	r.setIngredient('A', MaterialData.glowstoneDust);
	r.setIngredient('B', SpoutBinder.getItemHolder().ironGear);
	r.setIngredient('C', SpoutBinder.getItemHolder().machine);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
