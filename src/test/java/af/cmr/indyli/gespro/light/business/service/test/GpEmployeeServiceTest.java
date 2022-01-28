package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpEmployeeServiceImpl;

public class GpEmployeeServiceTest {

	private IGpEmployeeService<GpEmployee> empService = new GpEmployeeServiceImpl();
	

	private Integer empIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void testCreateEmployeeWithSuccess() throws GesproBusinessException {
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
		emp = empService.create(emp);
		//On le sauvegarde pour le supprimer apres
		this.createEmpId = emp.getId();

		// Then
		Assert.assertNotNull(emp.getId());
	}
	
	@Test
	public void testCreateEmployeeWithException() throws GesproBusinessException {
		// Given

		// When
		Exception exceptionFileNumber = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("2001");
			emp.setLastname("Laurent");
			emp.setFirstname("FABIUS");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setEmail("laurent.fabius@gouv.be");
			emp.setLogin("laurent.fabius.gouv");
			empService.create(emp);
	    });
		String actualMessageFileNumber = exceptionFileNumber.getMessage();
		Exception exceptionEmail = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("2002");
			emp.setLastname("Laurent");
			emp.setFirstname("FABIUS");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setEmail("laurent.fabius@gouv.fr");
			emp.setLogin("laurent.fabius.fr");
			empService.create(emp);
	    });
		String actualMessageEmail = exceptionEmail.getMessage();
		Exception exceptionLogin = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("2003");
			emp.setLastname("Laurent");
			emp.setFirstname("FABIUS");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setEmail("laurent.fabius@gouv.de");
			emp.setLogin("laurent.fabius");
			empService.create(emp);
	    });
		String actualMessageLogin = exceptionLogin.getMessage();
		Exception exceptionEmptyEmail = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("2002");
			emp.setLastname("FABIUS");
			emp.setFirstname("Laurent");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setLogin("laurent.fabius.fr");
			empService.create(emp);
	    });
		String actualMessageEmptyEmail = exceptionEmptyEmail.getMessage();
		Exception exceptionEmptyLogin = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("2002");
			emp.setLastname("FABIUS");
			emp.setFirstname("Laurent");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setEmail("laurent.fabius@gouv.be");
			emp.setLogin("");
			empService.create(emp);
	    });
		String actualMessageEmptyLogin = exceptionEmptyLogin.getMessage();
		Exception exceptionEmptyFileNumber = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("  ");
			emp.setLastname("FABIUS");
			emp.setFirstname("Laurent");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setEmail("laurent.fabius@gouv.be");
			emp.setLogin("laurent.fabius.fr");
			empService.create(emp);
	    });
		String actualMessageEmptyFileNumber = exceptionEmptyFileNumber.getMessage();
		Exception exceptionEmptyFirstname = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("2002");
			emp.setLastname("FABIUS");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setEmail("laurent.fabius@gouv.be");
			emp.setLogin("laurent.fabius.fr");
			empService.create(emp);
	    });
		String actualMessageEmptyFirstname = exceptionEmptyFirstname.getMessage();
		Exception exceptionEmptyLastname = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("2002");
			emp.setLastname("");
			emp.setFirstname("Laurent");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setEmail("laurent.fabius@gouv.be");
			emp.setLogin("laurent.fabius.fr");
			empService.create(emp);
	    });
		String actualMessageEmptyLastname = exceptionEmptyLastname.getMessage();

		// Then
		Assert.assertTrue(actualMessageFileNumber.contains("Un employee existe deja avec cet email"));
		Assert.assertTrue(actualMessageLogin.contains("Un employee existe deja avec cet email"));
		Assert.assertTrue(actualMessageEmail.contains("Un employee existe deja avec cet email"));
		Assert.assertTrue(actualMessageEmptyEmail.contains("Email ou login ou matricule ou nom manquant"));
		Assert.assertTrue(actualMessageEmptyLogin.contains("Email ou login ou matricule ou nom manquant"));
		Assert.assertTrue(actualMessageEmptyFileNumber.contains("Email ou login ou matricule ou nom manquant"));
		Assert.assertTrue(actualMessageEmptyFirstname.contains("Email ou login ou matricule ou nom manquant"));
		Assert.assertTrue(actualMessageEmptyLastname.contains("Email ou login ou matricule ou nom manquant"));
	}
	
	@Test
	public void testUpdateEmployeeWithSuccess() throws GesproBusinessException {
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
		emp = empService.create(emp);
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
		empService.update(emp);
		
		//Then
		emp = empService.findById(createEmpId);
		Assert.assertEquals("18888", emp.getFileNumber());
		Assert.assertEquals("UPELTIER", emp.getLastname());
		Assert.assertEquals("UGuillaume", emp.getFirstname());
		Assert.assertEquals("U0265863289", emp.getPhoneNumber());
		Assert.assertEquals("UmyPassword", emp.getPassword());
		Assert.assertEquals("guillaume.peltier@gouv.fr", emp.getEmail());
		Assert.assertEquals("guillaume.peltier", emp.getLogin());
		
	}
	
	@Test
	public void testUpdateEmployeeWithException() throws GesproBusinessException {
		// Given

		// When
		Exception exceptionFileNumber = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("18023");
			emp.setLastname("PELTIER");
			emp.setFirstname("Guillaume");
			emp.setPhoneNumber("0265863289");
			emp.setPassword("myPassword");
			emp.setEmail("guillaume.peltier@gouv.fr");
			emp.setLogin("guillaume.peltier");
			emp = empService.create(emp);
			createEmpId = emp.getId();
			emp.setFileNumber("2001");
			empService.update(emp);
	    });
		String actualMessageFileNumber = exceptionFileNumber.getMessage();
		empService.deleteById(createEmpId);
		
		Exception exceptionEmail = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("18023");
			emp.setLastname("PELTIER");
			emp.setFirstname("Guillaume");
			emp.setPhoneNumber("0265863289");
			emp.setPassword("myPassword");
			emp.setEmail("guillaume.peltier@gouv.fr");
			emp.setLogin("guillaume.peltier");
			emp = empService.create(emp);
			createEmpId = emp.getId();
			emp.setEmail("laurent.fabius@gouv.fr");
			empService.update(emp);
	    });
		String actualMessageEmail = exceptionEmail.getMessage();
		empService.deleteById(createEmpId);
		
		Exception exceptionLogin = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("18023");
			emp.setLastname("PELTIER");
			emp.setFirstname("Guillaume");
			emp.setPhoneNumber("0265863289");
			emp.setPassword("myPassword");
			emp.setEmail("guillaume.peltier@gouv.fr");
			emp.setLogin("guillaume.peltier");
			emp = empService.create(emp);
			createEmpId = emp.getId();
			emp.setLogin("laurent.fabius");
			empService.update(emp);
	    });
		String actualMessageLogin = exceptionLogin.getMessage();
		empService.deleteById(createEmpId);
		
		Exception exceptionEmptyEmail = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("18023");
			emp.setLastname("PELTIER");
			emp.setFirstname("Guillaume");
			emp.setPhoneNumber("0265863289");
			emp.setPassword("myPassword");
			emp.setEmail("guillaume.peltier@gouv.fr");
			emp.setLogin("guillaume.peltier");
			emp = empService.create(emp);
			createEmpId = emp.getId();
			emp.setEmail(null);
			empService.update(emp);
	    });
		String actualMessageEmptyEmail = exceptionEmptyEmail.getMessage();
		empService.deleteById(createEmpId);
		
		Exception exceptionEmptyLogin = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("18023");
			emp.setLastname("PELTIER");
			emp.setFirstname("Guillaume");
			emp.setPhoneNumber("0265863289");
			emp.setPassword("myPassword");
			emp.setEmail("guillaume.peltier@gouv.fr");
			emp.setLogin("guillaume.peltier");
			emp = empService.create(emp);
			createEmpId = emp.getId();
			emp.setLogin("");
			empService.update(emp);
	    });
		String actualMessageEmptyLogin = exceptionEmptyLogin.getMessage();
		empService.deleteById(createEmpId);
		
		Exception exceptionEmptyFileNumber = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("18023");
			emp.setLastname("PELTIER");
			emp.setFirstname("Guillaume");
			emp.setPhoneNumber("0265863289");
			emp.setPassword("myPassword");
			emp.setEmail("guillaume.peltier@gouv.fr");
			emp.setLogin("guillaume.peltier");
			emp = empService.create(emp);
			createEmpId = emp.getId();
			emp.setFileNumber(" ");
			empService.update(emp);
	    });
		String actualMessageEmptyFileNumber = exceptionEmptyFileNumber.getMessage();
		empService.deleteById(createEmpId);
		
		Exception exceptionEmptyFirstname = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("18023");
			emp.setLastname("PELTIER");
			emp.setFirstname("Guillaume");
			emp.setPhoneNumber("0265863289");
			emp.setPassword("myPassword");
			emp.setEmail("guillaume.peltier@gouv.fr");
			emp.setLogin("guillaume.peltier");
			emp = empService.create(emp);
			createEmpId = emp.getId();
			emp.setFirstname(null);
			empService.update(emp);
	    });
		String actualMessageEmptyFirstname = exceptionEmptyFirstname.getMessage();
		empService.deleteById(createEmpId);
		
		Exception exceptionEmptyLastname = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("18023");
			emp.setLastname("PELTIER");
			emp.setFirstname("Guillaume");
			emp.setPhoneNumber("0265863289");
			emp.setPassword("myPassword");
			emp.setEmail("guillaume.peltier@gouv.fr");
			emp.setLogin("guillaume.peltier");
			emp = empService.create(emp);
			createEmpId = emp.getId();
			emp.setLastname(null);
			empService.update(emp);
	    });
		String actualMessageEmptyLastname = exceptionEmptyLastname.getMessage();
		empService.deleteById(createEmpId);

		// Then
		Assert.assertTrue(actualMessageFileNumber.contains("Un employee existe deja avec cet email"));
		Assert.assertTrue(actualMessageLogin.contains("Un employee existe deja avec cet email"));
		Assert.assertTrue(actualMessageEmail.contains("Un employee existe deja avec cet email"));
		Assert.assertTrue(actualMessageEmptyEmail.contains("Email ou login ou matricule ou nom manquant"));
		Assert.assertTrue(actualMessageEmptyLogin.contains("Email ou login ou matricule ou nom manquant"));
		Assert.assertTrue(actualMessageEmptyFileNumber.contains("Email ou login ou matricule ou nom manquant"));
		Assert.assertTrue(actualMessageEmptyFirstname.contains("Email ou login ou matricule ou nom manquant"));
		Assert.assertTrue(actualMessageEmptyLastname.contains("Email ou login ou matricule ou nom manquant"));
	}

	@Test
	public void testFindAllEmployeeWithSuccess() {
		// Given

		// When
		List<GpEmployee> emps = this.empService.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpEmployee emp = this.empService.findById(empId);
		// Then
		Assert.assertNotNull(emp);
	}

	@Test
	public void testDelete() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		this.empService.deleteById(empId);
		GpEmployee emp = this.empService.findById(empId);

		// Then
		Assert.assertNull(emp);
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpEmployee emp = new GpEmployee();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2001");
		emp.setLastname("FABIUS");
		emp.setFirstname("Laurent");
		emp.setPhoneNumber("0125698745");
		emp.setPassword("myThirdPassword");
		emp.setEmail("laurent.fabius@gouv.fr");
		emp.setLogin("laurent.fabius");
		emp = empService.create(emp) ;
		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.empService.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createEmpId)) {
			this.empService.deleteById(this.createEmpId);
		}
	}
}
