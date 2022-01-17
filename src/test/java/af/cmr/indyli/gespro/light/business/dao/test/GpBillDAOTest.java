package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpBillDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpBillDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpBillDAOTest {

	private IGpBillDAO billDAO = new GpBillDAOImpl();
	
	private Integer createdBillId = null;

	@Test
	public void testCreateBillWithSuccess() {
		// Given
		GpBill bill = new GpBill();
		Assert.assertNull(bill.getId());
		
		bill.setBillCode("Bill-1");
		bill.setAmount(623.66);
		bill.setBillStatus("PAID");

		// début code-tempo

		GpPhase phase = new GpPhase();
		phase.setId(2);

		bill.setGpPhase(phase);

		// fin code-tempo

		// When
		bill = billDAO.create(bill);

		// Then
		Assert.assertNotNull(bill.getId());
	}

	@Test
	public void testFindAllBillsWithSuccess() {
		// Given

		// When
		List<GpBill> bills = this.billDAO.findAll();
		// Then
		Assert.assertTrue(bills.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer billId = 1;

		// When
		GpBill bill = this.billDAO.findById(billId);
		// Then
		Assert.assertNotNull(bill);
	}

	@Test
	public void testDeleteBillWithSuccess() {
		// Given
		GpBill bill = new GpBill();
		Assert.assertNull(bill.getId());
		bill.setBillCode("Bill-1");
		bill.setAmount(623.66);
		bill.setBillStatus("PAID");

		// début code-tempo

		GpPhase phase = new GpPhase();
		phase.setId(3);

		bill.setGpPhase(phase);

		// fin code-tempo

		// When
		bill = billDAO.create(bill);

		// Then
		Assert.assertNotNull(bill.getId());
		// Then
		billDAO.deleteById(bill.getId());
	}
	
	@Before
	public void prepareAllEntityBefore() {
		//Créer ici toutes les entites requises
	}
	
	@After
	public void deleteAllEntityAfter() {
		//Supprimer ici toutes les entités qui ont été crées
	}

}
