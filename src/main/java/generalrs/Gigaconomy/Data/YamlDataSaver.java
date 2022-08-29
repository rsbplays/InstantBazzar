package generalrs.Gigaconomy.Data;

import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Economy.Data.BankInfo;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class YamlDataSaver implements PersistantData, Listener {
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

    
    public void modifyPlayerBankInfo(OfflinePlayer offlinePlayer, BankInfo bankInfo) {
        FileConfiguration playerFile = YamlConfiguration.loadConfiguration(getDataFileForPlayer(offlinePlayer.getUniqueId()));
        for(String key:playerFile.getConfigurationSection("accounts").getKeys(false)){
            if (bankInfo.getAccounts().contains(key)){
                for (BankAccount bankAccount:bankInfo.getAccounts()){
                    if (bankAccount.getName().equals(key)){
                        playerFile.set(key+".amount",bankAccount.getAmount());
                        bankInfo.getAccounts().remove(bankAccount);
                    }
                }
            }
        }
        for (BankAccount bankAccount:bankInfo.getAccounts()){
            playerFile.set("accounts."+bankAccount.getName()+".amount",bankAccount.getAmount());
        }
        try {
            playerFile.save(getDataFileForPlayer(offlinePlayer.getUniqueId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadPlayerAccount(OfflinePlayer offlinePlayer){

    }
    @Override
    public BankInfo getPlayerAccounts(OfflinePlayer player) {
        //todo STILL SHITTY CODE BUT NOT AS SHITTY
        if (bankInfoCache.containsKey(player.getUniqueId())){
            return bankInfoCache.get(player.getUniqueId());
        }else{
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(getDataFileForPlayer(player.getUniqueId()));
            bankInfoCache.put(player.getUniqueId(),loadAccountFromSec(configuration.getConfigurationSection("accounts"),player));
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
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        bankInfoCache.remove(e.getPlayer().getUniqueId());
    }

}
