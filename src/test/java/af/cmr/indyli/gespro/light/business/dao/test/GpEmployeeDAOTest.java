package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmployeeDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpEmployeeDAOTest {

	private IGpEmployeeDAO<GpEmployee> empDAO = new GpEmployeeDAOImpl();
	
	@Test
	public void testCreateEmployeeWithSuccess() {
		//Given
		GpEmployee emp = new GpEmployee();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1023");
		emp.setLastname("SARKOZY");
		emp.setFirstname("Nicolas");
		emp.setPhoneNumber("0265863289");
		emp.setPassword("myPassword");
		emp.setEmail("nico.sarko@gmail.com");
		emp.setLogin("nico.sarko");
		
		//When
		emp = empDAO.create(emp);
		
		//Then
		Assert.assertNotNull(emp.getId());
	}
	
	@Test
	public void testFindAllEmployeeWithSuccess() {
		//Given
		
		//When 
		List<GpEmployee> emps = this.empDAO.findAll();
		//Then
		Assert.assertTrue(emps.size() >0);
	}
	
	@Test
	public void testFindByIdWithSuccess() {
		//Given
		Integer empId = 1;
		
		//When 
		GpEmployee emp = this.empDAO.findById(empId);
		//Then
		Assert.assertNotNull(emp);
	}
}
