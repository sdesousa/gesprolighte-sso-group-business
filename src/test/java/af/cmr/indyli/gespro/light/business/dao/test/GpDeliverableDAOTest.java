package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpDeliverableDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;


public class GpDeliverableDAOTest extends GpDataCreationDAOTest {
	
	
	private GpProjectDAOImpl prjDAO = new GpProjectDAOImpl();
	private GpProjectManagerDAOImpl empDAO = new GpProjectManagerDAOImpl();
	private GpOrganizationDAOImpl orgDAO = new GpOrganizationDAOImpl();
	private GpPhaseDAOImpl phsDAO = new GpPhaseDAOImpl();
	private GpDeliverableDAOImpl delDAO = new GpDeliverableDAOImpl();
	private Integer prjIdForAllTest = null;
	private Integer phsIdForAllTest = null;
	private Integer empIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer delIdForAllTest = null;
	private Integer createPrjId = null;
	private Integer createPhsId = null;
	private Integer createEmpId = null;
	private Integer createOrgId = null;
	private Integer createDelId = null;

	@Test
	public void testCreateAddressWithSuccess() {
		// Given
		GpProjectManager projectManager = new GpProjectManager();
		Assert.assertNull(projectManager.getId());
		projectManager = this.getPmCreate();
		projectManager = empDAO.create(projectManager);
		this.createEmpId = projectManager.getId();

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization = this.getOrgCreate();
		organization = orgDAO.create(organization);
		this.createOrgId = organization.getId();

		GpProject projectToCreate = new GpProject();
		Assert.assertNull(projectToCreate.getId());
		projectToCreate = this.getPrjCreate();
		projectToCreate = prjDAO.create(projectToCreate);
		this.createPrjId = projectToCreate.getId();
		
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs = this.getPhsCreate();
		phs = phsDAO.create(phs);
		this.createPhsId = phs.getId();
		
		GpDeliverable del = new GpDeliverable();
		Assert.assertNull(del.getId());
		del = this.getDelCreate();		

		// When
		del = delDAO.create(del);
		//On le sauvegarde pour le supprimer apres
		this.createDelId = del.getId();
		// Then
		Assert.assertNotNull(del.getId());

	}
	
	@Test
	public void testUpdateAddressWithSuccess() {
		// Given
		GpProjectManager projectManager = new GpProjectManager();
		Assert.assertNull(projectManager.getId());
		projectManager = this.getPmCreate();
		projectManager = empDAO.create(projectManager);
		this.createEmpId = projectManager.getId();

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization = this.getOrgCreate();
		organization = orgDAO.create(organization);
		this.createOrgId = organization.getId();

		GpProject projectToCreate = new GpProject();
		Assert.assertNull(projectToCreate.getId());
		projectToCreate = this.getPrjCreate();
		projectToCreate = prjDAO.create(projectToCreate);
		this.createPrjId = projectToCreate.getId();
		
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs = this.getPhsCreate();
		phs = phsDAO.create(phs);
		this.createPhsId = phs.getId();
		
		GpDeliverable del = new GpDeliverable();
		Assert.assertNull(del.getId());
		del = this.getDelCreate();
		del =delDAO.create(del);
		this.createDelId = del.getId();
		Assert.assertEquals("DE007", del.getDelCode());
		Assert.assertEquals("Livraision 007", del.getLabel());
		Assert.assertEquals("Description 007", del.getDescription());
		Assert.assertEquals("lien007", del.getDelPath());
		
		// When
		del.setDelCode("UDE007");
		del.setLabel("ULivraision 007");
		del.setDescription("UDescription 007");
		del.setDelPath("Ulien007");
		delDAO.update(del);

		// Then
		del = delDAO.findById(createDelId);
		Assert.assertEquals("UDE007", del.getDelCode());
		Assert.assertEquals("ULivraision 007", del.getLabel());
		Assert.assertEquals("UDescription 007", del.getDescription());
		Assert.assertEquals("Ulien007", del.getDelPath());
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpDeliverable> dels = this.delDAO.findAll();
		// Then
		Assert.assertTrue(dels.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer delId = this.delIdForAllTest;

		// When
		GpDeliverable del = this.delDAO.findById(delId);
		// Then
		Assert.assertNotNull(del);
	}

	@Test
	public void testDelete() {
		// Given
		Integer delId = this.delIdForAllTest;
		Integer phsId = this.phsIdForAllTest;
		Integer prjId = this.prjIdForAllTest;
		Integer orgId = this.orgIdForAllTest;
		Integer empId = this.empIdForAllTest;
		
		// When
		this.delDAO.deleteById(delId);
		GpDeliverable del = this.delDAO.findById(delId);
		this.phsDAO.deleteById(phsId);
		GpPhase phs = this.phsDAO.findById(phsId);
		this.prjDAO.deleteById(prjId);
		GpProject prj = this.prjDAO.findById(prjId);
		this.orgDAO.deleteById(orgId);
		GpOrganization org = this.orgDAO.findById(orgId);
		this.empDAO.deleteById(empId);
		GpProjectManager emp = this.empDAO.findById(empId);
		
		// Then
		Assert.assertNull(del);
		Assert.assertNull(phs);
		Assert.assertNull(prj);
		Assert.assertNull(org);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org = this.getOrgDefault();
		org = orgDAO.create(org);
		this.orgIdForAllTest = org.getId();
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp =this.getPmDefault();
		emp = empDAO.create(emp) ;
		this.empIdForAllTest = emp.getId();
		GpProject prj = new GpProject();
		Assert.assertNull(prj.getId());
		prj = this.getPrjDefault();
		prj = this.prjDAO.create(prj);
		this.prjIdForAllTest = prj.getId();
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs = this.getPhsDefault();
		phs = this.phsDAO.create(phs);
		this.phsIdForAllTest = phs.getId();
		
		GpDeliverable del = new GpDeliverable();
		Assert.assertNull(del.getId());
		del = this.getDelDefault();
		del = this.delDAO.create(del);
		this.delIdForAllTest = del.getId();
		

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.delDAO.deleteById(this.delIdForAllTest);
		this.phsDAO.deleteById(this.phsIdForAllTest);
		this.prjDAO.deleteById(this.prjIdForAllTest);
		this.orgDAO.deleteById(this.orgIdForAllTest);
		this.empDAO.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createDelId)) {
			this.delDAO.deleteById(this.createDelId);
		}
		if(!Objects.isNull(this.createPhsId)) {
			this.phsDAO.deleteById(this.createPhsId);
		}
		if(!Objects.isNull(this.createPrjId)) {
			this.prjDAO.deleteById(this.createPrjId);
		}
		if(!Objects.isNull(this.createOrgId)) {
			this.orgDAO.deleteById(this.createOrgId);
		}
		if(!Objects.isNull(this.createEmpId)) {
			this.empDAO.deleteById(this.createEmpId);
		}
	}
}
