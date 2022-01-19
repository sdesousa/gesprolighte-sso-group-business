package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAdminDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAdminDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAdmin;

public class GpAdminDAOTest {

	private IGpAdminDAO empDAO = new GpAdminDAOImpl();
	private Integer pmIdForAllTest = null;
	private Integer createPmId = null;
	
	@Test
	public void testCreateEmployeeWithSuccess() {
		//Given
		GpAdmin emp = new GpAdmin();
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
	public void testFindAllEmployeeWithSuccess() {
		//Given
		
		//When 
		List<GpAdmin> emps = this.empDAO.findAll();
		
		//Then
		Assert.assertTrue(emps.size() >0);
	}
	
	@Test
	public void testFindByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		GpAdmin emp = this.empDAO.findById(empId);
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
		GpAdmin emp = this.empDAO.findById(empId);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		GpAdmin emp = new GpAdmin();
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
