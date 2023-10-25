package game.coin;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;


public class Coin extends Item implements Resettable {
    private int value;

    private Location location;

    /***
     * Coin Constructor.
     *
     * @param location the location of this Coin
     *
     */
    public Coin(Location location) {
//      coin descriptions
        super("Coin", '$', false);

        this.location = location;

//      each coin has value of 20
        this.value=20;

//      add coin action to list of available actions to receive money on coin pickup
        addAction(new PickUpCoinAction(this));

        this.registerInstance();

    }
    /***
     * Coin Constructor with value.
     *
     * @param location the location of this Coin
     * @param value the value of this Coin
     */
    public Coin(Location location, int value) {
//      coin descriptions
        super("Coin", '$', false);

        this.location = location;

//      each coin has value of value that is given in constructor
        this.value=value;

//      add coin action to list of available actions to receive money on coin pickup
        addAction(new PickUpCoinAction(this));

    }


//  get value of coin
    /**
     * Gets value of coin.
     *

     */
    public int getValue() {
        return value;
    }

    /**
     * Reset Instance and remove coin.
     *
     */
    @Override
    public void resetInstance() {
        location.removeItem(this);
    }

    /**
     * Register Instance as resettable.
     *
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
