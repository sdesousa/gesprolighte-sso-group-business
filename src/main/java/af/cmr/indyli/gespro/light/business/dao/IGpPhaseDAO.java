package af.cmr.indyli.gespro.light.business.dao;

import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpPhaseDAO<Entity extends IEntity> {
	public Entity create(Entity emp);
	public void update(Entity emp);
	public List<Entity> findAll();
	public Entity findById(Integer empId);
	public void deleteById(Integer empId);
	public boolean isDateValid(Date startPhase, Date startProject);
	public boolean isAmountValid(Date startPhase, Date endPhase, double amount);
	public boolean isPhaseCodeExist(String code);
	public boolean isPhaseCodeExistUpdate(String code, int phsId);
}
