package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpTechnicianDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpTechnicianDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;

public class GpTechnicianDAOTest {

	private IGpTechnicianDAO empDAO = new GpTechnicianDAOImpl();
	private Integer pmIdForAllTest = null;
	private Integer createPmId = null;
	
	@Test
	public void testCreateEmployeeWithSuccess() {
		//Given
		GpTechnician emp = new GpTechnician();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1024");
		emp.setLastname("HOLLANDE");
		emp.setFirstname("Francois");
		emp.setPhoneNumber("0365987854");
		emp.setPassword("mySecondPassword");
		emp.setEmail("francois.hollande@gouv.fr");
		emp.setLogin("francois.hollande");
		emp.setLastDiploma("ENA");
		emp.setGraduationYear(1988);
		
		//When
		emp = empDAO.create(emp) ;
		//On le sauvegarde pour le supprimer après
		this.createPmId = emp.getId();
		
		//Then
		Assert.assertNotNull(emp.getId());
	}
	
	@Test
	public void testUpdateEmployeeWithSuccess() {
		//Given
		GpTechnician emp = new GpTechnician();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1024");
		emp.setLastname("HOLLANDE");
		emp.setFirstname("Francois");
		emp.setPhoneNumber("0365987854");
		emp.setPassword("mySecondPassword");
		emp.setEmail("francois.hollande@gouv.fr");
		emp.setLogin("francois.hollande");
		emp.setLastDiploma("ENA");
		emp.setGraduationYear(1988);
		emp = empDAO.create(emp);
		this.createPmId = emp.getId();
		
		//When
		emp.setFileNumber("U1024");
		emp.setLastname("UHOLLANDE");
		emp.setFirstname("UFrancois");
		emp.setPhoneNumber("U0365987854");
		emp.setPassword("UmySecondPassword");
		emp.setEmail("Ufrancois.hollande@gouv.fr");
		emp.setLogin("Ufrancois.hollande");
		emp.setLastDiploma("UENA");
		emp.setGraduationYear(1900);
		empDAO.update(emp);
		
		//Then
		emp = empDAO.findById(createPmId);
		Assert.assertEquals("U1024", emp.getFileNumber());
		Assert.assertEquals("UHOLLANDE", emp.getLastname());
		Assert.assertEquals("UFrancois", emp.getFirstname());
		Assert.assertEquals("U0365987854", emp.getPhoneNumber());
		Assert.assertEquals("UmySecondPassword", emp.getPassword());
		Assert.assertEquals("Ufrancois.hollande@gouv.fr", emp.getEmail());
		Assert.assertEquals("Ufrancois.hollande", emp.getLogin());
		Assert.assertEquals("UENA", emp.getLastDiploma());
		Assert.assertEquals(1900, emp.getGraduationYear());
		
	}
	
	@Test
	public void testFindAllEmployeeWithSuccess() {
		//Given
		
		//When 
		List<GpTechnician> emps = this.empDAO.findAll();
		
		//Then
		Assert.assertTrue(emps.size() >0);
	}
	
	@Test
	public void testFindByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		GpTechnician emp = this.empDAO.findById(empId);
		//Then
		Assert.assertNotNull(emp);
	}
	
	@Test
	public void testDeleteByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		this.empDAO.deleteById(empId);
		
		
		//Then
		GpTechnician emp = this.empDAO.findById(empId);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		GpTechnician emp = new GpTechnician();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1050");
		emp.setLastname("Segolene");
		emp.setFirstname("ROYAL");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("mySecondPassword");
		emp.setEmail("segolene.royal@gouv.fr");
		emp.setLogin("sego.royal");
		emp.setLastDiploma("ENA");
		emp.setGraduationYear(1990);
		emp = empDAO.create(emp) ;
		this.pmIdForAllTest = emp.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.empDAO.deleteById(this.pmIdForAllTest);
		if(!Objects.isNull(this.createPmId)) {
			this.empDAO.deleteById(this.createPmId);
		}
	}
}
