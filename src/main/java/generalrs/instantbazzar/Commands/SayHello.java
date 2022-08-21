package generalrs.instantbazzar.Commands;

import org.bukkit.Bukkit;

public class SayHello extends Object{
    public int num = 1;
    static float float1=0.000005F;

    public SayHello(int num) {
        this.num = num;
    }

    public void sayHello(){
        for (int times = 0;times<num;times++) {
            Bukkit.getLogger().info("Hello");
        }
    }

}
