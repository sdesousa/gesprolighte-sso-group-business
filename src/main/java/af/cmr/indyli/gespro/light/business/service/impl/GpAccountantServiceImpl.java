package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAccountantDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAccountantDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpAccountantService;

public class GpAccountantServiceImpl implements IGpAccountantService{

	private IGpAccountantDAO empDAO = new GpAccountantDAOImpl();
	
	public GpAccountant create(GpAccountant emp) throws GesproBusinessException{
		if (this.empDAO.ifEmpExistByFileNumberOrEmail(emp.getFileNumber(), emp.getEmail(), emp.getLogin())) {
			throw new GesproBusinessException(String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",emp.getEmail(),emp.getLogin(),emp.getFileNumber()));
		}
		return this.empDAO.create(emp);
	}

	public void update(GpAccountant emp) throws GesproBusinessException{
		
	}
	
	public List<GpAccountant> findAll() {
		return this.empDAO.findAll();
	}

	public GpAccountant findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

	public void deleteById(Integer empId) {
		this.empDAO.deleteById(empId);
	}

}
