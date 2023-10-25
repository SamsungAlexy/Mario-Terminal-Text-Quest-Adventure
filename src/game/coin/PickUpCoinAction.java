package game.coin;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class PickUpCoinAction extends Action {
    private final Coin coin;

    /**
     * Constructor for PickUpCoinAction.
     *
     * @param coin The coin to be picked up.
     */
    public PickUpCoinAction(Coin coin) {
        this.coin = coin;
    }


    //    execution of coin action

    /**
     * Perform the PickUpCoin Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

//      remove coin from map
        map.locationOf(actor).removeItem(coin);
//      top up wallet after picking up coin
        WalletManager.getInstance().topUpWallet(coin.getValue());
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the PickUpCoinAction.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
//      display in menu
        return actor + " picks up the " +coin + "($"+coin.getValue()+")" +" and add to wallet " ;
    }
}
