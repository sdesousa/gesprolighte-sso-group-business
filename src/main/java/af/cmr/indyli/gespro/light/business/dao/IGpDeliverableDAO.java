package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpDeliverableDAO <Entity extends IEntity> {
	public Entity create(Entity project);
	public void update(Entity project);
	public List<Entity> findAll();
	public void deleteById(Integer projectId);
	public Entity findById(Integer projectId);
}		