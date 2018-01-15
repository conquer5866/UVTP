package cn.edu.zucc.uvtp.model;

import cn.edu.zucc.uvtp.control.VehicleBrandManager;
import cn.edu.zucc.uvtp.util.BaseException;

public class VehicleLine {
	public static final String[] tableTitles = {"编号","所属品牌","车系","生产方式","首字母"};
	private String VlNum;
	private String VlBNum;
	private String VlName;
	private String VlPoductionMode;
	private String VlFirstLetter;
	public String getVlNum() {
		return VlNum;
	}
	public void setVlNum(String vlNum) {
		VlNum = vlNum;
	}
	public String getVlBNum() {
		return VlBNum;
	}
	public void setVlBNum(String VlBNum) {
		this.VlBNum = VlBNum;
	}
	public String getVlName() {
		return VlName;
	}
	public void setVlName(String vlName) {
		VlName = vlName;
	}
	public String getVlPoductionMode() {
		return VlPoductionMode;
	}
	public void setVlPoductionMode(String vlPoductionMode) {
		VlPoductionMode = vlPoductionMode;
	}
	public String getVlFirstLetter() {
		return VlFirstLetter;
	}
	public void setVlFirstLetter(String vlFirstLetter) {
		VlFirstLetter = vlFirstLetter;
	}
	public VehicleLine(
		String VlNum,
		String VlBNum,
		String VlName,
		String VlPoductionMode,
		String VlFirstLetter	
		)
	{
		this.VlNum = VlNum ;
		this.VlBNum = VlBNum ;
		this.VlName = VlName ;
		this.VlPoductionMode = VlPoductionMode ;
		this.VlFirstLetter = VlFirstLetter ;
	}
	public VehicleLine(){}
	public String getCell(int col){
		if(col == 0) return VlNum;
		else if(col == 1){
			String a = "";
			try {
				a = (new VehicleBrandManager()).getvb(VlBNum).getVbName();
			} catch (BaseException e) {
				e.printStackTrace();
			}
			return a;
		}
		else if(col == 2) return VlName;
		else if(col == 3) return VlPoductionMode;
		else if(col == 4) return VlFirstLetter;
		else return "";
	}
}
