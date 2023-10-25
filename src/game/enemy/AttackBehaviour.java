package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Behaviour;
import game.Status;

/**
 * A class that is used to figure out whether there is an enemy to attack.
 */
public class AttackBehaviour implements Behaviour {

    // TODO: develop and use it to attack the player automatically.

    /**
     * Returns an attack action if player is in enemy's exit, else return null
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an attack action if possible, null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ActionList actions = new ActionList();

        Location npcPosition = map.locationOf(actor);

        for (Exit exit : npcPosition.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                if(target.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    actions.add(target.allowableActions(actor, "near", map));
                    if(!actor.hasCapability(Status.CANNOT_FOLLOW_PLAYER)) {
                        Enemy enemy = (Enemy) actor;                // a bad practice type casting here
                        enemy.getBehaviours().put(8, new FollowBehaviour(target));      // once enemy found a player, it will attack and follow him
                    }
                }
            }
        }
        if(actions.size() > 0)
            return actions.get(0);
        else
            return null;
    }
}
