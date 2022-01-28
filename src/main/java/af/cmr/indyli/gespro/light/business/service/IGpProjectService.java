package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpProjectService<Entity extends IEntity> {
	public Entity create(Entity prj) throws GesproBusinessException;
	public void update(Entity prj) throws GesproBusinessException;
	public List<Entity> findAll();
	public Entity findById(Integer prjId);
	public void deleteById(Integer prjId);
}
