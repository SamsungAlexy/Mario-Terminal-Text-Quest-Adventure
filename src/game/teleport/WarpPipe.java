package game.teleport;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemy.PiranhaPlant;

public class WarpPipe extends Ground{
    /**
     * Constructor.
     *
     */

    private boolean spawnPiranhaPlant = true;

    /**
     * Constructor for WarpPipe
     */
    public WarpPipe() {
        super('C');
    }

    /**
     *  implement impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
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

//      if the actor wanting to jump is the actor on top( not enemy) provide jump action
        if(location.getActor() == actor){
            allowedActions.add(new TeleportAction(location));
        }

        return allowedActions;
    }

    /**
     * WarpPipe can also experience the joy of time.
     * @param location The location of the WarpPipe
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(spawnPiranhaPlant && !location.containsAnActor()){
            location.addActor(new PiranhaPlant());
            spawnPiranhaPlant = false;
        }
    }


    /**
     * WarpPipe toString method
     * @return  the WarpPipe String
     */
    @Override
    public String toString() {
        return "WarpPipe";
    }
}
