package generalrs.Gigaconemy.Data;

import org.bukkit.OfflinePlayer;

public interface PersistantData {
    //saves the balence of the specified player
    public boolean savePlayerBalance(OfflinePlayer player,long amount);
    public long getPlayerBalance(OfflinePlayer player);

}
