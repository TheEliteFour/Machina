package net.aesircraft.machina.Data;

import net.aesircraft.machina.Items.IronGear;
import net.aesircraft.machina.Items.Machine;
import net.aesircraft.machina.Items.WoodenCog;
import net.aesircraft.machina.Machina;

public class ItemHolder {

    public WoodenCog woodenCog;
    public IronGear ironGear;
    public Machine machine;
    public net.aesircraft.machina.Items.Machina machina;

    public ItemHolder(Machina plugin) {
    }

    public void register(Machina plugin) {
	woodenCog = new WoodenCog(plugin);
	ironGear = new IronGear(plugin);
	machine = new Machine(plugin);
	machina = new net.aesircraft.machina.Items.Machina(plugin);
    }
}
