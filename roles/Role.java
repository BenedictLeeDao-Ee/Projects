package game.roles;

import edu.monash.fit2099.engine.weapons.WeaponItem;


/**
 * The Role interface represents a combat archetype that a player can choose in the game.
 * It defines the methods that must be implemented by any class that represents a role.
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Alyssa Ting Sue-Lyn
 */
public interface Role {

    /**
     * Set the starting hit points of the role
     * @return the starting hit points of the role
     */
    int hitPoints();

    /**
     * Set the starting weapon of the role
     * @return the starting weapon of the role
     */
    WeaponItem weapon();
}
