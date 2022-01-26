package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpBillService<Entity extends IEntity> {
	public Entity create(Entity bll);
	public void update(Entity bll);
	public List<Entity> findAll();
	public Entity findById(Integer bllId);
	public void deleteById(Integer bllId);
}
