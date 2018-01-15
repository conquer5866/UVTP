package cn.edu.zucc.uvtp.model;

public class VehicleType {
	public static final String[] tableTitles = {"编号","车型","介绍","类别"};
	private String VtNum;
	private String VtName;
	private String VtIntroduction;
	private int VtType;
	public String getVtNum() {
		return VtNum;
	}
	public void setVtNum(String vtNum) {
		VtNum = vtNum;
	}
	public String getVtName() {
		return VtName;
	}
	public void setVtName(String vtName) {
		VtName = vtName;
	}
	public String getVtIntroduction() {
		return VtIntroduction;
	}
	public void setVtIntroduction(String vtIntroduction) {
		VtIntroduction = vtIntroduction;
	}
	public int getVtType() {
		return VtType;
	}
	public void setVtType(int vtType) {
		VtType = vtType;
	}
	public VehicleType(
		String VtNum,
		String VtName,
		String VtIntroduction,
		int VtType
		)
	{
		this.VtNum = VtNum;
		this.VtName = VtName;
		this.VtIntroduction = VtIntroduction;
		this.VtType = VtType; 
	}
	public VehicleType(){}
	public String getCell(int col){
		if(col == 0) return VtNum;
		else if(col == 1) return VtName;
		else if(col == 2) return VtIntroduction;
		else if(col == 3){
			if(VtType == 0) return "货车";
			else return "客车";
		}
		else return "";
	}
}
