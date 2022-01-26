package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpDeliverableService<Entity extends IEntity> {
	public Entity create(Entity dlr);
	public void update(Entity dlr);
	public List<Entity> findAll();
	public Entity findById(Integer dlrId);
	public void deleteById(Integer dlrId);
}
