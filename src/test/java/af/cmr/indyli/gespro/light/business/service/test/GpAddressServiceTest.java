package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.IGpAddressService;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.impl.GpAddressServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;


public class GpAddressServiceTest extends GpDataCreationServiceTest {

	private IGpOrganizationService<GpOrganization> orgService = new GpOrganizationServiceImpl();
	private IGpAddressService<GpAddress> adrService = new GpAddressServiceImpl();
	private Integer adrIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer createAdrId = null;
	private Integer createOrgId = null;

	@Test
	public void testCreateAddressWithSuccess() {
		// Given
		
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org = this.getOrgCreate();
		org = orgService.create(org) ;
		
		GpAddress adr = new GpAddress();
		Assert.assertNull(adr.getId());
		adr = this.getAdrCreate();

		// When
		adr = adrService.create(adr);
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
		org = this.getOrgCreate();
		org = orgService.create(org) ;
		GpAddress adr = new GpAddress();
		Assert.assertNull(adr.getId());
		adr = this.getAdrCreate();
		adr = adrService.create(adr);
		this.createOrgId = org.getId();
		this.createAdrId = adr.getId();
		Assert.assertEquals(new Integer(41), adr.getStreetNumber());
		Assert.assertEquals("rue azerty", adr.getStreetLabel());
		Assert.assertEquals(new Integer(25041), adr.getZipCode());
		Assert.assertEquals("France", adr.getCountry());
		Assert.assertEquals(1, adr.getIsMain());
		
		// When
		adr.setStreetNumber(51);
		adr.setStreetLabel("Urue azerty");
		adr.setZipCode(35041);
		adr.setCountry("UFrance");
		adrService.update(adr);

		// Then
		adr = adrService.findById(createAdrId);
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
		List<GpAddress> adrs = this.adrService.findAll();
		// Then
		Assert.assertTrue(adrs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer adrId = this.adrIdForAllTest;

		// When
		GpAddress adr = this.adrService.findById(adrId);
		// Then
		Assert.assertNotNull(adr);
	}

	@Test
	public void testDelete() {
		// Given
		Integer adrId = this.adrIdForAllTest;
		Integer orgId = this.orgIdForAllTest;
		
		// When
		this.adrService.deleteById(adrId);
		GpAddress adr = this.adrService.findById(adrId);
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);
		
		// Then
		Assert.assertNull(adr);
		Assert.assertNull(org);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org = this.getOrgDefault();
		org = orgService.create(org);
		this.orgIdForAllTest = org.getId();
		
		GpAddress adr = new GpAddress();
		Assert.assertNull(adr.getId());
		adr = this.getAdrDefault();
		adr = adrService.create(adr);
		this.adrIdForAllTest = adr.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.adrService.deleteById(this.adrIdForAllTest);
		this.orgService.deleteById(this.orgIdForAllTest);
		if(!Objects.isNull(this.createAdrId)) {
			this.adrService.deleteById(this.createAdrId);
		}
		if(!Objects.isNull(this.createOrgId)) {
			this.orgService.deleteById(this.createOrgId);
		}
	}
}
