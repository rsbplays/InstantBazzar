package generalrs.Gigaconomy.Commands;

import generalrs.Gigaconomy.Economy.Data.Languages;
import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BalenceCmd implements CmdExecutor {
    @Override
    public boolean onCommand(String label,CommandSender sender,  String[] args) {
        if (sender instanceof Player){
            sender.sendMessage(Languages.parsePlaceHolder(Languages.getString("command.balance.message"),"amount",String.valueOf(Gigaconomy.dataHandler.getPlayerAccounts((OfflinePlayer) sender).getAccount(0).getAmount())));
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return null;
    }


}
