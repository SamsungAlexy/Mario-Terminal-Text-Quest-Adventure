package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special action for actor that can remove themselves from the map
 */
public class SuicideAction extends Action {

    /**
     * The actor performing this action
     */
    private Actor actor;

    /**
     * Constructor
     * @param actor the actor to suicide
     */
    public SuicideAction(Actor actor) { this.actor = actor; }

    /**
     * Perform the suicide Action.
     *
     * @param actor The actor performing the suicide action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the suicide action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " suicided";
    }
}
