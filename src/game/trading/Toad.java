package game.trading;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.magical.PowerStar;
import game.magical.SuperMushroom;

import java.util.ArrayList;
import java.util.Random;


public class Toad extends Actor{
    private Monologue monologue = new Monologue();
    /**
     * Constructor for Toad
     *
     */
    public Toad() {
        super("Toad", 'O', 9999);
        this.monologue.addLine("You might need a wrench to smash Koopa's hard shells.");
        this.monologue.addLine("You better get back to finding the Power Stars.");
        this.monologue.addLine("The Princess is depending on you! You are our only hope.");
        this.monologue.addLine("Being imprisoned in these walls can drive a fungus crazy :(");
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // if speak() does not return null, then display will print the line
        String line = this.monologue.speak();
        if(line!=null)
            display.println(this + line);

        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList allowedActions = new ActionList();

//      3 actions for actor to buy from Toad
        allowedActions.add(new BuyItemAction(new PowerStar(false),600));
        allowedActions.add(new BuyItemAction(new SuperMushroom(false),400));
        allowedActions.add(new BuyItemAction(new Wrench(),200));


        return allowedActions;
    }
}
