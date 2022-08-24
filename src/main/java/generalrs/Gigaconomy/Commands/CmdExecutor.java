package generalrs.Gigaconomy.Commands;

import org.bukkit.command.CommandSender;

public interface CmdExecutor {
    public boolean onCommand(String label, CommandSender sender, String args[]);
}
