package game;

import edu.monash.fit2099.engine.*;

public class Arm extends WeaponItem implements Limb {

	boolean hasIt = false;
	public Arm(String name, boolean b) {
		super(name, 'A', 18,  "blah");
		addCapability(ZombieCapability.ALIVE);
		this.hasIt = b;
		if(isHasIt())
			allowableActions.add(new CraftAction());
//		allowableActions.add(new SpecialAction());
	}

	public boolean isHasIt() {
		return hasIt;
	}

	public void setHasIt(boolean hasIt) {
		this.hasIt = hasIt;
	}

	@Override
	public PickUpItemAction getPickUpAction(){

		this.setHasIt(true);
		System.out.println("the boolean is " + isHasIt());
		Arm arm = this;
		arm.setHasIt(true);
		return new PickUpItemAction(arm);
	}
	@Override
	public int craft(Actor actor, Item item, GameMap map) {

		if(item instanceof Arm){
			actor.removeItemFromInventory(item);
			actor.addItemToInventory(new Club());
			System.out.println("craft action works");
			return 1;
		}
		return 0;
	}
}
