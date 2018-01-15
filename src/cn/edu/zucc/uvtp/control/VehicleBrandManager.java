package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.uvtp.itf.IVehicleBrand;
import cn.edu.zucc.uvtp.model.VehicleBrand;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.BusinessException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class VehicleBrandManager implements IVehicleBrand {

	@Override
	public void add(VehicleBrand vb) throws BaseException {
		Connection conn=null;
		
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from VehicleBrand where vbName = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, vb.getVbName());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("已存在该品牌！");
			rs.close();
			pst.close();
			
			sql = "select * from VehicleBrand";
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
			sql = "insert into VehicleBrand (VbNum,VbName,VbCountry,VbDate,VbLineNum,VbFirstLetter) values (?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,""+i);
			pst.setString(2,vb.getVbName());
			pst.setString(3,vb.getVbCountry());
			//pst.setDate(4,Timestamp.valueOf(x));
			pst.setDate(4,new java.sql.Date(vb.getVbDate().getTime()));
			pst.setInt(5,0);
			pst.setString(6,(new OthersManager()).getPinYinHeadChar(vb.getVbName()));
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
	public void modify(VehicleBrand vb) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "update VehicleBrand set VbName=?,VbCountry=?,VbDate=?,VbFirstLetter=? where vbnum =?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,vb.getVbName());
			pst.setString(2,vb.getVbCountry());
			pst.setDate(3,new java.sql.Date(vb.getVbDate().getTime()));
			pst.setString(4,(new OthersManager()).getPinYinHeadChar(vb.getVbName()));
			pst.setString(5,vb.getVbNum());
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
	public void del(String vbNum) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from VehicleBrand where vbnum = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, vbNum);
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
	public VehicleBrand getvb(String vbnum) throws BaseException{
		Connection conn = null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from VehicleBrand where vbNum = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,vbnum);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			VehicleBrand vb = new VehicleBrand();
			vb.setVbNum(rs.getString(1));
			vb.setVbName(rs.getString(2));
			vb.setVbCountry(rs.getString(3));
			vb.setVbDate(rs.getDate(4));
			vb.setVbLineNum(rs.getInt(5));
			vb.setVbFirstLetter(rs.getString(6));
			return vb;
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
	public List<VehicleBrand> loadAll() throws BaseException{
		Connection conn=null;
		List<VehicleBrand> result = new ArrayList<VehicleBrand>();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from VehicleBrand";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()){
				VehicleBrand vb = new VehicleBrand();
				vb.setVbNum(rs.getString(1));
				vb.setVbName(rs.getString(2));
				vb.setVbCountry(rs.getString(3));
				vb.setVbDate(rs.getDate(4));
				vb.setVbLineNum(rs.getInt(5));
				vb.setVbFirstLetter(rs.getString(6));
				result.add(vb);
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
	public String[] loadVbname() throws BaseException{
		Connection conn=null;
		String[] result = null;
		try {
			conn=DBUtil.getConnection();
			String sql="select vbname from VehicleBrand";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			int i = 0;
			while(rs.next()){
				i++;
			}
			rs.close();
			pst.close();
			result = new String[i];
			sql="select vbname from VehicleBrand";
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
	public String getvbnum(String vbname) throws BaseException{
		Connection conn=null;
		String result = "";
		try {
			conn=DBUtil.getConnection();
			String sql="select vbnum from VehicleBrand where vbname = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, vbname);
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
	
	public List<VehicleBrand> search(String str) throws BaseException{
		Connection conn=null;
		List<VehicleBrand> result = new ArrayList<VehicleBrand>();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from VehicleBrand where vbname like ? or vbCountry like ? or VbFirstLetter like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+str+"%");
			pst.setString(2, "%"+str+"%");
			pst.setString(3, "%"+str+"%");
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()){
				VehicleBrand vb = new VehicleBrand();
				vb.setVbNum(rs.getString(1));
				vb.setVbName(rs.getString(2));
				vb.setVbCountry(rs.getString(3));
				vb.setVbDate(rs.getDate(4));
				vb.setVbLineNum(rs.getInt(5));
				vb.setVbFirstLetter(rs.getString(6));
				result.add(vb);
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
}
