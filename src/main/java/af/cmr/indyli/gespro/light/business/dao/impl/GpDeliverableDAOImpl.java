package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDeliverableDAO;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;

public class GpDeliverableDAOImpl implements IGpDeliverableDAO<GpDeliverable>{
	
	private GpEntityManager entityManager = new GpEntityManager();
	private GpPhaseDAOImpl phsImpl = new GpPhaseDAOImpl();
	
	@Override
	public GpDeliverable create(GpDeliverable del) {
		try {
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			String REQ_SQL = "INSERT INTO GP_DELIVERABLE ( DEL_CODE, LABEL, DESCRIPTION, DEL_PATH, CREATION_DATE, PHASE_ID) VALUES (?,?,?,?,?,?)";
	    	Object[] tabParam = {del.getDelCode(),del.getLabel(),del.getDescription(),del.getDelPath(),new Date(),del.getGpPhase().getId()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	String REQ_SQL_MAX_ID = "SELECT max(DEL_ID) AS MaxId FROM GP_DELIVERABLE";
	    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL_MAX_ID);
	    	if (resultat != null) {
	    		while (resultat.next()) {
	    			del.setId(resultat.getInt("MaxId"));
	    		}
	    	}
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return del;
	}

	@Override
	public void update(GpDeliverable del) {
		String REQ_SQL = "UPDATE GP_DELIVERABLE SET DEL_CODE=?,LABEL=?,DESCRIPTION=?,DEL_PATH=?,PHASE_ID=? WHERE DEL_ID = ?";
    	Object[] tabParam = {del.getDelCode(),del.getLabel(),del.getDescription(),del.getDelPath(),del.getGpPhase().getId(),del.getId()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	
	// TODO
	@Override
	public List<GpDeliverable> findAll() {
		String REQ_SQL = "SELECT * FROM GP_DELIVERABLE";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpDeliverable> delList = new ArrayList<GpDeliverable>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer delId = resultat.getInt("DEL_ID");
					String delCode = resultat.getString("DEL_CODE");
					String description = resultat.getString("DESCRIPTION");
					String label = resultat.getString("LABEL");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String delPath = resultat.getString("DEL_PATH");
					int phsId = resultat.getInt("PHASE_ID");
					GpDeliverable foundDel = new GpDeliverable();
					foundDel.setId(delId);
					foundDel.setDelCode(delCode);
					foundDel.setLabel(label);
					foundDel.setDescription(description);
					foundDel.setCreationDate(creationDate);
					foundDel.setDelPath(delPath);
					foundDel.setGpPhase(phsImpl.findById(phsId));
					delList.add(foundDel);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return delList;
	}

	// TODO
	@Override
	public GpDeliverable findById(Integer delId) {
		String REQ_SQL = "SELECT * FROM GP_DELIVERABLE where DEL_ID = ?";
		Object[] tabParam = {delId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpDeliverable foundDel = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					String delCode = resultat.getString("DEL_CODE");
					String description = resultat.getString("DESCRIPTION");
					String label = resultat.getString("LABEL");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String delPath = resultat.getString("DEL_PATH");
					int phsId = resultat.getInt("PHASE_ID");
					foundDel = new GpDeliverable();
					foundDel.setId(delId);
					foundDel.setDelCode(delCode);
					foundDel.setLabel(label);
					foundDel.setDescription(description);
					foundDel.setCreationDate(creationDate);
					foundDel.setDelPath(delPath);
					foundDel.setGpPhase(phsImpl.findById(phsId));
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundDel;
	}
	
	public void deleteById(Integer delId) {
		String REQ_SQL = "DELETE FROM "+this.getCurrentTableName()+" WHERE DEL_ID = ?";
    	Object[] tabParam = {delId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	
	}
	
	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public String getCurrentTableName() {
		return GpDeliverable.GP_DELIVERABLE_TABLE_NAME;
	}
}
