package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.uvtp.itf.IVehicleLine;
import cn.edu.zucc.uvtp.model.VehicleLine;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.BusinessException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class VehicleLineManager implements IVehicleLine {

	@Override
	public void add(VehicleLine vl) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from VehicleLine where vlName = ? and VlBNum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, vl.getVlName());
			pst.setString(2, vl.getVlBNum());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("该品牌已存在此车系！");
			rs.close();
			pst.close();
			
			sql ="Select vblinenum from VehicleBrand where vbnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, vl.getVlBNum());
			rs = pst.executeQuery();
			rs.next();
			int a = rs.getInt(1);
			rs.close();
			pst.close();
			
			sql = "update vehiclebrand set vblinenum =? where vbnum =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, a+1);
			pst.setString(2, vl.getVlBNum());
			pst.execute();
			pst.close();
			
			sql = "select * from VehicleLine";
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
			rs.close();
			pst.close();
			
			sql = "insert into VehicleLine (VlNum,VlBNum,VlName,VlPoductionMode,VlFirstLetter) values (?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,""+i);
			pst.setString(2,vl.getVlBNum());
			pst.setString(3,vl.getVlName());
			pst.setString(4,vl.getVlPoductionMode());
			pst.setString(5,(new OthersManager()).getPinYinHeadChar(vl.getVlName()));	
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
	public void modify(VehicleLine vl) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "update VehicleLine set VlName=?,VlPoductionMode=?,VlFirstLetter=? where VlNum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,vl.getVlName());
			pst.setString(2,vl.getVlPoductionMode());
			pst.setString(3,new OthersManager().getPinYinHeadChar(vl.getVlName()));
			pst.setString(4,vl.getVlNum());
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
	public void del(String VlNum) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "delete from VehicleLine where VlNum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,VlNum);
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
	
	
	public List<VehicleLine> loadAll() throws BaseException{
		Connection conn = null;
		List<VehicleLine> result = new ArrayList<VehicleLine>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from VehicleLine";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()){
				VehicleLine vl = new VehicleLine();
				vl.setVlNum(rs.getString(1));
				vl.setVlBNum(rs.getString(2));
				vl.setVlName(rs.getString(3));
				vl.setVlPoductionMode(rs.getString(4));
				vl.setVlFirstLetter(rs.getString(5));
				result.add(vl);
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
					e.printStackTrace();
				}
		}
		return result;
	}

	public VehicleLine getvl(String vlnum) throws BaseException {
		Connection conn = null;
		VehicleLine vl = new VehicleLine();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from VehicleLine where vlnum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, vlnum);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			vl.setVlNum(rs.getString(1));
			vl.setVlBNum(rs.getString(2));
			vl.setVlName(rs.getString(3));
			vl.setVlPoductionMode(rs.getString(4));
			vl.setVlFirstLetter(rs.getString(5));
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
					e.printStackTrace();
				}
		}
		return vl;
	}

	public String[] loadVlname() throws BaseException{
		Connection conn=null;
		String[] result = null;
		try {
			conn=DBUtil.getConnection();
			String sql="select vlname from VehicleLine";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			int i = 0;
			while(rs.next()){
				i++;
			}
			rs.close();
			pst.close();
			result = new String[i];
			sql="select vlname from VehicleLine";
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
	public String getvlnum(String vlname) throws BaseException{
		Connection conn=null;
		String result = "";
		try {
			conn=DBUtil.getConnection();
			String sql="select vlnum from VehicleLine where vlname = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, vlname);
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
