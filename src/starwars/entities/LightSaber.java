package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Leave;
import starwars.actions.Take;

/**
 * An extremely powerful, indestructible weapon that implements the Weapon capability
 * 
 * It does not take damage with use.
 * 
 * This class does not implement all of the abilities shown in the films; there is
 * no capacity for using it to play cover drives with Blaster bolts, or parry 
 * other lightsabers; there are also no restrictions on who wields it.
 * 
 *  @author Robert
 *  @author Chai Lam, extended the functionality
 *  @see {@link starwars.actions.Attack}
 */

public class LightSaber extends SWEntity {

	/**
	 * Constructor for the <code>LightSaber</code> class. This constructor will,
	 * <ul>
	 * 	<li>Initialize the message renderer for the <code>LightSaber</code></li>
	 * 	<li>Set the short description of this <code>LightSaber</code>>
	 * 	<li>Set the long description of this <code>LightSaber</code> 
	 * 	<li>Add a <code>Take</code> affordance to this <code>LightSaber</code> so it can be taken</li> 
	 * </ul>
	 * 
	 * @param m <code>MessageRenderer</code> to display messages.
	 * 
	 * @see {@link starwars.actions.Take}
	 * @see {@link starwars.Capability}
	 * @see {@link starwars.actions.Chop} 1
	 */
	public LightSaber(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "A Lightsaber";
		this.longDescription = "A lightsaber.  Bzzz-whoosh!";
		this.hitpoints = 100000; // start with a nice powerful, sharp axe
		
		this.addAffordance(new Take(this, m));//add the take affordance so that the LightSaber can be taken by SWActors
		this.addAffordance(new Leave(this, m));//add the Leave affordance so that the LightSaber can be leaved

		this.capabilities.add(Capability.FORCEABLE);// it's a weapon which need force when using it. 
		this.setForceNeeded(10); //The force needed by LightSaber when using it
	}
	
	
	
	/**
	 * Lightsabers are indestructible, so doing damage to them has no effect
	 * @param damage - the amount of damage that would be inflicted on a non-mystical Entity
	 */
	@Override
	public void takeDamage(int damage) {
		
		return;
	}
	
	/**
	 * A symbol that is used to represent the LightSaber on a text based user interface
	 * 
	 * @return 	A String containing a single character.
	 * @see 	{@link starwars.SWEntityInterface#getSymbol()}
	 */
	@Override
	public String getSymbol() {
		return "鈥�";
	}
	
	

}
