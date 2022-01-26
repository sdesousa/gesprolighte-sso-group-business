package af.cmr.indyli.gespro.light.business.entity;

import java.util.Date;

public class GpEmpReaPhase implements IEntity {
	private static final long serialVersionUID = 1L;
	public static final  String GP_EMP_REA_PHASE_TABLE_NAME = "GP_EMP_REA_PHASE";

	private Integer id;

	private Date creationDate;

	private GpEmployee gpEmployee;

	private GpPhase gpPhase;

	public GpEmpReaPhase() {
	}



	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public GpEmployee getGpEmployee() {
		return this.gpEmployee;
	}

	public void setGpEmployee(GpEmployee gpEmployee) {
		this.gpEmployee = gpEmployee;
	}

	public GpPhase getGpPhase() {
		return this.gpPhase;
	}

	public void setGpPhase(GpPhase gpPhase) {
		this.gpPhase = gpPhase;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}
}