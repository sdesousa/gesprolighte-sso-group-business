package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpPhaseDAO<Entity extends IEntity> {
	public Entity create(Entity phase);

	public void update(Entity phase);

	public List<Entity> findAll();

	public void deleteById(Integer phaseId);

	public Entity findById(Integer phaseId);
}