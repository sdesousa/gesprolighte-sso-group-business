package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpSecretaryDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpSecretaryDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpSecretary;

public class GpSecretaryDAOTest {

	private IGpSecretaryDAO empSecDAO = new GpSecretaryDAOImpl();

	@Test
	public void testCreateSecretaryWithSuccess() {
		// Given
		GpSecretary emp = new GpSecretary();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2026");
		emp.setLastname("JOBSec");
		emp.setFirstname("JoelSec");
		emp.setPhoneNumber("2365987865");
		emp.setPassword("mySecPassword");
		emp.setEmail("jobsec.joelsec@gouv.fr");
		emp.setLogin("jobsec.joelsec");

		// When
		emp = empSecDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllSecretaryWithSuccess() {
		// Given

		// When
		List<GpSecretary> empsSec = this.empSecDAO.findAll();
		// Then
		Assert.assertTrue(empsSec.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = 29;

		// When
		GpSecretary emp = this.empSecDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testDeleteSecretary() {
		// Given
		GpSecretary emp = new GpSecretary();
		Integer empId;
		emp.setFileNumber("2027");
		emp.setLastname("TCTsec2");
		emp.setFirstname("CY2sec2");
		emp.setPhoneNumber("22658632892");
		emp.setPassword("myPasswordsec");
		emp.setEmail("tctsec2tech2.cy@gmail.com");
		emp.setLogin("tct2sectech2.cy");
		emp = empSecDAO.create(emp);
		empId = emp.getId();

		// When
		this.empSecDAO.deleteById(empId);
		emp = this.empSecDAO.findById(empId);
		// Then
		Assert.assertNull(emp.getId());
	}
}
