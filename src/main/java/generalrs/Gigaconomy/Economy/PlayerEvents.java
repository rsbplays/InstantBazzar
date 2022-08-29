package generalrs.Gigaconomy.Economy;

import generalrs.Gigaconomy.Data.YamlDataSaver;
import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Economy.Data.Languages;
import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.TabCompleteEvent;

public class PlayerEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (Gigaconomy.dataHandler.playerHasAccount(e.getPlayer()))
        {
            Gigaconomy.dataHandler.createAccountForPlayer(e.getPlayer());
            e.getPlayer().sendMessage(Languages.getString("welcome.message"));
            Bukkit.getLogger().info("Has joined");
        }
        Bukkit.getLogger().info("Has joined1");

    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent e){
        Bukkit.getLogger().info(e.getBuffer());
    }
}
