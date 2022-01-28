package af.cmr.indyli.gespro.light.business.dao;

import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpProjectDAO<Entity extends IEntity> {
	public Entity create(Entity emp);
	public void update(Entity emp);
	public List<Entity> findAll();
	public Entity findById(Integer empId);
	public void deleteById(Integer empId);
	public boolean isProjectManagerCreated(Integer pmId);
	public boolean isOrganizationCreated(Integer orgId);
	public boolean isProjectCodeExist(String code);
	public boolean isProjectCodeExistUpdate(String code, int prjId);
	public boolean isDateExist(Date date);
	public boolean isDateValid(Date start, Date end);
}
