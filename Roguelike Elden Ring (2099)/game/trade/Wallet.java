package game.trade;

/**
 * Class to represent a wallet
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class Wallet {
    /**
     * Balance of the wallet
     */
    private int balance;

    /**
     * Constructor
     */
    public Wallet() {
        this.balance = 0;
    }

    /**
     * Adds runes (money) to the wallet
     * @param addValue the amount to be added
     */
    public void addWalletBalance(int addValue) {
        this.balance += addValue;
    }

    /**
     * Deducts runes (money) from the wallet
     * @param deductValue the amount to be deducted
     */
    public void deductWalletBalance(int deductValue) {
        this.balance -= deductValue;
    }

    /**
     * Gets the wallet's current balance
     * @return The wallet's current balance
     */
    public int getBalance() {
        return this.balance;
    }
}
