package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpOrganizationService<Entity extends IEntity> {
	public Entity create(Entity org);
	public void update(Entity org);
	public List<Entity> findAll();
	public Entity findById(Integer orgId);
	public void deleteById(Integer orgId);
}
