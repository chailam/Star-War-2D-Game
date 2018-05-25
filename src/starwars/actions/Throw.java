package starwars.actions;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.entities.Grenade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Throw extends SWAffordance implements SWActionInterface{
    private ArrayList<Direction> directions;

    Direction [] direction = {Grid.CompassBearing.EAST, Grid.CompassBearing.SOUTH, Grid.CompassBearing.WEST, Grid.CompassBearing.NORTHWEST,
            Grid.CompassBearing.SOUTHEAST, Grid.CompassBearing.SOUTHWEST, Grid.CompassBearing.NORTH, Grid.CompassBearing.NORTHEAST};

    public Throw(SWEntityInterface targetObject, MessageRenderer m){
        super(targetObject,m);
        priority=1;
        directions = new ArrayList<Direction>(Arrays.asList(direction));

    }
    @Override
    public int getDuration() {
        return 1;
    }

    @Override
    public void act(SWActor a) {
        SWEntityInterface target = this.getTarget();
        boolean targetIsActor = target instanceof SWActor;
        SWActor targetActor = null;
        if (targetIsActor) {
            targetActor = (SWActor) target;
        }
        SWEntity itemCarried = (SWEntity) a.getItemCarried();
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

        if (itemCarried != null && (a.isHumanControlled() || (targetIsActor && (a.getTeam() != targetActor.getTeam())))) {
            SWLocation sameLocation = a.getWorld().getEntityManager().whereIs(a);
            List<SWEntityInterface> sameLocEntities = a.getWorld().getEntitymanager().contents(sameLocation);
            SWLocation actorNeighbourLocation1 = (SWLocation) SWAction.getEntitymanager().whereIs(a).getNeighbour(directions.get(1));
            List<SWEntityInterface> actorNeighbourSWLocation1 = a.getWorld().getEntitymanager().contents(actorNeighbourLocation1);
            Location actorNeighbourLocation2 = a.getWorld().find(a).getNeighbour(directions.get(2));
            List<SWEntityInterface> actorNeighbourSWLocation2 = a.getWorld().getEntitymanager().contents((SWLocation) actorNeighbourLocation2);
            for (int i = 0; i < sameLocEntities.size(); i++) {
                sameLocEntities.get(i).takeDamage(20);
            }
            for (int j = 0; j < actorNeighbourSWLocation1.size(); j++) {
                actorNeighbourSWLocation1.get(j).takeDamage(10);
            }
            for (int k = 0; k < actorNeighbourSWLocation2.size(); k++) {
                actorNeighbourSWLocation2.get(k).takeDamage(5);
            }
                /*if (SWAction.getEntitymanager().whereIs(target) == a.getWorld().getEntitymanager().whereIs(a)) {
                    target.takeDamage(20);
                    //itemCarried.removeAffordance(this);
                    a.say(a.getShortDescription() + " threw a grenade on 20" + target.getShortDescription());
                } else if (a.getWorld().getEntityManager().contents()) {

                    String EntitySymbol;
                    EntitySymbol=((SWEntityInterface) a.getWorld().getEntitymanager().whereIs(a).getNeighbour(directions.get(1))).getSymbol();
                    target.takeDamage(10);
                    a.say(a.getShortDescription() + " threw a grenade on 10" + target.getShortDescription());
                } else if (SWAction.getEntitymanager().whereIs(target) == a.getWorld().getEntitymanager().whereIs(a).getNeighbour(directions.get(2))) {
                    target.takeDamage(5);
                    a.say(a.getShortDescription() + " threw a grenade on 5" + target.getShortDescription());
                }
                a.say(a.getShortDescription() + " threw a grenade on " + target.getShortDescription());

        }*/
        }
    }
    @Override
    public String getDescription() {
        return "throw "+target.getShortDescription();
    }

    @Override
    public boolean canDo(SWActor a) {
        return true;
    }
}
