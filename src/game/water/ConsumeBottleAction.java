package game.water;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;

public class ConsumeBottleAction extends Action {

    private Bottle bottle;

    /**
     * Constructor for ConsumeBottleAction
     *
     * @param bottle, bottle to be consumed
     */
    public ConsumeBottleAction(Bottle bottle) {
        this.bottle = bottle;

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

        if (this.bottle.getContainer().size()>=1){
            MagicalWater water = this.bottle.popFromBottle();
            water.giveActorEffect(actor);

            return menuDescription(actor);
        }

        return "No water exist";

    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return this.bottle.getContainer().size()>=1? actor + " consumes "+ this.bottle+ " " +this.bottle.getContainer():"No Water in Bottle [Does Nothing]";
    }
}
