package game.grounds.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An Environment in Elden Ring that can spawn Crustacean Enemy
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 */
public class PuddleOfWater extends Environment {

    /**
     * The display character of PuddleOfWater
     */
    private static final char DISPLAY_CHAR = '~';

    /**
     * Constructor.
     *
     */
    public PuddleOfWater() {
        super(DISPLAY_CHAR);
    }

    /**
     * Performs the tick action for the PuddleOfWater environment.
     * Determines the map length and spawns enemies accordingly.
     * @param location the location of the PuddleOfWater
     */
    @Override
    public void tick(Location location) {
        int mapLen = location.map().getXRange().max() + 1;

        if (location.x() >= mapLen/2){
            if(getFactory()==null){
                this.setFactory(new EastMapEnemyFactory());
            }
            this.spawn(location);

        }
        else if (location.x() < mapLen/2){
            if(getFactory()==null){
                this.setFactory(new WestMapEnemyFactory());
            }
            this.spawn(location);
        }
    }

    /**
     * Spawns an enemy at the specified location.
     * @param location the location to spawn the enemy
     */
    @Override
    public void spawn(Location location) {
        Actor enemy = getFactory().spawnEnemy(location, this.getDisplayChar());
        if(enemy != null){
            location.addActor(enemy);
        }
    }
}
