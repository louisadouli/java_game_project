package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

public class SniperRifle extends RangedWeapon {
	private static int num = 1;
	private BulletType type = BulletType.Sniper;
	private int aim = 0;
	private Actor aimTarget = null;
	private Actor shootTarget = null;

	public SniperRifle() {
		super("Sniper Rifle " + num, '-', 45, "shoots");
		setBarrelSize(1);
	}

	public void reload(Ammo ammo) {
		if (ammoList.size() < barrelSize && ammo != null) {
			ammoList.add(ammo);
		}
	}
	
	public void empty() {
		if (hasAmmo()) {
			ammoList.remove(0);
		}
	}
	
	public void incAim() {
		if (this.aim <= 2) {
			this.aim++;
		}
	}
	
	public void resetAim() {
		this.aim = 0;
	}
	
	public int getAim() {
		return this.aim;
	}
	
	public List<Action> getAllowableAction(Actor actor) {
		List<Action> actionList = new ArrayList<>();
		List<Ammo> ammoList = new ArrayList<>();
		ReloadAction reloadAction = new ReloadAction(this, ammoList);
		
		for (Item i : actor.getInventory()) {
			if (i.asAmmo(i) != null) {
				ammoList.add(i.asAmmo(i));
			}
		}
		
		if (this.hasAmmo()) {
			actionList.add(new SniperRifleShootAction(this));
			if (this.aim < 2) {
				actionList.add(new SniperRifleAimAction(this));
			}
		}
		else if (ammoList.size() > 0) {
			for (Ammo a : ammoList) {
				if (reloadAction.compatible(this, a)) {
					actionList.add(reloadAction);
				}
			}
		}
		
		return actionList;
	}

	@Override
	public BulletType getBulletType() {
		return type;
	}
	
	public void setAimTarget(Actor target) {
		this.aimTarget = target;
	}
	
	public Actor getAimTarget() {
		return aimTarget;
	}
	
	public void setShootTarget(Actor target) {
		this.shootTarget = target;
	}
	
	public Actor getShootTarget() {
		return shootTarget;
	}

	@Override
	public int getMeleeDamage() {
		setVerb("hits");
		return 10;
	}
	
	@Override
	public int damage() {
		if (this.aim == 1) {
			return 45 * 2;
		}
		else if (this.aim == 2) {
			return 1000000000;
		}
		
		return 45;
	}
	
}
