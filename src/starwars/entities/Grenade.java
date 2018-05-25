package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Leave;
import starwars.actions.Take;
import starwars.actions.Throw;

public class Grenade extends SWEntity {
    public Grenade(MessageRenderer m){
        super(m);
        this.longDescription="A grenade!!!! BOOM DEAD!";
        this.shortDescription="A Grenade";
        this.addAffordance(new Take(this,m));
        //this.addAffordance(new Leave(this,m));
        this.addAffordance(new Throw(this,m));
        this.capabilities.add(Capability.WEAPON);
        this.hitpoints=200;
    }

    @Override
    public void takeDamage(int damage){
        damage=this.hitpoints;
        super.takeDamage(damage);
        this.shortDescription="Grenade gone";
        this.longDescription="Grenade has been used up and disappeared";
        this.capabilities.remove(Capability.WEAPON);
    }

    @Override
    public String getSymbol() {
        return "G!";
    }
}
