package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem {
    /**
     * Constructor.
     *

     */
    public Wrench() {
//        80% hit rate, 50 damage
        super("Wrench", 'W', 50, "hits", 80);
        this.addCapability(Status.BREAK_SHELL);
    }
}
