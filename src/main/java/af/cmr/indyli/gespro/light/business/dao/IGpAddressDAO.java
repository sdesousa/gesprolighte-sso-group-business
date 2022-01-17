package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpAddress;

public interface IGpAddressDAO {
	public GpAddress create(GpAddress address);

	public void update(GpAddress address);

	public List<GpAddress> findAll();

	public void deleteById(Integer addressId);

	public GpAddress findById(Integer addressId);

}
