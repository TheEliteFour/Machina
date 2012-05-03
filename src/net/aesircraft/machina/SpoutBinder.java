package net.aesircraft.machina;

import net.aesircraft.machina.Data.ItemHolder;

public class SpoutBinder {
    
    private static ItemHolder itemHolder;;
    
    public static ItemHolder getItemHolder() {
	return itemHolder;
    }
    
    public static void bind(Machina plug){
	itemHolder = new ItemHolder(plug);
	itemHolder.register(plug);
    }
    
}
