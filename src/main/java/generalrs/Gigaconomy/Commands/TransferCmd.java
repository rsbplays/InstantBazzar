package generalrs.Gigaconomy.Commands;

import generalrs.Gigaconomy.Economy.Data.BankAccount;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TransferCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            public static TransferCmd transferCmd = new TransferCmd()
            sender.sendMessage( " Transfer of £ " +amount+ " was completed ");
        }
        return true;
    }
}
