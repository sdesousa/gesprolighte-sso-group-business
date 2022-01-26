package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpEmpReaPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;


public class GpEmpReaPhaseDAOTest extends GpDataCreationDAOTest {

	private GpProjectManagerDAOImpl empDAO = new GpProjectManagerDAOImpl();
	private GpOrganizationDAOImpl orgDAO = new GpOrganizationDAOImpl();
	private GpProjectDAOImpl prjDAO = new GpProjectDAOImpl();
	private GpPhaseDAOImpl phsDAO = new GpPhaseDAOImpl();
	private GpEmpReaPhaseDAOImpl erpDAO = new GpEmpReaPhaseDAOImpl();
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
		
		GpEmpReaPhase erp = new GpEmpReaPhase();
		erp.setGpEmployee(projectManager);
		erp.setGpPhase(phs);
		erp = this.getErpCreate();
		Assert.assertNull(erp.getId());

		// When
		erp =erpDAO.create(erp);
		//On le sauvegarde pour le supprimer apres
		this.createErpId = erp.getId();
		// Then
		Assert.assertNotNull(erp.getId());

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
		
		GpEmpReaPhase erp = new GpEmpReaPhase();
		Assert.assertNull(erp.getId());
		erp = this.getErpCreate();
		erp.setGpEmployee(projectManager);
		erp.setGpPhase(phs);
		erp = erpDAO.create(erp);
		this.createErpId = erp.getId();
		
		Assert.assertEquals(this.createPhsId, erp.getGpPhase().getId());
		Assert.assertEquals(this.createEmpId, erp.getGpEmployee().getId());
		
		// When
		GpPhase phsDefault = phsDAO.findById(phsIdForAllTest);
		GpProjectManager pmDefault = empDAO.findById(empIdForAllTest);
		erp.setGpPhase(phsDefault);
		erp.setGpEmployee(pmDefault);
		erpDAO.update(erp);

		// Then
		erp = erpDAO.findById(createErpId);
		Assert.assertEquals(phsDefault.getId(), erp.getGpPhase().getId());
		Assert.assertEquals(pmDefault.getId(), erp.getGpEmployee().getId());
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpEmpReaPhase> erps = this.erpDAO.findAll();
		// Then
		Assert.assertTrue(erps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer erpId = this.erpIdForAllTest;

		// When
		GpEmpReaPhase erp = this.erpDAO.findById(erpId);
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
		this.erpDAO.deleteById(erpId);
		GpEmpReaPhase erp = this.erpDAO.findById(erpId);
		this.phsDAO.deleteById(phsId);
		GpPhase phs = this.phsDAO.findById(phsId);
		this.prjDAO.deleteById(prjId);
		GpProject prj = this.prjDAO.findById(prjId);
		this.orgDAO.deleteById(orgId);
		GpOrganization org = this.orgDAO.findById(orgId);
		this.empDAO.deleteById(empId);
		GpProjectManager emp = this.empDAO.findById(empId);
		
		// Then
		Assert.assertNull(erp);
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
		
		GpEmpReaPhase erp = new GpEmpReaPhase();
		Assert.assertNull(erp.getId());
		erp = this.getErpDefault();
		erp = this.erpDAO.create(erp);
		this.erpIdForAllTest = erp.getId();

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.erpDAO.deleteById(this.erpIdForAllTest);
		if(!Objects.isNull(this.createErpId)) {
			this.erpDAO.deleteById(this.createErpId);
		}
		this.phsDAO.deleteById(this.phsIdForAllTest);
		if(!Objects.isNull(this.createPhsId)) {
			this.phsDAO.deleteById(this.createPhsId);
		}
		this.prjDAO.deleteById(this.prjIdForAllTest);
		if(!Objects.isNull(this.createPrjId)) {
			this.prjDAO.deleteById(this.createPrjId);
		}
		this.orgDAO.deleteById(this.orgIdForAllTest);
		if(!Objects.isNull(this.createOrgId)) {
			this.orgDAO.deleteById(this.createOrgId);
		}
		this.empDAO.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createEmpId)) {
			this.empDAO.deleteById(this.createEmpId);
		}
	}
}
