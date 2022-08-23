package generalrs.Gigaconomy.Commands;

import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class TransferCmd implements CommandExecutor {
    public String usage= "/transfer [player] [amount]\nThis will send money from your main bank account to another player's";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
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
            bankAccount.transferMoney(amount,Bukkit.getOfflinePlayer(recipient.getUniqueId()));

            sender.sendMessage( " Transfer of £ " +amount+ " was completed ");
        }
        return true;
    }
}
