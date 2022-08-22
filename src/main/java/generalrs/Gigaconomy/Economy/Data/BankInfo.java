package generalrs.Gigaconomy.Economy.Data;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;

public class BankInfo {
    public final OfflinePlayer owner;
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public BankInfo(ArrayList<BankAccount> accounts, OfflinePlayer owner) {
        this.owner = owner;
        this.accounts = accounts;
    }

    public BankAccount getAccount(int index){
        return accounts.get(index);
    }
}
