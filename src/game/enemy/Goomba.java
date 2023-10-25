package game.enemy;


import game.Monologue;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.SuicideAction;


/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.getBehaviours().put(4, new SuicideBehaviour(this));
		this.getBehaviours().put(10, new WanderBehaviour());

		this.addLineToMonologue("Mugga mugga!");
		this.addLineToMonologue("Ugha ugha... (Never gonna run around and desert you...)");
		this.addLineToMonologue("Ooga-Chaka Ooga-Ooga!");
	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		return super.allowableActions(otherActor, direction, map);
	}

	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// if enemy become resettable they perform suicide action/remove themselves from map
		if(this.hasCapability(Status.RESET_NOW)){
			return new SuicideAction(this);
		} else
			return super.playTurn(actions, lastAction, map, display);
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kicks");
	}

}
