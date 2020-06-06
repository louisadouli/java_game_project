package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SniperAimAction extends Action {
	private SniperRifle sniper;

	public SniperAimAction(SniperRifle sniper) {
		this.sniper = sniper;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
//		sniper.incAim();
		return actor + " is aiming";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " aims using " + sniper;
	}

	public SniperRifle getSniper() {
		return sniper;
	}

}
