package game.fire;

import edu.monash.fit2099.engine.items.Item;

public class FireFlower extends Item {
    /***
     * Constructor for FireFlower.

     */
    public FireFlower() {
        super("Fire Flower", 'f', false);
        addAction(new ConsumeFireFlowerAction(this));
    }
}
