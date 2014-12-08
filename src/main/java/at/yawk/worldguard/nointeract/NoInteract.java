package at.yawk.worldguard.nointeract;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author yawkat
 */
public class NoInteract extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        Location location;
        if (clickedBlock == null) {
            location = event.getPlayer().getLocation();
        } else {
            location = clickedBlock.getLocation();
        }

        boolean allow = WorldGuardPlugin.inst().canBuild(event.getPlayer(), location);
        if (!allow) {
            event.setCancelled(true);
        }
    }
}
