package generalrs.Gigaconemy;

import generalrs.Gigaconemy.Commands.SayHello;
import generalrs.Gigaconemy.Econemy.GConemyVault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class Gigaconemy extends JavaPlugin {
    public static SayHello sayHello = new SayHello(500);
    public static Gigaconemy instance;
    public static GConemyVault gConemy;

    @Override
    public void onEnable() {
        gConemy = new GConemyVault();
        setupEconomy();
        instance = this;
        // Plugin startup logic

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
