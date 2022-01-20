package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;


public class GpOrganizationDAOTest {

	private IGpOrganizationDAO<GpOrganization> orgDAO = new GpOrganizationDAOImpl();
	private Integer orgIdForAllTest = null;
	private Integer createOrgId = null;

	@Test
	public void testCreateOrganizationWithSuccess() {
		// Given
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org.setOrgCode("OR123");
		org.setName("M2I");
		org.setPhoneNumber("0174787576");
		org.setAdrWeb("www.m2i.fr");
		org.setContactName("Cl�ment d'Harcourt");
		org.setContactEmail("clement.dharcourt@m2i.fr");

		// When
		org = orgDAO.create(org);
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
		org.setOrgCode("OR123");
		org.setName("M2I");
		org.setPhoneNumber("0174787576");
		org.setAdrWeb("www.m2i.fr");
		org.setContactName("Cl�ment d'Harcourt");
		org.setContactEmail("clement.dharcourt@m2i.fr");
		org = orgDAO.create(org);
		this.createOrgId = org.getId();
		
		// When
		org.setOrgCode("UOR123");
		org.setName("UM2I");
		org.setPhoneNumber("U0174787576");
		org.setAdrWeb("Uwww.m2i.fr");
		org.setContactName("UCl�ment d'Harcourt");
		org.setContactEmail("Uclement.dharcourt@m2i.fr");
		orgDAO.update(org);

		// Then
		org = orgDAO.findById(createOrgId);
		Assert.assertEquals("UOR123", org.getOrgCode());
		Assert.assertEquals("UM2I", org.getName());
		Assert.assertEquals("U0174787576", org.getPhoneNumber());
		Assert.assertEquals("Uwww.m2i.fr", org.getAdrWeb());
		Assert.assertEquals("UCl�ment d'Harcourt", org.getContactName());
		Assert.assertEquals("Uclement.dharcourt@m2i.fr", org.getContactEmail());
	}

	@Test
	public void testFindAllOrganizationWithSuccess() {
		// Given

		// When
		List<GpOrganization> orgs = this.orgDAO.findAll();
		// Then
		Assert.assertTrue(orgs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer adrId = this.orgIdForAllTest;

		// When
		GpOrganization adr = this.orgDAO.findById(adrId);
		// Then
		Assert.assertNotNull(adr);
	}

	@Test
	public void testDelete() {
		// Given
		Integer orgId = this.orgIdForAllTest;
		
		// When
		this.orgDAO.deleteById(orgId);
		GpOrganization org = this.orgDAO.findById(orgId);
		
		// Then
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
		org = orgDAO.create(org) ;
		this.orgIdForAllTest = org.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.orgDAO.deleteById(this.orgIdForAllTest);
		if(!Objects.isNull(this.createOrgId)) {
			this.orgDAO.deleteById(this.createOrgId);
		}
	}
}
