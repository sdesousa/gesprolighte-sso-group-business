package af.cmr.indyli.gespro.light.business.entity;

public class GpTechnician extends GpEmployee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final  String GP_TECHNICIAN_TABLE_NAME = "GP_TECHNICIAN";

	private int graduationYear;

	private String lastDiploma;

	public GpTechnician() {
		super();
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getLastDiploma() {
		return lastDiploma;
	}

	public void setLastDiploma(String lastDiploma) {
		this.lastDiploma = lastDiploma;
	}
}