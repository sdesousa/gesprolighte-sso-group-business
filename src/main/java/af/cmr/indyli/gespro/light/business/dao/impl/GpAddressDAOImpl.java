package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;

public class GpAddressDAOImpl extends GpAbstractEmployeeDAOImpl<GpAddress> implements IGpAddressDAO {

	public GpAddress create(GpAddress addr) {
		try {
			// On demarre une transaction
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			// On commence par insérer dans la table mère avant d'inserer dans la table
			// fille
			String REQ_SQL = "INSERT INTO GP_ADDRESS (ADDRESS_ID, STREET_NUMBER, STREET_LABEL, ZIP_CODE,COUNTRY, IS_MAIN, ORG_ID, EMP_ID) VALUES (?,?,?,?,?,?,?,?)";
			Object[] tabParam = { addr.getStreetNumber(), addr.getStreetLabel(), addr.getZipCode(), addr.getCountry(),
					addr.getIsMain(), addr.getGpOrganization().getId(), addr.getGpEmployee().getId() };
			this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);

			Integer addrId = getEntityManager().findIdByAnyColumn("GP_ADDRESS", "ZIP_CODE", addr.getZipCode(),
					"ADDRESS_ID");
			addr.setId(addrId);

			this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addr;
	}

	public void update(GpAddress addr) {
		String REQ_SQL = "UPDATE  GP_ADDRESS SET STREET_NUMBER=?, STREET_LABEL = ?, ZIP_CODE = ? ,`COUNTRY`= ? ,`IS_MAIN`= ? ,`ORG_ID`= ? ,`EMP_ID`= ?   WHERE EMP_ID = ?";
		Object[] tabParam = { addr.getStreetNumber(), addr.getStreetLabel(), addr.getZipCode(), addr.getCountry(),
				addr.getIsMain(), addr.getGpOrganization().getId(), addr.getGpEmployee().getId() };
		this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	public List<GpAddress> findAll() {
		String REQ_SQL = "SELECT * FROM GP_ADDRESS";
		ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
		List<GpAddress> addrList = new ArrayList<GpAddress>();
		if (resultat != null) {
			try {
				while (resultat.next()) {

					Integer addrId = resultat.getInt("ADDRESS_ID");
					Integer streetNumber = resultat.getInt("STREET_NUMBER");
					String streetLabel = resultat.getString("STREET_LABEL");
					Integer zipCode = resultat.getInt("ZIP_CODE");
					String country = resultat.getString("COUNTRY");
					Byte isMain = resultat.getByte("IS_MAIN");

					GpAddress foundEmp = new GpAddress();
					foundEmp.setId(addrId);
					foundEmp.setStreetNumber(streetNumber);
					foundEmp.setStreetLabel(streetLabel);
					foundEmp.setZipCode(zipCode);
					foundEmp.setCountry(country);
					foundEmp.setIsMain(isMain);
					addrList.add(foundEmp);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return addrList;
	}

	public GpAddress findById(Integer addrId) {
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE where EMP_ID = ?";
		Object[] tabParam = { addrId };
		ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
		GpAddress foundEmp = new GpAddress();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					Integer id = resultat.getInt("ADDRESS_ID");
					Integer streetNumber = resultat.getInt("STREET_NUMBER");
					String streetLabel = resultat.getString("STREET_LABEL");
					Integer zipCode = resultat.getInt("ZIP_CODE");
					String country = resultat.getString("COUNTRY");
					Byte isMain = resultat.getByte("IS_MAIN");

					foundEmp.setId(id);
					foundEmp.setStreetNumber(streetNumber);
					foundEmp.setStreetLabel(streetLabel);
					foundEmp.setZipCode(zipCode);
					foundEmp.setCountry(country);
					foundEmp.setIsMain(isMain);

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
		return "GP_ADDRESS";
	}
}
