package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAccountantDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpAccountantDAOImpl extends GpAbstractEmployeeDAOImpl<GpAccountant> implements IGpAccountantDAO{

	@Override
	public GpAccountant create(GpAccountant emp) {
		String REQ_SQL = "INSERT INTO GP_EMPLOYEE ( FILE_NUMBER,LASTNAME,FIRSTNAME,PHONE_NUMBER,PASSWORD,CREATION_DATE,EMAIL,LOGIN) VALUES (?,?,?,?,?,?,?,?)";
	    Object[] tabParam = {emp.getFileNumber(),emp.getLastname(),emp.getFirstname(),emp.getPhoneNumber(),emp.getPassword(),new Date(),emp.getEmail(),emp.getLogin()};
	   	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	   	Integer empId = getEntityManager().findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", emp.getEmail(), "EMP_ID");
	   	//On insere maintenant dans la table GP_ACCOUNTANT
	   	String REQ_SQL_PM = "INSERT INTO GP_ACCOUNTANT ( EMP_ID) VALUES (?)";
	   	Object[] tabParamPM = {empId};
	   	this.getEntityManager().updateAvecParamGenerique(REQ_SQL_PM, tabParamPM);
	   	emp.setId(empId);
	   	return emp;
	}

	@Override
	public List<GpAccountant> findAll() {
		String REQ_SQL = "SELECT * FROM GP_ACCOUNTANT";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpAccountant> empList = new ArrayList<GpAccountant>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer empId = resultat.getInt("EMP_ID");
					GpEmployee emp = findById(empId);
					GpAccountant foundEmp = new GpAccountant();
					foundEmp.setId(empId);
					foundEmp.setFileNumber(emp.getFileNumber());
					foundEmp.setLastname(emp.getLastname());
					foundEmp.setFirstname(emp.getFirstname());
					foundEmp.setCreationDate(emp.getCreationDate());
					foundEmp.setPassword(emp.getPassword());
					foundEmp.setPhoneNumber(emp.getPhoneNumber());
					foundEmp.setEmail(emp.getEmail());
					foundEmp.setLogin(emp.getLogin());
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
	public GpAccountant findById(Integer empId) {
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE where EMP_ID = ?";
		Object[] tabParam = {empId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpAccountant foundEmp = null;
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
					foundEmp = new GpAccountant();
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

	@Override
	public String getCurrentTableName() {
		return GpAccountant.GP_ACCOUNTANT_TABLE_NAME;
	}
}
