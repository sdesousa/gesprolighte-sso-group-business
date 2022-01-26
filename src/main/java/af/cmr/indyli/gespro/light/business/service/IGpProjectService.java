package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpProjectService<Entity extends IEntity> {
	public Entity create(Entity prj);
	public void update(Entity prj);
	public List<Entity> findAll();
	public Entity findById(Integer prjId);
	public void deleteById(Integer prjId);
}
