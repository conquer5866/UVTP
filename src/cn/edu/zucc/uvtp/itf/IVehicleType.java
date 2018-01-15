package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.VehicleType;
import cn.edu.zucc.uvtp.util.BaseException;

public interface IVehicleType {
	public void add(VehicleType vt) throws BaseException;
	public void modify(VehicleType vt) throws BaseException;
	public void del(String VtNum) throws BaseException;
}
