package game.water;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

public class Bottle extends Item {

    private ArrayList<MagicalWater> container=new ArrayList<>();
    private ConsumeBottleAction consumeAction = new ConsumeBottleAction(this);
    private boolean actionOnMenu = true;

    /**
     * Constructor for Bottle
     */
    public Bottle() {
        super("Bottle", 'b', false);
        addAction(consumeAction);


    }

    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
//        gives consumption action when there is waters inside bottle

    }

    /**
     * Add water to bottle
     * @param water The water to be added

     */
    public void addToBottle(MagicalWater water){
        container.add(water);
    }

//  pop on consume
    /**
     * Remove a water from bottle when consumed
     *
     * @return water that is consumed
     */
    public MagicalWater popFromBottle(){

        if (container.size()>=1){
            int index = container.size()-1;
            MagicalWater water =  container.remove(index);
            return water;
        }
        return  null;
    }

    /**
     * Remove a water from bottle when consumed
     *
     * @return container , which is the bottle
     */
    public ArrayList<MagicalWater> getContainer() {
        return container;
    }

//    private void provideActionOnMenu(){
//
//        if (this.container.size()>=1 && !actionOnMenu){
//            addAction(consumeAction);
//            actionOnMenu = true;
//        }else if (this.container.size()<=0 && actionOnMenu){
//            removeAction(consumeAction);
//            actionOnMenu = false;
//        }
//    }
}
