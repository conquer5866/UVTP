package cn.edu.zucc.uvtp.model;

public class vorders {
	public static final String[] tableTitles = {"编号","车型","品牌","车系","变速箱","看车方式","车主","价格"};
	private String onum;
	private String vtnum;
	private String vtname;
	private String vbnum;
	private String vbname;
	private String vlnum;
	private String vlname;
	private String vcnum;
	private String vcgearbox;
	private String unum;
	private String uname;
	private int	otype;
	private Double oprice;
	private int ostate;
	public String getOnum() {
		return onum;
	}
	public void setOnum(String onum) {
		this.onum = onum;
	}
	public String getVtnum() {
		return vtnum;
	}
	public void setVtnum(String vtnum) {
		this.vtnum = vtnum;
	}
	public String getVtname() {
		return vtname;
	}
	public void setVtname(String vtname) {
		this.vtname = vtname;
	}
	public String getVbnum() {
		return vbnum;
	}
	public void setVbnum(String vbnum) {
		this.vbnum = vbnum;
	}
	public String getVbname() {
		return vbname;
	}
	public void setVbname(String vbname) {
		this.vbname = vbname;
	}
	public String getVlnum() {
		return vlnum;
	}
	public void setVlnum(String vlnum) {
		this.vlnum = vlnum;
	}
	public String getVlname() {
		return vlname;
	}
	public void setVlname(String vlname) {
		this.vlname = vlname;
	}
	public String getVcnum() {
		return vcnum;
	}
	public void setVcnum(String vcnum) {
		this.vcnum = vcnum;
	}
	public String getVcgearbox() {
		return vcgearbox;
	}
	public void setVcgearbox(String vcgearbox) {
		this.vcgearbox = vcgearbox;
	}
	public String getUnum() {
		return unum;
	}
	public void setUnum(String unum) {
		this.unum = unum;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getOtype() {
		return otype;
	}
	public void setOtype(int otype) {
		this.otype = otype;
	}
	public Double getOprice() {
		return oprice;
	}
	public void setOprice(Double oprice) {
		this.oprice = oprice;
	}
	public int getOstate() {
		return ostate;
	}
	public void setOstate(int ostate) {
		this.ostate = ostate;
	}
	public String getCell(int col){
		if(col == 0){
			return this.onum;
		}
		else if(col == 1){
			return this.vtname;
		}
		else if(col == 2){
			return this.vbname;
		}
		else if(col == 3){
			return this.vlname;
		}
		else if(col == 4){
			return this.vcgearbox;
		}
		else if(col == 5){
			if(this.otype == 1){
				return "远程";
			}
			else if(this.otype == 2)
			{
				return "现场";
			}
			else if(this.otype == 3){
				return "远程/现场";
			}
			else if(this.otype == 4){
				return "中介";
			}
			else if(this.otype == 5){
				return "远程/中介";
			}
			else if(this.otype == 6){
				return "现场/中介";
			}
			else if(this.otype == 7){
				return "均可";
			}
			else return "";
		}
		else if(col == 6){
			return this.uname;
		}
		else if(col == 7){
			return this.oprice+"";
		}
		else return "";
	}
}
