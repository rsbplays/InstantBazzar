package generalrs.Gigaconomy.Commands;

import generalrs.Gigaconomy.Economy.Data.BankAccount;
import generalrs.Gigaconomy.Economy.Data.Languages;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransferCmd implements CmdExecutor {
    public String usage = Languages.getString("command.transferhelp.usage");
    @Override
    public boolean onCommand(String label,CommandSender sender,  String[] args) {
        if (sender instanceof Player) {
            if (args.length<2){
                sender.sendMessage(Languages.getString("command.transfer.noArgs")+usage);
                return false;
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
                recipient.sendMessage(Languages.parsePlaceHolder(Languages.parsePlaceHolder(Languages.getString("command.transfer.recipientSuccess"),"amount",String.valueOf(amount)),"playername",player.getDisplayName()));
                sender.sendMessage(Languages.parsePlaceHolder(Languages.parsePlaceHolder(Languages.getString("command.transfer.transferSuccess"),"amount",String.valueOf(amount)),"playername",recipient.getDisplayName()));
            }else {
                sender.sendMessage(Languages.parsePlaceHolder(Languages.getString("command.transfer.recipientFailure"),"missingamount", String.valueOf(amount-bankAccount.getAmount())));
            }
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (args.length==1){
            ArrayList<String> players = new ArrayList<>();
            for (Player player :Bukkit.getOnlinePlayers()) {
                String playername = player.getName();
                players.add(playername);
            }
            Collections.sort(players);
            return players;
        }
        else return null;
    }
}
