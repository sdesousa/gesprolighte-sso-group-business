package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpPhaseService<Entity extends IEntity> {
	public Entity create(Entity phs) throws GesproBusinessException;
	public void update(Entity phs) throws GesproBusinessException;
	public List<Entity> findAll();
	public Entity findById(Integer phsId);
	public void deleteById(Integer phsId);
}
