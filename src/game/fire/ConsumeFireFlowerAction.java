package game.fire;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class ConsumeFireFlowerAction extends Action {

    private FireFlower fireFlower;

    /**
     * Constructor.
     *
     * @param fireFlower the fire flower to consume.
     */
    public ConsumeFireFlowerAction(FireFlower fireFlower) {
        this.fireFlower = fireFlower;
    }


    /**
     * Perform the consume fire flower Action.
     *
     * @param actor The actor performing the consume action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(fireFlower);
        actor.addCapability(Status.CONSUMED_FIRE_FLOWER);
        actor.addCapability(Status.FIRE_ATTACK_EFFECT);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the consumption.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the Fire Flower";
    }
}
