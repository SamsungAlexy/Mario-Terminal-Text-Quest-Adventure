package game.tree;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.enemy.FlyingKoopa;
import game.ground.Dirt;
import game.Status;
import game.enemy.Koopa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mature extends Tree {
    private int turns = 0;  // no. of turns this tree have existed

    /**
     * Constructor for Mature
     *
     */
    public Mature() {
        super('T', 70, 30);
    }

//  tick is called every turn
    /**
     * Mature can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

//      number of turns increases by 1 every turn
        turns += 1;

        double chance = Math.random();

//      15% chance to spawn Koopa in every turn if no actor stands on it
        if (chance<=0.15 && !location.containsAnActor()){
            double spawnKoopa = Math.random();
            location.addActor(spawnKoopa<0.5? new Koopa() : new FlyingKoopa());
        }


//      Every 5 turns, Grow a new sprout
        if (turns % 5 == 0) {

//          Get surroundings
            List<Exit> surroundings = location.getExits();

//          convert to array list for shuffling
            ArrayList<Exit> newSurroundings = new ArrayList<Exit>(surroundings);

//          shuffling for randomness as stated in requirements (since for loop always finds the first eligible place to spawn)
            Collections.shuffle(newSurroundings);




//          for each exit in surroundings
            for (Exit exit : newSurroundings) {

//              (no actor stands on it ) and  (ground is dirt(fertile)) then spawn sprout
                if (!exit.getDestination().containsAnActor() &&  exit.getDestination().getGround().hasCapability(Status.FERTILE)) {

//                  spawn new sprout
                    exit.getDestination().setGround(new Sprout());

//                  only spawn in 1 exit, instead of all exit that exists in surroundings
                    break;
                }

            }
        }


//      20% chance to wither and become dirt
        if(chance <=0.2){
            location.setGround(new Dirt());
        }
    }

    @Override
    public String toString() {
        return "Mature";
    }
}
