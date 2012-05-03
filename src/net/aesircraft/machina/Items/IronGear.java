package net.aesircraft.machina.Items;

import net.aesircraft.machina.Data.Config;
import net.aesircraft.machina.SpoutBinder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class IronGear extends GenericCustomItem {

    public IronGear(Plugin plugin) {
	super(plugin, "Iron Gear", Config.getIronGearTexture());
	ItemStack i = new SpoutItemStack(this, 1);
	SpoutShapedRecipe r = new SpoutShapedRecipe(i);
	r.shape("AAA", "ABA", "AAA");
	r.setIngredient('A', MaterialData.ironIngot);
	r.setIngredient('B', SpoutBinder.getItemHolder().woodenCog);
	SpoutManager.getMaterialManager().registerSpoutRecipe(r);
    }
}
