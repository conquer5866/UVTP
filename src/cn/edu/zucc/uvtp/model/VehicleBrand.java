package cn.edu.zucc.uvtp.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VehicleBrand {
	private String VbNum;
	public static final String[] tableTitles = {"编号","品牌","国家","创立时间","车系数","首字母"};
	private String VbName;
	private String VbCountry;
	private Date VbDate;
	private int VbLineNum;
	private String VbFirstLetter;
	public String getVbNum() {
		return VbNum;
	}
	public void setVbNum(String vbNum) {
		VbNum = vbNum;
	}
	public String getVbName() {
		return VbName;
	}
	public void setVbName(String vbName) {
		VbName = vbName;
	}
	public String getVbCountry() {
		return VbCountry;
	}
	public void setVbCountry(String vbCountry) {
		VbCountry = vbCountry;
	}
	public Date getVbDate() {
		return VbDate;
	}
	public void setVbDate(Date vbDate) {
		this.VbDate = vbDate;
	}
	public int getVbLineNum() {
		return VbLineNum;
	}
	public void setVbLineNum(int vbLineNum) {
		VbLineNum = vbLineNum;
	}
	public String getVbFirstLetter() {
		return VbFirstLetter;
	}
	public void setVbFirstLetter(String vbFirstLetter) {
		VbFirstLetter = vbFirstLetter;
	}
	public VehicleBrand(
		String VbNum,
		String VbName,
		String VbCountry,
		Date VbDate,
		int VbLineNum,
		String VbFirstLetter
		)
	{
		this.VbNum = VbNum ;
		this.VbName = VbName ;
		this.VbCountry = VbCountry ;
		this.VbDate = VbDate ;
		this.VbLineNum = VbLineNum ;
		this.VbFirstLetter = VbFirstLetter ;
	}
	public VehicleBrand(){}
	public String getCell(int col){
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		if(col == 0) return VbNum;
		else if(col == 1) return VbName;
		else if(col == 2) return VbCountry;
		else if(col == 3) return f.format(VbDate);
		else if(col == 4) return ""+VbLineNum;
		else if(col == 5) return VbFirstLetter;
		else return "";
	}
}
