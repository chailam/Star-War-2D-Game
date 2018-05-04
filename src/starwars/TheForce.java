package starwars;

/**
 * Class that implement the Force in Star Wars.
 * <p>
 * All people have Force, but only some people have the ability to use the Force.
 * 
 * @author ChaiLam
 * 
 */
public class TheForce {
	
	/**The ability of the <code>SWActor</code> to use the Force.*/
	private boolean forceAbility;
	
	/**The force number of the <code>SWActor</code> .*/
	private int forceNumber;
	
	/**
	 * Constructor for the <code>TheForce</code>.
	 * <p>
	 * The constructor initializes the Force in <code>SWActor</code>.
	 * <p>
	 * @param	ability the ability of the actor in <code>SWActor</code> to use force
	 * @param	value the value of the force number
	 */
	public TheForce(boolean ability, int value){
		forceAbility = ability;
		forceNumber = value;
	}
	
	/**
	 * Getter of the <code>TheForce</code> to get the forceNumber.
	 */
	public int getForceNumber() {
		return forceNumber;
	}
	
	/**
	 * Add value to the <code>TheForce</code>.
	 * <p>
	 * It mainly used by the legend in <code>SWLegend</code> to increase the force number of the <code>SWActor</code>.
	 * @param	value the value needed to be added
	 */
	public void addForceNumber(int value) {
		forceNumber = forceNumber + value;
	}
	
	public boolean checkForceAbility() {
		return forceAbility;
	}

}
