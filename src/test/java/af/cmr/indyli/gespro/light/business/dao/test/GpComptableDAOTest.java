package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpComptableDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpComptableDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpComptable;

public class GpComptableDAOTest {

	private IGpComptableDAO empAccDAO = new GpComptableDAOImpl();

	@Test
	public void testCreateComptableWithSuccess() {
		// Given
		GpComptable emp = new GpComptable();
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
		List<GpComptable> empsAcc = this.empAccDAO.findAll();
		// Then
		Assert.assertTrue(empsAcc.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = 53;

		// When
		GpComptable emp = this.empAccDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testDeleteComptable() {
		// Given
		GpComptable emp = new GpComptable();
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
