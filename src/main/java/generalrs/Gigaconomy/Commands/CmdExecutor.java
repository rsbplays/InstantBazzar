package generalrs.Gigaconomy.Commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface CmdExecutor {
    public boolean onCommand(String label, CommandSender sender, String args[]);
    public List<String> tabComplete(CommandSender sender, String alias, String[] args);
}
