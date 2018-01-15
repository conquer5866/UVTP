package cn.edu.zucc.uvtp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Users {
	public static Users currentu = new Users();
	public static final String[] tableTitles = {"编号","用户名","真实姓名","地址","手机号","邮箱","QQ","注册时间","状态"};
	private String UNum;
	private String UName;
	private String UPwd;
	private String URName;
	private String UAdress;
	private String UMobileNum;
	private String UEmail;
	private String UQq;
	private Date UDate;
	private int UState;
	public String getUNum() {
		return UNum;
	}
	public void setUNum(String uNum) {
		UNum = uNum;
	}
	public String getUName() {
		return UName;
	}
	public void setUName(String uName) {
		UName = uName;
	}
	public String getUPwd() {
		return UPwd;
	}
	public void setUPwd(String uPwd) {
		UPwd = uPwd;
	}
	public String getURName() {
		return URName;
	}
	public void setURName(String uRName) {
		URName = uRName;
	}
	public String getUAdress() {
		return UAdress;
	}
	public void setUAdress(String uAdress) {
		UAdress = uAdress;
	}
	public String getUMobileNum() {
		return UMobileNum;
	}
	public void setUMobileNum(String uMobileNum) {
		UMobileNum = uMobileNum;
	}
	public String getUEmail() {
		return UEmail;
	}
	public void setUEmail(String uEmail) {
		UEmail = uEmail;
	}
	public String getUQq() {
		return UQq;
	}
	public void setUQq(String uQq) {
		UQq = uQq;
	}
	public Date getUDate() {
		return UDate;
	}
	public void setUDate(Date uDate) {
		UDate = uDate;
	}
	public int getUState() {
		return UState;
	}
	public void setUState(int uState) {
		UState = uState;
	}
	public Users(){}
	/*
	public Users(
		String UName,
		String UPwd
		)
	{
		this.UName = UName ;
		this.UPwd = UPwd ;
	}
	*/
	public Users(
		String UNum,
		String UName,
		String UPwd,
		String URName,
		String UAdress,
		String UMobileNum,
		String UEmail,
		String UQq,
		Date UDate,
		int UState	
		)
	{
		this.UNum = UNum ;
		this.UName = UName ;
		this.UPwd = UPwd ;
		this.URName = URName ;
		this.UAdress = UAdress ;
		this.UMobileNum = UMobileNum ;
		this.UEmail = UEmail ;
		this.UQq = UQq ;
		this.UDate = UDate ;
		this.UState = UState ;
	}
	
	public String getCell(int col){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(col == 0){
			return this.UNum;
		}
		else if(col == 1){
			return this.UName;
		}
		else if(col == 2){
			return ""+this.URName;
		}
		else if(col == 3){
			return ""+this.UAdress;
		}
		else if(col == 4){
			return this.UMobileNum;
		}
		else if(col == 5){
			return ""+this.UEmail;
		}
		else if(col == 6){
			return ""+this.UQq;
		}
		else if(col == 7){
			return f.format(this.UDate);
		}
		else if(col == 8){
			if(this.UState == 0){
				return "正常";
			}
			else if(this.UState == 1){
				return "锁定";
			}
			else return "";
		}
		else return "";
	}
}
