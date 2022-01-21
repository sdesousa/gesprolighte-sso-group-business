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

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;


public class GpProjectDAOTest {

	private IGpOrganizationDAO<GpOrganization> orgDAO = new GpOrganizationDAOImpl();
	private IGpProjectManagerDAO empDAO = new GpProjectManagerDAOImpl();
	private IGpProjectDAO<GpProject> prjDAO = new GpProjectDAOImpl();
	private Integer prjIdForAllTest = null;
	private Integer orgIdForAllTest = null;
	private Integer empIdForAllTest = null;
	private Integer createPrjId = null;
	private Integer createOrgId = null;
	private Integer createEmpId = null;

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

		// When
		projectToCreate = prjDAO.create(projectToCreate);
		//On le sauvegarde pour le supprimer apres
		this.createPrjId = projectToCreate.getId();
		// Then
		Assert.assertNotNull(projectToCreate.getId());

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
		
		// When
		projectToCreate.setProjectCode("U18023");
		projectToCreate.setName("UService Cloud Amazon");
		projectToCreate.setDescription("UDemande d'une entit d'Amazon d'amliorer son service cloud");
		projectToCreate.setAmount(12000);
		prjDAO.update(projectToCreate);

		// Then
		projectToCreate = prjDAO.findById(createPrjId);
		Assert.assertEquals("U18023", projectToCreate.getProjectCode());
		Assert.assertEquals("UService Cloud Amazon", projectToCreate.getName());
		Assert.assertEquals("UDemande d'une entit d'Amazon d'amliorer son service cloud", projectToCreate.getDescription());
		Assert.assertEquals(12000, projectToCreate.getAmount(), 1);
	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpProject> prjs = this.prjDAO.findAll();
		// Then
		Assert.assertTrue(prjs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer prjId = this.prjIdForAllTest;

		// When
		GpProject prj = this.prjDAO.findById(prjId);
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
		this.prjDAO.deleteById(prjId);
		GpProject prj = this.prjDAO.findById(prjId);
		this.orgDAO.deleteById(orgId);
		GpOrganization org = this.orgDAO.findById(orgId);
		this.empDAO.deleteById(empId);
		GpProjectManager emp = this.empDAO.findById(empId);
		
		// Then
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

	}
	
	@After
	public void deleteAllEntityAfter() {
		this.prjDAO.deleteById(this.prjIdForAllTest);
		this.orgDAO.deleteById(this.orgIdForAllTest);
		this.empDAO.deleteById(this.empIdForAllTest);
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
