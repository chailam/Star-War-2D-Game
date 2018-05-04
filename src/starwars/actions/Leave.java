package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;

/**
* <code>SWAction</code> that lets a <code>SWActor</code> put down an object.
* 
* @author ChaiLam
*/
public class Leave extends SWAffordance {
	
	/**
	 * Constructor for the <code>Leave</code> Class. Will initialize the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0).
	 * 
	 * @param theTarget a <code>SWEntity</code> that is being taken
	 * @param m the message renderer to display messages
	 */
	public Leave(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}
	
	/**
	 * Perform the <code>Leave</code> action by setting the item carried by the <code>SWActor</code> to null. 
	 * <p>
	 * The item should be in the location of the actor when it was done.
	 * This method should only be called if the <code>SWActor a</code> is alive.
	 * 
	 * @author 	ChaiLam
	 * @param 	a the <code>SWActor</code> that is taking the target
	 * @see 	{@link #theTarget}
	 * @see		{@link starwars.SWActor#isDead()}
	 */
	@Override
	public void act(SWActor a) {
		if (target instanceof SWEntityInterface) {
			SWEntityInterface theItem = (SWEntityInterface) target;
			a.setItemCarried(null);
			SWLocation actorLocation = a.getWorld().getEntityManager().whereIs(a);
			//add the item to the entity manager since it's now removed by the SWActor
			SWAction.getEntitymanager().setLocation(theItem, actorLocation); 
		}
	}
	
	/**
	 * Returns if or not this <code>Leave</code> can be performed by the <code>SWActor a</code>.
	 * <p>
	 * This method returns true if and only if <code>a</code> is carrying any item already.
	 *  
	 * @author 	ChaiLam
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true if the <code>SWActor</code> is can leave this item, false otherwise
	 * @see		{@link starwars.SWActor#getItemCarried()}
	 */
	@Override
	public boolean canDo(SWActor a) {
		return a.getItemCarried()!=null;
	}
	
	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author ChaiLam
	 * @return String comprising "leave " and the short description of the target of this <code>Leave</code>
	 */
	@Override
	public String getDescription() {
		return "leave " + target.getShortDescription();
	}

}
