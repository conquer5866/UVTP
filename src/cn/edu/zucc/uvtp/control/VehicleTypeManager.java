package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.uvtp.itf.IVehicleType;
import cn.edu.zucc.uvtp.model.VehicleType;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.BusinessException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class VehicleTypeManager implements IVehicleType {

	@Override
	public void add(VehicleType vt) throws BaseException {
		Connection conn=null;
		
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from VehicleType where vtName = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, vt.getVtName());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("已存在该车型！");
			rs.close();
			pst.close();
			
			sql = "select * from VehicleType";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
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

			sql = "insert into vehicletype (vtnum,vtname,VtIntroduction,vttype) values (?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, ""+i);
			pst.setString(2, vt.getVtName());
			pst.setString(3, vt.getVtIntroduction());
			pst.setInt(4, vt.getVtType());
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
	public void modify(VehicleType vt) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update vehicletype set vtname=?,VtIntroduction=?,vttype=? where vtnum =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,vt.getVtName());
			pst.setString(2,vt.getVtIntroduction());
			pst.setInt(3,vt.getVtType());
			pst.setString(4,vt.getVtNum());
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
	public void del(String VtNum) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from vehicletype where vtnum = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,VtNum);
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
	public List<VehicleType> loadAll() throws BaseException {
		Connection conn = null;
		List<VehicleType> result = new ArrayList<VehicleType>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from vehicletype";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()){
				VehicleType vt = new VehicleType();
				vt.setVtNum(rs.getString(1));
				vt.setVtName(rs.getString(2));
				vt.setVtIntroduction(rs.getString(3));
				vt.setVtType(rs.getInt(4));
				result.add(vt);
			}
			rs.close();
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
		return result;
	}
	
	public VehicleType getvt(String vtnum) throws BaseException {
		Connection conn = null;
		VehicleType vt = new VehicleType();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from vehicletype where vtnum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, vtnum);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			vt.setVtNum(rs.getString(1));
			vt.setVtName(rs.getString(2));
			vt.setVtIntroduction(rs.getString(3));
			vt.setVtType(rs.getInt(4));
			rs.close();
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
		return vt;
	}

	public String[] loadVtname() throws BaseException{
		Connection conn=null;
		String[] result = null;
		try {
			conn=DBUtil.getConnection();
			String sql="select vtname from VehicleType";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			int i = 0;
			while(rs.next()){
				i++;
			}
			rs.close();
			pst.close();
			result = new String[i];
			sql="select vtname from VehicleType";
			pst=conn.prepareStatement(sql);
			rs = pst.executeQuery();
			for(int j = 0;rs.next();j++){
				result[j] = rs.getString(1);
			}
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
		return result;
	}
	public String getvbnum(String vtname) throws BaseException{
		Connection conn=null;
		String result = "";
		try {
			conn=DBUtil.getConnection();
			String sql="select vtnum from VehicleType where vtname = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, vtname);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			result = rs.getString(1);
			rs.close();
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
		return result;
	}
}