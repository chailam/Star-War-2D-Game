package starwars.entities;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.actions.Leave;
import starwars.actions.Move;
import starwars.actions.Take;
import starwars.entities.actors.behaviors.Patrol;

import java.util.ArrayList;


public class Droids extends SWActor {
    public Droids(int hitpoints, String name, MessageRenderer m, Direction [] moveOptions,SWWorld ownerWorld, SWWorld droidWorld){
        super(Team.NEUTRAL,50,m,droidWorld,false,0);
        int droidHitpoints=hitpoints;
        this.addAffordance(new Take(this,m));
        this.addAffordance(new Leave(this,m));
        droidPath=new Patrol(moveOptions);
        this.setShortDescription("Droid" +
                "");

    }


    private SWActor owner;
    private SWWorld ownerWorld;
    private SWLocation ownerLocation;
    private SWWorld droidWorld;
    private SWLocation droidLocation;
    int droidHitpoints=50;
    public Patrol droidPath;


    public SWLocation getDroidLocation() {

        return droidLocation;
    }

    public SWLocation getOwnerLocation() {
        return ownerLocation;
    }

    public void setOwner(SWActor owner) {
        this.owner = owner;
    }

    public SWActor getOwner() {
        return owner;
    }
    /*if in badlands*/



    public void setDroidLocation(SWLocation droidLocation) {
        this.droidLocation = droidLocation;
    }


    /*if no owner or hitpoints negative*/

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

                /*if owner is a neighbour*/
                if (world.canMove(this, droidDirection) && ownerLocation==droidLocation.getNeighbour(droidDirection)){
                    Move newMove = new Move(droidDirection, messageRenderer, world);
                    scheduler.schedule(newMove, this, 1);
                    say(getShortDescription() + " moves " + droidDirection);
                }
                /*pick random direction*/
                else{
                    ArrayList<Direction> droidAnyDirection = new ArrayList<Direction>();

                    // build a list of available directions
                    for (Grid.CompassBearing d : Grid.CompassBearing.values()) {
                        if (SWWorld.getEntitymanager().seesExit(this, d)) {
                            droidAnyDirection.add(d);
                        }
                    }

                    Direction heading = droidAnyDirection.get((int) (Math.floor(Math.random() * droidAnyDirection.size())));
                    say(getShortDescription() + "is heading " + heading + " next.");
                    Move myMove = new Move(heading, messageRenderer, world);

                    scheduler.schedule(myMove, this, 1);
                }
            }
        }

        if (droidLocation.getSymbol()=='b'){
            this.takeDamage(1);
        }

    }

}