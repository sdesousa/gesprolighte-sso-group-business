package af.cmr.indyli.gespro.light.business.entity;

public class GpBill implements IEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private double amount;

	private String billCode;

	private String billStatus;

	private GpPhase gpPhase;

	public GpBill() {
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBillCode() {
		return this.billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getBillStatus() {
		return this.billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public GpPhase getGpPhase() {
		return this.gpPhase;
	}

	public void setGpPhase(GpPhase gpPhase) {
		this.gpPhase = gpPhase;
	}

}