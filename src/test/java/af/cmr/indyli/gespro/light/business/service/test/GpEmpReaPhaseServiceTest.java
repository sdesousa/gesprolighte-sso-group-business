package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.impl.GpEmpReaPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;


public class GpEmpReaPhaseServiceTest extends GpDataCreationServiceTest {

	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();
	private GpProjectServiceImpl prjService = new GpProjectServiceImpl();
	private GpPhaseServiceImpl phsService = new GpPhaseServiceImpl();
	private GpEmpReaPhaseServiceImpl erpService = new GpEmpReaPhaseServiceImpl();
	private Integer empIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer prjIdForAllTest = null;
	private Integer phsIdForAllTest = null;
	private Integer erpIdForAllTest = null;
	private Integer createEmpId = null;
	private Integer createOrgId = null;
	private Integer createPrjId = null;
	private Integer createPhsId = null;
	private Integer createErpId = null;

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
		
		GpEmpReaPhase erp = new GpEmpReaPhase();
		erp.setGpEmployee(projectManager);
		erp.setGpPhase(phs);
		erp = this.getErpCreate();
		Assert.assertNull(erp.getId());

		// When
		erp =erpService.create(erp);
		//On le sauvegarde pour le supprimer apres
		this.createErpId = erp.getId();
		// Then
		Assert.assertNotNull(erp.getId());

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
		
		GpEmpReaPhase erp = new GpEmpReaPhase();
		Assert.assertNull(erp.getId());
		erp = this.getErpCreate();
		erp.setGpEmployee(projectManager);
		erp.setGpPhase(phs);
		erp = erpService.create(erp);
		this.createErpId = erp.getId();
		
		Assert.assertEquals(this.createPhsId, erp.getGpPhase().getId());
		Assert.assertEquals(this.createEmpId, erp.getGpEmployee().getId());
		
		// When
		GpPhase phsDefault = phsService.findById(phsIdForAllTest);
		GpProjectManager pmDefault = empService.findById(empIdForAllTest);
		erp.setGpPhase(phsDefault);
		erp.setGpEmployee(pmDefault);
		erpService.update(erp);

		// Then
		erp = erpService.findById(createErpId);
		Assert.assertEquals(phsDefault.getId(), erp.getGpPhase().getId());
		Assert.assertEquals(pmDefault.getId(), erp.getGpEmployee().getId());
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpEmpReaPhase> erps = this.erpService.findAll();
		// Then
		Assert.assertTrue(erps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer erpId = this.erpIdForAllTest;

		// When
		GpEmpReaPhase erp = this.erpService.findById(erpId);
		// Then
		Assert.assertNotNull(erp);
	}

	@Test
	public void testDelete() {
		// Given
		Integer erpId = this.erpIdForAllTest;
		Integer phsId = this.phsIdForAllTest;
		Integer prjId = this.prjIdForAllTest;
		Integer orgId = this.orgIdForAllTest;
		Integer empId = this.empIdForAllTest;
		
		// When
		this.erpService.deleteById(erpId);
		GpEmpReaPhase erp = this.erpService.findById(erpId);
		this.phsService.deleteById(phsId);
		GpPhase phs = this.phsService.findById(phsId);
		this.prjService.deleteById(prjId);
		GpProject prj = this.prjService.findById(prjId);
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);
		this.empService.deleteById(empId);
		GpProjectManager emp = this.empService.findById(empId);
		
		// Then
		Assert.assertNull(erp);
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
		emp = this.getPmDefault();
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
		
		GpEmpReaPhase erp = new GpEmpReaPhase();
		Assert.assertNull(erp.getId());
		erp = this.getErpDefault();
		erp = this.erpService.create(erp);
		this.erpIdForAllTest = erp.getId();

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.erpService.deleteById(this.erpIdForAllTest);
		if(!Objects.isNull(this.createErpId)) {
			this.erpService.deleteById(this.createErpId);
		}
		this.phsService.deleteById(this.phsIdForAllTest);
		if(!Objects.isNull(this.createPhsId)) {
			this.phsService.deleteById(this.createPhsId);
		}
		this.prjService.deleteById(this.prjIdForAllTest);
		if(!Objects.isNull(this.createPrjId)) {
			this.prjService.deleteById(this.createPrjId);
		}
		this.orgService.deleteById(this.orgIdForAllTest);
		if(!Objects.isNull(this.createOrgId)) {
			this.orgService.deleteById(this.createOrgId);
		}
		this.empService.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createEmpId)) {
			this.empService.deleteById(this.createEmpId);
		}
	}
}
