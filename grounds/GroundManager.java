package game.grounds;

import game.grounds.environments.SiteOfLostGrace;

import java.util.ArrayList;

/**
 * A Class that manages the grounds that need to be manipulated individually
 * Created by:
 * @author Benedict Lee Dao-Ee
 */
public class GroundManager {

    /**
     * ArrayList containing every Site Of Lost Grace
     */
    private ArrayList<SiteOfLostGrace> sites;
    /**
     * ArrayList containing every activated Site Of Lost Grace
     */
    private ArrayList<SiteOfLostGrace> activatedSites;
    /**
     * A unique instance of InstanceManager
     */
    private static GroundManager instance = null;

    /**
     * Constructor
     */
    private GroundManager() {
        this.sites = new ArrayList<>();
        this.activatedSites = new ArrayList<>();
    }

    /**
     * A factory method that creates a single instance of GroundManager
     * @return the only instance of GroundManager
     */
    public static GroundManager getInstance() {
        if (instance == null) {
            instance = new GroundManager();
        } return instance;
    }

    /**
     * Gets the ArrayList containing every Site Of Lost Grace
     * @return the ArrayList containing every Site Of Lost Grace
     */
    public ArrayList<SiteOfLostGrace> getSite(){
        return this.sites;
    }

    /**
     * Adds Site Of Lost Grace to the Array List of every Site Of Lost Grace
     * @param site the Site Of Lost Grace to be added
     */
    public void addSite(SiteOfLostGrace site){
        this.sites.add(site);
    }

    /**
     * Adds Site Of Lost Grace to the Array List of every activated Site Of Lost Grace
     * @param site the Site Of Lost Grace to be added
     */
    public void addActivatedSite(SiteOfLostGrace site){
        this.activatedSites.add((site));
    }

    /**
     * Gets the ArrayList containing every activated Site Of Lost Grace
     * @return the ArrayList containing every activated Site Of Lost Grace
     */
    public ArrayList<SiteOfLostGrace> getActivatedSite(){
        return this.activatedSites;
    }
}
