package generalrs.Gigaconomy.Economy.Data;

import org.bukkit.OfflinePlayer;


import java.util.ArrayList;

public class BankInfo {
    public final OfflinePlayer owner;
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public BankInfo(OfflinePlayer owner,BankAccount bankAccount) {
        this.accounts = createBankList(bankAccount);
        this.owner = owner;
    }

    public BankInfo(ArrayList<BankAccount> accounts, OfflinePlayer owner) {
        this.owner = owner;
        this.accounts = accounts;
    }

    public BankAccount getAccount(int index){
        return accounts.get(index);
    }
    private ArrayList<BankAccount> createBankList(BankAccount account){
        ArrayList<BankAccount> banks = new ArrayList<>();
        banks.add(account);
        return banks;
    }

    public OfflinePlayer getOwner() {
        return owner;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
}
