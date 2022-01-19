package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpTechnicianDAO;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

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
		String REQ_SQL = "SELECT * FROM GP_TECHNICIAN";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpTechnician> empList = new ArrayList<GpTechnician>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer empId = resultat.getInt("EMP_ID");
					GpEmployee foundEmp = findById(empId);
					GpTechnician foundTec = new GpTechnician();
					foundTec.setId(empId);
					foundTec.setFileNumber(foundEmp.getFileNumber());
					foundTec.setLastname(foundEmp.getLastname());
					foundTec.setFirstname(foundEmp.getFirstname());
					foundTec.setCreationDate(foundEmp.getCreationDate());
					foundTec.setPassword(foundEmp.getPassword());
					foundTec.setPhoneNumber(foundEmp.getPhoneNumber());
					foundTec.setEmail(foundEmp.getEmail());
					foundTec.setLogin(foundEmp.getLogin());
					foundTec.setLastDiploma(resultat.getString("LAST_DIPLOMA"));
					foundTec.setGraduationYear(resultat.getInt("GRADUATION_YEAR"));
					empList.add(foundTec);
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
		String REQ_SQL = "SELECT * FROM GP_TECHNICIAN where EMP_ID = ?";
		Object[] tabParam = {empId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpTechnician foundTec = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					GpEmployeeDAOImpl empImpl = new GpEmployeeDAOImpl();
					GpEmployee foundEmp = empImpl.findById(empId);
					foundTec = new GpTechnician();
					foundTec.setId(empId);
					foundTec.setFileNumber(foundEmp.getFileNumber());
					foundTec.setLastname(foundEmp.getLastname());
					foundTec.setFirstname(foundEmp.getFirstname());
					foundTec.setCreationDate(foundEmp.getCreationDate());
					foundTec.setPassword(foundEmp.getPassword());
					foundTec.setPhoneNumber(foundEmp.getPhoneNumber());
					foundTec.setEmail(foundEmp.getEmail());
					foundTec.setLogin(foundEmp.getLogin());
					foundTec.setLastDiploma(resultat.getString("LAST_DIPLOMA"));
					foundTec.setGraduationYear(resultat.getInt("GRADUATION_YEAR"));
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundTec;
	}

	@Override
	public String getCurrentTableName() {
		return GpTechnician.GP_TECHNICIAN_TABLE_NAME;
	}
}
