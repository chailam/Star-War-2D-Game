package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntity;
import starwars.actions.Exit;


public class Door extends SWEntity{
	
	/**
	 * Constructor for the <code>Door</code> class. This constructor will,
	 * <ul>
	 * 	<li>Initialize the message renderer for the <code>Door</code></li>
	 * 	<li>Set the short description of this <code>Door</code>>
	 * 	<li>Set the long description of this <code>Door</code> 
	 * 	<li>Add a <code>Exit</code> affordance to this <code>Door</code> so it can be exit by the actor</li> 
	 * </ul>
	 * 
	 * @param m <code>MessageRenderer</code> to display messages.
	 */
	public Door(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "A Door";
		this.longDescription = "A Door.  Exiting to the original world.";
		this.hitpoints = 100; 
		
		this.addAffordance(new Exit(this, m));//add the exit affordance so that the actor can exit the world
	}
	
	
	
	/**
	 * Door are indestructible, so doing damage to them has no effect
	 * @param damage - the amount of damage that would be inflicted on a non-mystical Entity
	 */
	@Override
	public void takeDamage(int damage) {
		return;
	}
}
