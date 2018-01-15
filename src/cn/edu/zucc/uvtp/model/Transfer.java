package cn.edu.zucc.uvtp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer {
	public static final String[] tableTitles = {"编号","车辆编号","买家","卖家","价格","时间","类型"};
	private String TNum;
	private String TVNum;
	private String TBuNum;
	private String TSuNum;
	private double TPrice;
	private Date TTime;
	private int TType;
	public String getTNum() {
		return TNum;
	}
	public void setTNum(String tNum) {
		TNum = tNum;
	}
	public String getTVNum() {
		return TVNum;
	}
	public void setTVNum(String tVNum) {
		TVNum = tVNum;
	}
	public String getTBuNum() {
		return TBuNum;
	}
	public void setTBuNum(String tBuNum) {
		TBuNum = tBuNum;
	}
	public String getTSuNum() {
		return TSuNum;
	}
	public void setTSuNum(String tSuNum) {
		TSuNum = tSuNum;
	}
	public double getTPrice() {
		return TPrice;
	}
	public void setTPrice(double tPrice) {
		TPrice = tPrice;
	}
	public Date getTTime() {
		return TTime;
	}
	public void setTTime(Date tTime) {
		TTime = tTime;
	}
	public int getTType() {
		return TType;
	}
	public void setTType(int tType) {
		TType = tType;
	}
	public String getCell(int col){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(col == 0){
			return this.TNum;
		}
		else if(col == 1){
			return this.TVNum;
		}
		else if(col == 2){
			return this.TBuNum;
		}
		else if(col == 3){
			return this.TSuNum;
		}
		else if(col == 4){
			return ""+this.TPrice;
		}
		else if(col == 5){
			return f.format(this.TTime);
		}
		else if(col == 6){
			if(this.TType == 0){
				return "交易";
			}
			else{
				return "退车";
			}
		}
		else return "";
	}
}
