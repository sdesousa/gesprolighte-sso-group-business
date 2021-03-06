package af.cmr.indyli.gespro.light.business.service.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;


public class GpDataCreationServiceTest {

	private GpOrganization orgDefault = new GpOrganization();
	private GpAddress adrDefault = new GpAddress();
	private GpProjectManager pmDefault = new GpProjectManager();
	private GpProject prjDefault = new GpProject();
	private GpPhase phsDefault = new GpPhase();
	private GpDeliverable delDefault = new GpDeliverable();
	private GpBill bllDefault = new GpBill();
	private GpEmpReaPhase erpDefault = new GpEmpReaPhase();
	private GpOrganization orgCreate = new GpOrganization();
	private GpAddress adrCreate = new GpAddress();
	private GpProjectManager pmCreate = new GpProjectManager();
	private GpProject prjCreate = new GpProject();
	private GpPhase phsCreate = new GpPhase();
	private GpDeliverable delCreate = new GpDeliverable();
	private GpBill bllCreate = new GpBill();
	private GpEmpReaPhase erpCreate = new GpEmpReaPhase();
	
	protected GpOrganization getOrgDefault() {
		orgDefault.setOrgCode("E498");
		orgDefault.setName("Pole emploi");
		orgDefault.setPhoneNumber("3949");
		orgDefault.setAdrWeb("www.pole-emploi.fr");
		orgDefault.setContactName("Julien");
		orgDefault.setContactEmail("julien@pole-emploi.fr");
		return orgDefault;
	}
	protected GpAddress getAdrDefault() {
		adrDefault.setStreetNumber(11);
		adrDefault.setStreetLabel("rue abc");
		adrDefault.setZipCode(12000);
		adrDefault.setCountry("France");
		adrDefault.setIsMain((byte) 0);
		adrDefault.setGpOrganization(this.getOrgDefault());
		return adrDefault;
	}
	protected GpProjectManager getPmDefault() {
		pmDefault.setFileNumber("1050");
		pmDefault.setLastname("Segolene");
		pmDefault.setFirstname("ROYAL");
		pmDefault.setPhoneNumber("0256897856");
		pmDefault.setPassword("mySecondPassword");
		pmDefault.setEmail("segolene.royal@gouv.fr");
		pmDefault.setLogin("sego.royal");
		return pmDefault;
	}
	protected GpProject getPrjDefault() {
		prjDefault.setProjectCode("18023");
		prjDefault.setName("Service Cloud Amazon");
		prjDefault.setDescription("Mise en place Cloud Test");
		prjDefault.setStartDate(new GregorianCalendar(2022, Calendar.JANUARY, 01).getTime());
		prjDefault.setEndDate(new GregorianCalendar(2022, Calendar.JANUARY, 31).getTime());
		prjDefault.setAmount(9000);
		prjDefault.setCreationDate(new Date());
		Date MajDate = new Date();
		prjDefault.setUpdateDate(MajDate);
		prjDefault.setGpOrganization(this.getOrgDefault()); 
		prjDefault.setGpChefProjet(this.getPmDefault());
		return prjDefault;
	}
	protected GpPhase getPhsDefault() {
		phsDefault.setPhaseCode("A1");
		phsDefault.setDescription("premiere phase");
		phsDefault.setStartDate(new GregorianCalendar(2022, Calendar.JANUARY, 11).getTime());
		phsDefault.setEndDate(new GregorianCalendar(2022, Calendar.JANUARY, 21).getTime());
		phsDefault.setAmount(145.50);
		phsDefault.setStatus((byte) 1);
		phsDefault.setIsEnded((byte) 0);
		phsDefault.setGpProject(this.getPrjDefault());
		return phsDefault;
	}
	
	protected GpDeliverable getDelDefault( ) {
		delDefault.setDelCode("DE123");
		delDefault.setLabel("Livraision 123");
		delDefault.setDescription("Description 123");
		delDefault.setDelPath("lien123");
		delDefault.setGpPhase(this.getPhsDefault());
		return delDefault;
		
	}
	
	protected GpBill getBllDefault() {
		bllDefault.setBillCode("BP12");
		bllDefault.setBillStatus("PAID");
		bllDefault.setAmount(600.50);
		bllDefault.setGpPhase(this.getPhsDefault());
		return bllDefault;
	}
	
	protected GpEmpReaPhase getErpDefault() {
		erpDefault.setGpEmployee(this.getPmDefault());
		erpDefault.setGpPhase(this.getPhsDefault());
		return erpDefault;
	}
	
	protected GpOrganization getOrgCreate() {
		orgCreate.setOrgCode("CODE-67");
		orgCreate.setName("INDYLI-SERVICES");
		orgCreate.setAdrWeb("indyli-services.com");
		orgCreate.setContactEmail("contact@indyli-services.com");
		orgCreate.setContactName("CName");
		orgCreate.setPhoneNumber("7895");
		return orgCreate;
	}
	protected GpAddress getAdrCreate() {
		adrCreate.setStreetNumber(41);
		adrCreate.setStreetLabel("rue azerty");
		adrCreate.setZipCode(25041);
		adrCreate.setCountry("France");
		adrCreate.setIsMain((byte) 1);
		adrCreate.setGpOrganization(this.getOrgCreate());
		return adrCreate;
	}
	protected GpProjectManager getPmCreate() {
		pmCreate.setFileNumber("4052");
		pmCreate.setLastname("Gabriel");
		pmCreate.setFirstname("ATTAL");
		pmCreate.setPhoneNumber("0236589745");
		pmCreate.setPassword("myPass");
		pmCreate.setEmail("gabriel.attal@gouv.fr");
		pmCreate.setLogin("gab.attal");
		return pmCreate;
	}
	protected GpProject getPrjCreate() {
		prjCreate.setProjectCode("4572");
		prjCreate.setName("Service Cloud Amazon");
		prjCreate.setDescription("Demande d'une entit d'Amazon d'amliorer son service cloud");
		prjCreate.setStartDate(new GregorianCalendar(2022, Calendar.JANUARY, 01).getTime());
		prjCreate.setEndDate(new GregorianCalendar(2022, Calendar.JANUARY, 31).getTime());
		prjCreate.setAmount(9000); 
		Date CreDate = new Date(); 
		prjCreate.setCreationDate(CreDate);
		Date MajDate = new Date(); 
		prjCreate.setUpdateDate(MajDate); 
		prjCreate.setGpOrganization(this.getOrgCreate());
		prjCreate.setGpChefProjet(this.getPmCreate());
		return prjCreate;
	}
	protected GpPhase getPhsCreate() {
		phsCreate.setPhaseCode("B2");
		phsCreate.setDescription("seconde phase");
		phsCreate.setStartDate(new GregorianCalendar(2022, Calendar.JANUARY, 11).getTime());
		phsCreate.setEndDate(new GregorianCalendar(2022, Calendar.JANUARY, 21).getTime());
		phsCreate.setAmount(145.50);
		phsCreate.setStatus((byte) 1);
		phsCreate.setIsEnded((byte) 0);
		phsCreate.setGpProject(this.getPrjCreate());
		return phsCreate;
	}
	protected GpDeliverable getDelCreate() {
		delCreate.setDelCode("DE007");
		delCreate.setLabel("Livraision 007");
		delCreate.setDescription("Description 007");
		delCreate.setDelPath("lien007");
		delCreate.setGpPhase(this.getPhsCreate());
		return delCreate;
	}
	protected GpBill getBllCreate() {
		bllCreate.setBillCode("BP33");
		bllCreate.setBillStatus("TRANSMITTED");
		bllCreate.setAmount(99.99);
		bllCreate.setGpPhase(this.getPhsCreate());
		return bllCreate;
	}
	
	protected GpEmpReaPhase getErpCreate() {
		erpCreate.setGpEmployee(this.getPmCreate());
		erpCreate.setGpPhase(this.getPhsCreate());
		return erpCreate;
	}

}
