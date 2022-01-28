package af.cmr.indyli.gespro.light.business.dao.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.IEntity;

public abstract class GpAbstractEmployeeDAOImpl<Entity extends IEntity> implements IGpEmployeeDAO<Entity>{

	private GpEntityManager entityManager = new GpEntityManager();
	
	public Entity create(Entity emp) {
		return null;
	}

	public void update(Entity emp) {
		
	}

	public List<Entity> findAll() {
		return null;
	}

	public void deleteById(Integer empId) {
		String REQ_SQL = "DELETE FROM "+this.getCurrentTableName()+" WHERE EMP_ID = ?";
    	Object[] tabParam = {empId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	//On supprime ensuite dans la table mere
    	if (!this.getCurrentTableName().equals(GpEmployee.GP_EMPLOYEE_TABLE_NAME)) {
    		String REQ_SQL_EMP = "DELETE FROM "+ GpEmployee.GP_EMPLOYEE_TABLE_NAME +" WHERE EMP_ID = ?";
        	this.getEntityManager().updateAvecParamGenerique(REQ_SQL_EMP, tabParam);
    	}
    	
	}

	public Entity findById(Integer empId) {
		return null;
	}

	public boolean ifEmpExistByFileNumberOrEmail(String fileNumber, String email,String login) {
		Integer empIdForEmail = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", email, "EMP_ID");
		Integer empIdForFileNumber = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "FILE_NUMBER", fileNumber, "EMP_ID");
		Integer empIdForLogin = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "LOGIN", login, "EMP_ID");
		return empIdForEmail != null || empIdForFileNumber != null || empIdForLogin != null;
	}
	
	public boolean ifEmpExistByFileNumberOrEmailUpdate(String fileNumber, String email,String login, int empId) {
		Integer empIdForEmail = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", email, "EMP_ID");
		Integer empIdForFileNumber = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "FILE_NUMBER", fileNumber, "EMP_ID");
		Integer empIdForLogin = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "LOGIN", login, "EMP_ID");
		return (empIdForEmail != null && empIdForEmail != empId) 
				|| (empIdForFileNumber != null && empIdForFileNumber != empId) 
				|| (empIdForLogin != null && empIdForLogin != empId
		);
	}
	
	public boolean ifFieldEmpty(String field) {
		boolean emptyField = (field == null || field.trim().isEmpty());
		return emptyField;
	}
	
	public boolean ifEmpFieldEmpty(String email, String login, String fileNumber, String firstname, String lastname) {
		return ( ifFieldEmpty(email) || ifFieldEmpty(login) || ifFieldEmpty(fileNumber) || ifFieldEmpty(firstname) || ifFieldEmpty(lastname) );
	}

	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public abstract String getCurrentTableName();

}
