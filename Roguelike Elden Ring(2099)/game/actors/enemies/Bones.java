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
import game.status.Status;
import game.utils.Utils;

import java.util.List;

/**
 * BEHOLD, BONE!
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn, Benedict Lee Dao-Ee
 *
 */
public abstract class Bones extends Enemy {
    /**
     * The number of turns for Bone type actor to be respawned
     */
    private int turnsToSkeleton = 3;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param runes       the amount of runes that the actor will drop when killed
     */
    public Bones(String name, char displayChar, int hitPoints, int runes) {
        super(name, displayChar, hitPoints, runes);
        this.addCapability(Status.HOSTILE_TO_CANINE);
        this.addCapability(Status.HOSTILE_TO_CRUSTACEAN);
        this.addCapability(Status.HOSTILE_TO_CASTLE);
        this.addCapability(Status.HOSTILE_TO_INVADER);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration
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
        if (hasCapability(Status.PILE_OF_BONES)) {  // countdown and check if is a pile of bones
            turnsToSkeleton -= 1;
            if (turnsToSkeleton < 0) {  // initialize back to respawn conditions, hp, behaviors etc. and remove capability
                resetMaxHp(maxHitPoints);
                super.behaviours.put(999, new WanderBehaviour());
                this.removeCapability(Status.PILE_OF_BONES);
                turnsToSkeleton = 3;
            }
        } else {
            for (Behaviour behaviour : super.behaviours.values()) {
                Action action = behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * The Bones can be attacked by any actor that has the HOSTILE_TO_ENEMY or HOSTILE_TO_BONES capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions that an attacking Actor can perform on this Bones object
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        List<Weapon> weapons = Utils.getActorWeapon(otherActor);
        Weapon w = Utils.getSingleWeapon(otherActor);
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction, null));  // add intrinsic weapon
            for (Weapon weapon : weapons) {
                actions.add(new AttackAction(this, direction, weapon));  // telling player that he can attack this(lone wolf)
                if (weapon != null) {
                    actions.add(weapon.getSkill(this, direction));  // telling player that he can attack this(lone wolf)
                }
                if (!hasCapability(Status.PILE_OF_BONES)) {  // pile of bones cannot have these behaviours
                    super.behaviours.put(1, new AttackBehaviour(otherActor));
                    super.behaviours.put(5, new FollowBehaviour(otherActor));
                }
                this.addCapability(Status.FOLLOWING);
            }
        } else if (otherActor.hasCapability(Status.HOSTILE_TO_BONES)) {
            super.behaviours.put(1, new AttackBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * If Bones is unconscious,it will turn into Pile of Bones
     * @param points number of hitpoints to deduct.
     */
    public void hurt(int points) {
        super.hurt(points);
        if (!isConscious() && !hasCapability(Status.PILE_OF_BONES)) {
            Display display = new Display();
            display.println(super.name + " turns to a Pile of Bones!");
            hitPoints = 1;
            super.behaviours.clear();
            addCapability(Status.PILE_OF_BONES);
            addCapability(Status.HOSTILE_TO_CANINE);
            addCapability(Status.HOSTILE_TO_CRUSTACEAN);
        }
    }

    /**
     * Replace current name to Pile of Bones if it has the PILE_OF_BONES capability
     * @return current actor's name
     */
    @Override
    public String toString() {
        return hasCapability(Status.PILE_OF_BONES) ? "Pile of Bones" : super.toString();
    }

    /**
     * Replace current display character to X if it has the PILE_OF_BONES capability
     * @return current actor's display character
     */
    public char getDisplayChar() {
        return hasCapability(Status.PILE_OF_BONES) ? 'X' : super.getDisplayChar();
    }

}
