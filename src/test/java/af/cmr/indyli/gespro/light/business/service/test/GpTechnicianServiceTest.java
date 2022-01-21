package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.service.IGpTechnicianService;
import af.cmr.indyli.gespro.light.business.service.impl.GpTechnicianServiceImpl;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public class GpTechnicianServiceTest {

	private IGpTechnicianService empService = new GpTechnicianServiceImpl();
	private Integer pmIdForAllTest = null;
	private Integer createPmId = null;
	
	@Test
	public void testCreateEmployeeWithSuccess() throws GesproBusinessException {
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
		emp = empService.create(emp) ;
		//On le sauvegarde pour le supprimer après
		this.createPmId = emp.getId();
		
		//Then
		Assert.assertNotNull(emp.getId());
	}
	
	@Test
	public void testUpdateEmployeeWithSuccess() throws GesproBusinessException {
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
		emp = empService.create(emp);
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
		empService.update(emp);
		
		//Then
		emp = empService.findById(createPmId);
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
		List<GpTechnician> emps = this.empService.findAll();
		
		//Then
		Assert.assertTrue(emps.size() >0);
	}
	
	@Test
	public void testFindByIdWithSuccess() {
		//Given
		Integer empId = this.pmIdForAllTest;
		
		//When 
		GpTechnician emp = this.empService.findById(empId);
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
		GpTechnician emp = this.empService.findById(empId);
		Assert.assertNull(emp);
	}
	
	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
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
