package game.teleport;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;

public class TeleportAction extends Action {

    private Location warpLocation;

    /**
     * Constructor for teleportAction
     * @param location the location to teleport to.
     */
    public TeleportAction(Location location) {
//      current warpLocation
        this.warpLocation = location;
    }


    /**
     * performs teleportation of actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on .
     */
    @Override
    public String execute(Actor actor, GameMap map) {


        // TODO SOMETHING

//      save current warp location before teleporing
        TeleportationManager.getInstance().setWarpLocationOnTeleport(warpLocation);

//      performs teleporting
        TeleportationManager.getInstance().switchMap(actor);

        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return TeleportationManager.getInstance().getNextMap()==2? "Player teleports to Lava Zone":"Player Teleports back to Main Map";
    }
}
