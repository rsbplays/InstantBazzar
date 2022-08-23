package generalrs.Gigaconomy.Economy;

import generalrs.Gigaconomy.Data.YamlDataSaver;
import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (Gigaconomy.dataHandler.playerHasAccount(e.getPlayer()));
        {

            Gigaconomy.dataHandler.createAccountForPlayer(e.getPlayer());
            e.getPlayer().sendMessage("Your Account has been created");
        }else{
            e.setJoinMessage( e.getPlayer().getDisplayName()+"Welcome to the server");
        }

    }
}
