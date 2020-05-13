package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

public class FertilizeBehaviour implements Behaviour {

	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an UnripeCrop on me?
		if (map.locationOf(actor).getDisplayChar() == 'c') {
			return new FertilizeAction(map.locationOf(actor));
		}
		return null;
	}
}
