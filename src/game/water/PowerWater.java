package game.water;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;

public class PowerWater extends Item implements MagicalWater{
    /***
     * Constructor for HealingWater.

     */
    public PowerWater() {
        super("Power Water", 'a', false);
    }

    /**
     * gives actor effects of magical water
     * @param actor The actor receiving the effect.

     */
    @Override
    public void giveActorEffect(Actor actor) {
        actor.addCapability(Status.CONSUMED_POWER_WATER);
    }
}
