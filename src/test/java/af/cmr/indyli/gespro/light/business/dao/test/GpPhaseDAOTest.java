package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;


public class GpPhaseDAOTest extends GpDataCreationDAOTest {

	private GpProjectDAOImpl prjDAO = new GpProjectDAOImpl();
	private GpProjectManagerDAOImpl empDAO = new GpProjectManagerDAOImpl();
	private GpOrganizationDAOImpl orgDAO = new GpOrganizationDAOImpl();
	private GpPhaseDAOImpl phsDAO = new GpPhaseDAOImpl();
	private Integer prjIdForAllTest = null;
	private Integer phsIdForAllTest = null;
	private Integer empIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer createPrjId = null;
	private Integer createPhsId = null;
	private Integer createEmpId = null;
	private Integer createOrgId = null;

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
		phs.setGpProject(projectToCreate);		

		// When
		phs = phsDAO.create(phs);
		//On le sauvegarde pour le supprimer apres
		this.createPhsId = phs.getId();
		// Then
		Assert.assertNotNull(phs.getId());

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
		phsDAO.update(phs);

		// Then
		phs = phsDAO.findById(createPhsId);
		Assert.assertEquals("UB2", phs.getPhaseCode());
		Assert.assertEquals("Useconde phase", phs.getDescription());
		Assert.assertEquals(245.50, phs.getAmount(), 0);
		Assert.assertEquals((byte) 1, phs.getStatus());
		Assert.assertEquals((byte) 1, phs.getIsEnded());
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpPhase> phss = this.phsDAO.findAll();
		// Then
		Assert.assertTrue(phss.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer phsId = this.phsIdForAllTest;

		// When
		GpPhase phs = this.phsDAO.findById(phsId);
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
		this.phsDAO.deleteById(phsId);
		GpPhase phs = this.phsDAO.findById(phsId);
		this.prjDAO.deleteById(prjId);
		GpProject prj = this.prjDAO.findById(prjId);
		this.orgDAO.deleteById(orgId);
		GpOrganization org = this.orgDAO.findById(orgId);
		this.empDAO.deleteById(empId);
		GpProjectManager emp = this.empDAO.findById(empId);
		
		// Then
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
		emp = this.getPmDefault();
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

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.phsDAO.deleteById(this.phsIdForAllTest);
		this.prjDAO.deleteById(this.prjIdForAllTest);
		this.orgDAO.deleteById(this.orgIdForAllTest);
		this.empDAO.deleteById(this.empIdForAllTest);
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
