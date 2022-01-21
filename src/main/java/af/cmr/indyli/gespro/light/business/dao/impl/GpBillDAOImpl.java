package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpBillDAO;
import af.cmr.indyli.gespro.light.business.entity.GpBill;

public class GpBillDAOImpl implements IGpBillDAO<GpBill>{
	
	private GpEntityManager entityManager = new GpEntityManager();
	private GpPhaseDAOImpl phsImpl = new GpPhaseDAOImpl();
	
	@Override
	public GpBill create(GpBill bll) {
		try {
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			String REQ_SQL = "INSERT INTO GP_BILL ( BILL_CODE, AMOUNT, BILL_STATUS, PHASE_ID) VALUES (?,?,?,?)";
	    	Object[] tabParam = {bll.getBillCode(),bll.getAmount(),bll.getBillStatus(),bll.getGpPhase().getId()};
	    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	    	String REQ_SQL_MAX_ID = "SELECT max(BILL_ID) AS MaxId FROM GP_BILL";
	    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL_MAX_ID);
	    	if (resultat != null) {
	    		while (resultat.next()) {
	    			bll.setId(resultat.getInt("MaxId"));
	    		}
	    	}
	    	this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bll;
	}

	@Override
	public void update(GpBill bll) {
		String REQ_SQL = "UPDATE GP_BILL SET BILL_CODE=?,AMOUNT=?,BILL_STATUS=?,PHASE_ID=? WHERE BILL_ID = ?";
    	Object[] tabParam = {bll.getBillCode(),bll.getAmount(),bll.getBillStatus(),bll.getGpPhase().getId(),bll.getId()};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpBill> findAll() {
		String REQ_SQL = "SELECT * FROM GP_BILL";
    	ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
    	List<GpBill> bllList = new ArrayList<GpBill>();
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					Integer bllId = resultat.getInt("BILL_ID");
					String billCode = resultat.getString("BILL_CODE");
					double amount = resultat.getDouble("AMOUNT");
					String billStatus = resultat.getString("BILL_STATUS");
					int phsId = resultat.getInt("PHASE_ID");
					GpBill foundBll = new GpBill();
					foundBll.setId(bllId);
					foundBll.setBillCode(billCode);
					foundBll.setAmount(amount);
					foundBll.setBillStatus(billStatus);
					foundBll.setGpPhase(phsImpl.findById(phsId));
					bllList.add(foundBll);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return bllList;
	}

	@Override
	public GpBill findById(Integer bllId) {
		String REQ_SQL = "SELECT * FROM GP_BILL where BILL_ID = ?";
		Object[] tabParam = {bllId};
    	ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
    	GpBill foundBll = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					String billCode = resultat.getString("BILL_CODE");
					double amount = resultat.getDouble("AMOUNT");
					String billStatus = resultat.getString("BILL_STATUS");
					int phsId = resultat.getInt("PHASE_ID");
					foundBll = new GpBill();
					foundBll.setId(bllId);
					foundBll.setBillCode(billCode);
					foundBll.setAmount(amount);
					foundBll.setBillStatus(billStatus);
					foundBll.setGpPhase(phsImpl.findById(phsId));
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundBll;
	}
	
	public void deleteById(Integer bllId) {
		String REQ_SQL = "DELETE FROM "+this.getCurrentTableName()+" WHERE BILL_ID = ?";
    	Object[] tabParam = {bllId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	
	}
	
	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public String getCurrentTableName() {
		return GpBill.GP_BILL_TABLE_NAME;
	}
}
