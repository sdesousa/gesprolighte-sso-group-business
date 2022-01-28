package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.entity.GpProject;

public class GpProjectDAOImpl implements IGpProjectDAO<GpProject>{
	
	private GpEntityManager entityManager = new GpEntityManager();
	private GpProjectManagerDAOImpl empImpl = new GpProjectManagerDAOImpl();
	private GpOrganizationDAOImpl orgImpl = new GpOrganizationDAOImpl();
	
	@Override
	public GpProject create(GpProject prj) {
		try {
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			String REQ_SQL = "INSERT INTO GP_PROJECT ( PROJECT_CODE,NAME,DESCRIPTION,START_DATE,END_DATE,AMOUNT,CREATION_DATE,ORG_ID,EMP_ID) VALUES (?,?,?,?,?,?,?,?,?)";
	    	Object[] tabParam = {prj.getProjectCode(),prj.getName(),prj.getDescription(),prj.getStartDate(),prj.getEndDate(),prj.getAmount(), new Date(),prj.getGpOrganization().getId(),prj.getGpChefProjet().getId()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	String REQ_SQL_MAX_ID = "SELECT max(PROJECT_ID) AS MaxId FROM GP_PROJECT";
	    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL_MAX_ID);
	    	if (resultat != null) {
	    		while (resultat.next()) {
	    			prj.setId(resultat.getInt("MaxId"));
	    		}
	    	}
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prj;
	}

	@Override
	public void update(GpProject prj) {
		String REQ_SQL = "UPDATE GP_PROJECT SET PROJECT_CODE=?,NAME=?,DESCRIPTION=?,START_DATE=?,END_DATE=?,AMOUNT=?,UPDATE_DATE=?,ORG_ID=?,EMP_ID=? WHERE PROJECT_ID = ?";
    	Object[] tabParam = {prj.getProjectCode(),prj.getName(),prj.getDescription(),prj.getStartDate(),prj.getEndDate(),prj.getAmount(), new Date(),prj.getGpOrganization().getId(),prj.getGpChefProjet().getId(),prj.getId()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpProject> findAll() {
		String REQ_SQL = "SELECT * FROM GP_PROJECT";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpProject> prjList = new ArrayList<GpProject>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer prjId = resultat.getInt("PROJECT_ID");
					String projectCode = resultat.getString("PROJECT_CODE");
					String name = resultat.getString("NAME");
					String description = resultat.getString("DESCRIPTION");
					Date startDate = resultat.getDate("START_DATE");
					Date endDate = resultat.getDate("END_DATE");
					double amount = resultat.getDouble("AMOUNT");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Date updateDate = resultat.getDate("UPDATE_DATE");
					int orgId = resultat.getInt("ORG_ID");
					int empId = resultat.getInt("EMP_ID");
					GpProject foundPrj = new GpProject();
					foundPrj.setId(prjId);
					foundPrj.setProjectCode(projectCode);
					foundPrj.setName(name);
					foundPrj.setDescription(description);
					foundPrj.setStartDate(startDate);
					foundPrj.setEndDate(endDate);
					foundPrj.setAmount(amount);
					foundPrj.setCreationDate(creationDate);
					foundPrj.setUpdateDate(updateDate);
					foundPrj.setGpOrganization(orgImpl.findById(orgId));
					foundPrj.setGpChefProjet(empImpl.findById(empId));
					prjList.add(foundPrj);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return prjList;
	}

	@Override
	public GpProject findById(Integer prjId) {
		String REQ_SQL = "SELECT * FROM GP_PROJECT where PROJECT_ID = ?";
		Object[] tabParam = {prjId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpProject foundPrj = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					String projectCode = resultat.getString("PROJECT_CODE");
					String name = resultat.getString("NAME");
					String description = resultat.getString("DESCRIPTION");
					Date startDate = resultat.getDate("START_DATE");
					Date endDate = resultat.getDate("END_DATE");
					double amount = resultat.getDouble("AMOUNT");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Date updateDate = resultat.getDate("UPDATE_DATE");
					int orgId = resultat.getInt("ORG_ID");
					int empId = resultat.getInt("EMP_ID");
					foundPrj = new GpProject();
					foundPrj.setId(prjId);
					foundPrj.setProjectCode(projectCode);
					foundPrj.setName(name);
					foundPrj.setDescription(description);
					foundPrj.setStartDate(startDate);
					foundPrj.setEndDate(endDate);
					foundPrj.setAmount(amount);
					foundPrj.setCreationDate(creationDate);
					foundPrj.setUpdateDate(updateDate);
					foundPrj.setGpOrganization(orgImpl.findById(orgId));
					foundPrj.setGpChefProjet(empImpl.findById(empId));
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundPrj;
	}
	
	public void deleteById(Integer prjId) {
		String REQ_SQL = "DELETE FROM "+this.getCurrentTableName()+" WHERE PROJECT_ID = ?";
    	Object[] tabParam = {prjId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	
	}
	
	public boolean isProjectManagerCreated(Integer pmId) {
		return pmId != null && empImpl.findById(pmId) !=null;
	}
	
	public boolean isOrganizationCreated(Integer orgId) {
		return orgId != null && orgImpl.findById(orgId) !=null;
	}
	
	public boolean isProjectCodeExist(String code) {
		Integer prjIdForCode = this.entityManager.findIdByAnyColumn("GP_PROJECT", "PROJECT_CODE", code, "PROJECT_ID");
		return prjIdForCode != null;
	}
	
	public boolean isProjectCodeExistUpdate(String code, int prjId) {
		Integer prjIdForCode = this.entityManager.findIdByAnyColumn("GP_PROJECT", "PROJECT_CODE", code, "PROJECT_ID");
		return (prjIdForCode != null && prjIdForCode != prjId);
	}
	
	public boolean isDateExist(Date date) {
		return date != null;
	}
	
	public boolean isDateValid(Date start, Date end) {
		boolean valid = true;
		if (isDateExist(end)) {
			valid = start.compareTo(end) < 0;
		}
		return valid;
		
	}
	
	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public String getCurrentTableName() {
		return GpProject.GP_PROJECT_TABLE_NAME;
	}
}
