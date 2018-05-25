package starwars.actions;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.entities.Grenade;

import java.util.ArrayList;

public class Throw extends SWAffordance implements SWActionInterface{
    private ArrayList<Direction> directions;

    Direction [] direction = {Grid.CompassBearing.EAST, Grid.CompassBearing.SOUTH, Grid.CompassBearing.WEST, Grid.CompassBearing.NORTHWEST,
            Grid.CompassBearing.SOUTHEAST, Grid.CompassBearing.SOUTHWEST, Grid.CompassBearing.NORTH, Grid.CompassBearing.NORTHEAST};
    public Throw(SWEntityInterface targetObject, MessageRenderer m){
        super(targetObject,m);


    }

    @Override
    public void act(SWActor a) {
        SWEntityInterface target=this.getTarget();
        boolean targetIsActor=target instanceof SWActor;
        SWActor targetActor=null;
        if (targetIsActor){
            targetActor=(SWActor) target;
        }
        SWEntity itemCarried=(SWEntity) a.getItemCarried();
        /*Actor can throw a distance of 5 steps*/
        /*if(itemCarried instanceof Grenade && targetIsActor){
            if(a.getActorLocation().getNeighbour(directions.get(5))==targetActor.getActorLocation()){
                targetActor.takeDamage(20);
                itemCarried.takeDamage(20);
            }
            else if(a.getActorLocation().getNeighbour(directions.get(6))==targetActor.getActorLocation()){
                targetActor.takeDamage(10);
                itemCarried.takeDamage(10);
            }
            else if(a.getActorLocation().getNeighbour(directions.get(7))==(targetActor).getActorLocation()){
                targetActor.takeDamage(5);
                itemCarried.takeDamage(5);
            }
            a.say(a.getShortDescription()+" threw a grenade on "+targetActor.getShortDescription());
        }*/
        if(SWAction.getEntitymanager().whereIs(target)==SWAction.getEntitymanager().whereIs(a)){
            target.takeDamage(20);
            itemCarried.removeAffordance(this);
        }
        else if(SWAction.getEntitymanager().whereIs(target)==SWAction.getEntitymanager().whereIs(a).getNeighbour(directions.get(1))){
            target.takeDamage(10);
        }
        else if(SWAction.getEntitymanager().whereIs(target)==SWAction.getEntitymanager().whereIs(a).getNeighbour(directions.get(2))){
            target.takeDamage(5);
        }
        a.say(a.getShortDescription()+" threw a grenade on "+target.getShortDescription());

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean canDo(SWActor a) {
        return false;
    }
}
