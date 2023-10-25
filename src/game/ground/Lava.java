package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Lava extends Ground {
    /**
     * Constructor.
     *
     */
    public Lava() {
        super('L');
    }


    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
//        can only enter if actor have access to lava
        return actor.hasCapability(Status.ACCESS_TO_LAVA);
    }

    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        if (location.containsAnActor()){
//           -15 per turn to player that is on top
            Actor actor = location.getActor();


//            lava hurts actor if no power star
            if (!actor.hasCapability(Status.POWER_STAR_EFFECT)){
                actor.hurt(15);
            }

            // if player has less than 15 hitpoint when stepped into lava
            if(!actor.isConscious()){
                Display display = new Display();        // not sure about using a new display but we have to report actor's dead
                location.map().removeActor(actor);
                display.println(actor + " stepped in lava and died.");
            }
        }
    }
}
