package game.coin;

public class WalletManager {

    private static WalletManager instance = new WalletManager();

//  Amount in wallet
    private int amount;

//  Wallet Manager constructor, initialise wallet amount to 0
    /**
     * Constructor for WalletManager.
     *
     */
    private WalletManager(){
        this.amount=1200;

    }

//  Top up wallet with Amount
    /**
     * Increases the wallet balance.
     *
     * @param value The amount to be topped up.
     */
    public void topUpWallet(int value){
        this.amount+= value;
    }
    /**
     * Deduct the wallet balance.
     *
     * @param value The amount to be deducted.
     */
//  spend money, deduct money
    public void spendMoney(int value){this.amount -=value;}

    /**
     *
     * @return instance, an instance of WalletManager
     */
    public static WalletManager getInstance(){
        return instance;
    }
    /**
     * Returns amount of money in wallet
     *
     */
    public int getAmount() {
        return amount;
    }
}
