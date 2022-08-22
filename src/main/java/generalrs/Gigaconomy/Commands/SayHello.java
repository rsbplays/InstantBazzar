package generalrs.Gigaconomy.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SayHello implements CommandExecutor {
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

// /say
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length>0) {
            sender.sendMessage(args[0]);
        }
        return false;

    }
}

