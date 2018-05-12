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


/*
Droids have an owner that they can set and it moves to the owner if the owner is in the neighbouring area
* */
public class Droids extends SWActor {
	
	private SWActor owner;
	private boolean flag;
	private Direction heading;
	private ArrayList<Direction> directions;
	
	Direction [] direction = {CompassBearing.EAST, CompassBearing.SOUTH,CompassBearing.WEST,CompassBearing.NORTHWEST, 
			CompassBearing.SOUTHEAST,CompassBearing.SOUTHWEST,CompassBearing.NORTH,CompassBearing.NORTHEAST};
	
    public Droids(int hitpoints, String name, MessageRenderer m,SWWorld world){
        super(Team.NEUTRAL,hitpoints,m,world,false,0);
        owner = null;
        flag = true;     
        
        this.addAffordance(new Take(this,m));
        
        this.setShortDescription("Droid");
        this.setLongDescription("Droids, it can't use Force");
        directions = new ArrayList<Direction>(Arrays.asList(direction));
    }

    public SWLocation getDroidLocation() {
        return this.world.getEntityManager().whereIs(this);
    }

    public SWLocation getOwnerLocation() {
        return this.world.getEntityManager().whereIs(owner);
    }
    
    /*set droids owner*/
    public void setOwner(SWActor owner) {
        this.owner = owner;
    }
    
    /*get droids owner*/
    public SWActor getOwner() {
        return owner;
    }
    
    /*check owner is neighbour*/
    public Direction findDroidNeighbour() {
    	for (int k = 0; k < directions.size();k++) {
    		if ((getDroidLocation().getNeighbour(directions.get(k))) == getOwnerLocation()) {
    			return directions.get(k);
    		}
    	}
    	return null;
    }
    	

    /*if no owner or hitpoints negative*/
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