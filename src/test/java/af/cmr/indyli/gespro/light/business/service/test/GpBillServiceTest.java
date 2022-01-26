package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.impl.GpBillServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;


public class GpBillServiceTest extends GpDataCreationServiceTest {

	private GpProjectServiceImpl prjService = new GpProjectServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();
	private GpPhaseServiceImpl phsService = new GpPhaseServiceImpl();
	private GpBillServiceImpl bllService = new GpBillServiceImpl();
	private Integer prjIdForAllTest = null;
	private Integer phsIdForAllTest = null;
	private Integer empIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer bllIdForAllTest = null;
	private Integer createPrjId = null;
	private Integer createPhsId = null;
	private Integer createEmpId = null;
	private Integer createOrgId = null;
	private Integer createBllId = null;

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
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll = this.getBllCreate();

		// When
		bll = bllService.create(bll);
		//On le sauvegarde pour le supprimer apres
		this.createBllId = bll.getId();
		// Then
		Assert.assertNotNull(bll.getId());

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
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll = this.getBllCreate();
		bll = bllService.create(bll);
		this.createBllId = bll.getId();
		Assert.assertEquals("BP33", bll.getBillCode());
		Assert.assertEquals("TRANSMITTED", bll.getBillStatus());
		Assert.assertEquals(99.99, bll.getAmount(), 0);
		
		// When
		bll.setBillCode("UBP33");
		bll.setBillStatus("PAID");
		bll.setAmount(109.99);
		bllService.update(bll);

		// Then
		bll = bllService.findById(createBllId);
		Assert.assertEquals("UBP33", bll.getBillCode());
		Assert.assertEquals("PAID", bll.getBillStatus());
		Assert.assertEquals(109.99, bll.getAmount(), 0);
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpBill> blls = this.bllService.findAll();
		// Then
		Assert.assertTrue(blls.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer bllId = this.bllIdForAllTest;

		// When
		GpBill bll = this.bllService.findById(bllId);
		// Then
		Assert.assertNotNull(bll);
	}

	@Test
	public void testDelete() {
		// Given
		Integer bllId = this.bllIdForAllTest;
		Integer phsId = this.phsIdForAllTest;
		Integer prjId = this.prjIdForAllTest;
		Integer orgId = this.orgIdForAllTest;
		Integer empId = this.empIdForAllTest;
		
		// When
		this.bllService.deleteById(bllId);
		GpBill bll = this.bllService.findById(bllId);
		this.phsService.deleteById(phsId);
		GpPhase phs = this.phsService.findById(phsId);
		this.prjService.deleteById(prjId);
		GpProject prj = this.prjService.findById(prjId);
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);
		this.empService.deleteById(empId);
		GpProjectManager emp = this.empService.findById(empId);
		
		// Then
		Assert.assertNull(bll);
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
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll = this.getBllDefault();
		bll = this.bllService.create(bll);
		this.bllIdForAllTest = bll.getId();
		

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.bllService.deleteById(this.bllIdForAllTest);
		this.phsService.deleteById(this.phsIdForAllTest);
		this.prjService.deleteById(this.prjIdForAllTest);
		this.orgService.deleteById(this.orgIdForAllTest);
		this.empService.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createBllId)) {
			this.bllService.deleteById(this.createBllId);
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
