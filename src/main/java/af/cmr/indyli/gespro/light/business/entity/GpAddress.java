package af.cmr.indyli.gespro.light.business.entity;


public class GpAddress implements IEntity {
	private static final long serialVersionUID = 1L;

	private int id;

	private String country;

	private byte isMain;

	private String streetLabel;

	private int streetNumber;

	private int zipCode;

	private GpEmployee gpEmployee;

	private GpOrganization gpOrganization;

	public GpAddress() {
	}



	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public byte getIsMain() {
		return this.isMain;
	}

	public void setIsMain(byte isMain) {
		this.isMain = isMain;
	}

	public String getStreetLabel() {
		return this.streetLabel;
	}

	public void setStreetLabel(String streetLabel) {
		this.streetLabel = streetLabel;
	}

	public int getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public GpEmployee getGpEmployee() {
		return this.gpEmployee;
	}

	public void setGpEmployee(GpEmployee gpEmployee) {
		this.gpEmployee = gpEmployee;
	}

	public GpOrganization getGpOrganization() {
		return this.gpOrganization;
	}

	public void setGpOrganization(GpOrganization gpOrganization) {
		this.gpOrganization = gpOrganization;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

}