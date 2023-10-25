package game.magical;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ConsumeMagicalItemAction extends Action {

    private final MagicalItem magicalItem;

//  constructor for ConsumeSuperMushroomAction
    /**
     * Constructor for ConsumeSuperMushroomAction
     *
     * @param magicalItem The SuperMushroom to be consumed
     */
    public ConsumeMagicalItemAction(MagicalItem magicalItem) {
        this.magicalItem = magicalItem;
    }

//  execution of consuming super mushroom
    /**
     * Perform the Action.
     *
     * @param actor The actor performing the ConsumePowerStarAction.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        magicalItem.consumeByActor(actor,map);



        return menuDescription(actor);
    }

//  display this option to user to consume mushroom
    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the "+magicalItem;
    }
}
