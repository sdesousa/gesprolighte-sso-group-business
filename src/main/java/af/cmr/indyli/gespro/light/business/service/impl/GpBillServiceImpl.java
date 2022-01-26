package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpBillDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpBillDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.service.IGpBillService;

public class GpBillServiceImpl implements IGpBillService<GpBill>{

	private IGpBillDAO<GpBill> bllDAO = new GpBillDAOImpl();
	
	public GpBill create(GpBill bll){
		return this.bllDAO.create(bll);
	}

	public void update(GpBill bll){
		this.bllDAO.update(bll);
	}

	public List<GpBill> findAll() {
		return this.bllDAO.findAll();
	}

	public void deleteById(Integer bllId) {
		this.bllDAO.deleteById(bllId);
	}

	public GpBill findById(Integer bllId) {
		return this.bllDAO.findById(bllId);
	}

}
