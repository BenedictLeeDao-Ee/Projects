package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.status.Status;
import game.trade.WalletManager;

/**
 * An Environment in Elden Ring that the Player will die immediately once step
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Benedict Lee Dao-Ee
 */
public class Cliff extends Ground {

    /**
     * The display character of Cliff
     */
    private static final char DISPLAY_CHAR = '+';

    /**
     * Constructor.
     *
     */
    public Cliff() {
        super(DISPLAY_CHAR);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
    }

    /**
     * Checks if there is an actor, if there is then the actor dies
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            Player player = WalletManager.getInstance().getPlayer();
            player.notConscious();
        }
    }

    /**
     * Checks to see if the actor is a player to see
     * if it can enter the ground or not
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}
