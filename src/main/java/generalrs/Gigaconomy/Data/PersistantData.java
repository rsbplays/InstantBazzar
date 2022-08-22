package generalrs.Gigaconomy.Data;

import generalrs.Gigaconomy.Economy.Data.BankInfo;
import org.bukkit.OfflinePlayer;

public interface PersistantData {
    
    public boolean playerHasAccount(OfflinePlayer offlinePlayer);
    //saves the balence of the specified player
    public void savePlayerAccounts(OfflinePlayer offlinePlayer, BankInfo bankInfo);
    public BankInfo getPlayerAccounts(OfflinePlayer player);

}
