package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.Messages;
import cn.edu.zucc.uvtp.util.BaseException;

public interface IMessages {
	public void add(Messages m) throws BaseException;
	public void changeState(String mnum,int state) throws BaseException;
}
