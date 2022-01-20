package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;


public class GpAddressDAOTest {

	private IGpOrganizationDAO<GpOrganization> orgDAO = new GpOrganizationDAOImpl();
	private IGpAddressDAO<GpAddress> adrDAO = new GpAddressDAOImpl();
	private Integer adrIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer createAdrId = null;
	private Integer createOrgId = null;

	@Test
	public void testCreateAddressWithSuccess() {
		// Given
		
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org.setOrgCode("OR123");
		org.setName("M2I");
		org.setPhoneNumber("0174787576");
		org.setAdrWeb("www.m2i.fr");
		org.setContactName("Clément d'Harcourt");
		org.setContactEmail("clement.dharcourt@m2i.fr");
		org = orgDAO.create(org) ;
		
		GpAddress adr = new GpAddress();
		Assert.assertNull(adr.getId());
		adr.setStreetNumber(41);
		adr.setStreetLabel("rue azerty");
		adr.setZipCode(25041);
		adr.setCountry("France");
		adr.setIsMain((byte) 1);
		adr.setGpOrganization(org);
		

		// When
		adr = adrDAO.create(adr);
		//On le sauvegarde pour le supprimer apres
		this.createAdrId = adr.getId();
		this.createOrgId = org.getId();
		// Then
		Assert.assertNotNull(adr.getId());
	}
	
	@Test
	public void testUpdateAddressWithSuccess() {
		// Given
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org.setOrgCode("OR123");
		org.setName("M2I");
		org.setPhoneNumber("0174787576");
		org.setAdrWeb("www.m2i.fr");
		org.setContactName("Clément d'Harcourt");
		org.setContactEmail("clement.dharcourt@m2i.fr");
		org = orgDAO.create(org) ;
		GpAddress adr = new GpAddress();
		Assert.assertNull(adr.getId());
		adr.setStreetNumber(41);
		adr.setStreetLabel("rue azerty");
		adr.setZipCode(25041);
		adr.setCountry("France");
		adr.setIsMain((byte) 1);
		adr.setGpOrganization(org);
		adr = adrDAO.create(adr);
		this.createOrgId = org.getId();
		this.createAdrId = adr.getId();
		
		// When
		adr.setStreetNumber(51);
		adr.setStreetLabel("Urue azerty");
		adr.setZipCode(35041);
		adr.setCountry("UFrance");
		adrDAO.update(adr);

		// Then
		adr = adrDAO.findById(createAdrId);
		Assert.assertEquals(new Integer(51), adr.getStreetNumber());
		Assert.assertEquals("Urue azerty", adr.getStreetLabel());
		Assert.assertEquals(new Integer(35041), adr.getZipCode());
		Assert.assertEquals("UFrance", adr.getCountry());
		Assert.assertEquals(1, adr.getIsMain());
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
		Integer orgId = this.orgIdForAllTest;
		
		// When
		this.adrDAO.deleteById(adrId);
		GpAddress adr = this.adrDAO.findById(adrId);
		this.orgDAO.deleteById(orgId);
		GpOrganization org = this.orgDAO.findById(orgId);
		
		// Then
		Assert.assertNull(adr);
		Assert.assertNull(org);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org.setOrgCode("E498");
		org.setName("Pole emploi");
		org.setPhoneNumber("3949");
		org.setAdrWeb("www.pole-emploi.fr");
		org.setContactName("Julien");
		org.setContactEmail("julien@pole-emploi.fr");
		org = orgDAO.create(org);
		this.orgIdForAllTest = org.getId();
		
		GpAddress adr = new GpAddress();
		Assert.assertNull(adr.getId());
		adr.setStreetNumber(11);
		adr.setStreetLabel("rue abc");
		adr.setZipCode(12000);
		adr.setCountry("France");
		adr.setIsMain((byte) 0);
		adr.setGpOrganization(org);
		adr = adrDAO.create(adr);
		this.adrIdForAllTest = adr.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.adrDAO.deleteById(this.adrIdForAllTest);
		this.orgDAO.deleteById(this.orgIdForAllTest);
		if(!Objects.isNull(this.createAdrId)) {
			this.adrDAO.deleteById(this.createAdrId);
		}
		if(!Objects.isNull(this.createOrgId)) {
			this.orgDAO.deleteById(this.createOrgId);
		}
	}
}
