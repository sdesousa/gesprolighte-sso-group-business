package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAccountantDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAccountantDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;

public class GpAccountantDAOTest {

	private IGpAccountantDAO empAccDAO = new GpAccountantDAOImpl();

	@Test
	public void testCreateComptableWithSuccess() {
		// Given
		GpAccountant emp = new GpAccountant();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2028");
		emp.setLastname("JOBAcc");
		emp.setFirstname("JoelAcc");
		emp.setPhoneNumber("2365987865");
		emp.setPassword("myAccPassword");
		emp.setEmail("jobAcc.joelAcc@gouv.fr");
		emp.setLogin("jobAcc.joelAcc");

		// When
		emp = empAccDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllComptableWithSuccess() {
		// Given

		// When
		List<GpAccountant> empsAcc = this.empAccDAO.findAll();
		// Then
		Assert.assertTrue(empsAcc.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = 53;

		// When
		GpAccountant emp = this.empAccDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testDeleteComptable() {
		// Given
		GpAccountant emp = new GpAccountant();
		Integer empId;
		emp.setFileNumber("2028");
		emp.setLastname("TCTAcc2");
		emp.setFirstname("CY2Acc2");
		emp.setPhoneNumber("22658632892");
		emp.setPassword("myPasswordAcc");
		emp.setEmail("tctsec2tech2.cy@gmail.com");
		emp.setLogin("tct2Acctech2.cy");
		emp = empAccDAO.create(emp);
		empId = emp.getId();

		// When
		this.empAccDAO.deleteById(empId);
		emp = this.empAccDAO.findById(empId);
		// Then
		Assert.assertNull(emp.getId());
	}
}
