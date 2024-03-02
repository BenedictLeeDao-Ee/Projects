package game.status;

/**
 * Use this enum class to give `buff` or `debuff`. It is also useful to give a `state` to abilities
 * or actions that can be attached-detached.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Chang Yee Vern, Alyssa Ting Sue-Lyn, Benedict Lee Dao-Ee
 */
public enum Status {
    /**
     * Use this status to be considered hostile towards enemy
     */
    HOSTILE_TO_ENEMY,
    /**
     * Use this if enemy is following the Player
     */
    FOLLOWING,
    /**
     * Use this status to be considered hostile towards Bones enemy
     */
    HOSTILE_TO_BONES,
    /**
     * Use this status to be considered hostile towards Canine enemy
     */
    HOSTILE_TO_CANINE,
    /**
     * Use this status to be considered hostile towards Crustacean enemy
     */
    HOSTILE_TO_CRUSTACEAN,
    /**
     * Use this status when a Bones enemy is defeated
     */
    PILE_OF_BONES,
    /**
     * Use this status if Weapon has AOE attack
     */
    HAS_AREA_ATTACK,
    /**
     * Use this status to tell that current instance is resetting.
     */
    RESETTING,
    /**
     * Use this status if Weapon has range attack
     */
    HAS_RANGE_ATTACK,
    /**
     * Use this status to be considered hostile towards Castle enemy
     */
    HOSTILE_TO_CASTLE,
    /**
     * Use this status to be considered hostile towards Invaders enemy
     */
    HOSTILE_TO_INVADER,
    /**
     * Use this if the ground is able to travel
     */
    TRAVELLABLE,
    /**
     * Use this status to be considered hostile to the player
     */
    HOSTILE_TO_PLAYER
}
