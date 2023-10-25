package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;


/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	/**
	 * Returns true if actor can enter, false otherwise
	 *
	 * @param actor the Actor to check
	 * @return boolean value (true/false)
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.ACCESS_TO_FLOOR);
	}

	/**
	 * Returns an empty Action list.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new, empty collections of Actions
	 */
	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		return super.allowableActions(actor, location, direction);
	}
}
