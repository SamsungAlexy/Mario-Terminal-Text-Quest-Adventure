package game.trading;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.coin.WalletManager;

public class BuyItemAction extends Action {
    private final Item item;
    private final int itemPrice;

    /**
     * Constructor for BuyItemAction
     *
     * @param item The Item to be purchased
     * @param itemPrice The price of Item to be purchased
     */
    public BuyItemAction(Item item, int itemPrice) {
        this.item = item;
        this.itemPrice = itemPrice;
    }

    /**
     * Perform the BuyItemAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

//      if enough money in wallet, buy item and add to inventory
        if (WalletManager.getInstance().getAmount()>=itemPrice){
            actor.addItemToInventory(item);
            WalletManager.getInstance().spendMoney(itemPrice);
            return menuDescription(actor);
        }

//      not enough money
        return "You Don't have enough coins !";



    }


    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor + " buys " +item + " ($"+itemPrice+")" ;
    }
}
