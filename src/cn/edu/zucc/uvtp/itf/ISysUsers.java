package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.SystemUsers;
import cn.edu.zucc.uvtp.util.BaseException;

public interface ISysUsers {
	public void create(String suname,String pwd,String pwd2) throws BaseException;
	public void modifyPwd(String suname,String oPwd,String nPwd1,String nPwd2) throws BaseException;
	public void del(String SuserNum) throws BaseException;
	public void login(String suname , String supwd) throws BaseException;
}
