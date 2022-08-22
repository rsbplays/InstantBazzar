package generalrs.Gigaconomy.Data;

import generalrs.Gigaconomy.Economy.Data.BankInfo;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class YamlDataSaver implements PersistantData{
    private HashMap<UUID, BankInfo> bankInfoCache = new HashMap<>();
    private File mainSaveFolder;
    private File playerDataFolder;

    public YamlDataSaver() throws IOException {
        //Get the main save file
        mainSaveFolder = new File(".").getParentFile();
        mainSaveFolder = new File(mainSaveFolder,"plugins\\");
        mainSaveFolder = new File(mainSaveFolder,"GigaConomy\\");

        playerDataFolder = new File(mainSaveFolder,"PlayerData\\");
        if (!playerDataFolder.exists()) playerDataFolder.createNewFile();
        

    }

    @Override
    public boolean playerHasAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    @Override
    public void savePlayerAccounts(OfflinePlayer offlinePlayer, BankInfo bankInfo) {

    }

    @Override
    public BankInfo getPlayerAccounts(OfflinePlayer player) {
        return null;
    }


}
