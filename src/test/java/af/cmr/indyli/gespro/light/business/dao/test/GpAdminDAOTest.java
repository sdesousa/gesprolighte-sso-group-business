package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAdminDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAdminDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAdmin;

public class GpAdminDAOTest {

	private IGpAdminDAO empAdmnDAO = new GpAdminDAOImpl();

	@Test
	public void testCreateAdminWithSuccess() {
		// Given
		GpAdmin emp = new GpAdmin();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2028");
		emp.setLastname("JOBAdmn");
		emp.setFirstname("JoelAdmn");
		emp.setPhoneNumber("2365987865");
		emp.setPassword("myAdmnPassword");
		emp.setEmail("jobAdmn.joelAdmn@gouv.fr");
		emp.setLogin("jobAdmn.joelAdmn");

		// When
		emp = empAdmnDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllAdminWithSuccess() {
		// Given

		// When
		List<GpAdmin> empsAdmn = this.empAdmnDAO.findAll();
		// Then
		Assert.assertTrue(empsAdmn.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = 48;

		// When
		GpAdmin emp = this.empAdmnDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testDeleteAdmin() {
		// Given
		GpAdmin emp = new GpAdmin();
		Integer empId;
		emp.setFileNumber("2028");
		emp.setLastname("TCTAdmn2");
		emp.setFirstname("CY2Admn2");
		emp.setPhoneNumber("22658632892");
		emp.setPassword("myPasswordAdmn");
		emp.setEmail("tctsec2tech2.cy@gmail.com");
		emp.setLogin("tct2Admntech2.cy");
		emp = empAdmnDAO.create(emp);
		empId = emp.getId();

		// When
		this.empAdmnDAO.deleteById(empId);
		emp = this.empAdmnDAO.findById(empId);
		// Then
		Assert.assertNull(emp.getId());
	}
}
