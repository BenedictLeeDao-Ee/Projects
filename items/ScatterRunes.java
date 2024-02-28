package game.items;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.status.Status;
import game.trade.WalletManager;
import game.utils.Utils;

/**
 * Automatically scatters GoldenRunes onto the map
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public class ScatterRunes {
    /**
     * The map to scatter GoldenRunes on
     */
    private GameMap map;
    /**
     * Unique instance of ScatterRunes
     */
    private static ScatterRunes instance;

    /**
     * Constructor
     * @param map map to scatter GoldenRunes on
     */
    private ScatterRunes(GameMap map) {
        this.map = map;
    }

    /**
     * Factory method that creates a single instance of ScatterRunes
     * @param map map to scatter GoldenRunes on
     * @return a unique instance of ScatterRunes
     */
    public static ScatterRunes getInstance(GameMap map) {
        if (instance == null) {
            instance = new ScatterRunes(map);
        }
        return instance;
    }

    /**
     * Scatters the runes on grounds that do not contain an Actor and is not hostile to the player
     */
    public void scatterRunes() {
        NumberRange xs = map.getXRange();
        NumberRange ys = map.getYRange();
        for (int x : xs) {
            for (int y : ys) {
                Location loc = map.at(x, y);
                if (!loc.containsAnActor() && loc.canActorEnter(WalletManager.getInstance().getPlayer())) {
                    if (!loc.getGround().hasCapability(Status.HOSTILE_TO_PLAYER)){
                        if (Utils.getRandomInt(100) <= 2) {
                            loc.addItem(new GoldenRunes());
                        }
                    }
                }
            }
        }
    }
}
