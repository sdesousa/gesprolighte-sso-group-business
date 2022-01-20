package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public class GpOrganizationDAOImpl extends GpAbstractOrganisationDAOImpl<GpOrganization> implements IGpOrganizationDAO<GpOrganization>{
	
	@Override
	public GpOrganization create(GpOrganization org) {
		try {
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			String REQ_SQL = "INSERT INTO GP_ORGANIZATION ( ORG_CODE,NAME,PHONE_NUMBER,ADR_WEB,CONTACT_NAME,CONTACT_EMAIL ) VALUES (?,?,?,?,?,?)";
	    	Object[] tabParam = {org.getOrgCode(),org.getName(),org.getPhoneNumber(),org.getAdrWeb(),org.getContactName(),org.getContactEmail()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	String REQ_SQL_MAX_ID = "SELECT max(ORG_ID) AS MaxId FROM GP_ORGANIZATION";
	    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL_MAX_ID);
	    	if (resultat != null) {
	    		while (resultat.next()) {
	    			org.setId(resultat.getInt("MaxId"));
	    		}
	    	}
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return org;
	}

	@Override
	public void update(GpOrganization org) {
		String REQ_SQL = "UPDATE FROM GP_ORGANIZATION SET ORG_CODE=? , NAME=? , PHONE_NUMBER=? ,ADR_WEB = ? ,CONTACT_NAME=? ,CONTACT_EMAIL=? WHERE ORG_ID = ?";
    	Object[] tabParam = {org.getOrgCode(),org.getName(),org.getPhoneNumber(),org.getAdrWeb(),org.getContactName(),org.getContactEmail()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpOrganization> findAll() {
		String REQ_SQL = "SELECT * FROM GP_ORGANIZATION";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpOrganization> orgList = new ArrayList<GpOrganization>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer orgId = resultat.getInt("ORG_ID");
					String orgCode = resultat.getString("ORG_CODE");
					String orgName = resultat.getString("NAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String adrWeb = resultat.getString("ADR_WEB");
					String contactName = resultat.getString("CONTACT_NAME");
					String contactEmail = resultat.getString("CONTACT_EMAIL");
					GpOrganization foundOrg = new GpOrganization();
					foundOrg.setId(orgId);
					foundOrg.setOrgCode(orgCode);
					foundOrg.setOrgCode(orgName);
					foundOrg.setPhoneNumber(phoneNumber);
					foundOrg.setAdrWeb(adrWeb);
					foundOrg.setContactName(contactName);
					foundOrg.setContactEmail(contactEmail);
					orgList.add(foundOrg);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return orgList;
	}

	@Override
	public GpOrganization findById(Integer orgId) {
		String REQ_SQL = "SELECT * FROM GP_ORGANIZATION where ORG_ID = ?";
		Object[] tabParam = {orgId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpOrganization foundOrg = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					String orgCode = resultat.getString("ORG_CODE");
					String orgName = resultat.getString("NAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String adrWeb = resultat.getString("ADR_WEB");
					String contactName = resultat.getString("CONTACT_NAME");
					String contactEmail = resultat.getString("CONTACT_EMAIL");
					foundOrg = new GpOrganization();
					foundOrg.setId(orgId);
					foundOrg.setOrgCode(orgCode);
					foundOrg.setOrgCode(orgName);
					foundOrg.setPhoneNumber(phoneNumber);
					foundOrg.setAdrWeb(adrWeb);
					foundOrg.setContactName(contactName);
					foundOrg.setContactEmail(contactEmail);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundOrg;
	}
	
	@Override
	public String getCurrentTableName() {
		return GpOrganization.GP_ORGANIZATION_TABLE_NAME;
	}
}
