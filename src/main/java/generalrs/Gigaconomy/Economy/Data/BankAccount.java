package generalrs.Gigaconomy.Economy.Data;

import generalrs.Gigaconomy.Gigaconomy;
import org.bukkit.OfflinePlayer;

public class BankAccount {
    private double amount;
    private String name;
    private OfflinePlayer owner;

    public BankAccount(OfflinePlayer owner) {
        this("CheckAccount",owner);
    }

    public BankAccount(String name, OfflinePlayer owner) {
        this.name = name;
        this.owner = owner;
        amount = 0;
    }

    public void transferMoney(double Amount, OfflinePlayer recipient){
        BankAccount bankAccount = Gigaconomy.dataHandler.getPlayerAccounts(recipient).getAccount(0);
        if (Amount>0&&amount>Amount) {
            bankAccount.setAmount(bankAccount.getAmount()+Amount);
            amount-=Amount;
            if (owner.isOnline()){
                owner.getPlayer().sendMessage("You have successfully transferred £"+Amount+" to "+recipient.getName());
            }
        }else{
            owner.getPlayer().sendMessage("NOT ENOUGH MONEY BOZO");
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public OfflinePlayer getOwner() {
        return owner;
    }
}
