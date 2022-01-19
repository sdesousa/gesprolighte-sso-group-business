package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpTechnicianDAO;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;

public class GpTechnicianDAOImpl extends GpAbstractEmployeeDAOImpl<GpTechnician> implements IGpTechnicianDAO{
	
	@Override
	public GpTechnician create(GpTechnician emp) {
		String REQ_SQL = "INSERT INTO GP_EMPLOYEE ( FILE_NUMBER,LASTNAME,FIRSTNAME,PHONE_NUMBER,PASSWORD,CREATION_DATE,EMAIL,LOGIN) VALUES (?,?,?,?,?,?,?,?)";
	    Object[] tabParam = {emp.getFileNumber(),emp.getLastname(),emp.getFirstname(),emp.getPhoneNumber(),emp.getPassword(),new Date(),emp.getEmail(),emp.getLogin()};
	   	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	   	Integer empId = getEntityManager().findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", emp.getEmail(), "EMP_ID");
	   	//On insere maintenant dans la table GP_TECHNICIAN
	   	String REQ_SQL_PM = "INSERT INTO GP_TECHNICIAN ( EMP_ID,LAST_DIPLOMA,GRADUATION_YEAR) VALUES (?,?,?)";
	   	Object[] tabParamPM = {empId,emp.getLastDiploma(),emp.getGraduationYear()};
	   	this.getEntityManager().updateAvecParamGenerique(REQ_SQL_PM, tabParamPM);
	   	emp.setId(empId);
	   	return emp;
	}

	@Override
	public List<GpTechnician> findAll() {
		String REQ_SQL = "SELECT * FROM GP_TECHNICIAN NATURAL JOIN GP_EMPLOYEE";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpTechnician> empList = new ArrayList<GpTechnician>();
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
					String lastDiploma = resultat.getString("LAST_DIPLOMA");
					int graduationYear = resultat.getInt("GRADUATION_YEAR");
					GpTechnician foundEmp = new GpTechnician();
					foundEmp.setId(empId);
					foundEmp.setFileNumber(fileNumber);
					foundEmp.setLastname(lastname);
					foundEmp.setFirstname(firstname);
					foundEmp.setCreationDate(creationDate);
					foundEmp.setPassword(password);
					foundEmp.setPhoneNumber(phoneNumber);
					foundEmp.setEmail(email);
					foundEmp.setLogin(login);
					foundEmp.setLastDiploma(lastDiploma);
					foundEmp.setGraduationYear(graduationYear);
					empList.add(foundEmp);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return empList;
	}

	@Override
	public GpTechnician findById(Integer empId) {
		String REQ_SQL = "SELECT * FROM GP_TECHNICIAN NATURAL JOIN GP_EMPLOYEE WHERE EMP_ID = ?";
		Object[] tabParam = {empId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpTechnician foundEmp = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					foundEmp = new GpTechnician();
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					String lastDiploma = resultat.getString("LAST_DIPLOMA");
					int graduationYear = resultat.getInt("GRADUATION_YEAR");
					foundEmp.setId(empId);
					foundEmp.setFileNumber(fileNumber);
					foundEmp.setLastname(lastname);
					foundEmp.setFirstname(firstname);
					foundEmp.setCreationDate(creationDate);
					foundEmp.setPassword(password);
					foundEmp.setPhoneNumber(phoneNumber);
					foundEmp.setEmail(email);
					foundEmp.setLogin(login);
					foundEmp.setLastDiploma(lastDiploma);
					foundEmp.setGraduationYear(graduationYear);
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
		return GpTechnician.GP_TECHNICIAN_TABLE_NAME;
	}
}
