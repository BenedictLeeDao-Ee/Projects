package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.reset.ResetAction;
import game.items.FlaskOfCrimsonTears;
import game.items.Runes;
import game.reset.Resettable;
import game.roles.Role;
import game.status.Status;
import game.trade.Wallet;
import game.trade.WalletManager;
import game.utils.FancyMessage;
import game.utils.Utils;
import game.weapons.GameWeapon;
import game.weapons.RangeWeaponManager;

import java.util.List;

import static java.lang.Math.min;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn, Benedict Lee Dao-Ee
 *
 */
public class Player extends Actor implements Resettable {

	/**
	 * Player's display character
	 */
	private static final char DISPLAY_CHAR = '@';

	private final Menu menu = new Menu();

	/**
	 * Player's wallet
	 */
	private final Wallet playerWallet;

	/**
	 * Player's Location
	 */
	private PlayerLocation playerLocation;

	/**
	 * Is player at the starting location or not
	 */
	private boolean isFirstLocation = true;

	/**
	 * Constructor.
	 *
	 * @param role the starting role that is to be assigned to Player
	 */
	public Player(Role role) {
		super("Tarnished", DISPLAY_CHAR, role.hitPoints());
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addItemToInventory(new FlaskOfCrimsonTears());
		this.addWeaponToInventory(role.weapon());
		this.playerWallet = new Wallet();
		WalletManager.getInstance().setPlayer(this);
		registerInstance();
	}

	/**
	 * At each turn, select a valid action to perform and also check if player is conscious, if so reset it
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the valid action that can be performed in that iteration
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (hasCapability(Status.HAS_RANGE_ATTACK)) {
			for (Weapon weapon: this.getWeaponInventory()) {
				GameWeapon gw = RangeWeaponManager.getInstance().getRangeWeapon(weapon);
				if (gw == null) {
					continue;
				}
				Location here = map.locationOf(this);
				List<int[]> tiles = Utils.getRangeWeaponTiles(gw, here);

				for (int[] coordinates: tiles) {
					int x = coordinates[0], y = coordinates[1];
					Location location = map.at(x, y);
					if (location.containsAnActor() && !location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
						Actor e = map.getActorAt(location);
						actions.add(new AttackAction(e, "(" + x +", " + y + ")", gw));
					}
				}
			}
		}

		// To get the player's previous location to drop the runes
		if (!isFirstLocation) {  // if Player's location is not his starting location
			playerLocation.updateLocation(map.locationOf(this).x(), map.locationOf(this).y());
		} else {  // player at starting location
			playerLocation = PlayerLocation.getInstance(map.locationOf(this).x(), map.locationOf(this).y());
			isFirstLocation = false;
		}

		// Checks if the player is conscious
		if (!isConscious()) {
			// Prints YOU DIED
			for (String line : FancyMessage.YOU_DIED.split("\n")) {
				new Display().println(line);
				try {
					Thread.sleep(200);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

			Runes rune = new Runes(getPlayerWallet().getBalance());
			// Add runes that have value equivalent to the current wallet balance
			addItemToInventory(rune);
			// Make the wallet empty
			getPlayerWallet().deductWalletBalance(getPlayerWallet().getBalance());

			new ResetAction().execute(this, map);
			return new DeathAction();
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		printStatus(display, map.locationOf(this));
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * When the game resets, reset the player's hp back to full
	 */
	@Override
	public void reset() {
		this.resetMaxHp(this.getMaxHp());
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 * By default, the Player 'punches' for 11 damage.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches");
	}

	private void printStatus(Display display, Location location) {
		display.println(String.format("%s's HP: %s at (%d, %d), runes: %d", name, printHp(), location.x(), location.y(), getPlayerWallet().getBalance()));
	}

	/**
	 * Gets the player's wallet
	 * @return the player's wallet
	 */
	public Wallet getPlayerWallet() {
		return this.playerWallet;
	}

	/**
	 * Gets player's previous x coordinate
	 * @return player's previous x coordinate
	 */
	public int getPlayerPrevX() {
		return playerLocation.getPrevX();
	}

	/**
	 * Gets player's previous y coordinate
	 * @return player's previous y coordinate
	 */
	public int getPlayerPrevY() {
		return playerLocation.getPrevY();
	}

	/**
	 * Kills the player
	 */
	public void notConscious() {
		this.hitPoints=0;
	}
}
