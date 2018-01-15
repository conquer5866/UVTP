package cn.edu.zucc.uvtp;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import cn.edu.zucc.uvtp.control.UsersManager;
import cn.edu.zucc.uvtp.control.VehicleBrandManager;
import cn.edu.zucc.uvtp.control.VehicleLineManager;
import cn.edu.zucc.uvtp.control.VehicleTypeManager;
import cn.edu.zucc.uvtp.control.VehiclesManager;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.model.VehicleBrand;
import cn.edu.zucc.uvtp.model.VehicleLine;
import cn.edu.zucc.uvtp.model.VehicleType;
import cn.edu.zucc.uvtp.model.Vehicles;
import cn.edu.zucc.uvtp.ui.Frmtest;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.DBUtil;

public class test {
	public static void main(String[] args){
		/*(new test()).testConnection();
		String a = "1";
		String b = "2";
		String c = "3";
		String d = "4";
		String e = "5";
		String f = "6";
		String g = "7";
		String h = "8";
		String i = "9";
		String j = "10";
		Date x = new Timestamp(System.currentTimeMillis());
		VehicleType vt = new VehicleType(a,b,c,4);
		VehicleBrand vb = new VehicleBrand(a,b,c,x,4,e);
		VehicleLine vl = new VehicleLine(a,a,b,c,d);
		Users u = new Users ();
		u.setUName("123456");
		u.setUPwd("123456");
		Vehicles vc = new Vehicles(a,a,a,a,e,f,x,x,0.0,g,1.2,h,i,j);
		try {
			(new VehicleTypeManager()).add(vt);
			(new VehicleBrandManager()).add(vb);
			(new VehicleLineManager()).add(vl);
			(new UsersManager()).create(u.getUName(),u.getUPwd(),u.getUPwd());
			(new VehiclesManager()).add(vc);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		new Frmtest();
	}
	public void testConnection(){
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			System.out.println("connected!");
		} catch (SQLException e) {
			System.out.println("connect failed!");
			e.printStackTrace();
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
