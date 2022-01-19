package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;


public class GpAddressDAOTest {

	private IGpAddressDAO<GpAddress> adrDAO = new GpAddressDAOImpl();
	private Integer adrIdForAllTest = null;
	private Integer createAdrId = null;

	@Test
	public void testCreateAddressWithSuccess() {
		// Given
		GpAddress adr = new GpAddress();
		Assert.assertNull(adr.getId());
		adr.setStreetNumber(41);
		adr.setStreetLabel("rue azerty");
		adr.setZipCode(25041);
		adr.setCountry("France");
		adr.setIsMain((byte) 0);
		adr.setGpOrganization(null);
		adr.setGpEmployee(null);

		// When
		adr = adrDAO.create(adr);
		//On le sauvegarde pour le supprimer apres
		this.createAdrId = adr.getId();
		// Then
		Assert.assertNotNull(adr.getId());
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpAddress> adrs = this.adrDAO.findAll();
		// Then
		Assert.assertTrue(adrs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer adrId = this.adrIdForAllTest;

		// When
		GpAddress adr = this.adrDAO.findById(adrId);
		// Then
		Assert.assertNotNull(adr);
	}

	@Test
	public void testDelete() {
		// Given
		Integer adrId = this.adrIdForAllTest;
		
		// When
		this.adrDAO.deleteById(adrId);
		GpAddress emp = this.adrDAO.findById(adrId);
		
		// Then
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		GpAddress adr = new GpAddress();
		Assert.assertNull(adr.getId());
		adr.setStreetNumber(11);
		adr.setStreetLabel("rue abc");
		adr.setZipCode(12000);
		adr.setCountry("France");
		adr.setIsMain((byte) 0);
		adr.setGpOrganization(null);
		adr.setGpEmployee(null);
		adr = adrDAO.create(adr) ;
		this.adrIdForAllTest = adr.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.adrDAO.deleteById(this.adrIdForAllTest);
		if(!Objects.isNull(this.createAdrId)) {
			this.adrDAO.deleteById(this.createAdrId);
		}
	}
}
