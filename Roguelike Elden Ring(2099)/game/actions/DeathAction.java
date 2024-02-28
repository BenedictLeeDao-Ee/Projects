package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.trade.WalletManager;
import game.actors.Player;
import game.actors.enemies.Enemy;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn, Benedict Lee Dao-Ee
 *
 */
public class DeathAction extends Action {
    /**
     * The actor that is currently attacking
     */
    private Actor attacker;
    /**
     * Used to display how many runes the enemy drop when killed by the player
     */
    private String runeDrop = "";
    /**
     * Used to transfer runes from killed enemies to the player's wallet
     */
    private int moneyToDrop = 0;

    /**
     * Constructor
     *
     * @param actor The actor that is attacking
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * Constructor
     */
    public DeathAction() {}

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        Player player = WalletManager.getInstance().getPlayer();

        if (attacker == player) { // Checks if the attacker is the player
            Enemy enemyToBeRemoved = null;
            for (Enemy enemy : WalletManager.getInstance().getEnemies()) {
                if (enemy == target) {
                    moneyToDrop = enemy.getRunesToDrop();
                    enemyToBeRemoved = enemy;
                }
            }
            WalletManager.getInstance().removeEnemies(enemyToBeRemoved);
            player.getPlayerWallet().addWalletBalance(moneyToDrop);
            runeDrop = target + " transfers " + moneyToDrop + " runes.";
        }

        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : target.getItemInventory())
            if (item.getDropAction(target) != null) {
                dropActions.add(item.getDropAction(target));
            }
        // drop all weapons except player
        if(target != player){
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
        }
        for (Action drop : dropActions)
            drop.execute(target, map);

        if (target != player) {
            // remove actor
            map.removeActor(target);
        }
        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    /**
     * A message that says which actor is killed
     * @param actor The actor performing the action.
     * @return A message that says which actor is killed
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.\n" + runeDrop;
    }
}
