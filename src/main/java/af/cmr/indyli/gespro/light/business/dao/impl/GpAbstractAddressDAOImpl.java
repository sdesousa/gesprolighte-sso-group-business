package af.cmr.indyli.gespro.light.business.dao.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.entity.IEntity;

public abstract class GpAbstractAddressDAOImpl<Entity extends IEntity> implements IGpAddressDAO<Entity>{

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
    	
	}

	public Entity findById(Integer empId) {
		return null;
	}

	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public abstract String getCurrentTableName();

}
