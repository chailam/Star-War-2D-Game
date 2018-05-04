package starwars;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;

/**
 * This class represents "legends" - major characters - in the Star Wars universe.  
 * They use a variation of the Singleton
 * pattern to ensure that only ONE of each legend can exist.
 * 
 * Subclasses are intended to contain a static instance which represents the one
 * and only instance of the subclass.  
 * 
 * Subclasses should implement their own "getLegendClass" method that returns 
 * the single instance. There is no abstract method for this to avoid an 
 * unnecessary downcast.
 * 
 * To prevent SWLegends acting until intended, this abstract class implements
 * an API for activating them when getInstance is called.
 * 
 * Rather than implement act() like regular SWActors, Legends should implement
 * legendAct().  
 * 
 * @author Robert Merkel
 *
 */
public abstract class SWLegend extends SWActor {

	private boolean isActivated;

	
	/** 
	 * Protected constructor to prevent random other code from creating 
	 * SWLegends or their descendents.
	 * @param team
	 * @param hitpoints
	 * @param m
	 * @param world
	 * @param ability
	 * @param value
	 */
	
	protected SWLegend(Team team, int hitpoints, MessageRenderer m, SWWorld world,boolean ability, int value) {
		super(team, hitpoints, m, world,ability,value);
		isActivated = false;
	}

	
	protected boolean isActive() {
		return isActivated;
	}
	
	protected void activate() {
		isActivated = true;
	}
	
	@Override
	public void act() {
		if (isActive()) {
			this.legendAct();
		}
		return;
	}

	protected abstract void legendAct();
	
	/**
	 * Train the <code>SWActor</code> to increase the force number <code>TheForce</code>.
	 * <p>
	 * All Legend can train the actors.
	 * @author 	ChaiLam
	 * @param 	a the <code>SWActor</code> needed to be trained
	 * @param	value the force number of the actor added after get trained
	 * 
	 */
	protected void train(SWActor a, int value) {
		SWLocation thisLocation = this.world.getEntityManager().whereIs(this);
		SWLocation studentLocation = this.world.getEntityManager().whereIs(a);
		if (thisLocation == studentLocation) {
			a.force.addForceNumber(value);
			say(a.getShortDescription() + " has increased his force number to " + a.force.getForceNumber());
		}
	}
}
