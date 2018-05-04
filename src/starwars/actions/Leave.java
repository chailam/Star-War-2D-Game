package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;

/**
 * @author user
 *
 */
public class Leave extends SWAffordance {
	
	public Leave(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}
	
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
	
	@Override
	public boolean canDo(SWActor a) {
		return a.getItemCarried()!=null;
	}
	
	@Override
	public String getDescription() {
		return "leave " + target.getShortDescription();
	}

}
