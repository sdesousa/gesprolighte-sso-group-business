package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpAddressService<Entity extends IEntity> {
	public Entity create(Entity emp);
	public void update(Entity emp);
	public List<Entity> findAll();
	public Entity findById(Integer empId);
	public void deleteById(Integer empId);
}
