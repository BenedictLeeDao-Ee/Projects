package game.grounds.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import java.util.HashMap;
import java.util.Map;

public abstract class EnemyFactory {
    /**
     * A map of enemy Instance that is paired with the character of it's spawning ground
     */
    private Map<Character, Actor> enemyInstance;

    /**
     * A hashmap of the spawn chance of an enemy that is paired with the character of it's spawning ground
     */
    private HashMap<Character,Integer> enemySpawnChance;

    /**
     * To return the hashmap of the Character Enemy instance pair
     * @return A hashmap of the Character Enemy instance pair
     */
    public Map<Character, Actor> getEnemyInstance() {
        return enemyInstance;
    }

    /**
     * To set the hashmap of the Character Enemy instance pair
     * @param enemyInstance the hashmap of the Character Enemy instance pair
     */
    public void setEnemyInstance(Map<Character, Actor> enemyInstance) {
        this.enemyInstance = enemyInstance;
    }

    /**
     * To return the hashmap of the Character enemy spawn chance pair
     * @return the hashmap of the Character enemy spawn chance pair
     */
    public HashMap<Character, Integer> getEnemySpawnChance() {
        return enemySpawnChance;
    }

    /**
     * To set the hashmap of the Character enemy spawn chance pair
     * @param enemySpawnChance the hashmap of the Character enemy spawn chance pair
     */
    public void setEnemySpawnChance(HashMap<Character, Integer> enemySpawnChance) {
        this.enemySpawnChance = enemySpawnChance;
    }

    /**
     * An abstract method to return an enemy to be spawned at a certain location
     * @param location The location where the enemy will be spawning
     * @param displayChar The display character of the ground
     * @return An actor which will the enemy to be spawned on the location if granted; null otherwise
     */
    public abstract Actor spawnEnemy(Location location, Character displayChar);
}
