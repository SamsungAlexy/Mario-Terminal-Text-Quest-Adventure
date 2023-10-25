package game.magical;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class PowerStar extends Item implements MagicalItem {

//   no. of turns this power star existed (either in inventory or ground)
    private int turns;

    /**
     * Constructor for PowerStar
     *
     */
    public PowerStar() {
        super("Power Star", '*', true);

//      add action to give user the choice to consume this Power Star
        addAction(new ConsumeMagicalItemAction(this));


    }

//  for trading (REQ 5), Items from Toad can't be dropped
    /**
     * Constructor for PowerStar
     *
     * @param portable whether this PowerStar can be picked up and dropped
     */
    public PowerStar(boolean portable) {
        super("Power Star", '*', portable);

//      add action to give user the choice to consume this Power Star
        addAction(new ConsumeMagicalItemAction(this));


    }


//  tick is called every turn (item on ground)
    /**
     * Inform an PowerStar on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

//      Number of turns increases by 1 every turn
        turns+=1;

//      Fade away from ground after 10 turns
        if (turns==10){
            currentLocation.removeItem(this);
        }



    }
//  tick is called every turn (item on actor's inventory)
    /**
     * Inform a carried PowerStar of the passage of time.
     *
     * This method is called once per turn, if the PowerStar is being carried.
     * @param currentLocation The location of the actor carrying this PowerStar.
     * @param actor The actor carrying this PowerStar.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);

        //      Number of turns increases by 1 every turn
        turns+=1;

//      Fade away from actor's inventory after 10 turns
        if (turns==10){
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * performs consumption of magical item by actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on .
     */
    @Override
    public void consumeByActor(Actor actor, GameMap map) {
        //      remove item from map on consume
        map.locationOf(actor).removeItem(this);

//      for increasing duration of power star effect turns
        actor.addCapability(Status.CONSUMED_POWER_STAR);

//      add Power Star buff capability to actor
        actor.addCapability(Status.POWER_STAR_EFFECT);

//      remove item from inventory on consume
        actor.removeItemFromInventory(this);

    }
}
