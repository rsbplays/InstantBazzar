package generalrs.Gigaconomy;

import generalrs.Gigaconomy.Commands.BalenceCmd;

import generalrs.Gigaconomy.Commands.CommandHandler;
import generalrs.Gigaconomy.Commands.TransferCmd;
import generalrs.Gigaconomy.Data.PersistantData;
import generalrs.Gigaconomy.Data.YamlDataSaver;
import generalrs.Gigaconomy.Economy.GConemyVault;
import generalrs.Gigaconomy.Economy.PlayerEvents;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.IOException;

public final class Gigaconomy extends JavaPlugin {

    public static Gigaconomy instance;
    public static GConemyVault gConemy;
    public static PersistantData dataHandler;
    public static CommandHandler commandHandler;

    static {
        try {
            dataHandler = new YamlDataSaver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override

    public void onEnable() {
        saveDefaultConfig();

        gConemy = new GConemyVault();
        setupEconomy();
        instance = this;
        //Setup the custom command handler
        commandHandler = new CommandHandler();

        // plugin command registries
        commandHandler.registerCommand("balance","Gets your current balence",new BalenceCmd());
        commandHandler.registerCommand("transfer","transfers money out of your account and into another",new TransferCmd());

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
        getServer().getServicesManager().register(Economy.class, gConemy,this, ServicePriority.Highest);

        
        return false;
    }
    

}
