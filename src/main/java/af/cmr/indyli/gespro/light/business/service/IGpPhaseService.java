package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpPhaseService<Entity extends IEntity> {
	public Entity create(Entity phs);
	public void update(Entity phs);
	public List<Entity> findAll();
	public Entity findById(Integer phsId);
	public void deleteById(Integer phsId);
}
