package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.trade.WalletManager;
import game.actors.enemies.Enemy;

/**
 * An Action to despawn an actor
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class DespawnAction extends Action {

    /**
     * When executed, the actor will be removed from the map and the instance stored in the
     * InstanceManage list will be removed as well
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return a message that says an Actor has despawned
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Enemy enemyToBeRemoved = null;
        for (Enemy enemy : WalletManager.getInstance().getEnemies()) {
            if (enemy == actor) {
                enemyToBeRemoved = enemy;
            }
        }
        WalletManager.getInstance().removeEnemies(enemyToBeRemoved);
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Describes which actor has been despawned
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been removed from the map";
    }
}

