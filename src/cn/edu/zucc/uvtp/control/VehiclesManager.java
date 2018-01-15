package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.uvtp.itf.IVehicles;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.model.Vehicles;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.BusinessException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class VehiclesManager implements IVehicles {

	@Override
	public void add(cn.edu.zucc.uvtp.model.Vehicles vc) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from Vehicles";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			int i = 0;
			if(!rs.next()) i = 1;
			else {
				i = Integer.valueOf(rs.getString(1)).intValue();
				while(rs.next()){
					if(i<=Integer.valueOf(rs.getString(1)).intValue()){
						i = Integer.valueOf(rs.getString(1)).intValue();
					}
				}
				i = i + 1;
			}
			rs.close();
			pst.close();
			sql = "insert into Vehicles (VcNum, VcOwnerId, VcTypeId, VcLNum, VcGearBox, VcDisplacement, VcDate, VcTime, VcMileage, VcColor, VcPrice, VcDrive, VcSeatNum, VcOrigin) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,""+i);
			pst.setString(2,Users.currentu.getUNum());
			pst.setString(3,vc.getVcTypeId());
			pst.setString(4,vc.getVcLNum());
			pst.setString(5,vc.getVcGearBox());
			pst.setString(6,vc.getVcDisplacement());
			pst.setDate(7,new java.sql.Date(vc.getVcDate().getTime()));
			pst.setDate(8,new java.sql.Date(vc.getVcTime().getTime()));
			pst.setDouble(9,vc.getVcMileage());
			pst.setString(10,vc.getVcColor());
			pst.setDouble(11,vc.getVcPrice());
			pst.setString(12,vc.getVcDrive());
			pst.setString(13,vc.getVcSeatNum());
			pst.setString(14,vc.getVcOrigin());						
			pst.execute();
			pst.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
	@Override
	public void modify(cn.edu.zucc.uvtp.model.Vehicles vc) throws BaseException {
		Connection conn=null;
		
		try {
			conn=DBUtil.getConnection();
			String sql = "update Vehicles set VcTypeId = ?, VcLNum = ?, VcGearBox = ?, VcDisplacement = ?, VcDate = ?, VcTime = ?, VcMileage = ?, VcColor = ?, VcPrice = ?, VcDrive = ?, VcSeatNum = ?, VcOrigin = ? where VcNum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,vc.getVcTypeId());
			pst.setString(2,vc.getVcLNum());
			pst.setString(3,vc.getVcGearBox());
			pst.setString(4,vc.getVcDisplacement());
			pst.setDate(5,new java.sql.Date(vc.getVcDate().getTime()));
			pst.setDate(6,new java.sql.Date(vc.getVcTime().getTime()));
			pst.setDouble(7,vc.getVcMileage());
			pst.setString(8,vc.getVcColor());
			pst.setDouble(9,vc.getVcPrice());
			pst.setString(10,vc.getVcDrive());
			pst.setString(11,vc.getVcSeatNum());
			pst.setString(12,vc.getVcOrigin());	
			pst.setString(13,vc.getVcNum());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
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
	}

	@Override
	public void del(String vcNum) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from Orders where ovnum = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, vcNum);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next() && (rs.getInt(8) == 0)) throw new BusinessException("这辆车已经有上牌信息，请先取消上牌再删除。");
			rs.close();
			pst.close();
			sql="delete from Vehicles where vcnum = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, vcNum);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
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
	}
	public List<Vehicles> loadAll() throws BaseException {
		Connection conn = null;
		List<Vehicles> result = new ArrayList<Vehicles>();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from Vehicles";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				Vehicles vc = new Vehicles();
				vc.setVcNum(rs.getString(1));
				vc.setVcOwnerId(rs.getString(2));
				vc.setVcTypeId(rs.getString(3));
				vc.setVcLNum(rs.getString(4));
				vc.setVcGearBox(rs.getString(5));
				vc.setVcDisplacement(rs.getString(6));
				vc.setVcDate(rs.getTimestamp(7));
				vc.setVcTime(rs.getTimestamp(8));
				vc.setVcMileage(rs.getDouble(9));
				vc.setVcColor(rs.getString(10));
				vc.setVcPrice(rs.getDouble(11));
				vc.setVcDrive(rs.getString(12));
				vc.setVcSeatNum(rs.getString(13));
				vc.setVcOrigin(rs.getString(14));
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
	public List<Vehicles> loadAll2() throws BaseException {
		Connection conn = null;
		List<Vehicles> result = new ArrayList<Vehicles>();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from Vehicles where VcOwnerId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Users.currentu.getUNum());
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				Vehicles vc = new Vehicles();
				vc.setVcNum(rs.getString(1));
				vc.setVcOwnerId(rs.getString(2));
				vc.setVcTypeId(rs.getString(3));
				vc.setVcLNum(rs.getString(4));
				vc.setVcGearBox(rs.getString(5));
				vc.setVcDisplacement(rs.getString(6));
				vc.setVcDate(rs.getTimestamp(7));
				vc.setVcTime(rs.getTimestamp(8));
				vc.setVcMileage(rs.getDouble(9));
				vc.setVcColor(rs.getString(10));
				vc.setVcPrice(rs.getDouble(11));
				vc.setVcDrive(rs.getString(12));
				vc.setVcSeatNum(rs.getString(13));
				vc.setVcOrigin(rs.getString(14));
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

}
