package generalrs.Gigaconomy.Commands;

import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Gigaconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class TransferCmd implements CmdExecutor {
    public String usage= command.transferhelp.description;

    @Override
    public boolean onCommand(String label,CommandSender sender,  String[] args) {
        if (sender instanceof Player) {
            if (args.length<2){
                sender.sendMessage("TYPE MORE YOU BUFFOON \n"+usage);
            }
            Player player = (Player) sender;
            BankAccount bankAccount = Gigaconomy.dataHandler.getPlayerAccounts(player).getAccount(0);
            Player recipient = Bukkit.getPlayer(args[0]);
            if (recipient==null){
                sender.sendMessage("Invalid player\n"+usage);
                return false;
            }

            int amount;
            if(args[1].matches("[0-9]+")){
                amount=Integer.parseInt(args[1]);
            }else{
                sender.sendMessage("Not Valid number you absoulute moron\n"+usage);
                return false;
            }
            EconomyResponse response = bankAccount.transferMoney(amount,Bukkit.getOfflinePlayer(recipient.getUniqueId()));
            if(response.transactionSuccess()){
                recipient.sendMessage(ChatColor.translateAlternateColorCodes('&',"&2You have recieved $"+amount+" from "+((Player) sender).getDisplayName()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&2You have transferred $"+amount+" to "+recipient.getDisplayName()));
            }else {
                sender.sendMessage(ChatColor.RED+"You do not have enough money for this transaction, You are missing "+ChatColor.GREEN+"$"+amount);
            }
        }
        return true;
    }
}
