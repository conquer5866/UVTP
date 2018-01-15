package cn.edu.zucc.uvtp.model;

import cn.edu.zucc.uvtp.control.UsersManager;
import cn.edu.zucc.uvtp.util.BaseException;

public class Messages {
	public static final String[] tableTitles = {"发信人","目标","内容"};
	private String MNum;
	private String MSender;
	private String MReceiver;
	private String MONum;
	private int Message;
	private int MState;
	
	public String getMNum() {
		return MNum;
	}
	public void setMNum(String mNum) {
		MNum = mNum;
	}
	public String getMSender() {
		return MSender;
	}
	public void setMSender(String mSender) {
		MSender = mSender;
	}
	public String getMReceiver() {
		return MReceiver;
	}
	public void setMReceiver(String mReceiver) {
		MReceiver = mReceiver;
	}
	public String getMONum() {
		return MONum;
	}
	public void setMONum(String mONum) {
		MONum = mONum;
	}
	public int getMessage() {
		return Message;
	}
	public void setMessage(int message) {
		Message = message;
	}
	public int getMState() {
		return MState;
	}
	public void setMState(int mState) {
		MState = mState;
	}
	public String getCell(int col){
		if(col == 0){
			String uname = "";
			try {
				uname = (new UsersManager()).getUser(this.MSender).getUName();
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uname;
		}
		else if(col == 1){
			return this.MONum;
		}
		else if(col == 2){
			if(this.Message == 1){
				return "预约";
			}
			else if(this.Message == 2){
				return "过户";
			}
			else if(this.Message == 3){
				return "退车";
			}
			else{
				return "通知";
			}
		}
		else return "";
	}
}
