package cn.edu.zucc.uvtp.itf;

import cn.edu.zucc.uvtp.model.Transfer;
import cn.edu.zucc.uvtp.util.BaseException;

public interface ITransfer {
	public void add(Transfer t) throws BaseException;
}
