package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.behaviours.Behaviour;
import game.grounds.environments.SiteOfLostGrace;
import game.items.RemembranceOfTheGrafted;
import game.status.Status;
import game.trade.WalletManager;
import game.utils.FancyMessage;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;
import game.weapons.GameWeapon;

/**
 * BEHOLD, GODRICK!
 *
 * Created by:
 * @author Chnag Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 *
 */
public class GodrickTheGrafted extends Enemy {

    /**
     * The display character of GodrickTheGrafted
     */
    private static final char DISPLAY_CHAR = 'Y';
    /**
     * The hit point of GodrickTheGrafted
     */
    private static final int HIT_POINT = 6080;
    /**
     * Weapon that Godrick the Grafted can hold
     */
    private GameWeapon axeOfGodrick = new AxeOfGodrick();
    /**
     * Weapon that Godrick the Grafted can hold
     */
    private GameWeapon graftedDragon = new GraftedDragon();
    /**
     * Initial location of Godrick the Grafted
     */
    private Location initialLocation;
    /**
     * Unique instance of Godrick the Grafted
     */
    private static GodrickTheGrafted instance;

    /**
     * Constructor.
     */
    public GodrickTheGrafted() {
        super("Godrick the Grafted", DISPLAY_CHAR, HIT_POINT, 20000);
        addWeaponToInventory(axeOfGodrick);
        addWeaponToInventory(graftedDragon);
    }

    /**
     * Change weapon when health is less than half and if the game resets, reset the hp and move this Godrick the Grafted back to its initial position
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (initialLocation == null) {
            initialLocation = map.locationOf(this);
        }
        changeWeapon();
        if (this.hasCapability(Status.RESETTING)) { // actors despawn when game has been reset
            this.removeCapability(Status.RESETTING);
            this.resetMaxHp(this.getMaxHp());
            Location here = map.locationOf(this);
            Exit initialPos = new Exit("Initial",map.at(initialLocation.x(), initialLocation.y()),"");
            here.addExit(initialPos);

            for (Exit exit : here.getExits()) {
                if(exit.getName().equals("Initial")){
                    if (map.locationOf(this) != exit.getDestination()) {
                        map.moveActor(this, exit.getDestination());
                    }
                }
            }
            here.removeExit(initialPos);
        }

        if (!isConscious()) {
            removeWeaponFromInventory(graftedDragon);
            addItemToInventory(new RemembranceOfTheGrafted());

            WalletManager.getInstance().getPlayer().getPlayerWallet().addWalletBalance(getRunesToDrop());
            display.println(this + " transfers " + getRunesToDrop() + " runes");
            map.at(map.locationOf(this).x(), map.locationOf(this).y()).setGround(new SiteOfLostGrace("Godrick the Grafted"));

            for (String line : FancyMessage.DEMIGOD_FELLED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            return new DeathAction();
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Changes weapon when health is less than half
     */
    public void changeWeapon() {
        if (this.hitPoints <= getMaxHp() / 2) {
            removeWeaponFromInventory(axeOfGodrick);
            new Display().println(this + " switched their weapon to " + graftedDragon);
        }
    }

    /**
     * A factory method that creates a single instance of GodrickTheGrafted
     * @return a unique instance of GodrickTheGrafted
     */
    public static GodrickTheGrafted getInstance() {
        if (instance == null) {
            instance = new GodrickTheGrafted();
        }
        return instance;
    }
}
