package cn.edu.zucc.uvtp.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemUsers {
	public static SystemUsers currentsu = new SystemUsers();
	public static final String[] tableTitles = {"编号","用户名","密码","创建时间"};
	private String SuNum;
	private String SuName;
	private String SuPwd;
	private Date SuDate;
	public String getSuNum() {
		return SuNum;
	}
	public void setSuNum(String suNum) {
		SuNum = suNum;
	}
	public String getSuName() {
		return SuName;
	}
	public void setSuName(String suName) {
		SuName = suName;
	}
	public String getSuPwd() {
		return SuPwd;
	}
	public void setSuPwd(String suPwd) {
		SuPwd = suPwd;
	}
	public Date getSuDate() {
		return SuDate;
	}
	public void setSuDate(Date suDate) {
		SuDate = suDate;
	}
	public String getCell(int col){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(col == 0){
			return this.SuNum;
		}
		else if(col == 1){
			return this.SuName;
		}
		else if(col == 2){
			return this.SuPwd;
		}
		else if(col == 3){
			return f.format(this.SuDate);
		}
		else{
			return "";
		}
	}
}
