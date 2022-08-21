package generalrs.instantbazzar;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class BasicEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        InstantBazzar.sayHello.sayHelloToPlayer(event.getPlayer());

        BukkitTask taskid = Bukkit.getScheduler().runTaskTimer(InstantBazzar.instance, new Runnable() {
            @Override
            public void run() {
                event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation().add(0,3,0), new ItemStack(Material.DIAMOND));

            }

        },1,1);
        Bukkit.getScheduler().runTaskLater(InstantBazzar.instance, new Runnable() {
            @Override
            public void run() {
                taskid.cancel();
            }
        },100);
    }
}
