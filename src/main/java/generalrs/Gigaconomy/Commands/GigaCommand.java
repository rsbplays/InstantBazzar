package generalrs.Gigaconomy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GigaCommand extends Command {
    CmdExecutor commandExecutor;

    protected GigaCommand(String name) {
        super(name);
    }

    protected GigaCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }
    public void setCommandExecutor(CmdExecutor executor){
        commandExecutor=executor;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return commandExecutor.onCommand(commandLabel,sender,args);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return commandExecutor.tabComplete(sender,alias,args);
    }
}
