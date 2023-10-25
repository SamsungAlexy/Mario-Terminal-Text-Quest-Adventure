package game.tree;

import edu.monash.fit2099.engine.positions.Location;
import game.coin.Coin;

public class Sapling extends Tree {

    /**
     * Constructor for Sapling
     *
     */
    public Sapling() {
        // call super constructor with the displayChar
        super('t', 80, 20);
    }
    private int turns = 0; // no. of turns this tree have existed

//  tick is called every turn

    /**
     * Sapling can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

//      number of turns increases by 1 every turn
        turns +=1;

//      Turns into Mature after 10 turns
        if(turns==10){
            location.setGround(new Mature());
        }

        double chance = Math.random();

//        spawn coin every 10 turns
        if(chance<=0.1){
            location.addItem(new Coin(location));

        }
    }

    @Override
    public String toString() {
        return "Sapling";
    }
}
