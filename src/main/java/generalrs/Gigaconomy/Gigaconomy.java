package generalrs.Gigaconomy;

import generalrs.Gigaconomy.Commands.SayHello;

import generalrs.Gigaconomy.Commands.TransferCmd;
import generalrs.Gigaconomy.Data.PersistantData;
import generalrs.Gigaconomy.Data.YamlDataSaver;
import generalrs.Gigaconomy.Economy.GConemyVault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Gigaconomy extends JavaPlugin {
    public static SayHello sayHello = new SayHello(500);
    public static Gigaconomy instance;
    public static GConemyVault gConemy;
    public static PersistantData dataHandler;

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
        // plugin command registries
        getCommand("say").setExecutor(sayHello);
        //Transfer Command
        this.getCommand("transfer").setExecutor(new TransferCmd());

        //Event registries
        getServer().getPluginManager().registerEvents(new BasicEvent(),this);
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
