package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectManagerService;

public class GpProjectManagerServiceImpl implements IGpProjectManagerService{

	private IGpProjectManagerDAO empDAO = new GpProjectManagerDAOImpl();
	
	public GpProjectManager create(GpProjectManager emp) throws GesproBusinessException{
		if (this.empDAO.ifEmpExistByFileNumberOrEmail(emp.getFileNumber(), emp.getEmail(), emp.getLogin())) {
			throw new GesproBusinessException(String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",emp.getEmail(),emp.getLogin(),emp.getFileNumber()));
		}
		if (this.empDAO.ifEmpFieldEmpty(emp.getEmail(), emp.getLogin(), emp.getFileNumber(), emp.getFirstname(), emp.getLastname())) {
			throw new GesproBusinessException("Email ou login ou matricule ou nom manquant");
		}
		return this.empDAO.create(emp);
	}

	public void update(GpProjectManager emp) throws GesproBusinessException{
		if (this.empDAO.ifEmpExistByFileNumberOrEmailUpdate(emp.getFileNumber(), emp.getEmail(), emp.getLogin(), emp.getId())) {
			throw new GesproBusinessException(String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",emp.getEmail(),emp.getLogin(),emp.getFileNumber()));
		}
		if (this.empDAO.ifEmpFieldEmpty(emp.getEmail(), emp.getLogin(), emp.getFileNumber(), emp.getFirstname(), emp.getLastname())) {
			throw new GesproBusinessException("Email ou login ou matricule ou nom manquant");
		}
		this.empDAO.update(emp);
	}
	
	public List<GpProjectManager> findAll() {
		return this.empDAO.findAll();
	}

	public GpProjectManager findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

	public void deleteById(Integer empId) {
		this.empDAO.deleteById(empId);
	}
	
	public boolean isEmployeeProjectManager(Integer empId) {
		return this.empDAO.findById(empId) != null;
	}
	
	public GpProjectManager promoteToProjectManager(GpEmployee emp) {
		return this.empDAO.promoteToProjectManager(emp);
	}

}
