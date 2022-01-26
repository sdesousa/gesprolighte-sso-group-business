package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmployeeDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpEmployeeDAOTest {

	private IGpEmployeeDAO<GpEmployee> empDAO = new GpEmployeeDAOImpl();
	private Integer empIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void testCreateEmployeeWithSuccess() {
		// Given
		GpEmployee emp = new GpEmployee();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("18023");
		emp.setLastname("PELTIER");
		emp.setFirstname("Guillaume");
		emp.setPhoneNumber("0265863289");
		emp.setPassword("myPassword");
		emp.setEmail("guillaume.peltier@gouv.fr");
		emp.setLogin("guillaume.peltier");

		// When
		emp = empDAO.create(emp);
		//On le sauvegarde pour le supprimer apres
		this.createEmpId = emp.getId();
		// Then
		Assert.assertNotNull(emp.getId());
	}
	
	@Test
	public void testUpdateEmployeeWithSuccess() {
		//Given
		GpEmployee emp = new GpEmployee();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("18023");
		emp.setLastname("PELTIER");
		emp.setFirstname("Guillaume");
		emp.setPhoneNumber("0265863289");
		emp.setPassword("myPassword");
		emp.setEmail("guillaume.peltier@gouv.fr");
		emp.setLogin("guillaume.peltier");
		emp = empDAO.create(emp);
		this.createEmpId = emp.getId();
		Assert.assertEquals("18023", emp.getFileNumber());
		Assert.assertEquals("PELTIER", emp.getLastname());
		Assert.assertEquals("Guillaume", emp.getFirstname());
		Assert.assertEquals("0265863289", emp.getPhoneNumber());
		Assert.assertEquals("myPassword", emp.getPassword());
		Assert.assertEquals("guillaume.peltier@gouv.fr", emp.getEmail());
		Assert.assertEquals("guillaume.peltier", emp.getLogin());
		
		//When
		emp.setFileNumber("18888");
		emp.setLastname("UPELTIER");
		emp.setFirstname("UGuillaume");
		emp.setPhoneNumber("U0265863289");
		emp.setPassword("UmyPassword");
		emp.setEmail("Uguillaume.peltier@gouv.fr");
		emp.setLogin("Uguillaume.peltier");
		empDAO.update(emp);
		
		//Then
		emp = empDAO.findById(createEmpId);
		Assert.assertEquals("18888", emp.getFileNumber());
		Assert.assertEquals("UPELTIER", emp.getLastname());
		Assert.assertEquals("UGuillaume", emp.getFirstname());
		Assert.assertEquals("U0265863289", emp.getPhoneNumber());
		Assert.assertEquals("UmyPassword", emp.getPassword());
		Assert.assertEquals("Uguillaume.peltier@gouv.fr", emp.getEmail());
		Assert.assertEquals("Uguillaume.peltier", emp.getLogin());
		
	}

	@Test
	public void testFindAllEmployeeWithSuccess() {
		// Given

		// When
		List<GpEmployee> emps = this.empDAO.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpEmployee emp = this.empDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp);
	}

	@Test
	public void testDelete() {
		// Given
		Integer empId = this.empIdForAllTest;
		
		// When
		this.empDAO.deleteById(empId);
		GpEmployee emp = this.empDAO.findById(empId);
		
		// Then
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() {
		GpEmployee emp = new GpEmployee();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2001");
		emp.setLastname("FABIUS");
		emp.setFirstname("Laurent");
		emp.setPhoneNumber("0125698745");
		emp.setPassword("myThirdPassword");
		emp.setEmail("laurent.fabius@gouv.fr");
		emp.setLogin("laurent.fabius");
		emp = empDAO.create(emp) ;
		this.empIdForAllTest = emp.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.empDAO.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createEmpId)) {
			this.empDAO.deleteById(this.createEmpId);
		}
	}
}
