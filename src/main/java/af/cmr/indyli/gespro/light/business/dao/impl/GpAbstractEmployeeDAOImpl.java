package af.cmr.indyli.gespro.light.business.dao.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.entity.IEntity;

public class GpAbstractEmployeeDAOImpl<Entity extends IEntity> implements IGpEmployeeDAO<Entity>{

	private GpEntityManager entityManager = new GpEntityManager();
	@Override
	public Entity create(Entity emp) {
		return null;
	}

	@Override
	public void update(Entity emp) {
		
	}

	@Override
	public List<Entity> findAll() {
		return null;
	}

	@Override
	public void deleteById(Integer empId) {
	}

	@Override
	public Entity findById(Integer empId) {
		return null;
	}

	@Override
	public boolean ifEmpExistByFileNumberOrEmail(String fileNumber, String email,String login) {
		Integer empIdForEmail = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", email, "EMP_ID");
		Integer empIdForFileNumber = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "FILE_NUMBER", fileNumber, "EMP_ID");
		Integer empIdForLogin = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "LOGIN", fileNumber, "EMP_ID");
		return empIdForEmail != null || empIdForFileNumber != null || empIdForLogin != null;
	}

	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
