package game.grounds.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An Environment in Elden Ring that can spawn Bone Enemy
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 */
public class Graveyard extends Environment {

    /**
     * The display character of Graveyard
     */
    private static final char DISPLAY_CHAR = 'n';

    public Graveyard() {
        super(DISPLAY_CHAR);
    }

    /**
     * Performs the tick action for the Graveyard environment.
     * Determines the map length and spawns enemies accordingly.
     * @param location the location of the Graveyard
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
