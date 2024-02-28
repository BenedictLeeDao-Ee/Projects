package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;
import game.reset.Resttable;
import game.status.Status;
import game.trade.WalletManager;
import game.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BEHOLD, ENEMY!
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn, Benedict Lee Dao-Ee
 *
 */
public abstract class Enemy extends Actor implements Resettable, Resttable {
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * The amount of runes that the enemy will drop when killed
     */
    private final int runesToDrop;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param runes       the amount of runes that the enemy will drop when killed
     */
    public Enemy(String name, char displayChar, int hitPoints, int runes) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(999, new WanderBehaviour());
        this.runesToDrop = runes;
        registerInstance();
        registerInstanceRest();
        WalletManager.getInstance().addEnemies(this);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESETTING)) { // actors despawn when game has been reset
            this.removeCapability(Status.RESETTING);
            return new DespawnAction();
        }
        if (!this.hasCapability(Status.FOLLOWING) && Utils.getRandomInt(100) <= 10) {  // 10% chance of being de-spawned unless they follow player
            return new DespawnAction();
        }
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions that an attacking Actor can perform on this Enemy object
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        List<Weapon> weapons = Utils.getActorWeapon(otherActor);
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction, null));  // add intrinsic weapon
            for (Weapon weapon : weapons) {
                actions.add(new AttackAction(this, direction, weapon));  // telling player that he can attack this(lone wolf)
                if (weapon != null) {
                    actions.add(weapon.getSkill(this, direction));
                }
            }
            behaviours.put(1, new AttackBehaviour(otherActor));
            behaviours.put(5, new FollowBehaviour(otherActor));
            this.addCapability(Status.FOLLOWING);
        }
        return actions;
    }

    /**
     * Gets the amount of runes that will be dropped by an enemy
     * @return amount of runes to be dropped
     */
    public int getRunesToDrop() {
        return this.runesToDrop;
    }

    /**
     * When the game resets, the actors will gain the capability of resetting
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESETTING);
    }

    /**
     * When the game resets when the Player rests, all Enemy will gain the capability of resetting
     */
    @Override
    public void rest() {
        this.addCapability(Status.RESETTING);
    }
}
