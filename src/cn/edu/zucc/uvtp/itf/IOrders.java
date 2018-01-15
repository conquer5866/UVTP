package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.Orders;
import cn.edu.zucc.uvtp.util.BaseException;

public interface IOrders {
	public void add(Orders o) throws BaseException;
	public void del(String oNum) throws BaseException;
	public void modify(Orders o) throws BaseException;
	public void changeState(String onum,int state) throws BaseException;
}
