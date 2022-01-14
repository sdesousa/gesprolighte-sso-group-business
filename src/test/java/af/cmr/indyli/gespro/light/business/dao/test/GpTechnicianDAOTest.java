package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpTechnicianDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpTechnicianDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;

public class GpTechnicianDAOTest {

	private IGpTechnicianDAO empTechDAO = new GpTechnicianDAOImpl();

	@Test
	public void testCreateTechnicianWithSuccess() {
		// Given
		GpTechnician emp = new GpTechnician();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1026");
		emp.setLastname("JOB");
		emp.setFirstname("Joel");
		emp.setPhoneNumber("0365987865");
		emp.setPassword("myTecPassword");
		emp.setEmail("job.joel@gouv.fr");
		emp.setLogin("job.joel");
		emp.setGraduationYear(2015);
		emp.setLastDiploma("Master");

		// When
		emp = empTechDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllTechnicianWithSuccess() {
		// Given

		// When
		List<GpTechnician> empsTech = this.empTechDAO.findAll();
		// Then
		Assert.assertTrue(empsTech.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = 1;

		// When
		GpTechnician emp = this.empTechDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp);
	}

	@Test
	public void testDeleteTechnician() {
		// Given
		GpTechnician emp = new GpTechnician();
		Integer empId;
		emp.setFileNumber("1027");
		emp.setLastname("TCTtech2");
		emp.setFirstname("CY2tech2");
		emp.setPhoneNumber("02658632892");
		emp.setPassword("myPasswordtech");
		emp.setEmail("tct2tech2.cy@gmail.com");
		emp.setLogin("tct2tech2.cy");
		emp.setGraduationYear(2015);
		emp.setLastDiploma("Master");
		emp = empTechDAO.create(emp);
		empId = emp.getId();

		// When
		this.empTechDAO.deleteById(empId);
		emp = this.empTechDAO.findById(empId);
		// Then
		Assert.assertNull(emp.getId());
	}
}
