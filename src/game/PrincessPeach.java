package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.RescueAction;

/**
 * Class representing PrincessPeach.
 */
public class PrincessPeach extends Actor{
    private Monologue monologue = new Monologue();

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 1);
        this.monologue.addLine("Dear Mario, I'll be waiting for you...");
        this.monologue.addLine("Never gonna give you up!");
        this.monologue.addLine("Release me, or I will kick you!");
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // if speak() does not return null, then display will print the monologue
        String line = this.monologue.speak();
        if(line!=null)
            display.println(this + line);

        return new DoNothingAction();
    }

    /**
     * At the moment, only mario with the key can interact with princess.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor.
     * @param map        current GameMap.
     * @return list of actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        for(Item item : otherActor.getInventory()){
            if(item.hasCapability(Status.FREE_CAPTIVE)) {
                actions.add(new RescueAction(this));
                break;
            }
        }
        return actions;
    }
}
