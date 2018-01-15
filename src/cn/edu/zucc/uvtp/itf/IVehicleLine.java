package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.VehicleLine;
import cn.edu.zucc.uvtp.util.BaseException;

public interface IVehicleLine {
	public void add(VehicleLine vl) throws BaseException;
	public void modify(VehicleLine vl) throws BaseException;
	public void del(String VlNum) throws BaseException;
}
