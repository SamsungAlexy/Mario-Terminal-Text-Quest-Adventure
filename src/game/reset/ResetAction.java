package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Reset action that can be performed by player
 **/
public class ResetAction extends Action {

    /**
     * Empty constructor.
     */
    public ResetAction(){}

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return actor + " resets the game.";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset game";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @return The key we use for this Action in the menu
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
