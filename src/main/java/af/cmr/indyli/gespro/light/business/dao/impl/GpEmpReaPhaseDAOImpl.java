package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmpReaPhaseDAO;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;

public class GpEmpReaPhaseDAOImpl implements IGpEmpReaPhaseDAO<GpEmpReaPhase>{
	
	private GpEntityManager entityManager = new GpEntityManager();
	private GpEmployeeDAOImpl empImpl = new GpEmployeeDAOImpl();
	private GpPhaseDAOImpl phsImpl = new GpPhaseDAOImpl();
	
	@Override
	public GpEmpReaPhase create(GpEmpReaPhase erp) {
		try {
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			String REQ_SQL = "INSERT INTO GP_EMP_REA_PHASE ( CREATION_DATE,EMP_ID,PHASE_ID ) VALUES (?,?,?)";
	    	Object[] tabParam = {new Date(),erp.getGpEmployee().getId(),erp.getGpPhase().getId()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	String REQ_SQL_MAX_ID = "SELECT max(ASSO_REA_ID) AS MaxId FROM GP_EMP_REA_PHASE";
	    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL_MAX_ID);
	    	if (resultat != null) {
	    		while (resultat.next()) {
	    			erp.setId(resultat.getInt("MaxId"));
	    		}
	    	}
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return erp;
	}

	@Override
	public void update(GpEmpReaPhase erp) {
		String REQ_SQL = "UPDATE GP_EMP_REA_PHASE SET EMP_ID=?, PHASE_ID=? WHERE ASSO_REA_ID = ?";
    	Object[] tabParam = {erp.getGpEmployee().getId(),erp.getGpPhase().getId(),erp.getId()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpEmpReaPhase> findAll() {
		String REQ_SQL = "SELECT * FROM GP_EMP_REA_PHASE";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpEmpReaPhase> erpList = new ArrayList<GpEmpReaPhase>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer erpId = resultat.getInt("ASSO_REA_ID");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Integer empId = resultat.getInt("EMP_ID");
					Integer phsId = resultat.getInt("PHASE_ID");
					GpEmpReaPhase foundErp = new GpEmpReaPhase();
					foundErp.setId(erpId);
					foundErp.setCreationDate(creationDate);
					foundErp.setGpEmployee(empImpl.findById(empId));
					foundErp.setGpPhase(phsImpl.findById(phsId));
					erpList.add(foundErp);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return erpList;
	}

	@Override
	public GpEmpReaPhase findById(Integer erpId) {
		String REQ_SQL = "SELECT * FROM GP_EMP_REA_PHASE where ASSO_REA_ID = ?";
		Object[] tabParam = {erpId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpEmpReaPhase foundErp = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Date creationDate = resultat.getDate("CREATION_DATE");
					Integer empId = resultat.getInt("EMP_ID");
					Integer phsId = resultat.getInt("PHASE_ID");
					foundErp = new GpEmpReaPhase();
					foundErp.setId(erpId);
					foundErp.setCreationDate(creationDate);
					foundErp.setGpEmployee(empImpl.findById(empId));
					foundErp.setGpPhase(phsImpl.findById(phsId));
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundErp;
	}
	
	public void deleteById(Integer erpId) {
		String REQ_SQL = "DELETE FROM "+this.getCurrentTableName()+" WHERE ASSO_REA_ID = ?";
    	Object[] tabParam = {erpId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	
	}
	
	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public String getCurrentTableName() {
		return GpEmpReaPhase.GP_EMP_REA_PHASE_TABLE_NAME;
	}
}
