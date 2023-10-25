package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.actions.AttackAction;
import game.Behaviour;
import game.reset.Resettable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class representing enemy
 */
public abstract class Enemy extends Actor implements Resettable{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private Monologue monologue = new Monologue();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(6, new AttackBehaviour());
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.registerInstance();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor.
     * @param map        current GameMap.
     * @return list of actions.
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // if the enemy is conscious and speak() does not return null, then display will print the line
        if(this.isConscious()){
            String line = this.monologue.speak();
            if(line!=null)
                display.println(this + line);
        }

        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Returns a mapping of behaviours that enemy can use to figure out its next action
     * @return a map of Integer and Behaviour
     */
    public Map<Integer, Behaviour> getBehaviours() { return this.behaviours; }

    /**
     * Add a status to tell all enemies they should reset themselves now.
     * The actual implementation/what they should do is in the Enemy's playTurn method
     */
    @Override
    public void resetInstance() {
         this.addCapability(Status.RESET_NOW);
    }

    /**
     * Register this instance to the Singleton manager to be affected by global reset
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }

    /**
     * Add a string to the Monologue instance
     * @param line a string
     */
    public void addLineToMonologue(String line){
        this.monologue.addLine(line);
    }
}
