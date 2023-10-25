package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.fire.Fire;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack.
	 * @param direction the direction of target from actor performing this action
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Perform the Attack Action.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

//		when actor misses the target
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

//		if actor has power star, instantly kill target, else, just deal weapon damage
		int damage = actor.hasCapability(Status.POWER_STAR_EFFECT)? Integer.MAX_VALUE: weapon.damage();

//		target takes no damage, if target has power star effect
		if (target.hasCapability(Status.POWER_STAR_EFFECT))
			damage = 0;

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		if(damage>0){
			target.hurt(damage);
//			remove target's super mushroom effect, when target receive damages
			target.removeCapability(Status.SUPER_MUSHROOM_EFFECT);
		}

//		if actor has fire attack effect, drop fire at target ground
		if(actor.hasCapability(Status.FIRE_ATTACK_EFFECT)){
			map.locationOf(target).addItem(new Fire());
		}

//		target is unconscious && does not hide in shell will die and drop item
		if (!target.isConscious() && !target.hasCapability(Status.HIDE_IN_SHELL)) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the attack action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		String ret = actor + " attacks " + target + " at " + direction;
		if(actor.hasCapability(Status.FIRE_ATTACK_EFFECT))
			ret += " with fire";
		return ret;
	}
}
