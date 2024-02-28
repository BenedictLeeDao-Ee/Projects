package game.grounds.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ActivateAction;
import game.actions.FastTravelAction;
import game.reset.RestAction;
import game.grounds.GroundManager;
import game.status.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An Environment in Elden Ring that when a Player stands on it, the game will be reset
 * Created by:
 * @author Chang Yee Vern
 * Modified by: Benedict Lee Dao-Ee
 */
public class SiteOfLostGrace extends Ground {
    private static final char DISPLAY_CHAR = 'U';
    /**
     * The location of the Site Of Lost Grace
     */
    private Location location;
    /**
     * Maps the name of the Site Of Lost Grace to its location
     */
    private Map<String,Location> travelLocation;
    /**
     * Name of the Site Of Lost Grace
     */
    private String name;
    /**
     * Is the Site Of Lost Grace activated
     */
    private boolean isActivated;
    /**
     * Is this Site Of Lost Grace the last one that was visited
     */
    private boolean lastVisited;

    /**
     * Constructor for pre-activated Site Of Lost Grace
     * @param name name of the Site Of Lost Grace
     * @param activated is the Site Of Lost Grace activated
     */
    public SiteOfLostGrace(String name, boolean activated) {
        super(DISPLAY_CHAR);
        this.name = name;
        this.travelLocation = new HashMap<>();
        setActivated(activated);
        GroundManager.getInstance().addSite(this);
        GroundManager.getInstance().addActivatedSite(this);
        this.setTravelLocation(this.name,this.getLocation());
        lastVisited = true;
    }

    /**
     * Constructor for not activated Site Of Lost Grace
     * @param name name of the Site Of Lost Grace
     */
    public SiteOfLostGrace(String name) {
        super(DISPLAY_CHAR);
        this.name = name;
        this.travelLocation = new HashMap<>();
        setActivated(false);
        GroundManager.getInstance().addSite(this);
        lastVisited = false;
    }

    /**
     * Only allow the Player to enter, not the Enemy
     * @param actor the Actor to check
     * @return true if the actor is a Player, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);  // only Player can enter
    }

    /**
     * Sets the location of the Site of Lost Grace
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        setLocation(location);
    }


    /**
     * Shows the actor the actions that it is able to perform
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of actions that the actor can perform
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        List<Exit> exits = location.getExits();
        for(Exit exit:exits){
            if ((location.containsAnActor() || exit.getDestination().containsAnActor()) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                ArrayList<SiteOfLostGrace> sites = GroundManager.getInstance().getActivatedSite();
                if (isActivated()) {
                    actionList.add(new RestAction(this));
                    for(SiteOfLostGrace siteOfLostGrace: sites){
                        if(sites.size()>1 && !siteOfLostGrace.getName().equals(this.getName())){
                            actionList.add(new FastTravelAction(siteOfLostGrace));
                        }
                    }
                    break;
                }
                else if (!isActivated()) {
                    actionList.add(new ActivateAction(this));
                    break;
                }
            }
        }
        return actionList;
    }


    /**
     * Gets the location of the Site Of Lost Grace
     * @return location of the Site Of Lost Grace
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the Site Of Lost Grace
     * @param location location of the Site Of Lost Grace
     */

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the location of the Site Of Lost Grace based on its name
     * @param name name of the Site Of Lost Grace
     * @return location of the Site Of Lost Grace
     */
    public Location getTravelLocation(String name) {
        return travelLocation.get(name);
    }

    /**
     * Sets the location of the Site Of Lost Grace based on its name
     * @param name name of the Site Of Lost Grace
     * @param location location of the Site Of Lost Grace
     */
    public void setTravelLocation(String name,Location location) {
        travelLocation.put(name,location);
    }

    /**
     * Sets the name of the Site Of Lost Grace
     * @param name name of the Site Of Lost Grace
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the Site Of Lost Grace
     * @return name of the Site Of Lost Grace
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Site Of Lost Grace to activated
     * @param activated is the Site Of Lost Grace activated
     */
    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    /**
     * Checks if the Site Of Lost Grace is activated
     * @return if the Site Of Lost Grace is activated or not
     */
    public boolean isActivated() {
        return isActivated;
    }

    /**
     * Checks if the Site Of Lost Grace was the last one visited by the actor
     * @return if the Site Of Lost Grace was the last one visited by the actor or not
     */
    public boolean isLastVisited() {
        return lastVisited;
    }

    /**
     * Sets the Site Of Lost Grace to be the last visited
     * @param lastVisited is the Site Of Lost Grace the last one visited by the actor or not
     */
    public void setLastVisited(boolean lastVisited) {
        this.lastVisited = lastVisited;
    }
}
