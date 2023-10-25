package game.water;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class HealingWater extends Item implements MagicalWater {

    private int healAmount;


    /***
     * Constructor for HealingWater.

     */
    public HealingWater() {
        super("Healing Water", 'h', false);
        this.healAmount = 50;

    }

    /**
     * gives actor effects of magical water
     * @param actor The actor receiving the effect.

     */
    @Override
    public void giveActorEffect(Actor actor) {
        actor.heal(healAmount);
    }
}
