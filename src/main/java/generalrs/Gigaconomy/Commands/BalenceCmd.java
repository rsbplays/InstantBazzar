package generalrs.Gigaconomy.Commands;

import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalenceCmd implements CmdExecutor {
    @Override
    public boolean onCommand(String label,CommandSender sender,  String[] args) {
        if (sender instanceof Player){
            sender.sendMessage(String.valueOf(Gigaconomy.dataHandler.getPlayerAccounts((OfflinePlayer) sender).getAccount(0).getAmount()));
        }
        return false;
    }


}
