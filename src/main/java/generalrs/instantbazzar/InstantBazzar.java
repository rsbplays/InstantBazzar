package generalrs.instantbazzar;

import generalrs.instantbazzar.Commands.SayHello;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class InstantBazzar extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("test 2");
        System.out.println("test 1");
        SayHello sayHello = new SayHello(500);
        sayHello.sayHello();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
