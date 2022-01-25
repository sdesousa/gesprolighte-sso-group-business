package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.impl.GpBillDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;


public class GpBillDAOTest extends GpDataCreationDAOTest {

	private GpProjectDAOImpl prjDAO = new GpProjectDAOImpl();
	private GpProjectManagerDAOImpl empDAO = new GpProjectManagerDAOImpl();
	private GpOrganizationDAOImpl orgDAO = new GpOrganizationDAOImpl();
	private GpPhaseDAOImpl phsDAO = new GpPhaseDAOImpl();
	private GpBillDAOImpl bllDAO = new GpBillDAOImpl();
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
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll = this.getBllCreate();

		// When
		bll = bllDAO.create(bll);
		//On le sauvegarde pour le supprimer apres
		this.createBllId = bll.getId();
		// Then
		Assert.assertNotNull(bll.getId());

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
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll = this.getBllCreate();
		bll = bllDAO.create(bll);
		this.createBllId = bll.getId();
		Assert.assertEquals("BP33", bll.getBillCode());
		Assert.assertEquals("TRANSMITTED", bll.getBillStatus());
		Assert.assertEquals(99.99, bll.getAmount(), 0);
		
		// When
		bll.setBillCode("UBP33");
		bll.setBillStatus("PAID");
		bll.setAmount(109.99);
		bllDAO.update(bll);

		// Then
		bll = bllDAO.findById(createBllId);
		Assert.assertEquals("UBP33", bll.getBillCode());
		Assert.assertEquals("PAID", bll.getBillStatus());
		Assert.assertEquals(109.99, bll.getAmount(), 0);
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpBill> blls = this.bllDAO.findAll();
		// Then
		Assert.assertTrue(blls.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer bllId = this.bllIdForAllTest;

		// When
		GpBill bll = this.bllDAO.findById(bllId);
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
		this.bllDAO.deleteById(bllId);
		GpBill bll = this.bllDAO.findById(bllId);
		this.phsDAO.deleteById(phsId);
		GpPhase phs = this.phsDAO.findById(phsId);
		this.prjDAO.deleteById(prjId);
		GpProject prj = this.prjDAO.findById(prjId);
		this.orgDAO.deleteById(orgId);
		GpOrganization org = this.orgDAO.findById(orgId);
		this.empDAO.deleteById(empId);
		GpProjectManager emp = this.empDAO.findById(empId);
		
		// Then
		Assert.assertNull(bll);
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
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll = this.getBllDefault();
		bll = this.bllDAO.create(bll);
		this.bllIdForAllTest = bll.getId();
		

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.bllDAO.deleteById(this.bllIdForAllTest);
		this.phsDAO.deleteById(this.phsIdForAllTest);
		this.prjDAO.deleteById(this.prjIdForAllTest);
		this.orgDAO.deleteById(this.orgIdForAllTest);
		this.empDAO.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createBllId)) {
			this.bllDAO.deleteById(this.createBllId);
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
