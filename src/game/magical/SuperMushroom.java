package game.magical;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.water.MagicalWater;

public class SuperMushroom extends Item implements MagicalItem {

//  heath that actor can gain max HP from consuming super mushroom
    private final int maxHPGain;

    /**
     * Constructor for SuperMushroom
     *
     */

    public SuperMushroom() {
//       superMushroom descriptions
        super("Super Mushroom", '^', true);


//      each mushroom can give the actor who consumed it 50 maxHP
        this.maxHPGain = 50;

//      add action to give user the choice to consume this mushroom
        addAction(new ConsumeMagicalItemAction(this));

    }


//  for trading (REQ 5), Items from Toad cant be dropped
    /**
     * Constructor for SuperMushroom
     *
     * @param portable whether this SuperMushroom can be picked up and dropped.
     */
    public SuperMushroom(boolean portable) {
//       superMushroom descriptions
        super("Super Mushroom", '^', portable);


//      each mushroom can give the actor who consumed it 50 maxHP
        this.maxHPGain = 50;

//      add action to give user the choice to consume this mushroom
        addAction(new ConsumeMagicalItemAction(this));

    }

    /**
     * performs consumption of magical item by actor
     * returns maxHP gain
     */
    public int getMaxHPGain() {
        return maxHPGain;
    }


    /**
     * performs consumption of magical item by actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on .
     */
    @Override
    public void consumeByActor(Actor actor, GameMap map) {
        //      remove item from map on consume
        map.locationOf(actor).removeItem(this);

//      increase maxHP after consuming
        actor.increaseMaxHp(this.getMaxHPGain());

//      add Super mushRoom buff capability to actor
        actor.addCapability(Status.SUPER_MUSHROOM_EFFECT);

//      remove item from inventory on consume
        actor.removeItemFromInventory(this);
    }
}
