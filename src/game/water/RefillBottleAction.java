package game.water;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;

public class RefillBottleAction extends Action {

    private MagicalWater water;

    /**
     * Constructor for RefillBottleAction
     *
     * @param water to be refilled
     */
    public RefillBottleAction(MagicalWater water) {
        this.water = water;
    }


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor instanceof Player){
            ((Player) actor).addWaterToBottle(water);
        }

        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {return actor + " refill "+ water; };

}
