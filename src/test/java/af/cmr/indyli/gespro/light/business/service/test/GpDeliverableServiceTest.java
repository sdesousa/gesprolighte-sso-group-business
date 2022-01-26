package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.impl.GpDeliverableServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;


public class GpDeliverableServiceTest extends GpDataCreationServiceTest {
	
	
	private GpProjectServiceImpl prjService = new GpProjectServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();
	private GpPhaseServiceImpl phsService = new GpPhaseServiceImpl();
	private GpDeliverableServiceImpl delService = new GpDeliverableServiceImpl();
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
	public void testCreateAddressWithSuccess() throws GesproBusinessException {
		// Given
		GpProjectManager projectManager = new GpProjectManager();
		Assert.assertNull(projectManager.getId());
		projectManager = this.getPmCreate();
		projectManager = empService.create(projectManager);
		this.createEmpId = projectManager.getId();

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization = this.getOrgCreate();
		organization = orgService.create(organization);
		this.createOrgId = organization.getId();

		GpProject projectToCreate = new GpProject();
		Assert.assertNull(projectToCreate.getId());
		projectToCreate = this.getPrjCreate();
		projectToCreate = prjService.create(projectToCreate);
		this.createPrjId = projectToCreate.getId();
		
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs = this.getPhsCreate();
		phs = phsService.create(phs);
		this.createPhsId = phs.getId();
		
		GpDeliverable del = new GpDeliverable();
		Assert.assertNull(del.getId());
		del = this.getDelCreate();		

		// When
		del = delService.create(del);
		//On le sauvegarde pour le supprimer apres
		this.createDelId = del.getId();
		// Then
		Assert.assertNotNull(del.getId());

	}
	
	@Test
	public void testUpdateAddressWithSuccess() throws GesproBusinessException {
		// Given
		GpProjectManager projectManager = new GpProjectManager();
		Assert.assertNull(projectManager.getId());
		projectManager = this.getPmCreate();
		projectManager = empService.create(projectManager);
		this.createEmpId = projectManager.getId();

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization = this.getOrgCreate();
		organization = orgService.create(organization);
		this.createOrgId = organization.getId();

		GpProject projectToCreate = new GpProject();
		Assert.assertNull(projectToCreate.getId());
		projectToCreate = this.getPrjCreate();
		projectToCreate = prjService.create(projectToCreate);
		this.createPrjId = projectToCreate.getId();
		
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs = this.getPhsCreate();
		phs = phsService.create(phs);
		this.createPhsId = phs.getId();
		
		GpDeliverable del = new GpDeliverable();
		Assert.assertNull(del.getId());
		del = this.getDelCreate();
		del =delService.create(del);
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
		delService.update(del);

		// Then
		del = delService.findById(createDelId);
		Assert.assertEquals("UDE007", del.getDelCode());
		Assert.assertEquals("ULivraision 007", del.getLabel());
		Assert.assertEquals("UDescription 007", del.getDescription());
		Assert.assertEquals("Ulien007", del.getDelPath());
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpDeliverable> dels = this.delService.findAll();
		// Then
		Assert.assertTrue(dels.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer delId = this.delIdForAllTest;

		// When
		GpDeliverable del = this.delService.findById(delId);
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
		this.delService.deleteById(delId);
		GpDeliverable del = this.delService.findById(delId);
		this.phsService.deleteById(phsId);
		GpPhase phs = this.phsService.findById(phsId);
		this.prjService.deleteById(prjId);
		GpProject prj = this.prjService.findById(prjId);
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);
		this.empService.deleteById(empId);
		GpProjectManager emp = this.empService.findById(empId);
		
		// Then
		Assert.assertNull(del);
		Assert.assertNull(phs);
		Assert.assertNull(prj);
		Assert.assertNull(org);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org = this.getOrgDefault();
		org = orgService.create(org);
		this.orgIdForAllTest = org.getId();
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp =this.getPmDefault();
		emp = empService.create(emp) ;
		this.empIdForAllTest = emp.getId();
		GpProject prj = new GpProject();
		Assert.assertNull(prj.getId());
		prj = this.getPrjDefault();
		prj = this.prjService.create(prj);
		this.prjIdForAllTest = prj.getId();
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs = this.getPhsDefault();
		phs = this.phsService.create(phs);
		this.phsIdForAllTest = phs.getId();
		
		GpDeliverable del = new GpDeliverable();
		Assert.assertNull(del.getId());
		del = this.getDelDefault();
		del = this.delService.create(del);
		this.delIdForAllTest = del.getId();
		

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.delService.deleteById(this.delIdForAllTest);
		this.phsService.deleteById(this.phsIdForAllTest);
		this.prjService.deleteById(this.prjIdForAllTest);
		this.orgService.deleteById(this.orgIdForAllTest);
		this.empService.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createDelId)) {
			this.delService.deleteById(this.createDelId);
		}
		if(!Objects.isNull(this.createPhsId)) {
			this.phsService.deleteById(this.createPhsId);
		}
		if(!Objects.isNull(this.createPrjId)) {
			this.prjService.deleteById(this.createPrjId);
		}
		if(!Objects.isNull(this.createOrgId)) {
			this.orgService.deleteById(this.createOrgId);
		}
		if(!Objects.isNull(this.createEmpId)) {
			this.empService.deleteById(this.createEmpId);
		}
	}
}
