package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.Vehicles;
import cn.edu.zucc.uvtp.util.BaseException;

public interface IVehicles {
	public void add(Vehicles vc) throws BaseException;
	public void modify(Vehicles vc) throws BaseException;
	public void del(String vcNum) throws BaseException;
}
