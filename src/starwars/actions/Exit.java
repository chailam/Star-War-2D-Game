package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;

public class Exit extends SWAffordance{
	
	/**
	 * Constructor for the <code>Exit</code> Class. Will initialize the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0).
	 * 
	 * @param theTarget a <code>SWEntity</code> that is being exit
	 * @param m the message renderer to display messages
	 */
	public Exit(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}


	/**
	 * Returns if or not this <code>Exit</code> can be performed by the <code>SWActor a</code>.
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
	 * Perform the <code>Exit</code> action by changing the <code>SWActor</code> to the original world 
	 * <p>
	 * This method should only be called if the <code>SWActor a</code> is alive.
	 * 
	 * @author 	chailam
	 * @param 	a the <code>SWActor</code> that has force ability
	 */
	@Override
	public void act(SWActor a) {
		if (canDo(a)) {
			SWEntityInterface theTarget = (SWEntityInterface) target;
			SWWorld originalWorld = a.getWorld();
			//SWLocation oriLoc =  originalWorld.getEntityManager().whereIs(theTarget);
			SWAction.getEntitymanager().remove(a);
			
			
			SWLocation playerLoc = originalWorld.getGrid().getLocationByCoordinates(0, 0);
			SWAction.getEntitymanager().setLocation(a, playerLoc);
			originalWorld.getController().changeWorld(originalWorld);
		}
	}

	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author chailam
	 * @return String comprising "exit " and the short description of the target of this <code>Exit</code>
	 */
	@Override
	public String getDescription() {
		return "Exit " + target.getShortDescription();
	}

}
