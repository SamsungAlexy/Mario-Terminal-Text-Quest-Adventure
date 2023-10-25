package game.enemy;
import game.Status;

/**
 * Flying Koopa.
 */
public class FlyingKoopa extends Koopa{

    /**
     * Constructor
     */
    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
        this.addCapability(Status.FLY_CAPABLE);
    }
}
