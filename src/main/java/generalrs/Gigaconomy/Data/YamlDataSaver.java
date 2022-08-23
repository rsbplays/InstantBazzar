package generalrs.Gigaconomy.Data;

import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Economy.Data.BankInfo;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
        if (!playerDataFolder.exists()) playerDataFolder.mkdirs();
        

    }

    @Override
    public boolean playerHasAccount(OfflinePlayer offlinePlayer) {
        if (getDataFileForPlayer(offlinePlayer.getUniqueId())!=null){
            return true;
        }
        return false;
    }

    @Override
    public void savePlayerAccounts(OfflinePlayer offlinePlayer, BankInfo bankInfo) {

    }


    @Override
    public BankInfo getPlayerAccounts(OfflinePlayer player) {
        return null;
    }

    @Override
    public BankInfo createAccountForPlayer(OfflinePlayer player) {
        File newPlayerFile = getDataFileForPlayer(player.getUniqueId());
        FileConfiguration playerDataFile = YamlConfiguration.loadConfiguration(newPlayerFile);
        playerDataFile.set("Version",getCurrentConfigVersion());

        //Create the bank classes
        BankAccount pBankAccount = new BankAccount(player);
        BankInfo pBankInfo = new BankInfo(player,pBankAccount);


    };

    private File getFolderForPlayer(UUID uuid){
        String chars = uuid.toString().substring(0,2);
        File pfile = new File(playerDataFolder, chars+"//");
        if (!pfile.exists()){
            pfile.mkdirs();
        }
        return pfile;

    }
    private File getDataFileForPlayer(UUID uuid){
        File playerDataFolder = getFolderForPlayer(uuid);
        File playerDataFile = new File(playerDataFolder,uuid.toString()+".yml");
        if (playerDataFolder.exists()){
            return playerDataFile;
        }
        return playerDataFile;

    }
    private ConfigurationSection savePlayerBankInfo(ConfigurationSection configurationSection, BankInfo bankInfo){

    }

}
