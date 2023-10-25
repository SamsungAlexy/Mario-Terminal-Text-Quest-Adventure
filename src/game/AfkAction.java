package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class AfkAction extends Action {

    private int turns = 5;

    public AfkAction(){}

    @Override
    public String execute(Actor actor, GameMap map) {
        turns-=1;
        new DoNothingAction().execute(actor, map);
        return actor + " afk -ing";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " goes afk.";
    }

    @Override
    public String hotkey() {
        return "z";
    }

    @Override
    public Action getNextAction() {
        return this.turns>0? this: null;
    }
}
