package game.fire;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Fire extends Item {


    private int turns;

    /***
     * Constructor for Fire

     */
    public Fire() {
        super("Fire", 'v', false);
        turns = 0;
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        Display display = new Display();
        turns += 1;

//        remove fire after 3 turns
        if (turns == 3) {
            currentLocation.removeItem(this);
        }

//        fire deals 20 damage per turn
        if (currentLocation.containsAnActor()) {
            Actor currentActor = currentLocation.getActor();

//            hurt if no fire no power star
            if(!currentActor.hasCapability(Status.POWER_STAR_EFFECT)){
                currentActor.hurt(20);
                display.println("Fire burns " + currentActor + " for 20 damage");

            }
            if (!currentActor.isConscious()) {
                currentLocation.map().removeActor(currentActor);
                display.println(currentActor + " get burned to death.");
            }
        }
    }
}
