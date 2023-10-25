package game.enemy;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.BreakAction;
import game.actions.SuicideAction;
import game.magical.SuperMushroom;

/**
 * Koopa.
 */
public class Koopa extends Enemy {

    private boolean dormant = false;

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.addItemToInventory(new SuperMushroom());
        this.addCapability(Status.HIDE_IN_SHELL);
        this.getBehaviours().put(10, new WanderBehaviour());

        this.addLineToMonologue("Never gonna make you cry!");
        this.addLineToMonologue("Koopi koopi koopii~!");
    }

    /**
     * Another constructor, mainly used by creating flying koopa.
     * @param name the name of the koopa.
     * @param displayChar the character that will represent the Actor in the display.
     * @param hitPoints the flying koopa's starting hit points.
     */
    public Koopa(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addItemToInventory(new SuperMushroom());
        this.addCapability(Status.HIDE_IN_SHELL);
        this.getBehaviours().put(10, new WanderBehaviour());

        this.addLineToMonologue("Never gonna make you cry!");
        this.addLineToMonologue("Koopi koopi koopii~!");
        this.addLineToMonologue("Pam pam pam!");
    }

    /**
     * If koopa is dormant and player has a wrench, then it will return a break action.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if(this.dormant){
            for(Item item : otherActor.getInventory()){
                if(item.hasCapability(Status.BREAK_SHELL))
                    actions.add(new BreakAction(this, direction));
            }
        }
        else
            actions = super.allowableActions(otherActor, direction, map);

        return actions;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious() && !this.dormant) {
            this.setDisplayChar('D');
            this.dormant = true;
            this.getBehaviours().clear();
        }

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
        return new IntrinsicWeapon(30, "punches");
    }

}
