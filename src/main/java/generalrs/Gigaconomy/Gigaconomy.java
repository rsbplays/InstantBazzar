package generalrs.Gigaconomy;

import generalrs.Gigaconomy.Commands.BalenceCmd;
import generalrs.Gigaconomy.Commands.CommandHandler;
import generalrs.Gigaconomy.Commands.TransferCmd;
import generalrs.Gigaconomy.Data.PersistantData;
import generalrs.Gigaconomy.Data.YamlDataSaver;
import generalrs.Gigaconomy.Economy.Data.Languages;
import generalrs.Gigaconomy.Economy.GConemyVault;
import generalrs.Gigaconomy.Economy.Gconemy;
import generalrs.Gigaconomy.Economy.PlayerEvents;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Gigaconomy extends JavaPlugin {

    public static Gigaconomy instance;
    public static GConemyVault gConemyVault;
    public static Gconemy economy;
    public static PersistantData dataHandler;
    public static CommandHandler commandHandler;
    public static Languages languages;

    static {
        try {
            dataHandler = new YamlDataSaver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        languages = new Languages();
        gConemyVault = new GConemyVault();
        Gconemy gconemy = new Gconemy();
        setupEconomy();
        //Setup the custom command handler
        commandHandler = new CommandHandler();
        commandHandler.register();
        // plugin command registries
        commandHandler.registerCommand("balance","",Languages.getString("command.balance.description"),new BalenceCmd());
        commandHandler.registerCommand("transfer","",Languages.getString("command.transfer.description"),new TransferCmd());

        //Event registries
        getServer().getPluginManager().registerEvents(new BasicEvent(),this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(),this);
        

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getLogger().severe("Vault not Installed");
            return false;

        }
        getServer().getServicesManager().register(Economy.class, gConemyVault,this, ServicePriority.Highest);


        return false;
    }
    public PluginCommand lgetCommand(String name){
        return getCommand(name);
    }

}
