package game.tree;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.coin.Coin;
import game.fire.FireFlower;
import game.ground.Dirt;
import game.jump.JumpAction;
import game.jump.Jumpable;
import game.reset.Resettable;

public abstract class Tree extends Ground implements Jumpable, Resettable {

    /**
     * The success rate for jump action
     */
    private int jumpSuccessRate;

    /**
     * The fall damage
     */
    private int fallDamage;

    // whether ground is destroyed;
    private boolean destroyed;

    /**
     * Constructor for Tree
     *
     */

//    Constructor for tree, takes in displayChar for Sapling,Sprout,Mature
//    LXY > since atm each tree is jumpable I put the variable under tree
//    LXY >> added 2 parameters for tree constructor
    /**
     * Constructor for Tree
     *
     * @param displayChar character to display for this type of terrain
     * @param jumpSuccessRate success rate of jump performed
     * @param fallDamage damage to be taken when fall
     */
    public Tree(char displayChar, int jumpSuccessRate, int fallDamage) {
        super(displayChar);
        setJumpSuccessRate(jumpSuccessRate);
        setFallDamage(fallDamage);
        this.registerInstance();
    }

//    LXY >> actor now cannot freely enter tree object
    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.POWER_STAR_EFFECT) || actor.hasCapability(Status.FLY_CAPABLE);
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
        ActionList actions = new ActionList();
        // actor needs to be Jump capable, location no actor, and no PowerStar buff (if got PowerStar buff, actor can just walk(see canActorEnter))
        if(actor.hasCapability(Status.JUMP_CAPABLE) && !location.containsAnActor() && !actor.hasCapability(Status.POWER_STAR_EFFECT)){
            actions.add(new JumpAction(this, location, direction));
        }
        return actions;
    }

    /**
     * Tree can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        // if tree becomes resettable, has a chance to convert back to dirt
        double resetToDirt = Math.random();
        if (this.hasCapability(Status.RESET_NOW)) {
            if(resetToDirt <= 0.5)
                location.setGround(new Dirt());
//          since tree has a chance to remain, needs to remove this status even if tree does not convert back to dirt
            this.removeCapability(Status.RESET_NOW);
        } else {
            Actor currentActor = location.getActor();
//        destroy ground if actor exist and actor has PowerStar
            if (currentActor != null && currentActor.hasCapability(Status.POWER_STAR_DESTROY_GROUND)) {
//		    if ground is destroyed, it becomes dirt  (from actor with powerUp stepping on i)
                this.destroyed = true;
                location.setGround(new Dirt());
                location.addItem(new Coin(location, 5));   // convert to coin when ground destroy
            }
        }

//        50% chance to spawn fire flower
        if (Math.random()<=0.5){
            location.addItem(new FireFlower());
        }
    }

    /**
     * Set this tree jump success rate
     * @param successRate integer
     */
    @Override
    public void setJumpSuccessRate(int successRate) {
        this.jumpSuccessRate = successRate;
    }

    /**
     * Set this tree fall damage
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

    /**
     * Add a status to tell all trees they should reset themselves now.
     * The actual implementation/what they should do is in the Tree's playTurn method
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET_NOW);
    }

    /**
     * Register this instance to the Singleton manager to be affected by global reset
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
