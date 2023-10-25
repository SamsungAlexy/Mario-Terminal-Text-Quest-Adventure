package game.jump;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import java.util.Random;

public class JumpAction extends Action {

    /**
     * The ground that is to be jumped
     */
    private Jumpable target;

    /**
     * The location of the target
     */
    private Location location;

    /**
     * The direction of incoming jump
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target The ground to jump
     * @param location The location of the ground
     * @param direction The direction of target from actor performing this jump action
     */
    public JumpAction(Jumpable target, Location location, String direction){
        this.target = target;
        this.location = location;
        this.direction = direction;
    }


    /**
     * Perform the Jump Action.
     * If actor jump successfully, move actor to the ground's location.
     * Otherwise, actor remains on original position and receives fall damage.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return return a string description of the jump action outcome.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // actor fall and takes damage, depending on their jump success rate, and NOT on SUPER MUSHROOM effect
        if(rand.nextInt(100) < (100-target.getJumpSuccessRate()) && !actor.hasCapability(Status.SUPER_MUSHROOM_EFFECT)){
//          Jump Failure
            actor.hurt(target.getFallDamage());
            return actor + " did not jump properly" + " and received " + target.getFallDamage() + " damage";
        }
        else{
//          Jump successful
            new MoveActorAction(location, direction).execute(actor, map);
            return actor + " jumped onto " + target;
        }
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + target + " at " + direction;
    }
}
