package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Key;
import game.Status;

/**
 * Bowser.
 */
public class Bowser extends Enemy{

    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        this.addItemToInventory(new Key());
        this.addCapability(Status.FIRE_ATTACK_EFFECT);

        this.addLineToMonologue("What was that sound? Oh, just a fire.");
        this.addLineToMonologue("Princess Peach! You are formally invited... to the creation of my new kingdom!");
        this.addLineToMonologue("Never gonna let you down!");
        this.addLineToMonologue("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.RESET_NOW)){
            this.removeCapability(Status.RESET_NOW);
            return new MoveActorAction(map.at(1, 12), "back to its original position");
        } else
            return super.playTurn(actions, lastAction, map, display);
    }

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
        return new IntrinsicWeapon(80, "punches");
    }
}
