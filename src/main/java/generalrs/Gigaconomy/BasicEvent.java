package generalrs.Gigaconomy;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class BasicEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){


    }
    @EventHandler
    public void onTabComplete(PlayerChatTabCompleteEvent e){
    }
}
