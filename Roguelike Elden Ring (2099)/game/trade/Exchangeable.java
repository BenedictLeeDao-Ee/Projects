package game.trade;

import edu.monash.fit2099.engine.items.Item;

/**
 * An interface for items or weapons that can be exchanged
 * Created by:
 * @author Alyssa Ting Sue-Lyn
 */
public interface Exchangeable {

    /**
     * Returns itself of type Exchangeable
     * @return itself of type Exchangeable
     */
    Exchangeable exchangeItself();

    /**
     * Returns itself of type Item
     * @return itself of type Item
     */
    Item exchangeItem();
}
