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
/**
 * Command to throw grenade.
 *
 * This affordance is attached to all attackable entities
 *
 * @author Vinitha Raj Rajagopal Muthu
 */
/*
 *
 */
public class Throw extends SWAffordance implements SWActionInterface{

    /**
     * Constructor for the <code>Attack</code> class. Will initialize the <code>messageRenderer</code> and
     * give <code>Attack</code> a priority of 1 (lowest priority is 0).
     *
     * @param targetObject the target being attacked
     * @param m message renderer to display messages
     */
    public Throw(SWEntityInterface targetObject, MessageRenderer m){
        super(targetObject,m);
        priority=1;


    }

    /**
     * Returns the time is takes to perform this <code>Attack</code> action.
     *
     * @return The duration of the Attack action. Currently hard coded to return 1.
     */
    @Override
    public int getDuration() {
        return 1;
    }
    /**
     * Perform the <code>Throw</code> command on an entity.
     * <p>
     * This method performs damage (an attack) if,
     * <ul>
     * 	<li>The entity is in the same location as the <code>SWActor a</code> </li>
     * 	<li>The entity is 1 step away from the <code>SWActor a</code> </li>
     * 	<li>The entity is 2 steps away from the <code>SWActor a</code></li>
     * </ul>
     * <p>
     *
     * @author 	Vinitha Raj Rajagopal Muthu
     * @param 	a the <code>SWActor</code> who is attacking
     */
    @Override
    public void act(SWActor a) {

        SWEntity itemCarried = (SWEntity) a.getItemCarried();


        for (int p=0;p<10;p++){
            for (int q=0;q<10;q++) {
                SWLocation initloc=SWAction.getEntitymanager().whereIs(a);
                if (a.getWorld().getGrid().getLocationByCoordinates(p,q)==SWAction.getEntitymanager().whereIs(a)){
                    //List<SWLocation> locationGuide=[];
                    SWLocation loc= a.getWorld().getGrid().getLocationByCoordinates(p,q);
                    List<SWEntityInterface> nearEntities = a.getWorld().getEntitymanager().contents(loc);
                    for (int j = 0; j < nearEntities.size(); j++) {
                        if(nearEntities.get(j)!=a) {
                            nearEntities.get(j).takeDamage(20);
                            a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade on the spot by " + a.getShortDescription());
                        }
                    }
                    if(p<8){
                        SWLocation loc1= a.getWorld().getGrid().getLocationByCoordinates(p+2,q);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc1);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(10);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 2 steps away by " + a.getShortDescription());
                            }
                        }

                    }
                    if(p>1 && a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p-2,q))!=null){
                        SWLocation loc2= a.getWorld().getGrid().getLocationByCoordinates(p-2,q);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc2);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(10);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 2 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(p<8 && q<8 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p + 2, q + 2))!=null){
                        SWLocation loc3= a.getWorld().getGrid().getLocationByCoordinates(p+2,q+2);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc3);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(10);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 2 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(p<8 && q>1 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p + 2, q - 2))!=null) {
                        SWLocation loc4 = a.getWorld().getGrid().getLocationByCoordinates(p + 2, q - 2);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc4);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(10);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 2 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(p>1 && q>1 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p - 2, q - 2))!=null) {
                        SWLocation loc5 = a.getWorld().getGrid().getLocationByCoordinates(p - 2, q - 2);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc5);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(10);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 2 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(p>1 && q<8 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p - 2, q + 2))!=null) {
                        SWLocation loc6 = a.getWorld().getGrid().getLocationByCoordinates(p - 2, q + 2);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc6);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(10);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 2 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(q<8 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p , q + 2))!=null) {
                        SWLocation loc7 = a.getWorld().getGrid().getLocationByCoordinates(p, q + 2);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc7);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(10);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 2 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(q>1 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p , q - 2))!=null) {
                        SWLocation loc8 = a.getWorld().getGrid().getLocationByCoordinates(p, q - 2);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc8);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(10);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 2 steps away by " + a.getShortDescription());
                            }
                        }
                    }

                    if(p<9){
                        SWLocation loc1= a.getWorld().getGrid().getLocationByCoordinates(p+1,q);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc1);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(5);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 1 steps away by " + a.getShortDescription());
                            }
                        }

                    }
                    if(p>0 && a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p-1,q))!=null){
                        SWLocation loc2= a.getWorld().getGrid().getLocationByCoordinates(p-1,q);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc2);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(5);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 1 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(p<9 && q<9 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p + 1, q + 1))!=null){
                        SWLocation loc3= a.getWorld().getGrid().getLocationByCoordinates(p+1,q+1);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc3);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(5);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 1 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(p<9 && q>0 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p + 1, q - 1))!=null) {
                        SWLocation loc4 = a.getWorld().getGrid().getLocationByCoordinates(p + 1, q - 1);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc4);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(5);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 1 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(p>0 && q>0 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p - 1, q - 1))!=null) {
                        SWLocation loc5 = a.getWorld().getGrid().getLocationByCoordinates(p - 1, q - 1);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc5);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(5);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 1 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(p>0 && q<9 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p - 1, q + 1))!=null) {
                        SWLocation loc6 = a.getWorld().getGrid().getLocationByCoordinates(p - 1, q + 1);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc6);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(5);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 1 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(q<9 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p , q + 1))!=null) {
                        SWLocation loc7 = a.getWorld().getGrid().getLocationByCoordinates(p, q + 1);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc7);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(5);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 1 steps away by " + a.getShortDescription());
                            }
                        }
                    }
                    if(q>0 &&a.getWorld().getEntitymanager().contents(a.getWorld().getGrid().getLocationByCoordinates(p , q - 1))!=null) {
                        SWLocation loc8 = a.getWorld().getGrid().getLocationByCoordinates(p, q - 1);
                        nearEntities = a.getWorld().getEntitymanager().contents(loc8);

                        for (int j = 0; j < nearEntities.size(); j++) {
                            if(nearEntities.get(j)!=a) {
                                nearEntities.get(j).takeDamage(5);
                                a.say(nearEntities.get(j).getShortDescription() + " was attacked by a grenade 1 steps away by " + a.getShortDescription());
                            }
                        }
                    }




                }

            }
        }
        itemCarried.takeDamage(0);
        itemCarried.removeAffordance(this);



    }
    /**
     * A String describing what this <code>Attack</code> action will do, suitable for display on a user interface
     *
     * @return String comprising "attack " and the short description of the target of this <code>Affordance</code>
     */
    @Override
    public String getDescription() {
        return "throw "+target.getShortDescription();
    }
    /**
     * Determine whether a particular <code>SWActor a</code> can attack the target.
     *
     * @author 	Vinitha Raj Rajagopal Muthu
     * @param 	a the <code>SWActor</code> being queried
     * @return 	true any <code>SWActor</code> can always try an attack, it just won't do much
     * 			good unless this <code>SWActor a</code> has a suitable weapon.
     */
    @Override
    public boolean canDo(SWActor a) {
        return true;
    }

}
