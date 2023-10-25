package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.Wrench;

import java.util.Random;

/**
 * Special action for breaking koopa's shell
 */
public class BreakAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack.
     * @param direction the direction of the target to break its shell
     */
    public BreakAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform the Break Action.
     *
     * @param actor The actor performing the break action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Weapon weapon = actor.getWeapon();          // get the first weapon

        // if there are more than one weapon and wrench is not the first, loop through inventory and find the item that can break koopa shell
        for(Item actorItem : actor.getInventory()) {
            if (actorItem.hasCapability(Status.BREAK_SHELL)) {
                weapon = actorItem.asWeapon();
                break;
            }
        }
        if (rand.nextInt(100) > weapon.chanceToHit())
            result = actor + " misses " + target + ".";

        else {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item targetItem : target.getInventory())
                dropActions.add(targetItem.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result = target + " is killed and it dropped a super mushroom.";
        }
        return result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the break action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " breaks " + target + "'s shell at " + direction + ".";
    }
}
