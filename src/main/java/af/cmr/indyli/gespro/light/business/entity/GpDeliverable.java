package af.cmr.indyli.gespro.light.business.entity;

import java.util.Date;

public class GpDeliverable implements IEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date creationDate;

	private String delCode;

	private String delPath;

	private String description;

	private String label;

	private GpPhase gpPhase;

	public GpDeliverable() {
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

	public String getDelCode() {
		return this.delCode;
	}

	public void setDelCode(String delCode) {
		this.delCode = delCode;
	}

	public String getDelPath() {
		return this.delPath;
	}

	public void setDelPath(String delPath) {
		this.delPath = delPath;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public GpPhase getGpPhase() {
		return this.gpPhase;
	}

	public void setGpPhase(GpPhase gpPhase) {
		this.gpPhase = gpPhase;
	}

}