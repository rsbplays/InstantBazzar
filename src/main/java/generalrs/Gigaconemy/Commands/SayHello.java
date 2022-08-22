package generalrs.Gigaconemy.Commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SayHello extends Object{
    public int num = 1;
    static float float1=0.000005F;

    public SayHello(int num) {
        this.num = num;
    }

    public void sayHelloToPlayer(Player p){
        for (int times = 0;times<num;times++) {
            p.sendMessage("Hello");
        }
    }
    public void sayHello(){
        for (int times = 0;times<num;times++){
            Bukkit.getLogger().info("Hello");
        }
    }

}
