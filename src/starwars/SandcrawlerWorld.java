package starwars;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.entities.actors.Player;



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
	
	@Override
	public void initializeWorld(MessageRenderer iface) {
		SWLocation loc;
		// Set default location string
		for (int row=0; row < height(); row++) {
			for (int col=0; col < width(); col++) {
				loc = getGrid().getLocationByCoordinates(col, row);
				loc.setLongDescription("SWWorld (" + col + ", " + row + ")");
				loc.setShortDescription("SWWorld (" + col + ", " + row + ")");
				if (row == exitX && col == exitY) {
					loc.setSymbol('E');  //Exit	
				}
				else {
				loc.setSymbol('.');				
				}
			}
		}
		loc =  getGrid().getLocationByCoordinates(0,0);
		Player luke = new Player(Team.GOOD, 100, iface, this,true,50);////
		luke.setShortDescription("Luke");
		getEntitymanager().setLocation(luke, loc);
		luke.resetMoveCommands(loc);
	}
	
	public int getExitX() {
		return exitX;
	}
	
	public int getExitY() {
		return exitY;
	}
}
	

