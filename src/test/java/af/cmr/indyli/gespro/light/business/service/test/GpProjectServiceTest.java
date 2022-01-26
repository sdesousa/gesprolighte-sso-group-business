package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectManagerService;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;


public class GpProjectServiceTest extends GpDataCreationServiceTest {

	private IGpOrganizationService<GpOrganization> orgService = new GpOrganizationServiceImpl();
	private IGpProjectManagerService empService = new GpProjectManagerServiceImpl();
	private IGpProjectService<GpProject> prjService = new GpProjectServiceImpl();
	private Integer prjIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer empIdForAllTest = null;
	private Integer createPrjId = null;
	private Integer createOrgId = null;
	private Integer createEmpId = null;

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

		// When
		projectToCreate = prjService.create(projectToCreate);
		//On le sauvegarde pour le supprimer apres
		this.createPrjId = projectToCreate.getId();
		// Then
		Assert.assertNotNull(projectToCreate.getId());

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
		Assert.assertEquals("18023", projectToCreate.getProjectCode());
		Assert.assertEquals("Service Cloud Amazon", projectToCreate.getName());
		Assert.assertEquals("Demande d'une entit d'Amazon d'amliorer son service cloud", projectToCreate.getDescription());
		Assert.assertEquals(9000, projectToCreate.getAmount(), 0);
		
		// When
		projectToCreate.setProjectCode("U18023");
		projectToCreate.setName("UService Cloud Amazon");
		projectToCreate.setDescription("UDemande d'une entit d'Amazon d'amliorer son service cloud");
		projectToCreate.setAmount(12000);
		prjService.update(projectToCreate);

		// Then
		projectToCreate = prjService.findById(createPrjId);
		Assert.assertEquals("U18023", projectToCreate.getProjectCode());
		Assert.assertEquals("UService Cloud Amazon", projectToCreate.getName());
		Assert.assertEquals("UDemande d'une entit d'Amazon d'amliorer son service cloud", projectToCreate.getDescription());
		Assert.assertEquals(12000, projectToCreate.getAmount(), 0);
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpProject> prjs = this.prjService.findAll();
		// Then
		Assert.assertTrue(prjs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer prjId = this.prjIdForAllTest;

		// When
		GpProject prj = this.prjService.findById(prjId);
		// Then
		Assert.assertNotNull(prj);
	}

	@Test
	public void testDelete() {
		// Given
		Integer prjId = this.prjIdForAllTest;
		Integer orgId = this.orgIdForAllTest;
		Integer empId = this.empIdForAllTest;
		
		// When
		this.prjService.deleteById(prjId);
		GpProject prj = this.prjService.findById(prjId);
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);
		this.empService.deleteById(empId);
		GpProjectManager emp = this.empService.findById(empId);
		
		// Then
		Assert.assertNull(prj);
		Assert.assertNull(org);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org =this.getOrgDefault();
		org = orgService.create(org);
		this.orgIdForAllTest = org.getId();
		
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp =this.getPmDefault();
		emp = empService.create(emp) ;
		this.empIdForAllTest = emp.getId();
		
		GpProject prj = new GpProject();
		Assert.assertNull(prj.getId());
		prj =this.getPrjDefault();
		prj = this.prjService.create(prj);
		this.prjIdForAllTest = prj.getId();

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.prjService.deleteById(this.prjIdForAllTest);
		this.orgService.deleteById(this.orgIdForAllTest);
		this.empService.deleteById(this.empIdForAllTest);
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
