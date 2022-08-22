package generalrs.Gigaconomy.Economy.Data;

import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.OfflinePlayer;

public class BankAccount {
    private double amount;
    private OfflinePlayer owner;

    public void transferMoney(double Amount, OfflinePlayer recipient){
        BankAccount bankAccount = Gigaconomy.dataHandler.getPlayerAccounts(recipient).getAccount(0);
        if (Amount>0&&amount>Amount) {
            bankAccount.setAmount(bankAccount.getAmount()+Amount);
            amount-=Amount;
            if (owner.isOnline()){
                owner.getPlayer().sendMessage("You have successfully transferred £"+Amount+" to "+recipient.getName());
            }
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
