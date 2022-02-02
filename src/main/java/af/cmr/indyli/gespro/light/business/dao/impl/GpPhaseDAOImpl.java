package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpPhaseDAOImpl implements IGpPhaseDAO<GpPhase>{
	
	private GpEntityManager entityManager = new GpEntityManager();
	private GpProjectDAOImpl prjImpl = new GpProjectDAOImpl();
	
	@Override
	public GpPhase create(GpPhase phs) {
		try {
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			String REQ_SQL = "INSERT INTO GP_PHASE ( PHASE_CODE,DESCRIPTION,START_DATE,END_DATE,AMOUNT,STATUS,IS_ENDED,CREATION_DATE,PROJECT_ID) VALUES (?,?,?,?,?,?,?,?,?)";
	    	Object[] tabParam = {phs.getPhaseCode(),phs.getDescription(),phs.getStartDate(),phs.getEndDate(),phs.getAmount(),phs.getStatus(),phs.getIsEnded(),new Date(),phs.getGpProject().getId()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	String REQ_SQL_MAX_ID = "SELECT max(PHASE_ID) AS MaxId FROM GP_PHASE";
	    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL_MAX_ID);
	    	if (resultat != null) {
	    		while (resultat.next()) {
	    			phs.setId(resultat.getInt("MaxId"));
	    		}
	    	}
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phs;
	}

	@Override
	public void update(GpPhase phs) {
		String REQ_SQL = "UPDATE GP_PHASE SET PHASE_CODE=?,DESCRIPTION=?,START_DATE=?,END_DATE=?,AMOUNT=?,STATUS=?,IS_ENDED=?,UPDATE_DATE=?,PROJECT_ID=? WHERE PHASE_ID = ?";
    	Object[] tabParam = {phs.getPhaseCode(),phs.getDescription(),phs.getStartDate(),phs.getEndDate(),phs.getAmount(),phs.getStatus(),phs.getIsEnded(),new Date(),phs.getGpProject().getId(),phs.getId()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpPhase> findAll() {
		String REQ_SQL = "SELECT * FROM GP_PHASE";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpPhase> phsList = new ArrayList<GpPhase>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer phsId = resultat.getInt("PHASE_ID");
					String phaseCode = resultat.getString("PHASE_CODE");
					String description = resultat.getString("DESCRIPTION");
					Date startDate = resultat.getDate("START_DATE");
					Date endDate = resultat.getDate("END_DATE");
					double amount = resultat.getDouble("AMOUNT");
					byte status = (byte) resultat.getInt("STATUS");
					byte isEnded = (byte) resultat.getInt("IS_ENDED");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Date updateDate = resultat.getDate("UPDATE_DATE");
					int prjId = resultat.getInt("PROJECT_ID");
					GpPhase foundPhs = new GpPhase();
					foundPhs.setId(phsId);
					foundPhs.setPhaseCode(phaseCode);
					foundPhs.setDescription(description);
					foundPhs.setStartDate(startDate);
					foundPhs.setEndDate(endDate);
					foundPhs.setAmount(amount);
					foundPhs.setStatus(status);
					foundPhs.setIsEnded(isEnded);
					foundPhs.setCreationDate(creationDate);
					foundPhs.setUpdateDate(updateDate);
					foundPhs.setGpProject(prjImpl.findById(prjId));
					phsList.add(foundPhs);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return phsList;
	}

	@Override
	public GpPhase findById(Integer phsId) {
		String REQ_SQL = "SELECT * FROM GP_PHASE where PHASE_ID = ?";
		Object[] tabParam = {phsId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpPhase foundPhs = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					String phaseCode = resultat.getString("PHASE_CODE");
					String description = resultat.getString("DESCRIPTION");
					Date startDate = resultat.getDate("START_DATE");
					Date endDate = resultat.getDate("END_DATE");
					double amount = resultat.getDouble("AMOUNT");
					byte status = (byte) resultat.getInt("STATUS");
					byte isEnded = (byte) resultat.getInt("IS_ENDED");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Date updateDate = resultat.getDate("UPDATE_DATE");
					int prjId = resultat.getInt("PROJECT_ID");
					foundPhs = new GpPhase();
					foundPhs.setId(phsId);
					foundPhs.setPhaseCode(phaseCode);
					foundPhs.setDescription(description);
					foundPhs.setStartDate(startDate);
					foundPhs.setEndDate(endDate);
					foundPhs.setAmount(amount);
					foundPhs.setStatus(status);
					foundPhs.setIsEnded(isEnded);
					foundPhs.setCreationDate(creationDate);
					foundPhs.setUpdateDate(updateDate);
					foundPhs.setGpProject(prjImpl.findById(prjId));
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundPhs;
	}
	
	public void deleteById(Integer phsId) {
		String REQ_SQL = "DELETE FROM "+this.getCurrentTableName()+" WHERE PHASE_ID = ?";
    	Object[] tabParam = {phsId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	
	}
	
	public boolean isDateValid(Date startPhase, Date startProject) {
		return startPhase.compareTo(startProject) >= 0;
	}
	
	public boolean isAmountValid(Date startPhase, Date endPhase, double amount) {
		boolean valid = true;
		Date startDatePlusSix = new Date();
        startDatePlusSix.setMonth(startPhase.getMonth() + 6);
        if ( startDatePlusSix.compareTo(endPhase) <= 0) valid = amount >= 150000;
        return valid;
	}
	
	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public String getCurrentTableName() {
		return GpPhase.GP_PHASE_TABLE_NAME;
	}
}
