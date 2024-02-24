package starwars;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.Door;


	/**
	 * Set up the world for Sandcrawler
	 * @author 	chailam
	 */


public class SandcrawlerWorld extends SWWorld{
	
	private int exitX = height()-1;
	private int exitY = 0;
	
	/**
	 * Constructor of <code>SandcrawlerWorld</code>. This will initialize the <code>SWLocationMaker</code>
	 * and the grid.
	 */
	public SandcrawlerWorld() {
		super(4,4);	
	}
	
	/**
	 * Set up the world, setting descriptions for locations and placing items on the grid.
	 * 
	 * @author 	chailam
	 * @param 	iface a MessageRenderer to be passed onto newly-created entities
	 */
	@Override
	public void initializeWorld(MessageRenderer iface) {
		SWLocation loc;
		// Set default location string
		for (int row=0; row < height(); row++) {
			for (int col=0; col < width(); col++) {
				loc = getGrid().getLocationByCoordinates(col, row);
				loc.setLongDescription("SWWorld (" + col + ", " + row + ")");
				loc.setShortDescription("SWWorld (" + col + ", " + row + ")");
				loc.setSymbol('.');				
				}
			}
		
		//Set the door
		loc =  getGrid().getLocationByCoordinates(exitX,exitY);
		Door door = new Door(iface);
		door.setSymbol("��");
		SWWorld.getEntitymanager().setLocation(door, loc);
	}
	
	/**
	 * Accessor for the exitX.
	 * <p>
	 * Will return the exit coordinate X
	 * 
	 * @author chailam
	 * @return exitX
	 */
	public int getExitX() {
		return exitX;
	}
	
	/**
	 * Accessor for the exitY.
	 * <p>
	 * Will return the exit coordinate Y
	 * 
	 * @author chailam
	 * @return exitY
	 */
	public int getExitY() {
		return exitY;
	}
}
	

