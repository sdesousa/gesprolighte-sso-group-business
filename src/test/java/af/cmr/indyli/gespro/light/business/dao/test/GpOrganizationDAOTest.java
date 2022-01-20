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
		org.setContactName("Clément d'Harcourt");
		org.setContactEmail("clement.dharcourt@m2i.fr");

		// When
		org = orgDAO.create(org);
		//On le sauvegarde pour le supprimer apres
		this.createOrgId = org.getId();
		// Then
		Assert.assertNotNull(org.getId());
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
		Integer adrId = this.orgIdForAllTest;
		
		// When
		this.orgDAO.deleteById(adrId);
		GpOrganization emp = this.orgDAO.findById(adrId);
		
		// Then
		Assert.assertNull(emp);
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
