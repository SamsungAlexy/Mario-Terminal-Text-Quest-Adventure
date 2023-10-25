package game.tree;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.enemy.Goomba;

import java.util.List;

public class Sprout extends Tree {


    private int turns = 0;

    /**
     * Constructor for Sprout
     */
    public Sprout() {
        super('+', 90, 10);
    }

//  tick is called every turn
    /**
     * Sprout can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

//      Number of turns increases by 1 every turn
        turns += 1;

//      After 10 turns, Turns into Sapling
        if (turns == 10) {
            location.setGround(new Sapling());
        }


        double chance = Math.random();

//       10% chance to spawn Goomba and nothing stands on it.
        if (chance <= 0.1 && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
    }

    @Override
    public String toString() {
        return "Sprout";
    }
}
