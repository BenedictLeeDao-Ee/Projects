package game.grounds.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.GiantCrab;
import game.actors.enemies.HeavySkeletalSwordsman;
import game.actors.enemies.LoneWolf;
import game.utils.Utils;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class WestMapEnemyFactory extends EnemyFactory {
    /**
     * Constructor for the enemy factory
     */
    public WestMapEnemyFactory() {
        HashMap<Character, Actor> enemyInstance = new HashMap<>(){{
            put('n', new HeavySkeletalSwordsman());
            put('&', new LoneWolf());
            put('~', new GiantCrab());
        }};
        HashMap<Character, Integer> enemySpawnChance = new HashMap<>(){{
            put('n',27);
            put('&',33);
            put('~',2);
        }};

        this.setEnemySpawnChance(enemySpawnChance);
        this.setEnemyInstance(enemyInstance);
    }

    /**
     * To return an enemy to be spawned at a certain location at west
     * @param location The location where the enemy will be spawning
     * @param displayChar The display character of the ground
     * @return An actor which will the enemy to be spawned on the location if granted; null otherwise
     */
    public Actor spawnEnemy(Location location, Character displayChar){
        try {
            Actor actorToAdd = getEnemyInstance().get(displayChar);
            Class<?> cls = actorToAdd.getClass();
            Constructor<?> constructor;
            constructor = cls.getConstructor();
            int spawnChance = getEnemySpawnChance().get(displayChar);

            if(Utils.getRandomInt(100)< spawnChance && !location.containsAnActor()){
                return (Actor) constructor.newInstance();
            }
            else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
