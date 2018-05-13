package starwars.entities.actors;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.actions.Move;
import starwars.actions.Take;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that implement the Droids in Star Wars.
 * <p>
 * Droids have an owner that they can set and it moves to the owner if the owner is in the neighbouring area
 *
 * @author Vinitha Raj Rajagopal Muthu
 *
 */

public class Droids extends SWActor {
    /**The owner of the <code>Droids</code> .*/
	private SWActor owner;
    /**Whether the <code>Droids</code> is flagged or not.*/
	private boolean flag;
    /**The <code>Direction</code> in which the <code>Droids</code> is heading next.*/
	private Direction heading;
    /**The array of <code>Direction</code> available.*/
	private ArrayList<Direction> directions;
	
	Direction [] direction = {CompassBearing.EAST, CompassBearing.SOUTH,CompassBearing.WEST,CompassBearing.NORTHWEST, 
			CompassBearing.SOUTHEAST,CompassBearing.SOUTHWEST,CompassBearing.NORTH,CompassBearing.NORTHEAST};
    /**
     * Constructor for the <code>Droids</code>.
     * <p>
     * The constructor initializes the Droids in <code>SWActor</code>.
     * <p>
     * @param	hitpoints the measure of health of the droid. Decreases if it is in badlands and Droid can't move if it hits 0
     * @param	name the name of the droid
     * @param m the <code>MessageRenderer</code> message displayed
     * @param world the <code>SWWorld</code> the droid belongs to
     */
    public Droids(int hitpoints, String name, MessageRenderer m,SWWorld world){
        super(Team.NEUTRAL,hitpoints,m,world,false,0);
        owner = null;
        flag = true;     
        
        this.addAffordance(new Take(this,m));
        
        this.setShortDescription("Droid");
        this.setLongDescription("Droids, it can't use Force");
        directions = new ArrayList<Direction>(Arrays.asList(direction));
    }
    /**
     * Getter of the <code>Droids</code> <code>SWLocation</code>.
     */
    public SWLocation getDroidLocation() {
        return this.world.getEntityManager().whereIs(this);
    }
    /**
     * Getter of the <code>SWActor</code>, who is the owner's, <code>SWLocation</code>.
     */
    public SWLocation getOwnerLocation() {
        return this.world.getEntityManager().whereIs(owner);
    }

    /**
     * Setter of the <code>SWActor</code> who is the owner for the <code>Droids</code>.
     */
    public void setOwner(SWActor owner) {
        this.owner = owner;
    }

    /**
     * Getter of the <code>Droids</code> owner who is a <code>SWActor</code>.
     */
    public SWActor getOwner() {
        return owner;
    }

    /**
     * Method to find the <code>Direction</code> if the owner is in neighbouring <code>SWLocation</code>.
     */
    public Direction findDroidNeighbour() {
    	for (int k = 0; k < directions.size();k++) {
    		if ((getDroidLocation().getNeighbour(directions.get(k))) == getOwnerLocation()) {
    			return directions.get(k);
    		}
    	}
    	return null;
    }
    	

    /*if no owner or hitpoints negative*/
    /**
     * Determines how the <code>Droids</code> behaves when owner is in same position, neighbouring position or not
     * <p>
     * If <code>Droids</code> is in Badlands hitpoints decreases and if it has no owner hitpoints set to 0.
     * @param
     */
    @Override
    public void act(){
        if (this.getHitpoints() <= 0 || owner == null || getOwnerLocation() == getDroidLocation()){
            return;
        }
        
        /*if droid is in badlands take damage*/
        if (getDroidLocation().getSymbol()=='b'){
            this.takeDamage(1);
            say(this.getShortDescription()+"'s hitpoint -1 (in badlands) = "+this.getHitpoints());
        }
        
        if (findDroidNeighbour() != null) {
        	this.world.moveEntity(this, findDroidNeighbour()); 
        	return;
		}

		 /*pick random direction*/
        if (flag == true) {
        	ArrayList<Direction> droidAvailableDirection = new ArrayList<Direction>();
            for (Grid.CompassBearing d : Grid.CompassBearing.values()) {
                   if (SWWorld.getEntitymanager().seesExit(this, d)) {
                    droidAvailableDirection.add(d);
                   }
            }
            flag = false;
            heading = droidAvailableDirection.get((int) (Math.floor(Math.random() * droidAvailableDirection.size())));
           }
        
        say(getShortDescription() + "is heading " + heading + " next.");
        Move myMove = new Move(heading, messageRenderer, world);
        scheduler.schedule(myMove, this, 1);
        	
        if (world.canMove(this, heading) == false) {
            flag = true;
         }
    }
}