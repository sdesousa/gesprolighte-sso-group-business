package af.cmr.indyli.gespro.light.business.service.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;


public class GpPhaseServiceTest extends GpDataCreationServiceTest {

	private GpProjectServiceImpl prjService = new GpProjectServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();
	private GpPhaseServiceImpl phsService = new GpPhaseServiceImpl();
	private Integer prjIdForAllTest = null;
	private Integer phsIdForAllTest = null;
	private Integer empIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer createPrjId = null;
	private Integer createPhsId = null;
	private Integer createEmpId = null;
	private Integer createOrgId = null;

	@Test
	public void testCreatePhaseWithSuccess() throws GesproBusinessException {
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
		phs.setGpProject(projectToCreate);		

		// When
		phs = phsService.create(phs);
		//On le sauvegarde pour le supprimer apres
		this.createPhsId = phs.getId();
		// Then
		
		Assert.assertNotNull(phs.getId());

	}
	
	@Test
	public void testCreatePhaseWithExceptionDate() throws GesproBusinessException {
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

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpPhase phs = new GpPhase();
			Assert.assertNull(phs.getId());
			phs = this.getPhsCreate();
			phs.setStartDate(new GregorianCalendar(2020, Calendar.JANUARY, 01).getTime());
			phs = phsService.create(phs);
			this.createPhsId = phs.getId();
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("La phase doit commencer après le projet"));
	}
	
	@Test
	public void testCreatePhaseWithExceptionAmount() throws GesproBusinessException {
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
		projectToCreate.setStartDate(new GregorianCalendar(2022, Calendar.JANUARY, 01).getTime());
		projectToCreate.setEndDate(new GregorianCalendar(2022, Calendar.DECEMBER, 31).getTime());
		projectToCreate = prjService.create(projectToCreate);
		this.createPrjId = projectToCreate.getId();

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpPhase phs = new GpPhase();
			Assert.assertNull(phs.getId());
			phs = this.getPhsCreate();
			phs.setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 01).getTime());
			phs.setEndDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 31).getTime());
			phs.setAmount(10000.00);
			phs = phsService.create(phs);
			this.createPhsId = phs.getId();
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Le montant doit être supérieur à 150.000"));
	}
	
	@Test
	public void testUpdatePhaseWithSuccess() throws GesproBusinessException {
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
		
		Assert.assertEquals("B2", phs.getPhaseCode());
		Assert.assertEquals("seconde phase", phs.getDescription());
		Assert.assertEquals(145.50, phs.getAmount(), 0);
		Assert.assertEquals((byte) 1, phs.getStatus());
		Assert.assertEquals((byte) 0, phs.getIsEnded());
		
		// When
		phs.setPhaseCode("UB2");
		phs.setDescription("Useconde phase");
		phs.setAmount(245.50);
		phs.setIsEnded((byte) 1);
		phsService.update(phs);

		// Then
		phs = phsService.findById(createPhsId);
		Assert.assertEquals("UB2", phs.getPhaseCode());
		Assert.assertEquals("Useconde phase", phs.getDescription());
		Assert.assertEquals(245.50, phs.getAmount(), 0);
		Assert.assertEquals((byte) 1, phs.getStatus());
		Assert.assertEquals((byte) 1, phs.getIsEnded());
	}
	
	@Test
	public void testUpdatePhaseWithExceptionDate() throws GesproBusinessException {
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
		projectToCreate.setStartDate(new GregorianCalendar(2022, Calendar.JANUARY, 01).getTime());
		projectToCreate.setEndDate(new GregorianCalendar(2022, Calendar.DECEMBER, 31).getTime());
		projectToCreate = prjService.create(projectToCreate);
		this.createPrjId = projectToCreate.getId();

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpPhase phs = new GpPhase();
			Assert.assertNull(phs.getId());
			phs = this.getPhsCreate();
			phs.setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 01).getTime());
			phs.setEndDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 31).getTime());
			phs.setAmount(200000.00);
			phs = phsService.create(phs);
			this.createPhsId = phs.getId();
			phs.setAmount(120000);
			phsService.update(phs);
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Le montant doit être supérieur à 150.000"));
	}
	
	@Test
	public void testUpdatePhaseWithExceptionAmount() throws GesproBusinessException {
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

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpPhase phs = new GpPhase();
			Assert.assertNull(phs.getId());
			phs = this.getPhsCreate();
			phs = phsService.create(phs);
			this.createPhsId = phs.getId();
			phs.setStartDate(new GregorianCalendar(2020, Calendar.JANUARY, 01).getTime());
			phsService.update(phs);
	    });
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("La phase doit commencer après le projet"));
	}

	@Test
	public void testFindAllPhaseWithSuccess() {
		// Given

		// When
		List<GpPhase> phss = this.phsService.findAll();
		// Then
		Assert.assertTrue(phss.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer phsId = this.phsIdForAllTest;

		// When
		GpPhase phs = this.phsService.findById(phsId);
		// Then
		Assert.assertNotNull(phs);
	}

	@Test
	public void testDelete() {
		// Given
		Integer phsId = this.phsIdForAllTest;
		Integer prjId = this.prjIdForAllTest;
		Integer orgId = this.orgIdForAllTest;
		Integer empId = this.empIdForAllTest;
		
		// When
		this.phsService.deleteById(phsId);
		GpPhase phs = this.phsService.findById(phsId);
		this.prjService.deleteById(prjId);
		GpProject prj = this.prjService.findById(prjId);
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);
		this.empService.deleteById(empId);
		GpProjectManager emp = this.empService.findById(empId);
		
		// Then
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

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.phsService.deleteById(this.phsIdForAllTest);
		this.prjService.deleteById(this.prjIdForAllTest);
		this.orgService.deleteById(this.orgIdForAllTest);
		this.empService.deleteById(this.empIdForAllTest);
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
