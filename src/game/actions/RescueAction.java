package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class RescueAction extends Action {
    private Actor target;
    /**
     * Constructor.
     *
     * @param actor the Actor to rescue.

     */
    public RescueAction(Actor actor){
        this.target = actor;
    }

    /**
     * Perform the Rescue Action.
     *
     * @param actor The actor performing the Rescue action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " rescued " + target + ".";
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the rescue action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " free " + target + ".";
    }
}
