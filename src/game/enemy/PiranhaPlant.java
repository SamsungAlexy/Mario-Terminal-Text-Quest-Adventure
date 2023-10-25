package game.enemy;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status;

/**
 * Piranha Plant.
 */
public class PiranhaPlant extends Enemy{
    /**
     * Constructor for Piranha Plant.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.addCapability(Status.CANNOT_FOLLOW_PLAYER);
        this.addCapability(Status.HOSTILE_TO_PLAYER);

        this.addLineToMonologue("Slsstssthshs~! (Never gonna say goodbye~)");
        this.addLineToMonologue("Ohmnom nom nom nom.");
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.RESET_NOW)) {
            this.removeCapability(Status.RESET_NOW);
            this.increaseMaxHp(50);
        }
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
        return new IntrinsicWeapon(90, "chomps");
    }
}
