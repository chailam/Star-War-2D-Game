package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.entities.Grenade;

public class Throw extends SWAffordance implements SWActionInterface{
    public Throw(SWEntityInterface targetObject, MessageRenderer m){
        super(targetObject,m);


    }

    @Override
    public void act(SWActor a) {
        SWEntityInterface target=this.getTarget();
        SWEntity itemCarried=(SWEntity) a.getItemCarried();
        if(itemCarried instanceof Grenade){
            
        }
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean canDo(SWActor a) {
        return false;
    }
}
