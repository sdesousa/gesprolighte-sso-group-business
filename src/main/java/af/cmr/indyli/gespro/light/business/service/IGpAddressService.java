package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpAddressService<Entity extends IEntity> {
	public Entity create(Entity adr);
	public void update(Entity adr);
	public List<Entity> findAll();
	public Entity findById(Integer adrId);
	public void deleteById(Integer adrId);
}
