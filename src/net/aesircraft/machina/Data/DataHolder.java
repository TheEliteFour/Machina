package net.aesircraft.machina.Data;

import java.util.HashMap;
import net.aesircraft.machina.Objects.MachinaBlock;
import org.bukkit.entity.Player;

public class DataHolder {

    //public static HashMap<Block,MachinaBlock> blocks=new HashMap<Block,MachinaBlock>();
    public static HashMap<Player, MachinaBlock> workings = new HashMap<Player, MachinaBlock>();
}
