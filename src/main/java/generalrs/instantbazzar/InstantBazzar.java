package generalrs.instantbazzar;

import generalrs.instantbazzar.Commands.SayHello;
import org.bukkit.plugin.java.JavaPlugin;

public final class InstantBazzar extends JavaPlugin {
    public static SayHello sayHello = new SayHello(500);
    public static InstantBazzar instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        System.out.println("test 2");
        System.out.println("test 1");

        System.out.println("jh;lfsadh lfkdaslhnbyckluafdsbitckjasdihtd,fcrklyjdzsmgulkvcgnjyasdfuiojhfrydlkjzjycrfadlzyjgfclikzjynflkdszgyfvgkasjyngflvtadsjyoifejadsc8oloc;fu");
        getServer().getPluginManager().registerEvents(new BasicEvent(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
