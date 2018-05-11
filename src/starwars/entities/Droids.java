package starwars.entities;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.actions.Move;
import starwars.entities.actors.behaviors.Patrol;


public class Droids extends SWActor {
    public Droids(int hitpoints, String name, MessageRenderer m, SWWorld ownerWorld, SWWorld droidWorld){
        super(Team.NEUTRAL,50,m,droidWorld,false,0);
        int droidHitpoints=hitpoints;
    }
    private SWLegend owner;
    private SWWorld ownerWorld;
    private Location ownerLocation=ownerWorld.find(owner);
    private SWWorld droidWorld;
    private Location droidLocation=droidWorld.find(this);
    int droidHitpoints=this.getHitpoints();
    private Patrol droidPath;
    public void moveDroid(){

    }

    public Location getDroidLocation() {
        return droidLocation;
    }

    public Location getOwnerLocation() {
        return ownerLocation;
    }

    public void setOwner(SWLegend owner) {
        this.owner = owner;
    }

    public SWLegend getOwner() {
        return owner;
    }
    /*if in badlands*/
    public void droidDamage(){
        //if (droidLocation){
        droidHitpoints-=1;
    }


    public void setDroidLocation(Location droidLocation) {
        this.droidLocation = droidLocation;
    }

    public void setOwnerLocation(Location ownerLocation) {
        this.ownerLocation = ownerLocation;
    }
    /*if no owner or hitpoints negative*/
    private SWGrid dGrid;
    @Override
    public void act(){
        if (droidHitpoints<=0 || owner==null){
            droidHitpoints=0;
            return;
        }
        /*move until droid location equals owner location*/
        if (droidLocation!=ownerLocation){
            while (droidLocation!=ownerLocation){
                Direction droidDirection = droidPath.getNext();
                say(getShortDescription() + " moves " + droidDirection);
                Move newMove = new Move(droidDirection, messageRenderer, world);
                scheduler.schedule(newMove, this, 1);
            }
        }
        for(int i=5;i<8;i++){
            for(int j=4;j<7;j++){
                Location dLoc=dGrid.getLocationByCoordinates(i,j);
                if (droidLocation==dLoc){
                    droidHitpoints-=1;
                }
            }
        }

    }

}