package generalrs.Gigaconomy.Economy.Data;

import generalrs.Gigaconomy.Gigaconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
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

    public EconomyResponse transferMoney(double Amount, OfflinePlayer recipient){
        BankAccount bankAccount = Gigaconomy.dataHandler.getPlayerAccounts(recipient).getAccount(0);
        if (Amount>0&&amount>Amount) {
            bankAccount.setAmount(bankAccount.getAmount()+Amount);
            amount-=Amount;
            return new EconomyResponse(amount,bankAccount.amount, EconomyResponse.ResponseType.SUCCESS,null);
        }else{
           return new EconomyResponse(0, bankAccount.amount, EconomyResponse.ResponseType.FAILURE,null);
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
