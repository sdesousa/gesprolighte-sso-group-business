package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpProjectManagerDAOTest {

	private IGpProjectManagerDAO empDAO = new GpProjectManagerDAOImpl();
	private Integer pmIdForAllTest = null;
	private Integer createPmId = null;
	
	@Test
	public void testCreateEmployeeWithSuccess() {
		//Given
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1024");
		emp.setLastname("HOLLANDE");
		emp.setFirstname("Francois");
		emp.setPhoneNumber("0365987854");
		emp.setPassword("mySecondPassword");
		emp.setEmail("francois.hollande@gouv.fr");
		emp.setLogin("francois.hollande");
		
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
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1024");
		emp.setLastname("HOLLANDE");
		emp.setFirstname("Francois");
		emp.setPhoneNumber("0365987854");
		emp.setPassword("mySecondPassword");
		emp.setEmail("francois.hollande@gouv.fr");
		emp.setLogin("francois.hollande");
		emp = empDAO.create(emp);
		this.createPmId = emp.getId();
		Assert.assertEquals("1024", emp.getFileNumber());
		Assert.assertEquals("HOLLANDE", emp.getLastname());
		Assert.assertEquals("Francois", emp.getFirstname());
		Assert.assertEquals("0365987854", emp.getPhoneNumber());
		Assert.assertEquals("mySecondPassword", emp.getPassword());
		Assert.assertEquals("francois.hollande@gouv.fr", emp.getEmail());
		Assert.assertEquals("francois.hollande", emp.getLogin());
		
		//When
		emp.setFileNumber("U1024");
		emp.setLastname("UHOLLANDE");
		emp.setFirstname("UFrancois");
		emp.setPhoneNumber("U0365987854");
		emp.setPassword("UmySecondPassword");
		emp.setEmail("Ufrancois.hollande@gouv.fr");
		emp.setLogin("Ufrancois.hollande");
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
		
	}
	
	@Test
	public void testFindAllEmployeeWithSuccess() {
		//Given
		
		//When 
		List<GpProjectManager> emps = this.empDAO.findAll();
		
		//Then
		Assert.assertTrue(emps.size() >0);
	}
	
	@Test
	public void testFindByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		GpProjectManager emp = this.empDAO.findById(empId);
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
		GpProjectManager emp = this.empDAO.findById(empId);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() {
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
