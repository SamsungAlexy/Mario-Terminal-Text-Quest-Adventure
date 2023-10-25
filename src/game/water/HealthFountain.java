package game.water;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.teleport.TeleportAction;

public class HealthFountain extends Ground {


    /**
     * Constructor for Health Fountain.
     *
     */
    public HealthFountain() {
        super('H');

    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList allowedActions = new ActionList();

        if(location.getActor() == actor){
            allowedActions.add(new RefillBottleAction(new HealingWater()));
        }


        return allowedActions;
    }
}
