package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.coin.WalletManager;
import game.reset.ResetAction;
import game.reset.Resettable;
import game.water.Bottle;
import game.water.MagicalWater;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private WalletManager walletManager;
	private int powerStarRemainingTurns;
	private int fireAttackRemainingTurns;
	private Bottle bottle;
	private int intrinsicDamage;



	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints,GameMap mapOne, GameMap mapTwo) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.walletManager = WalletManager.getInstance();
		this.addCapability(Status.JUMP_CAPABLE);
		this.addCapability(Status.ACCESS_TO_FLOOR);
		powerStarRemainingTurns = 0;
		this.addCapability(Status.RESET_NOW);
		this.registerInstance();

		this.addCapability(Status.ACCESS_TO_LAVA);

//		bottle permanent
		this.bottle = new Bottle();
		this.addItemToInventory(bottle);



//		initialise intrinsic daamge
		this.intrinsicDamage = 5;

//		initialise fire attack effect remaining turns
		this.fireAttackRemainingTurns = 0;
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

//		print current wallet amount
		display.println("Wallet Amount: $ "+getWalletAmount());

//		print max HP
		display.println("HP: "+ this.printHp());

//		hanlde power star related matters
		handlePowerStar(display);


//		handle power water related matters
		handlePowerWater();

//		handle fire attack related matters
		handleFireAttack(display);

		display.println( "Base Damage: " + this.intrinsicDamage);

		// player has yet to use reset action, give player this action
		if(this.hasCapability(Status.RESET_NOW))
			actions.add(new ResetAction());

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Get the current display character of player.
	 * @return a char representing mario.
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.SUPER_MUSHROOM_EFFECT)? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

//top  up wallet
	public void topUpWallet(int amount){
		this.walletManager.topUpWallet(amount);
	}

	public int getWalletAmount() {
		return walletManager.getAmount();
	}

	/**
	 * Returns a new collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER))
			actions.add(new AttackAction(this, direction));

		return actions;
	}

//	function to handle power star status
	/**
	 * function to handle power star status
	 * @param display    the I/O object to which messages may be written
	 */
	private void handlePowerStar(Display display){

//		powerStar remaining  turns -1 ever turn;
		if (powerStarRemainingTurns>=1) powerStarRemainingTurns-=1;

//		print turns remaining for power star
		if(powerStarRemainingTurns>=0  && this.hasCapability(Status.POWER_STAR_DESTROY_GROUND)){
			if(powerStarRemainingTurns !=0){
				if(!this.hasCapability(Status.CONSUMED_POWER_STAR)){
					display.println("Power Star -  " +powerStarRemainingTurns+" turns remaining" );

				}
			}else{
				display.println("Power Star -  Expired" );
			}
		}

//		if power star expires, remove ability to destroy ground
		if (!this.hasCapability(Status.POWER_STAR_EFFECT)) this.removeCapability(Status.POWER_STAR_DESTROY_GROUND);


//		if powerStar expires, remove Power Star effect.
		if(powerStarRemainingTurns ==1) this.removeCapability(Status.POWER_STAR_EFFECT);

//		set powerStar to increase 10 turns, if it just obtained PowerStar buff
		if ( this.hasCapability(Status.CONSUMED_POWER_STAR)){
			// increase 10 turns of power star effect
			powerStarRemainingTurns +=10;

			// can destroy ground now
			this.addCapability(Status.POWER_STAR_DESTROY_GROUND);

			display.println("Power Star -  " +powerStarRemainingTurns+" rounds remaining" );

//			remove the status of recently consumed
			this.removeCapability(Status.CONSUMED_POWER_STAR);

		}
	}


// power water consumption
	/**
	 * function to handle power water functionality
	 */
	private void handlePowerWater(){
		if(this.hasCapability(Status.CONSUMED_POWER_WATER)){
			this.removeCapability(Status.CONSUMED_POWER_WATER);
			this.increaseIntrinsicDamage();
		}
	}

//	function to handle fire attack status
	/**
	 * function to handle fire attack  status
	 * @param display    the I/O object to which messages may be written
	 */
	private void handleFireAttack(Display display){

//		fire attack remaining  turns -1 ever turn;
		if (fireAttackRemainingTurns>=1) fireAttackRemainingTurns-=1;



//		print turns remaining for fire attack
		if(fireAttackRemainingTurns>=0  && this.hasCapability(Status.FIRE_ATTACK_EFFECT)){
			if(fireAttackRemainingTurns !=0){
				if(!this.hasCapability(Status.CONSUMED_FIRE_FLOWER)) {
					display.println("Fire Attack -  " +fireAttackRemainingTurns+" turns remaining" );

				}

			}else{
				if(!this.hasCapability(Status.CONSUMED_FIRE_FLOWER))display.println("Fire attack -  Expired" );
			}
		}

//		if fire attack expires, remove fire attack effect.
		if(fireAttackRemainingTurns ==0) this.removeCapability(Status.FIRE_ATTACK_EFFECT);


//		set fire attack to increase 20 turns, if it just obtained fire attack buff
		if ( this.hasCapability(Status.CONSUMED_FIRE_FLOWER)){
			// increase 20 turns of power star effect
			fireAttackRemainingTurns +=20;

			this.addCapability(Status.FIRE_ATTACK_EFFECT);

			display.println("Fire Attack -  " +fireAttackRemainingTurns+" turns remaining" );

//			remove the status of recently consumed
			this.removeCapability(Status.CONSUMED_FIRE_FLOWER);

		}


	}



	/**
	 * Reset player's heal.
	 * Remove power star and super mushroom effect.
	 * Action to reset the game removed when player triggered this once.
	 */
	@Override
	public void resetInstance() {
		this.resetMaxHp(this.getMaxHp());
		this.removeCapability(Status.POWER_STAR_EFFECT);
		this.removeCapability(Status.POWER_STAR_DESTROY_GROUND);
		this.removeCapability(Status.SUPER_MUSHROOM_EFFECT);
		this.removeCapability(Status.RESET_NOW);
	}

	/**
	 * Register this instance to the Singleton manager to be affected by global reset
	 */
	@Override
	public void registerInstance() {
		Resettable.super.registerInstance();
	}


//	for consuming water
	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * By default, the Actor 'punches' for 5 damage. Override this method to create
	 * an Actor with more interesting descriptions and/or different damage.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(this.intrinsicDamage, "punches");
	}

	/**
	 * Function to increase base damage
	 */
	public void increaseIntrinsicDamage(){
		this.intrinsicDamage+=15;
	}


	/**
	 * Function to add water to bottle
	 */
	public void addWaterToBottle(MagicalWater water) {
		this.bottle.addToBottle(water);
	}
}
