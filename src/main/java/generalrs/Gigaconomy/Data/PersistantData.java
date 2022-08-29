package generalrs.Gigaconomy.Data;

import generalrs.Gigaconomy.Economy.Data.BankInfo;
import org.bukkit.OfflinePlayer;

public interface PersistantData {

    public default float getCurrentConfigVersion(){
        return 1F;
    }
    public boolean playerHasAccount(OfflinePlayer offlinePlayer);
    //saves the balence of the specified player
    public BankInfo getPlayerAccounts(OfflinePlayer player);

    public BankInfo createAccountForPlayer(OfflinePlayer player);

}
