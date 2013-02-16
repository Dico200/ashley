package ashley.systems;

import ashley.core.Engine;
import ashley.core.Entity;
import ashley.core.EntitySystem;
import ashley.core.Family;
import ashley.utils.Array;

/**
 * A simple EntitySystem that iterates over each entity and calls processEntity() for each entity every time
 * the EntitySystem is updated. This is really just a convenience class as most systems iterate over a list
 * of entities.
 * 
 * @author Stefan Bachmann
 */
public abstract class IteratingSystem extends EntitySystem {
	/** The family describing this systems entities */
	private Family family;
	/** The entities used by this system */
	private Array<Entity> entities;
	
	/**
	 * Instantiates a system that will iterate over the entities described by the Family.
	 * @param family The family of entities iterated over in this System
	 */
	public IteratingSystem(Family family){
		this.family = family;
	}
		
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(family);
	}

	@Override
	public void removedFromEngine(Engine engine) {
		entities = null;
	}

	@Override
	public void update(float deltaTime) {
		for(int i=0; i<entities.size; i++)
			processEntity(entities.get(i));
	}

	/**
	 * This method is called on every entity on every update call of the EntitySystem. Override this to implement
	 * your system's specific processeing.
	 * @param entity The current Entity being processed
	 */
	public abstract void processEntity(Entity entity);
}