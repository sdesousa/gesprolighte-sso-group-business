package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpEmployeeDAO<Entity extends IEntity> {
	public Entity create(Entity emp);
	public void update(Entity emp);
	public List<Entity> findAll();
	public void deleteById(Integer empId);
	public Entity findById(Integer empId);
	public boolean ifEmpExistByFileNumberOrEmail(String fileNumber,String email,String login);
	public boolean ifEmpExistByFileNumberOrEmailUpdate(String fileNumber, String email,String login, int empId);
	public boolean ifFieldEmpty(String field);
	public boolean ifEmpFieldEmpty(String email, String login, String fileNumber, String firstname, String lastname);
}
