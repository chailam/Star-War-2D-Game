package starwars.entities.actors;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLegend;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.entities.LightSaber;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;
import starwars.entities.actors.behaviors.Patrol;

/**
 * Ben (aka Obe-Wan) Kenobi.  
 * 
 * At this stage, he's an extremely strong critter with a <code>Lightsaber</code>
 * who wanders around in a fixed pattern and neatly slices any Actor not on his
 * team with his lightsaber.
 * 
 * Note that you can only create ONE Ben, like all SWLegends.
 * @author rober_000
 * @author Chai Lam, extended the functionality of Ben Kenobi
 *
 */
public class BenKenobi extends SWLegend {

	private static BenKenobi ben = null; // yes, it is OK to return the static instance!
	private Patrol path;
	private SWActor student;
	
	/*The value which BenKenobi can increase the force number of SWActor*/
	private int canAddForce = 5;
	
	private BenKenobi(MessageRenderer m, SWWorld world, Direction [] moves, boolean ability, int value) {
		super(Team.GOOD, 1000, m, world,ability,value);
		path = new Patrol(moves);
		this.setShortDescription("Ben Kenobi");
		this.setLongDescription("Ben Kenobi, an old man who has perhaps seen too much");
		LightSaber bensweapon = new LightSaber(m);
		setItemCarried(bensweapon);
	}

	public static BenKenobi getBenKenobi(MessageRenderer m, SWWorld world, Direction [] moves,boolean ability, int value) {
		ben = new BenKenobi(m, world, moves,ability,value);
		ben.activate();
		return ben;
	}
	
	public void setStudent(SWActor a) {
		student = a;
	}
	
	@Override
	protected void legendAct() {
		
		/*Each movement of BenKenobi will call the train method*/
		this.train(student,canAddForce);

		if(isDead()) {
			return;
		}
		
		AttackInformation attack;
		attack = AttackNeighbours.attackLocals(ben,  ben.world, true, true);
		
		if (attack != null) {
			say(getShortDescription() + " suddenly looks sprightly and attacks " +
		attack.entity.getShortDescription());
			scheduler.schedule(attack.affordance, ben, 1);
		}
		else {
			Direction newdirection = path.getNext();
			say(getShortDescription() + " moves " + newdirection);
			Move myMove = new Move(newdirection, messageRenderer, world);
			
			scheduler.schedule(myMove, this, 1);
		}
	}
}
