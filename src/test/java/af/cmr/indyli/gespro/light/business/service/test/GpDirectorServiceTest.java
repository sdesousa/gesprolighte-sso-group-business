package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.IGpDirectorService;
import af.cmr.indyli.gespro.light.business.service.impl.GpDirectorServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDirector;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public class GpDirectorServiceTest {

	private IGpDirectorService empService = new GpDirectorServiceImpl();
	private Integer pmIdForAllTest = null;
	private Integer createPmId = null;
	
	@Test
	public void testCreateEmployeeWithSuccess() throws GesproBusinessException {
		//Given
		GpDirector emp = new GpDirector();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1024");
		emp.setLastname("HOLLANDE");
		emp.setFirstname("Francois");
		emp.setPhoneNumber("0365987854");
		emp.setPassword("mySecondPassword");
		emp.setEmail("francois.hollande@gouv.fr");
		emp.setLogin("francois.hollande");
		
		//When
		emp = empService.create(emp) ;
		//On le sauvegarde pour le supprimer après
		this.createPmId = emp.getId();
		
		//Then
		Assert.assertNotNull(emp.getId());
	}
	
	@Test
	public void testFindAllEmployeeWithSuccess() {
		//Given
		
		//When 
		List<GpDirector> emps = this.empService.findAll();
		
		//Then
		Assert.assertTrue(emps.size() >0);
	}
	
	@Test
	public void testFindByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		GpDirector emp = this.empService.findById(empId);
		//Then
		Assert.assertNotNull(emp);
	}
	
	@Test
	public void testDeleteByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		this.empService.deleteById(empId);
		
		
		//Then
		GpDirector emp = this.empService.findById(empId);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpDirector emp = new GpDirector();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1050");
		emp.setLastname("Segolene");
		emp.setFirstname("ROYAL");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("mySecondPassword");
		emp.setEmail("segolene.royal@gouv.fr");
		emp.setLogin("sego.royal");
		emp = empService.create(emp) ;
		this.pmIdForAllTest = emp.getId();
	}
	
	@After
	public void deleteAllEntityAfter() {
		this.empService.deleteById(this.pmIdForAllTest);
		if(!Objects.isNull(this.createPmId)) {
			this.empService.deleteById(this.createPmId);
		}
	}
}
