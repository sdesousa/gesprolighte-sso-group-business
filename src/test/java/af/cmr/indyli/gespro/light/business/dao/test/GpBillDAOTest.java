package af.cmr.indyli.gespro.light.business.dao.test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.time.DateUtils;
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


public class GpBillDAOTest {

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
		projectManager.setFileNumber("4052");
		projectManager.setLastname("Gabriel");
		projectManager.setFirstname("ATTAL");
		projectManager.setPhoneNumber("0236589745");
		projectManager.setPassword("myPass");
		projectManager.setEmail("gabriel.attal@gouv.fr");
		projectManager.setLogin("gab.attal");
		projectManager = empDAO.create(projectManager);
		this.createEmpId = projectManager.getId();

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization.setOrgCode("CODE-67");
		organization.setName("INDYLI-SERVICES");
		organization.setAdrWeb("indyli-services.com");
		organization.setContactEmail("contact@indyli-services.com");
		organization.setContactName("CName");
		organization.setPhoneNumber("7895");
		organization = orgDAO.create(organization);
		this.createOrgId = organization.getId();

		GpProject projectToCreate = new GpProject();
		Assert.assertNull(projectToCreate.getId());
		projectToCreate.setProjectCode("18023");
		projectToCreate.setName("Service Cloud Amazon");
		projectToCreate.setDescription("Demande d'une entit d'Amazon d'amliorer son service cloud");
		Date maDate =null ;
		try {
			maDate = DateUtils.parseDate("20-01-2022", "dd-MM-yyyy");
		} catch (ParseException e) {
			maDate = new Date();
		}
		projectToCreate.setStartDate(maDate);
		projectToCreate.setEndDate(maDate);
		projectToCreate.setAmount(9000); 
		Date CreDate = new Date(); 
		projectToCreate.setCreationDate(CreDate);
		Date MajDate = new Date(); 
		projectToCreate.setUpdateDate(MajDate); 
		projectToCreate.setGpOrganization(organization);
		projectToCreate.setGpChefProjet(projectManager);
		projectToCreate = prjDAO.create(projectToCreate);
		this.createPrjId = projectToCreate.getId();
		
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs.setPhaseCode("B2");
		phs.setDescription("seconde phase");
		Date phsDate =null ;
		try {
			phsDate = DateUtils.parseDate("20-01-2022", "dd-MM-yyyy");
		} catch (ParseException e) {
			phsDate = new Date();
			// possible rajouter logger
		}
		phs.setStartDate(phsDate);
		phs.setEndDate(phsDate);
		phs.setAmount(145.50);
		phs.setStatus((byte) 1);
		phs.setIsEnded((byte) 0);
		phs.setGpProject(projectToCreate);
		phs = phsDAO.create(phs);
		this.createPhsId = phs.getId();
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll.setBillCode("BP33");
		bll.setBillStatus("TRANSMITTED");
		bll.setAmount(99.99);
		bll.setGpPhase(phs);

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
		projectManager.setFileNumber("4052");
		projectManager.setLastname("Gabriel");
		projectManager.setFirstname("ATTAL");
		projectManager.setPhoneNumber("0236589745");
		projectManager.setPassword("myPass");
		projectManager.setEmail("gabriel.attal@gouv.fr");
		projectManager.setLogin("gab.attal");
		projectManager = empDAO.create(projectManager);
		this.createEmpId = projectManager.getId();

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization.setOrgCode("CODE-67");
		organization.setName("INDYLI-SERVICES");
		organization.setAdrWeb("indyli-services.com");
		organization.setContactEmail("contact@indyli-services.com");
		organization.setContactName("CName");
		organization.setPhoneNumber("7895");
		organization = orgDAO.create(organization);
		this.createOrgId = organization.getId();

		GpProject projectToCreate = new GpProject();
		Assert.assertNull(projectToCreate.getId());
		projectToCreate.setProjectCode("18023");
		projectToCreate.setName("Service Cloud Amazon");
		projectToCreate.setDescription("Demande d'une entit d'Amazon d'amliorer son service cloud");
		Date maDate =null ;
		try {
			maDate = DateUtils.parseDate("20-01-2022", "dd-MM-yyyy");
		} catch (ParseException e) {
			maDate = new Date();
		}
		projectToCreate.setStartDate(maDate);
		projectToCreate.setEndDate(maDate);
		projectToCreate.setAmount(9000); 
		Date CreDate = new Date(); 
		projectToCreate.setCreationDate(CreDate);
		Date MajDate = new Date(); 
		projectToCreate.setUpdateDate(MajDate); 
		projectToCreate.setGpOrganization(organization);
		projectToCreate.setGpChefProjet(projectManager);
		projectToCreate = prjDAO.create(projectToCreate);
		this.createPrjId = projectToCreate.getId();
		
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs.setPhaseCode("B2");
		phs.setDescription("seconde phase");
		Date phsDate =null ;
		try {
			phsDate = DateUtils.parseDate("20-01-2022", "dd-MM-yyyy");
		} catch (ParseException e) {
			phsDate = new Date();
			// possible rajouter logger
		}
		phs.setStartDate(phsDate);
		phs.setEndDate(phsDate);
		phs.setAmount(145.50);
		phs.setStatus((byte) 1);
		phs.setIsEnded((byte) 0);
		phs.setGpProject(projectToCreate);
		phs = phsDAO.create(phs);
		this.createPhsId = phs.getId();
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll.setBillCode("BP33");
		bll.setBillStatus("TRANSMITTED");
		bll.setAmount(99.99);
		bll.setGpPhase(phs);
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
		org.setOrgCode("E498");
		org.setName("Pole emploi");
		org.setPhoneNumber("3949");
		org.setAdrWeb("www.pole-emploi.fr");
		org.setContactName("Julien");
		org.setContactEmail("julien@pole-emploi.fr");
		org = orgDAO.create(org);
		this.orgIdForAllTest = org.getId();
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1050");
		emp.setLastname("Segolene");
		emp.setFirstname("ROYAL");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("mySecondPassword");
		emp.setEmail("segolene.royal@gouv.fr");
		emp.setLogin("sego.royal");
		emp = empDAO.create(emp) ;
		this.empIdForAllTest = emp.getId();
		GpProject prj = new GpProject();
		Assert.assertNull(prj.getId());
		prj.setProjectCode("18023");
		prj.setName("Service Cloud Amazon");
		prj.setDescription("Mise en place Cloud Test");
		Date maDate =null ;
		try {
			maDate = DateUtils.parseDate("20-01-2022", "dd-MM-yyyy");
		} catch (ParseException e) {
			maDate = new Date();
			// possible rajouter logger
		}
		prj.setStartDate(maDate);
		prj.setEndDate(maDate);
		prj.setAmount(9000); 
		prj.setCreationDate(new Date());
		Date MajDate = new Date(); 
		prj.setUpdateDate(MajDate);
		prj.setGpOrganization(org); 
		prj.setGpChefProjet(emp);
		prj = this.prjDAO.create(prj);
		this.prjIdForAllTest = prj.getId();
		
		GpPhase phs = new GpPhase();
		Assert.assertNull(phs.getId());
		phs.setPhaseCode("A1");
		phs.setDescription("premiere phase");
		Date phsDate =null ;
		try {
			phsDate = DateUtils.parseDate("20-01-2022", "dd-MM-yyyy");
		} catch (ParseException e) {
			phsDate = new Date();
			// possible rajouter logger
		}
		phs.setStartDate(phsDate);
		phs.setEndDate(phsDate);
		phs.setAmount(145.50);
		phs.setStatus((byte) 1);
		phs.setIsEnded((byte) 0);
		phs.setGpProject(prj);
		phs = this.phsDAO.create(phs);
		this.phsIdForAllTest = phs.getId();
		
		GpBill bll = new GpBill();
		Assert.assertNull(bll.getId());
		bll.setBillCode("BP12");
		bll.setBillStatus("PAID");
		bll.setAmount(600.50);
		bll.setGpPhase(phs);
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
