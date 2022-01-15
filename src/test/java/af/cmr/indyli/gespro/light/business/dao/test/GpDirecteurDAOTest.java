package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpDirecteurDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDirecteurDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDirecteur;

public class GpDirecteurDAOTest {

	private IGpDirecteurDAO empDirecDAO = new GpDirecteurDAOImpl();

	@Test
	public void testCreateDirecteurWithSuccess() {
		// Given
		GpDirecteur emp = new GpDirecteur();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2026");
		emp.setLastname("JOBDirec");
		emp.setFirstname("JoelDirec");
		emp.setPhoneNumber("2365987865");
		emp.setPassword("myDirecPassword");
		emp.setEmail("jobDirec.joelDirec@gouv.fr");
		emp.setLogin("jobDirec.joelDirec");

		// When
		emp = empDirecDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllDirecteurWithSuccess() {
		// Given

		// When
		List<GpDirecteur> empsDirec = this.empDirecDAO.findAll();
		// Then
		Assert.assertTrue(empsDirec.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = 30;

		// When
		GpDirecteur emp = this.empDirecDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testDeleteDirecteur() {
		// Given
		GpDirecteur emp = new GpDirecteur();
		Integer empId;
		emp.setFileNumber("2027");
		emp.setLastname("TCTDirec2");
		emp.setFirstname("CY2Direc2");
		emp.setPhoneNumber("22658632892");
		emp.setPassword("myPasswordDirec");
		emp.setEmail("tctsec2tech2.cy@gmail.com");
		emp.setLogin("tct2Directech2.cy");
		emp = empDirecDAO.create(emp);
		empId = emp.getId();

		// When
		this.empDirecDAO.deleteById(empId);
		emp = this.empDirecDAO.findById(empId);
		// Then
		Assert.assertNull(emp.getId());
	}
}
