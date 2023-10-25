package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Behaviour;
import game.actions.SuicideAction;

/**
 * A class used by Goomba to self-destruct or by enemy when player resets the game.
 */
public class SuicideBehaviour implements Behaviour {

    /**
     * The actor that is going to suicide.
     */
    private Actor target;

    public SuicideBehaviour(Actor actor){
        this.target = actor;
    }
    /**
     * Returns a new suicide action
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a new SuicideAction if successful, null otherwise
     */

    @Override
    public Action getAction(Actor actor, GameMap map) {
        double chance = Math.random();
//		10% chance to suicide
        return (chance <= 0.1)? new SuicideAction(target) : null;
    }
}
