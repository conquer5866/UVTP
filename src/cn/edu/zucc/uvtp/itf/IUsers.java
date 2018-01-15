package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.util.BaseException;

public interface IUsers {
	public Users create(String uname,String pwd1,String pwd2,String call) throws BaseException;
	public void modify(Users u) throws BaseException;
	public void modifyPwd(String oPwd,String nPwd1,String nPwd2) throws BaseException;
	public void del(String userId) throws BaseException;
	public void lock(String userId) throws BaseException;
	public void ulock(String userId) throws BaseException;
	public void login(String uname , String upwd) throws BaseException;
}
