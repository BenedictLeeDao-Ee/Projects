package game.grounds.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.GodrickSoldier;
import game.utils.Utils;

/**
 * An Environment in Elden Ring that can spawn Castle Enemy
 * Created by:
 * @author Chang Yee Vern
 */
public class Barrack extends Ground {

    /**
     * The display character of Barrack
     */
    private static final char DISPLAY_CHAR = 'B';
    /**
     * The chance to spawn GodrickSoldier
     */
    private static final int CHANCE_TO_SPAWN_ENEMY = 45;

    /**
     * Constructor.
     *
     */
    public Barrack() {
        super(DISPLAY_CHAR);
    }

    /**
     * Performs the tick action for the cage environment.
     * Randomly spawns a GodrickSoldier enemy at the location if it is empty.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        int chance = Utils.getRandomInt(100);
        if (chance <= CHANCE_TO_SPAWN_ENEMY && !location.containsAnActor()) {
            location.addActor(new GodrickSoldier());
        }
    }
}
