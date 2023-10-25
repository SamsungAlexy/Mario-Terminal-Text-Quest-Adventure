package game.magical;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface MagicalItem {
    /**
     * performs consumption of magical item by actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on .
     */
    void consumeByActor(Actor actor, GameMap map);
}
