package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpEmpReaPhaseService<Entity extends IEntity> {
	public Entity create(Entity erp);
	public void update(Entity erp);
	public List<Entity> findAll();
	public Entity findById(Integer erpId);
	public void deleteById(Integer erpId);
}
