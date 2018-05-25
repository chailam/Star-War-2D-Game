package starwars.entities.actors;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.SandcrawlerWorld;
import starwars.Team;
import starwars.actions.Enter;
import starwars.actions.Move;
import starwars.entities.actors.behaviors.Patrol;

public class Sandcrawler extends SWActor {
	
	private Patrol path;
	private int move = 1;
	private SandcrawlerWorld theWorld;
	
	/**
	 * Constructor for the <code>Sandcrawler</code> class. This constructor will,
	 * <ul>
	 * 	<li>Initialize the message renderer for the <code>Sandcrawler</code></li>
	 * 	<li>Initialize the world for this <code>Sandcrawler</code></li>
	 *  <li>Initialize the <code>Team</code> for this <code>Sandcrawler</code></li>
	 * 	<li>Initialize the hit points for this <code>Sandcrawler</code></li>
	 * </ul>
	 * 
	 * @param team the <code>Team</code> to which the this <code>Sandcrawler</code> belongs to
	 * @param hitpoints the hit points of this <code>Sandcrawler</code> to get started with
	 * @param m <code>MessageRenderer</code> to display messages.
	 * @param world the <code>SWWorld</code> world to which this <code>Sandcrawler</code> belongs to
	 * @param Direction the direction of the patrol move of the <code>Sandcrawler</code>
	 * @param	ability the ability of the <code>Sandcrawler</code> to use <code>TheForce</code>.
	 * @param	value the value of the force number of <code>Sandcrawler</code> in <code>TheForce</code>.
	 * 
	 */
	public Sandcrawler(Team team, int hitpoints, MessageRenderer m, SWWorld world, Direction [] moves,boolean ability, int value) {
		super(team, hitpoints, m, world,ability,value);
		path = new Patrol(moves);
		this.setShortDescription("Sandcrawler");
		this.setLongDescription("Sandcrawler, it collects Droids...");
		
		theWorld = new SandcrawlerWorld();
		theWorld.initializeWorld(m);
		
		this.addAffordance(new Enter(this,m));
	}
	
	/**
	 * This method will describe this <code>Sandcrawler</code>'s act.
	 * <p>
	 * <code>Sandcrawler</code>will either move every second turn or stop to collect <code>Droids</code>.
	 * 
	 * @author chailam
	 */
	@Override
	public void act() {	
		if(isDead()) {
			return;
		}
		
		boolean flag = collectDroid();
		
		if (flag == true) {
			say(getShortDescription() + " is collecting Droid " );
		}
		else {
			if (move%2 == 0) {
				Direction newdirection = path.getNext();
				say(getShortDescription() + " moves " + newdirection);
				Move myMove = new Move(newdirection, messageRenderer, world);
				scheduler.schedule(myMove, this, 1);
			}
			move ++;
		}
	}
	
	
	/**
	 * Perform the collect <code>Droids</code> action.
	 * <p>
	 * This method returns true if and only if there is a <code>Droids</code>.
	 *  
	 * @author 	chailam
	 * @return 	true if the there is a <code>Droids</code>, false otherwise
	 */
	private boolean collectDroid() {
		
		boolean flag = false; // to indicate whether there is a Droid
		//get the contents of the location
		List<SWEntityInterface> contents = this.world.getEntityManager().contents(this.world.getEntityManager().whereIs(this));
		
		//if the contents is a Droid
		for (SWEntityInterface entity : contents) {
			if (entity instanceof Droids) {
				flag = true;
				ArrayList<Integer> location = randomLocation();
				
				SWWorld.getEntitymanager().remove(entity);	
				int row = location.get(0);
				int col = location.get(1);
				
				SWLocation droidLoc = theWorld.getGrid().getLocationByCoordinates(row, col);
				
				SWWorld.getEntitymanager().setLocation(entity, droidLoc);
			}
		}
		return flag;
	}
	
	
	private ArrayList<Integer> randomLocation() {
		ArrayList<Integer> location = new ArrayList<Integer>(); 
		int row = (int)(Math.floor(Math.random() * theWorld.height()));
		int col = (int)(Math.floor(Math.random() * theWorld.width()));
		location.add(row);
		location.add(col);
		return location;
	}
	
	
	/**
	 * The <code>Sandcrawler</code> never take damage
	 * 
	 * @param 	damage the amount of <code>hitpoints</code> to be reduced
	 */
	@Override
	public void takeDamage(int damage) {
		return;
	}
	
	@Override
	public SWWorld getInsideWorld() {
		return theWorld;
	}

}
