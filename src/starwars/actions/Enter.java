package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;

/**
	 * The action "Enter"
	 * @author 	chailam
	 */

public class Enter extends SWAffordance{
	
	/**
	 * Constructor for the <code>Enter</code> Class. Will initialize the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0).
	 * 
	 * @param theTarget a <code>SWEntity</code> , which is the <code>Sandcrawler</code>
	 * @param m the message renderer to display messages
	 */
	public Enter(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}


	/**
	 * Returns if or not this <code>Enter</code> can be performed by the <code>SWActor a</code>.
	 * <p>
	 * This method returns true if and only if <code>a</code> has force ability.
	 *  
	 * @author 	chailam
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true if the <code>SWActor</code> has force ability, false otherwise
	 */
	@Override
	public boolean canDo(SWActor a) {
		return a.hasForceAbility();
	}

	/**
	 * Perform the <code>Enter</code> action by changing the <code>SWActor</code> to the <code>Sandscrawler</code>'s world 
	 * <p>
	 * This method should only be called if the <code>SWActor a</code> is alive.
	 * 
	 * @author 	chailam
	 * @param 	a the <code>SWActor</code> that has force ability
	 */
	@Override
	public void act(SWActor a) {
		if (canDo(a)) {
			SWActor theTarget = (SWActor)target;
			
			a.setSandcrawler(theTarget);  //Set the Sandcrawler to the actor
			
			SWWorld targetWorld = theTarget.getWorld();
			SWWorld targetInsideWorld = theTarget.getInsideWorld();
			
			SWAction.getEntitymanager().remove(a);
			SWLocation playerLoc = targetInsideWorld.getGrid().getLocationByCoordinates(0, 0);
			SWAction.getEntitymanager().setLocation(a, playerLoc);
			
			targetWorld.getController().changeWorld(targetInsideWorld);
		}
	}

	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author chailam
	 * @return String comprising "enter " and the short description of the target of this <code>Enter</code>
	 */
	@Override
	public String getDescription() {
		return "Enter " + target.getShortDescription();
	}

}


