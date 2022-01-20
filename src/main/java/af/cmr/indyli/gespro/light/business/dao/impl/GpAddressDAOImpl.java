package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;

public class GpAddressDAOImpl extends GpAbstractAddressDAOImpl<GpAddress> implements IGpAddressDAO<GpAddress>{
	
	private GpEmployeeDAOImpl empImpl = new GpEmployeeDAOImpl();
	private GpOrganizationDAOImpl orgImpl = new GpOrganizationDAOImpl();
	
	@Override
	public GpAddress create(GpAddress adr) {
		try {
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			String REQ_SQL = "INSERT INTO GP_ADDRESS ( STREET_NUMBER,STREET_LABEL,ZIP_CODE,COUNTRY,IS_MAIN,ORG_ID,EMP_ID) VALUES (?,?,?,?,?,?,?)";
	    	Object[] tabParam = {adr.getStreetNumber(),adr.getStreetLabel(),adr.getZipCode(),adr.getCountry(),adr.getIsMain(),adr.getGpOrganization().getId(), adr.getGpEmployee().getId()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	String REQ_SQL_MAX_ID = "SELECT max(ADDRESS_ID) AS MaxId FROM GP_ADDRESS";
	    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL_MAX_ID);
	    	adr.setId(resultat.getInt("MaxId"));
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return adr;
	}

	@Override
	public void update(GpAddress adr) {
		String REQ_SQL = "UPDATE FROM GP_ADDRESS SET STREET_NUMBER=? , STREET_LABEL=? , ZIP_CODE=? ,COUNTRY = ? ,IS_MAIN=? ,ORG_ID=? ,EMP_ID WHERE ADDRESS_ID = ?";
    	Object[] tabParam = {adr.getStreetNumber(),adr.getStreetLabel(),adr.getZipCode(),adr.getCountry(),adr.getIsMain(),adr.getGpOrganization().getId(),adr.getGpEmployee().getId()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpAddress> findAll() {
		String REQ_SQL = "SELECT * FROM GP_ADDRESS";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpAddress> adrList = new ArrayList<GpAddress>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer adrId = resultat.getInt("ADDRESS_ID");
					Integer streetNumber = resultat.getInt("STREET_NUMBER");
					String streetLabel = resultat.getString("STREET_LABEL");
					Integer zipCode = resultat.getInt("ZIP_CODE");
					String country = resultat.getString("COUNTRY");
					byte isMain = (byte) resultat.getInt("IS_MAIN");
					Integer empId = resultat.getInt("EMP_ID");
					Integer orgId = resultat.getInt("ORG_ID");
					GpAddress foundAdr = new GpAddress();
					foundAdr.setId(adrId);
					foundAdr.setStreetNumber(streetNumber);;
					foundAdr.setStreetLabel(streetLabel);
					foundAdr.setZipCode(zipCode);
					foundAdr.setCountry(country);
					foundAdr.setIsMain(isMain);
					foundAdr.setGpEmployee(empImpl.findById(empId));
					foundAdr.setGpOrganization(orgImpl.findById(orgId));
					adrList.add(foundAdr);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return adrList;
	}

	@Override
	public GpAddress findById(Integer adrId) {
		String REQ_SQL = "SELECT * FROM GP_ADDRESS where ADDRESS_ID = ?";
		Object[] tabParam = {adrId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpAddress foundEmp = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer streetNumber = resultat.getInt("STREET_NUMBER");
					String streetLabel = resultat.getString("STREET_LABEL");
					Integer zipCode = resultat.getInt("ZIP_CODE");
					String country = resultat.getString("COUNTRY");
					byte isMain = (byte) resultat.getInt("IS_MAIN");
					Integer empId = resultat.getInt("EMP_ID");
					Integer orgId = resultat.getInt("ORG_ID");
					GpAddress foundAdr = new GpAddress();
					foundAdr.setId(adrId);
					foundAdr.setStreetNumber(streetNumber);;
					foundAdr.setStreetLabel(streetLabel);
					foundAdr.setZipCode(zipCode);
					foundAdr.setCountry(country);
					foundAdr.setIsMain(isMain);
					foundAdr.setGpEmployee(empImpl.findById(empId));
					foundAdr.setGpEmployee(empImpl.findById(empId));
					foundAdr.setGpOrganization(orgImpl.findById(orgId));
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundEmp;
	}
	
	@Override
	public String getCurrentTableName() {
		return GpAddress.GP_ADDRESS_TABLE_NAME;
	}
}
