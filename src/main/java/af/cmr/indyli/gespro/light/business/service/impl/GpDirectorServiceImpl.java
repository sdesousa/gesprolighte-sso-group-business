package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDirectorDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDirectorDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDirector;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpDirectorService;

public class GpDirectorServiceImpl implements IGpDirectorService{

	private IGpDirectorDAO empDAO = new GpDirectorDAOImpl();
	
	public GpDirector create(GpDirector emp) throws GesproBusinessException{
		if (this.empDAO.ifEmpExistByFileNumberOrEmail(emp.getFileNumber(), emp.getEmail(), emp.getLogin())) {
			throw new GesproBusinessException(String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",emp.getEmail(),emp.getLogin(),emp.getFileNumber()));
		}
		if (this.empDAO.ifEmpFieldEmpty(emp.getEmail(), emp.getLogin(), emp.getFileNumber(), emp.getFirstname(), emp.getLastname())) {
			throw new GesproBusinessException("Email ou login ou matricule ou nom manquant");
		}
		return this.empDAO.create(emp);
	}

	public void update(GpDirector emp) throws GesproBusinessException{
		if (this.empDAO.ifEmpExistByFileNumberOrEmailUpdate(emp.getFileNumber(), emp.getEmail(), emp.getLogin(), emp.getId())) {
			throw new GesproBusinessException(String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",emp.getEmail(),emp.getLogin(),emp.getFileNumber()));
		}
		if (this.empDAO.ifEmpFieldEmpty(emp.getEmail(), emp.getLogin(), emp.getFileNumber(), emp.getFirstname(), emp.getLastname())) {
			throw new GesproBusinessException("Email ou login ou matricule ou nom manquant");
		}
		this.empDAO.update(emp);
	}
	
	public List<GpDirector> findAll() {
		return this.empDAO.findAll();
	}

	public GpDirector findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

	public void deleteById(Integer empId) {
		this.empDAO.deleteById(empId);
	}

}
