package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Leave;
import starwars.actions.Take;
import starwars.actions.Throw;
/**
 * An entity that has the <code>WEAPON</code> attribute and so can
 * be used to <code>Throw</code> on others, etc.
 *
 * @author 	Vinitha RAj RAjagopal Muthu
 *
 * @see 	{@link starwars.actions.Throw}
 */
/*
 */
public class Grenade extends SWEntity {
    /**
     * Constructor for the <code>Grenade</code> class. This constructor will,
     * <ul>
     * 	<li>Initialize the message renderer for the <code>Grenade</code></li>
     * 	<li>Set the short description of this <code>Grenade</code> to "A Grenade"</li>
     * 	<li>Set the long description of this <code>Grenade</code> to "A grenade!!!! BOOM DEAD!"</li>
     * 	<li>Set the hit points of this <code>Grenade</code> to 200</li>
     * 	<li>Add a <code>Take</code> affordance to this <code>Grenade</code> so it can be taken</li>
     *	<li>Add a <code>WEAPON Capability</code> to this <code>Grenade</code> so it can be used to <code>Throw</code></li>
     * </ul>
     *
     * @param m <code>MessageRenderer</code> to display messages.
     *
     * @see {@link starwars.actions.Take}
     * @see {@link starwars.Capability}
     * @see {@link starwars.actions.Throw}
     */
    public Grenade(MessageRenderer m){
        super(m);
        this.longDescription="A grenade!!!! BOOM DEAD!";
        this.shortDescription="A Grenade";
        this.addAffordance(new Take(this,m));
        this.addAffordance(new Leave(this,m));
        this.addAffordance(new Throw(this,m));
        this.capabilities.add(Capability.WEAPON);
        this.hitpoints=200;
    }
    /**
     * Method insists damage on this <code>Grenade</code> by removing all of its <code>hitpoints</code>
     * <p>
     * This method will also change this <code>Grenade</code>s <code>longDescription</code> to
     * "Grenade has been used up and disappeared"  and this <code>Grenade</code>s <code>shortDescription</code> to
     * "Grenade gone".
     * <p>
     *
     * @author 	Vinitha Raj Rajagopal Muthu
     * @param 	damage the amount of <code>hitpoints</code> to be reduced
     * @see 	{@link starwars.actions.Throw}
     */
    @Override
    public void takeDamage(int damage){
        damage=this.hitpoints;
        super.takeDamage(damage);
        this.shortDescription="Grenade gone";
        this.longDescription="Grenade has been used up and disappeared";
        this.capabilities.remove(Capability.WEAPON);
    }
    /**
     * A symbol that is used to represent the Grenade on a text based user interface
     *
     * @return 	Double Character string "G!"
     * @see 	{@link starwars.SWEntityInterface#getSymbol()}
     */
    @Override
    public String getSymbol() {
        return "G!";
    }
}
