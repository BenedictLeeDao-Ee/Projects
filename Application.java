package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.enemies.GodrickTheGrafted;
import game.actors.traders.FingerReaderEnia;
import game.actors.traders.GatekeeperGostoc;
import game.actors.traders.MerchantKale;
import game.actors.Player;
import game.grounds.*;
import game.grounds.environments.*;
import game.items.ScatterRunes;
import game.roles.Role;
import game.roles.RoleManager;
import game.utils.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn, Benedict Lee Dao-Ee
 *
 */
public class Application {

	public static void main(String[] args) {

		Display display = new Display();
		World world = new World(display);

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
				new Graveyard(), new GustOfWind(), new PuddleOfWater(), new Cliff(),
				new SummonSign(),new GoldenFogDoor());

		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................=...............................................",
				"........++++......................###___###................................",
				"........+++++++...................________#................................",
				"..........+++.....................#________................................",
				"............+++...................#_______#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+...................=........................................",
				"..............++.................................................=.........",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");

		GameMap gameMap = new GameMap(groundFactory, limgrave);
		world.addGameMap(gameMap);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		new Display().print("\n");

		// Add player
		Role role = null;
		while (role == null) {
			char selection = RoleManager.menuItem(display);
			role = RoleManager.selectCombatArchetypes(selection);
			if (role == null)
				display.println("Invalid selection. Please choose 's', 'b', 'w', or 'a'.");
		}
		Actor player = new Player(role);
		world.addPlayer(player, gameMap.at(36, 10));

		// Add merchant
		MerchantKale merchant = new MerchantKale();
		gameMap.at(40, 12).addActor(merchant);

		// Add Site of Lost Grace (The First Step)
		SiteOfLostGrace site1 = new SiteOfLostGrace("The First Step", true);
		gameMap.at(38, 11).setGround(site1);
		site1.setTravelLocation(site1.getName(), gameMap.at(38,11));

		// Add Door from Limgrave to Stormveil
		GoldenFogDoor LimgraveToStormveil = new GoldenFogDoor();
		gameMap.at(30,0).setGround(LimgraveToStormveil);

		// Add Door from Limgrave to Roundtable Hold
		GoldenFogDoor LimgraveToRoundtable = new GoldenFogDoor();
		gameMap.at(5,23).setGround(LimgraveToRoundtable);

		// Scatter runes
		ScatterRunes.getInstance(gameMap).scatterRunes();


		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
				new Cliff(),new Graveyard(), new GustOfWind(), new PuddleOfWater(), new Cage(), new Barrack(),
				new GoldenFogDoor());

		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

		GameMap gameMap2 = new GameMap(groundFactory2, stormveilCastle);
		world.addGameMap(gameMap2);

		// Add Site of Lost Grace (Stormveil Main Gate)
		SiteOfLostGrace site2 = new SiteOfLostGrace("Stormveil Main Gate");
		gameMap2.at(38,20).setGround(site2);

		// Add Door from Stormveil Castle to Limgrave
		GoldenFogDoor StormveilToLimgrave = new GoldenFogDoor();
		gameMap2.at(38,23).setGround(StormveilToLimgrave);

		// Add Door from Stormveil Castle to Boss Room
		GoldenFogDoor StormveilToBoss = new GoldenFogDoor();
		gameMap2.at(5,0).setGround(StormveilToBoss);

		// Scatter runes
		ScatterRunes.getInstance(gameMap2).scatterRunes();

		// Add merchant
		GatekeeperGostoc gatekeeperGostoc = new GatekeeperGostoc();
		gameMap2.at(3, 15).addActor(gatekeeperGostoc);


		FancyGroundFactory groundFactory3 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new GoldenFogDoor());

		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");

		GameMap gameMap3 = new GameMap(groundFactory3, roundtableHold);
		world.addGameMap(gameMap3);

		// Add merchant
		FingerReaderEnia fingerReaderEnia = new FingerReaderEnia();
		gameMap3.at(8, 0).addActor(fingerReaderEnia);

		// Add Site of Lost Grace (Table of Lost Grace)
		SiteOfLostGrace site3 = new SiteOfLostGrace("Table of Lost Grace");
		gameMap3.at(9, 5).setGround(site3);

		// Add Door from Roundtable Hold to Limgrave
		GoldenFogDoor RoundtableToLimgrave = new GoldenFogDoor();
		gameMap3.at(9,10).setGround(RoundtableToLimgrave);


		FancyGroundFactory groundFactory4 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
				new Cliff(), new SummonSign());

		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");

		GameMap gameMap4 = new GameMap(groundFactory4, bossRoom);
		world.addGameMap(gameMap4);

		// Add Boss
		gameMap4.at(0, 5).addActor(GodrickTheGrafted.getInstance());

		// Set the destination of the door and the name of the destination map
		LimgraveToStormveil.setTeleportLocations(gameMap,gameMap2.at(38,23));
		LimgraveToStormveil.setMapName(gameMap.at(30,0),"Stormveil Castle");

		LimgraveToRoundtable.setTeleportLocations(gameMap,gameMap3.at(9,10));
		LimgraveToRoundtable.setMapName(gameMap.at(5,23),"Roundtable Hold");

		StormveilToLimgrave.setTeleportLocations(gameMap2,gameMap.at(30,0));
		StormveilToLimgrave.setMapName(gameMap2.at(38,23),"Limgrave");

		StormveilToBoss.setTeleportLocations(gameMap2,gameMap4.at(0,	4));
		StormveilToBoss.setMapName(gameMap2.at(5,0),"Boss Room");

		RoundtableToLimgrave.setTeleportLocations(gameMap3,gameMap.at(5,23));
		RoundtableToLimgrave.setMapName(gameMap3.at(9,10),"Limgrave");

		world.run();
	}
}
