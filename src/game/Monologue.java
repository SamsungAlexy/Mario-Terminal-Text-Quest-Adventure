package game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Monologue class used by actor to say something in-game.
 */
public class Monologue {
    /**
     * Array of string to store the monologues of an actor.
     */
    private ArrayList<String> monologues = new ArrayList<>();

    /**
     * Number of turns used to signal when to speak.
     */
    private int turns = 0;

    /**
     * Random number generator.
     */
    private Random rand = new Random();

    /**
     * Empty constructor.
     */
    public Monologue(){}

    /**
     * Called in actor's playTurn() to return a string randomly from the monologues.
     * @return a string as monologue.
     */
    public String speak(){
        String ret = null;
        if(this.turns % 2 == 0) {
            int line = rand.nextInt(this.monologues.size());
            ret = ": \"" + this.monologues.get(line) + "\"";
        }
        this.turns += 1;
        return ret;
    }

    /**
     * Add a string as monologue.
     * @param line a string to be added to the array.
     */
    public void addLine(String line){
        this.monologues.add(line);
    }
}
