package af.cmr.indyli.gespro.light.business.entity;

import java.util.List;

public class GpProjectManager extends GpEmployee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final  String GP_PROJECT_MANAGER_TABLE_NAME = "GP_PROJECT_MANAGER";
	private List<GpProject> gpProjects;

	public GpProjectManager() {
		super();
	}
	
	public GpProjectManager(GpEmployee emp) {
		this.setFileNumber(emp.getFileNumber());
		this.setFirstname(emp.getFirstname());
		this.setLastname(emp.getLastname());
		this.setEmail(emp.getEmail());
		this.setPhoneNumber(emp.getPhoneNumber());
		this.setLogin(emp.getLogin());
		this.setPassword(emp.getPassword());
		this.setGpAddresses(emp.getGpAddresses());
	}


	public List<GpProject> getGpProjects() {
		return gpProjects;
	}

	public void setGpProjects(List<GpProject> gpProjects) {
		this.gpProjects = gpProjects;
	}

}