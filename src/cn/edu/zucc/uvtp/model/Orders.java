package cn.edu.zucc.uvtp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Orders {
	public static final String[] tableTitles1 = {"编号","车辆编号","卖家编号","买家编号","价格","有效时间","看车方式","状态"};
	public static final String[] tableTitles2 = {"车辆编号","价格","有效时间","看车方式"};
	private String ONum;
	private String OVNum;
	private String OSuNum;
	private String OBuNum;
	private double OPrice;
	private Date OTime;
	private int OType;
	private int OState;
	public String getONum() {
		return ONum;
	}
	public void setONum(String oNum) {
		ONum = oNum;
	}
	public String getOVNum() {
		return OVNum;
	}
	public void setOVNum(String oVNum) {
		OVNum = oVNum;
	}
	public String getOSuNum() {
		return OSuNum;
	}
	public void setOSuNum(String oSuNum) {
		OSuNum = oSuNum;
	}
	public String getOBuNum() {
		return OBuNum;
	}
	public void setOBuNum(String oBuNum) {
		OBuNum = oBuNum;
	}
	public double getOPrice() {
		return OPrice;
	}
	public void setOPrice(double oPrice) {
		OPrice = oPrice;
	}
	public Date getOTime() {
		return OTime;
	}
	public void setOTime(Date oTime) {
		OTime = oTime;
	}
	public int getOType() {
		return OType;
	}
	public void setOType(int oType) {
		OType = oType;
	}
	public int getOState() {
		return OState;
	}
	public void setOState(int oState) {
		OState = oState;
	}
	public String getCell1(int col){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String a = "";
		if(this.OType == 1){
			a = "远程";
		}
		else if(this.OType == 2)
		{
			a = "现场";
		}
		else if(this.OType == 3){
			a = "远程/现场";
		}
		else if(this.OType == 4){
			a = "中介";
		}
		else if(this.OType == 5){
			a = "远程/中介";
		}
		else if(this.OType == 6){
			a = "现场/中介";
		}
		else if(this.OType == 7){
			a = "均可";
		}
		if(col == 0){
			return this.ONum;
		}
		else if(col == 1){
			return this.OVNum;
		}
		else if(col == 2){
			return this.OSuNum;
		}
		else if(col == 3){
			if(this.OBuNum!=null){
				return this.OBuNum;
			}
			else return "";
		}
		else if(col == 4){
			return ""+this.OPrice;
		}
		else if(col == 5){
			return f.format(this.OTime);
		}
		else if(col == 6){
			return a;
		}
		else if(col == 7){
			if(this.OState == 0){
				return "正常";
			}
			else if(this.OState == 1){
				return "成功";
			}
			else if(this.OState == 2){
				return "取消";
			}
			else if(this.OState == 3){
				return "失败";
			}
			else return "";
		}
		else return "";
	}
	public String getCell2(int col){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(col == 0){
			return this.OVNum;
		}
		else if(col == 1){
			return ""+this.OPrice;
		}
		else if(col == 2){
			return ""+f.format(this.OTime);
		}
		else if(col == 3){
			if(this.OType == 1){
				return "远程";
			}
			else if(this.OType == 2)
			{
				return "现场";
			}
			else if(this.OType == 3){
				return "远程/现场";
			}
			else if(this.OType == 4){
				return "中介";
			}
			else if(this.OType == 5){
				return "远程/中介";
			}
			else if(this.OType == 6){
				return "现场/中介";
			}
			else if(this.OType == 7){
				return "均可";
			}
			else return "";
		}
		else{
			return "";
		}
	}
}
