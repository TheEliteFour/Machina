package net.aesircraft.machina.Items;

import net.aesircraft.machina.Data.Config;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class WoodenCog extends GenericCustomItem {

    public WoodenCog(Plugin plugin) {
	super(plugin, "Wooden Cog", Config.getWoodenCogTexture());
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("ABA", "ABA", "ABA");
	r.setIngredient('A', MaterialData.cactusGreen);
	r.setIngredient('B', MaterialData.stick);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
