package generalrs.Gigaconomy.Data;

import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Economy.Data.BankInfo;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    private void loadPlayerAccount(OfflinePlayer offlinePlayer){

    }
    @Override
    public BankInfo getPlayerAccounts(OfflinePlayer player) {
        //todo FIX THIS SHITTY CODE
        if (bankInfoCache.containsKey(player.getUniqueId())){
            return bankInfoCache.get(player.getUniqueId());
        }else{
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(getDataFileForPlayer(player.getUniqueId()));

            return loadAccountFromSec(configuration.getConfigurationSection("accounts"),player);
        }


    }

    @Override
    public BankInfo createAccountForPlayer(OfflinePlayer player) {
        File newPlayerFile = getDataFileForPlayer(player.getUniqueId());
        FileConfiguration playerDataFile = YamlConfiguration.loadConfiguration(newPlayerFile);
        playerDataFile.set("Version",getCurrentConfigVersion());

        //Create the bank classes
        BankAccount pBankAccount = new BankAccount(player);
        BankInfo pBankInfo = new BankInfo(player,pBankAccount);
        pBankAccount.setAmount(1000);

        savePlayerBankInfo(playerDataFile,pBankInfo);
        try{
            playerDataFile.save(newPlayerFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bankInfoCache.put(player.getUniqueId(),pBankInfo);
        return pBankInfo;

    }

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
    private ConfigurationSection savePlayerBankInfo(FileConfiguration config, BankInfo bankInfo){
        ConfigurationSection accountssec = config.createSection("accounts");
        for (BankAccount bankAccount:bankInfo.getAccounts()){
            ConfigurationSection accsec = accountssec.createSection(bankAccount.getName());
            accsec.set("amount",bankAccount.getAmount());
        }
        return config;
    }
    private BankInfo loadAccountFromSec(ConfigurationSection section,OfflinePlayer offlinePlayer){
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        for (String key:section.getKeys(false)){
            ConfigurationSection bc = section.getConfigurationSection(key);
            BankAccount ba = new BankAccount(key,offlinePlayer);
            ba.setAmount(bc.getDouble("amount"));
            bankAccounts.add(ba);

        }
        BankInfo bankInfo = new BankInfo(bankAccounts,offlinePlayer);
        return bankInfo;
    }

}
