package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.model.Vehicles;
import cn.edu.zucc.uvtp.model.vorders;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class vordersmanager {
	public List<vorders> loadAll() throws BaseException{
		Connection conn = null;
		List<vorders> result = new ArrayList<vorders>();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from vorders where ostate = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, 0);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				vorders vc = new vorders();
				vc.setOnum(rs.getString(1));
				vc.setVtnum(rs.getString(2));
				vc.setVtname(rs.getString(3));
				vc.setVbnum(rs.getString(4));
				vc.setVbname(rs.getString(5));
				vc.setVlnum(rs.getString(6));
				vc.setVlname(rs.getString(7));
				vc.setVcnum(rs.getString(8));
				vc.setVcgearbox(rs.getString(9));
				vc.setUnum(rs.getString(10));
				vc.setUname(rs.getString(11));
				vc.setOtype(rs.getInt(12));
				vc.setOprice(rs.getDouble(13));
				vc.setOstate(rs.getInt(14));
				result.add(vc);
			}
			rs.close();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
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
		return result;
	}
	public List<vorders> seearch(String str) throws BaseException{
		Connection conn = null;
		List<vorders> result = new ArrayList<vorders>();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from vorders where vtname like ? or vbname like ? or vlname like ? or vcgearbox like ? or uname like ? and ostate = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+str+"%");
			pst.setString(2, "%"+str+"%");
			pst.setString(3, "%"+str+"%");
			pst.setString(4, "%"+str+"%");
			pst.setString(5,"%"+str+"%");
			pst.setInt(5, 0);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				vorders vc = new vorders();
				vc.setOnum(rs.getString(1));
				vc.setVtnum(rs.getString(2));
				vc.setVtname(rs.getString(3));
				vc.setVbnum(rs.getString(4));
				vc.setVbname(rs.getString(5));
				vc.setVlnum(rs.getString(6));
				vc.setVlname(rs.getString(7));
				vc.setVcnum(rs.getString(8));
				vc.setVcgearbox(rs.getString(9));
				vc.setUnum(rs.getString(10));
				vc.setUname(rs.getString(11));
				vc.setOtype(rs.getInt(12));
				vc.setOprice(rs.getDouble(13));
				vc.setOstate(rs.getInt(14));
				result.add(vc);
			}
			rs.close();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
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
		return result;
	}
	public vorders find(String vnum,int state) throws BaseException{
		Connection conn = null;
		vorders vc = new vorders();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from vorders where ostate=? and vcnum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, state);
			pst.setString(2, vnum);
			java.sql.ResultSet rs = pst.executeQuery();
				rs.next();
				vc.setOnum(rs.getString(1));
				vc.setVtnum(rs.getString(2));
				vc.setVtname(rs.getString(3));
				vc.setVbnum(rs.getString(4));
				vc.setVbname(rs.getString(5));
				vc.setVlnum(rs.getString(6));
				vc.setVlname(rs.getString(7));
				vc.setVcnum(rs.getString(8));
				vc.setVcgearbox(rs.getString(9));
				vc.setUnum(rs.getString(10));
				vc.setUname(rs.getString(11));
				vc.setOtype(rs.getInt(12));
				vc.setOprice(rs.getDouble(13));
				vc.setOstate(rs.getInt(14));
			rs.close();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
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
		return vc;
	}
}
