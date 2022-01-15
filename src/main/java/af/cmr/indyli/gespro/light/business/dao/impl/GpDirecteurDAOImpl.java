package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDirecteurDAO;
import af.cmr.indyli.gespro.light.business.entity.GpDirecteur;

public class GpDirecteurDAOImpl implements IGpDirecteurDAO {

	private GpEntityManager entityManager = new GpEntityManager();

	public GpDirecteur create(GpDirecteur emp) {
		try {
			// On demarre une transaction
			this.entityManager.getDbConnect().setAutoCommit(false);
			// On commence par insérer dans la table mère avant d'inserer dans la table
			// fille
			String REQ_SQL = "INSERT INTO GP_EMPLOYEE ( FILE_NUMBER,LASTNAME,FIRSTNAME,PHONE_NUMBER,PASSWORD,CREATION_DATE,EMAIL,LOGIN) VALUES (?,?,?,?,?,?,?,?)";
			Object[] tabParam = { emp.getFileNumber(), emp.getLastname(), emp.getFirstname(), emp.getPhoneNumber(),
					emp.getPassword(), new Date(), emp.getEmail(), emp.getLogin() };
			this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
			Integer empId = entityManager.findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", emp.getEmail(), "EMP_ID");
			// On insere maintenant dans la table GP_DIRECTOR
			String REQ_SQL_PM = "INSERT INTO GP_DIRECTOR ( EMP_ID) VALUES (?)";
			Object[] tabParamPM = { empId };
			this.entityManager.updateAvecParamGenerique(REQ_SQL_PM, tabParamPM);
			emp.setId(empId);
			this.entityManager.getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	public void update(GpDirecteur emp) {
		String REQ_SQL = "UPDATE FROM GP_EMPLOYEE SET LASTNAME=? , FIRSTNAME=? , PHONE_NUMBER=? ,PASSWORD = ? ,EMAIL=? ,LOGIN=?     WHERE EMP_ID = ?";
		Object[] tabParam = { emp.getLastname(), emp.getFirstname(), emp.getPhoneNumber(), emp.getPassword(),
				emp.getEmail(), emp.getLogin(), emp.getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	public List<GpDirecteur> findAll() {
		String REQ_SQL = "SELECT * FROM GP_DIRECTOR AS direct JOIN GP_EMPLOYEE AS emp WHERE direct.EMP_ID=emp.EMP_ID";
		ResultSet resultat = this.entityManager.exec(REQ_SQL);
		List<GpDirecteur> empList = new ArrayList<GpDirecteur>();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					Integer empId = resultat.getInt("EMP_ID");
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					GpDirecteur foundEmp = new GpDirecteur();
					foundEmp.setId(empId);
					foundEmp.setFileNumber(fileNumber);
					foundEmp.setLastname(lastname);
					foundEmp.setFirstname(firstname);
					foundEmp.setCreationDate(creationDate);
					foundEmp.setPassword(password);
					foundEmp.setPhoneNumber(phoneNumber);
					foundEmp.setEmail(email);
					foundEmp.setLogin(login);
					empList.add(foundEmp);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

	public void deleteById(Integer empId) {
		String REQ_SQL = "DELETE FROM  GP_DIRECTOR WHERE EMP_ID = ?";
		Object[] tabParam = { empId };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	public GpDirecteur findById(Integer empId) {
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE where EMP_ID = ?";
		Object[] tabParam = { empId };
		ResultSet resultat = this.entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		GpDirecteur foundEmp = new GpDirecteur();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					foundEmp.setId(empId);
					foundEmp.setFileNumber(fileNumber);
					foundEmp.setLastname(lastname);
					foundEmp.setFirstname(firstname);
					foundEmp.setCreationDate(creationDate);
					foundEmp.setPassword(password);
					foundEmp.setPhoneNumber(phoneNumber);
					foundEmp.setEmail(email);
					foundEmp.setLogin(login);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundEmp;
	}
}
