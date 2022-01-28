package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpTechnicianDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpTechnicianDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpTechnicianService;

public class GpTechnicianServiceImpl implements IGpTechnicianService{

	private IGpTechnicianDAO empDAO = new GpTechnicianDAOImpl();
	
	public GpTechnician create(GpTechnician emp) throws GesproBusinessException{
		if (this.empDAO.ifEmpExistByFileNumberOrEmail(emp.getFileNumber(), emp.getEmail(), emp.getLogin())) {
			throw new GesproBusinessException(String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",emp.getEmail(),emp.getLogin(),emp.getFileNumber()));
		}
		if (this.empDAO.ifEmpFieldEmpty(emp.getEmail(), emp.getLogin(), emp.getFileNumber(), emp.getFirstname(), emp.getLastname())) {
			throw new GesproBusinessException("Email ou login ou matricule ou nom manquant");
		}
		return this.empDAO.create(emp);
	}

	public void update(GpTechnician emp) throws GesproBusinessException{
		if (this.empDAO.ifEmpExistByFileNumberOrEmailUpdate(emp.getFileNumber(), emp.getEmail(), emp.getLogin(), emp.getId())) {
			throw new GesproBusinessException(String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",emp.getEmail(),emp.getLogin(),emp.getFileNumber()));
		}
		if (this.empDAO.ifEmpFieldEmpty(emp.getEmail(), emp.getLogin(), emp.getFileNumber(), emp.getFirstname(), emp.getLastname())) {
			throw new GesproBusinessException("Email ou login ou matricule ou nom manquant");
		}
		this.empDAO.update(emp);
		
	}
	
	public List<GpTechnician> findAll() {
		return this.empDAO.findAll();
	}

	public GpTechnician findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

	public void deleteById(Integer empId) {
		this.empDAO.deleteById(empId);
	}

}
