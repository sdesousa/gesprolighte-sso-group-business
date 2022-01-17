package af.cmr.indyli.gespro.light.business.entity;

import java.util.Date;
import java.util.List;

public class GpEmployee implements IEntity {
	private static final long serialVersionUID = 1L;

	public static final  String GP_EMPLOYEE_TABLE_NAME = "GP_EMPLOYEE";
	
	private Integer id;

	private Date creationDate;

	private String email;

	private String fileNumber;

	private String firstname;

	private String lastname;

	private String login;

	private String password;

	private String phoneNumber;

	private Date updateDate;

	private List<GpAddress> gpAddresses;

	public GpEmployee() {
	}


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFileNumber() {
		return this.fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<GpAddress> getGpAddresses() {
		return this.gpAddresses;
	}

	public void setGpAddresses(List<GpAddress> gpAddresses) {
		this.gpAddresses = gpAddresses;
	}

	public GpAddress addGpAddress(GpAddress gpAddress) {
		getGpAddresses().add(gpAddress);
		gpAddress.setGpEmployee(this);

		return gpAddress;
	}

	public GpAddress removeGpAddress(GpAddress gpAddress) {
		getGpAddresses().remove(gpAddress);
		gpAddress.setGpEmployee(null);

		return gpAddress;
	}

}