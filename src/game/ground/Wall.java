package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.jump.JumpAction;
import game.jump.Jumpable;
import game.Status;
import game.coin.Coin;

public class Wall extends Ground implements Jumpable {

	/**
	 * The jump success rate of this ground
	 */
	private int jumpSuccessRate;

	/**
	 * The fall damage of this ground
	 */
	private int fallDamage;

	// whether ground is destroyed;
	private boolean destroyed;

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
		setJumpSuccessRate(80);
		setFallDamage(20);
		this.destroyed = false;
	}


	/**
	 * impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.POWER_STAR_EFFECT) || actor.hasCapability(Status.FLY_CAPABLE);
	}

	/**
	 * Wall can also experience the joy of time.
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		Actor currentActor= location.getActor();
//        destroy ground if actor exist and actor has PowerStar
		if(currentActor!=null && currentActor.hasCapability(Status.POWER_STAR_DESTROY_GROUND)){
//		    if ground is destroyed, it becomes dirt  (from actor with powerUp stepping on i)
			this.destroyed = true;
			location.setGround(new Dirt());
			location.addItem(new Coin(location,5));   // convert to coin when ground destroy
		}
	}

	/**
	 * Returns an action list
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a list of actions actor can do to this instance
	 */
	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		ActionList actions = new ActionList();

		// actor needs to be Jump capable, location no actor, and no PowerStar buff (if got PowerStar buff, actor can just walk(see canActorEnter))
		if(actor.hasCapability(Status.JUMP_CAPABLE) && !location.containsAnActor() && !actor.hasCapability(Status.POWER_STAR_EFFECT)){
			actions.add(new JumpAction(this, location, direction));
		}
		return actions;
	}

	/**
	 * Returns true
	 *
	 * @return boolean value true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * Set this wall jump success rate
	 * @param successRate integer
	 */
	@Override
	public void setJumpSuccessRate(int successRate) {
		this.jumpSuccessRate = successRate;
	}

	/**
	 * Set this wall fall damage
	 * @param fallDamage integer
	 */
	@Override
	public void setFallDamage(int fallDamage) {
		this.fallDamage = fallDamage;
	}

	/**
	 * Returns jump success rate
	 * @return integer value of success rate
	 */
	@Override
	public int getJumpSuccessRate() {
		return this.jumpSuccessRate;
	}

	/**
	 * Returns fall damage
	 * @return integer value of fall damage
	 */
	@Override
	public int getFallDamage() {
		return this.fallDamage;
	}


	@Override
	public String toString() {
		return "Wall";
	}
}
