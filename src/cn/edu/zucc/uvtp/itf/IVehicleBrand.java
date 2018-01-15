package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.VehicleBrand;
import cn.edu.zucc.uvtp.util.BaseException;

public interface IVehicleBrand {
	public void add(VehicleBrand vb) throws BaseException;
	public void modify(VehicleBrand vb) throws BaseException;
	public void del(String vbNum) throws BaseException;
}
