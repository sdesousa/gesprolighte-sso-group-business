package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;


public class GpOrganizationServiceTest extends GpDataCreationServiceTest {

	private IGpOrganizationService<GpOrganization> orgService = new GpOrganizationServiceImpl();
	private Integer orgIdForAllTest = null;
	private Integer createOrgId = null;

	@Test
	public void testCreateOrganizationWithSuccess() {
		// Given
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org = this.getOrgCreate();

		// When
		org = orgService.create(org);
		//On le sauvegarde pour le supprimer apres
		this.createOrgId = org.getId();
		// Then
		Assert.assertNotNull(org.getId());
	}
	
	@Test
	public void testUpdateOrganizationWithSuccess() {
		// Given
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org = this.getOrgCreate();
		org = orgService.create(org);
		this.createOrgId = org.getId();
		
		
		Assert.assertEquals("CODE-67", org.getOrgCode());
		Assert.assertEquals("INDYLI-SERVICES", org.getName());
		Assert.assertEquals("7895", org.getPhoneNumber());
		Assert.assertEquals("indyli-services.com", org.getAdrWeb());
		Assert.assertEquals("CName", org.getContactName());
		Assert.assertEquals("contact@indyli-services.com", org.getContactEmail());
		
		// When
		org.setOrgCode("UOR123");
		org.setName("UM2I");
		org.setPhoneNumber("U0174787576");
		org.setAdrWeb("Uwww.m2i.fr");
		org.setContactName("UClément d'Harcourt");
		org.setContactEmail("Uclement.dharcourt@m2i.fr");
		orgService.update(org);

		// Then
		org = orgService.findById(createOrgId);
		Assert.assertEquals("UOR123", org.getOrgCode());
		Assert.assertEquals("UM2I", org.getName());
		Assert.assertEquals("U0174787576", org.getPhoneNumber());
		Assert.assertEquals("Uwww.m2i.fr", org.getAdrWeb());
		Assert.assertEquals("UClément d'Harcourt", org.getContactName());
		Assert.assertEquals("Uclement.dharcourt@m2i.fr", org.getContactEmail());
	}

	@Test
	public void testFindAllOrganizationWithSuccess() {
		// Given

		// When
		List<GpOrganization> orgs = this.orgService.findAll();
		// Then
		Assert.assertTrue(orgs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer adrId = this.orgIdForAllTest;

		// When
		GpOrganization adr = this.orgService.findById(adrId);
		// Then
		Assert.assertNotNull(adr);
	}

	@Test
	public void testDelete() {
		// Given
		Integer orgId = this.orgIdForAllTest;
		
		// When
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);
		
		// Then
		Assert.assertNull(org);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org = this.getOrgDefault();
		org = orgService.create(org) ;
		this.orgIdForAllTest = org.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.orgService.deleteById(this.orgIdForAllTest);
		if(!Objects.isNull(this.createOrgId)) {
			this.orgService.deleteById(this.createOrgId);
		}
	}
}
