package net.aesircraft.machina.Listeners;

import net.aesircraft.machina.Data.Config;
import net.aesircraft.machina.Data.DataHolder;
import net.aesircraft.machina.Machina;
import net.aesircraft.machina.Objects.MachinaBlock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;

public class PlayerListener implements Listener {

    public PlayerListener(Machina plugin) {
	Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent e) {
	if (e.getBlock() == null) {
	    return;
	}
	if (e.isCancelled()) {
	    return;
	}
	SpoutBlock b = (SpoutBlock) e.getBlock();
	if (!b.isCustomBlock()) {
	    return;
	}
	if (!b.getCustomBlock().getName().contains("Machina Block")) {
	    return;
	}
	if (Config.userPermissions) {
	    if (!Machina.permission.has(e.getPlayer(), "machina.user")) {
		e.getPlayer().sendMessage(("§4You do not have permission to use that!"));
		e.setCancelled(true);
		return;
	    }
	}

	MachinaBlock mb = new MachinaBlock(e.getBlock());
	mb.newBlock(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlacePiece(BlockPlaceEvent e) {
	if (e.getBlock() == null) {
	    return;
	}
	if (e.isCancelled()) {
	    return;
	}
	if (!DataHolder.workings.containsKey(e.getPlayer())) {
	    return;
	}
	MachinaBlock mb = DataHolder.workings.get(e.getPlayer());
	mb.load();
	int id = e.getBlock().getTypeId();
	byte data = e.getBlock().getData();
	ItemStack i = mb.getType();
	if (id != i.getTypeId() || data != i.getDurability()) {
	    return;
	}
	if (!mb.withingRange(e.getBlock())) {
	    e.getPlayer().sendMessage(("§4OUT OF RANGE!"));
	    return;
	}
	e.getPlayer().sendMessage(("§eAdded block!"));
	mb.addBlock(e.getBlock());

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRemovePiece(BlockBreakEvent e) {
	if (e.getBlock() == null) {
	    return;
	}
	if (e.isCancelled()) {
	    return;
	}
	if (!DataHolder.workings.containsKey(e.getPlayer())) {
	    return;
	}
	MachinaBlock mb = DataHolder.workings.get(e.getPlayer());
	mb.load();
	int id = e.getBlock().getTypeId();
	byte data = e.getBlock().getData();
	ItemStack i = mb.getType();
	if (id != i.getTypeId() || data != i.getDurability()) {
	    return;
	}
	if (!mb.withingRange(e.getBlock())) {
	    return;
	}
	e.getPlayer().sendMessage(("§eRemoved block!"));
	mb.removeBlock(e.getBlock());

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
	if (e.isCancelled()) {
	    return;
	}
	if (e.getAction() != Action.LEFT_CLICK_BLOCK) {
	    return;
	}

	if (e.getClickedBlock() == null) {
	    return;
	}

	SpoutBlock b = (SpoutBlock) e.getClickedBlock();
	if (!b.isCustomBlock()) {
	    return;
	}
	if (!b.getCustomBlock().getName().contains("Machina Block")) {
	    return;
	}
	MachinaBlock mb = new MachinaBlock(e.getClickedBlock());
	mb.load();
	if (!e.getPlayer().getName().toLowerCase().equals(mb.getOwner().toLowerCase()) && !e.getPlayer().isOp()) {
	    if (Config.userPermissions) {
		if (!Machina.permission.has(e.getPlayer(), "machina.admin")) {
		    e.getPlayer().sendMessage(("§4You do not have permission to use that!"));
		    e.setCancelled(true);
		    return;
		}
	    }
	}

	if (e.getPlayer().getItemInHand() != null) {
	    if (e.getPlayer().getItemInHand().getType() == Material.STICK) {
		if (mb.getBlocks() > 0) {
		    e.getPlayer().sendMessage("§4There are still blocks in this machine!");
		    return;
		}
		mb.clearType();
		return;
	    }
	}

	if (DataHolder.workings.containsKey(e.getPlayer())) {
	    if (DataHolder.workings.get(e.getPlayer()).getBlock() == mb.getBlock()) {
		DataHolder.workings.get(e.getPlayer()).finishWorking();
		e.getPlayer().sendMessage("§2Finished editing Machina Block!");
		return;
	    } else {
		e.getPlayer().sendMessage("§4Please finish the block you are working on!");
		return;
	    }
	}
	if (mb.getType().getTypeId() == 0) {
	    if (e.getPlayer().getItemInHand() == null) {
		e.getPlayer().sendMessage("§4Please set a type to work with!");
		return;
	    }
	    mb.setType(e.getPlayer().getItemInHand());
	    return;
	}
	mb.setWorking(e.getPlayer());
	e.getPlayer().sendMessage("§2Now editing Machina Block with §b" + mb.getType().getTypeId() + ":" + mb.getType().getDurability() + "§2!");
	return;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e) {
	if (e.getBlock() == null) {
	    return;
	}
	if (e.isCancelled()) {
	    return;
	}
	SpoutBlock b = (SpoutBlock) e.getBlock();
	if (!b.isCustomBlock()) {
	    return;
	}
	if (!b.getCustomBlock().getName().contains("Machina Block")) {
	    return;
	}
	if (Config.userPermissions) {
	    if (!Machina.permission.has(e.getPlayer(), "machina.user")) {
		e.getPlayer().sendMessage(("§4You do not have permission to remove that!"));
		e.setCancelled(true);
		return;
	    }
	}

	MachinaBlock mb = new MachinaBlock(e.getBlock());
	mb.load();
	if (!e.getPlayer().getName().toLowerCase().equals(mb.getOwner().toLowerCase()) && !e.getPlayer().isOp()) {
	    if (Config.userPermissions) {
		if (!Machina.permission.has(e.getPlayer(), "machina.admin")) {
		    e.getPlayer().sendMessage(("§4You do not have permission to remove that!"));
		    e.setCancelled(true);
		    return;
		}
	    }
	}
	mb.breakNaturally();
    }
}
