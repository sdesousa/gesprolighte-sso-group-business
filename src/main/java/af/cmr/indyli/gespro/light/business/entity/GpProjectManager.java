package af.cmr.indyli.gespro.light.business.entity;

import java.util.List;

public class GpProjectManager extends GpEmployee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GpProject> gpProjects;

	public GpProjectManager() {
		super();
	}


	public List<GpProject> getGpProjects() {
		return gpProjects;
	}

	public void setGpProjects(List<GpProject> gpProjects) {
		this.gpProjects = gpProjects;
	}

}