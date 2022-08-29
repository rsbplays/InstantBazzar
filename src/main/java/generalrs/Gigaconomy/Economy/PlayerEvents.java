package generalrs.Gigaconomy.Economy;

import generalrs.Gigaconomy.Data.YamlDataSaver;
import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Economy.Data.Languages;
import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (Gigaconomy.dataHandler.playerHasAccount(e.getPlayer()))
        {
            Gigaconomy.dataHandler.createAccountForPlayer(e.getPlayer());
            e.getPlayer().sendMessage(Languages.getString("welcome.message"));
        }

    }

    @EventHandler
    public void onTabComplete(PlayerChatTabCompleteEvent e){

    }
}
