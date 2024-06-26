package edu.monash.fit2099.simulator.matter;

import java.util.ArrayList;
import java.util.Set;

import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.time.Scheduler;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;

/**
 * Base class for Actors, i.e. entities that can perform <code>Actions</code>.
 * <p>
 * Generated by UML Lab.
 * 
 * @author 	ram
 * @date 	17 February 2013
 * @see		{@link edu.monash.fit2099.simulator.matter.Action}
 */

/*
 * Changelog
 * 
 * 2013-02-28: Added perform() method (ram)
 * 2013-03-01: Made generic to avoid downcasts in client code (ram)
 * 	Made actions protected so that it can be instantiated in client code (ram)
 * 2013-03-04: Took out genericity because it was getting excessive (ram)
 * 	And then put it back in. (ram)
 * 2013-03-07: added MessageRenderer to constructor to accommodate change in Entity (ram)
 * 2013-04-07: fixed up generics and made getActions return an ArrayList instead of an Array.
 */
public abstract class Actor<T extends ActionInterface> extends Entity {

	/**
	 * The scheduler of this <code>Actor</code>
	 */
	protected static Scheduler s;
	
	/**The command set of this <code>Actor</code>, i.e. a set of <code>Actions</code> of this <code>Actor</code>*/
	protected Set<T> actions;
	
	/**The amount of time this <code>Actor</code> has to wait before it could <code>act</code>*/
	private int waittime;
	
	/**
	 * Register a new <code>Action</code> as part of this <code>Actor</code>'s command set.  
	 * <p>
	 * This does nothing if the <code>Action</code>
	 * is already registered, although note that it is possible to add two identically-constructed <code>Actions</code>
	 * to the command set if they are not the same object, i.e. the comparison is done with == rather than
	 * isEqual.
	 * 
	 * @param a the <code>Action</code> to add
	 */
	public void addAction(T a) {
		actions.add(a);
	}
	
	
	/**
	 * Remove an <code>Action</code> from this <code>Actors</code>'s set of potential commands.  This does nothing if the
	 * <code>Action a</code> was not in that set.
	 * 
	 * @param a the <code>Action</code> to remove
	 */
	public void removeAction(T a) {
		actions.remove(a);
	}
	
	/**
	 * The constructor for the <code>Actor</code>
	 * <p>
	 * The default constructor in the parent class (Entity) is private, so we have to pass the
	 * super constructor a parameter.
	 * <p>
	 * <code>waitime</code> of this <code>Actor</code> is set to zero (0) by default.
	 * 
	 * @param m a <code>MessageRenderer</code> that will allow the <code>Actor</code> to display messages
	 */
	public Actor(MessageRenderer m) {
		super(m);
		waittime =0;
	}
	
	/**
	 * Returns an <code>ArrayList</code> containing references to all the <code>Actions</code> this <code>Actor</code> can currently
	 * perform.  
	 * <p>
	 * Does not include <code>Affordances</code> in objects in the same <code>Location</code>. 
	 * Such information must be queried from the <code>EntityManager</code>.
	 * 
	 * @return an <code>ArrayList</code> containing this <code>Actor</code>'s <code>Actions</code>
	 */
	public ArrayList<T> getActions() {
		ArrayList<T> newActions = new ArrayList<T>();
		for (T action: actions)
			newActions.add(action);
		return newActions;
	}
	
	/**
	 * Allows this <code>Actor</code> to act. This method will only be called if this <code>Actor</code> is not waiting.
	 */
	public abstract void act();
	

	/**
	 * Tick method for this <code>Actor</code>. 
	 * <p>
	 * This method will call the act method if this <code>Actor</code> is not waiting, else it will decrement the 
	 * <code>waitime</code> of this <code>Actor</code> by 1 and do nothing else. 
	 * 
	 * TODO: Should we decrement the waittime by the tick size of the scheduler?- Asel
	 * 
	 * @param 	l is the current <code>Location</code> of this <code>Actor</code>.
	 * @see 	#waittime
	 * @see 	#act
	 */
	public void tick(Location l) {
		
		if (waittime >0){//waiting
			waittime --;//decrement the waittime
			this.setWaittime(waittime); 
		}
		else{
			//act if not waiting
			act();
		}
	}
	
	/**
	 * Sets the <code>waittime</code> of this <code>Actor</code> to the new wait time
	 * 
	 * @param 	waittime the new wait time of this <code>Actor</code> 
	 * @pre		<code>waittime</code> should be greater than or equal to zero (0)
	 * @see 	#waittime
	 */
	public void setWaittime(int waittime){
		
		//Precondition 1: Ensure the waittime is greater than or equal to zero (0)
		assert (waittime>=0):"waittime must be greater than or equal to zero (0)";
		
		this.waittime = waittime;
	}

	
	/**
	 * Returns the <code>waittime</code> of this <code>Actor</code>.
	 * <p>
	 * The <code>tick(Location)</code> method uses this method to determine if this <code>Actor</code> can <code>act</code> within the tick
	 * 
	 * @return 	the wait time of this <code>Actor</code>
	 * @see 	#tick(Location)
	 * @see		#waittime
	 */
	public int getWaittime() {
		return waittime;
	}
	

}

