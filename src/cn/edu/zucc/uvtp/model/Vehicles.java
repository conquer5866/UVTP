package cn.edu.zucc.uvtp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.zucc.uvtp.control.VehicleLineManager;
import cn.edu.zucc.uvtp.control.VehicleTypeManager;
import cn.edu.zucc.uvtp.util.BaseException;

public class Vehicles {
	public static final String[] tableTitles1 = {"编号","拥有者编号","车型","车系","出厂时间","上牌时间","市场指导价","来源"};
	public static final String[] tableTitles2 = {"编号","车型","车系","出厂时间","上牌时间","市场指导价","来源"};
	private String VcNum;
	private String VcOwnerId;
	private String VcTypeId;
	private String VcLNum;
	private String VcGearBox;
	private String VcDisplacement;
	private Date VcDate;
	private Date VcTime;
	private double VcMileage;
	private String VcColor;
	private double VcPrice;
	private String VcDrive;
	private String VcSeatNum;
	private String VcOrigin;
	public String getVcNum() {
		return VcNum;
	}
	public void setVcNum(String vcNum) {
		VcNum = vcNum;
	}
	public String getVcOwnerId() {
		return VcOwnerId;
	}
	public void setVcOwnerId(String vcOwnerId) {
		VcOwnerId = vcOwnerId;
	}
	public String getVcTypeId() {
		return VcTypeId;
	}
	public void setVcTypeId(String vcTypeId) {
		VcTypeId = vcTypeId;
	}
	public String getVcLNum() {
		return VcLNum;
	}
	public void setVcLNum(String vcLNum) {
		VcLNum = vcLNum;
	}
	public String getVcGearBox() {
		return VcGearBox;
	}
	public void setVcGearBox(String vcGearBox) {
		VcGearBox = vcGearBox;
	}
	public String getVcDisplacement() {
		return VcDisplacement;
	}
	public void setVcDisplacement(String vcDisplacement) {
		VcDisplacement = vcDisplacement;
	}
	public Date getVcDate() {
		return VcDate;
	}
	public void setVcDate(Date vcDate) {
		VcDate = vcDate;
	}
	public Date getVcTime() {
		return VcTime;
	}
	public void setVcTime(Date vcTime) {
		VcTime = vcTime;
	}
	public double getVcMileage() {
		return VcMileage;
	}
	public void setVcMileage(double vcMileage) {
		VcMileage = vcMileage;
	}
	public String getVcColor() {
		return VcColor;
	}
	public void setVcColor(String vcColor) {
		VcColor = vcColor;
	}
	public double getVcPrice() {
		return VcPrice;
	}
	public void setVcPrice(double vcPrice) {
		VcPrice = vcPrice;
	}
	public String getVcDrive() {
		return VcDrive;
	}
	public void setVcDrive(String vcDrive) {
		VcDrive = vcDrive;
	}
	public String getVcSeatNum() {
		return VcSeatNum;
	}
	public void setVcSeatNum(String vcSeatNum) {
		VcSeatNum = vcSeatNum;
	}
	public String getVcOrigin() {
		return VcOrigin;
	}
	public void setVcOrigin(String vcOrigin) {
		VcOrigin = vcOrigin;
	}
	public Vehicles(
		String VcNum,
		String VcOwnerId,
		String VcTypeId,
		String VcLNum,
		String VcGearBox,
		String VcDisplacement,
		Date VcDate,
		Date VcTime,
		double VcMileage,
		String VcColor,
		double VcPrice,
		String VcDrive,
		String VcSeatNum,
		String VcOrigin
		)
	{
		this.VcNum = VcNum ; 
		this.VcOwnerId = VcOwnerId ; 
		this.VcTypeId = VcTypeId ; 
		this.VcLNum = VcLNum ; 
		this.VcGearBox = VcGearBox ; 
		this.VcDisplacement = VcDisplacement ; 
		this.VcDate = VcDate ; 
		this.VcTime = VcTime ; 
		this.VcMileage = VcMileage ; 
		this.VcColor = VcColor ; 
		this.VcPrice = VcPrice ; 
		this.VcDrive = VcDrive ; 
		this.VcSeatNum = VcSeatNum ; 
		this.VcOrigin = VcOrigin ; 		
	}
	public Vehicles(){}
	
	public String getCell1(int col){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		if(col == 0){
			return this.VcNum;
		}
		else if(col == 1){
			return this.VcOwnerId;
		}
		else if(col == 2){
			return this.VcTypeId;
		}
		else if(col == 3){
			return this.VcLNum;
		}
		else if(col == 4){
			return f.format(this.VcDate);
		}
		else if(col == 5){
			return f.format(this.VcTime);
		}
		else if(col == 6){
			return ""+this.VcPrice;
		}
		else if(col == 7){
			return this.VcOrigin;
		}
		else return "";
	}
	public String getCell2(int col){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		if(col == 0){
			return this.VcNum;
		}
		else if(col == 1){
			String vtnum = "";
			try {
				vtnum = (new VehicleTypeManager()).getvt(this.VcTypeId).getVtName();
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vtnum;
		}
		else if(col == 2){
			String vlnum = "";
			try {
				vlnum = (new VehicleLineManager()).getvl(this.VcLNum).getVlName();
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vlnum;
		}
		else if(col == 3){
			return f.format(this.VcDate);
		}
		else if(col == 4){
			return f.format(this.VcTime);
		}
		else if(col == 5){
			return ""+this.VcPrice;
		}
		else if(col == 6){
			return this.VcOrigin;
		}
		else return "";
	}
}
