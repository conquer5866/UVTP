package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.uvtp.itf.IOrders;
import cn.edu.zucc.uvtp.model.Orders;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.BusinessException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class OrdersManager implements IOrders {

	@Override
	public void add(Orders o) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from Orders where  OVNum = ? and OState = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, o.getOVNum());
			pst.setInt(2, 0);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()){
				if(rs.getInt(8) == 0) throw new BusinessException("该车已挂牌");
			}
			rs.close();
			pst.close();
			
			sql = "select * from Orders";
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
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, +30);
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String x = time.format(Calendar.getInstance().getTime());
			Date y = null;
			try {
				y = time.parse(x);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sql = "insert into Orders (onum,ovnum,osunum,oprice,otime,otype,ostate) values (?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, ""+i);
			pst.setString(2, o.getOVNum());
			pst.setString(3, o.getOSuNum());
			pst.setDouble(4, o.getOPrice());
			pst.setDate(5,new java.sql.Date(y.getTime()));
			pst.setInt(6, o.getOType());
			pst.setInt(7, 0);
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
	public void del(String oNum) throws BaseException {
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select ostate from Orders where onum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, oNum);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			if(rs.getInt(1) == 0){
				changeState(oNum,2);
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

	}

	@Override
	public void modify(Orders o) throws BaseException {
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "update Orders set oprice = ?,otype = ? where onum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, o.getOPrice());
			pst.setInt(2, o.getOType());
			pst.setString(3,o.getONum());
			pst.execute();
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

	}

	@Override
	public void changeState(String onum,int state) throws BaseException {
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "update Orders set ostate = ? where onum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, state);
			pst.setString(2, onum);
			pst.execute();
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

	}
	
	public List<Orders> loadAll() throws BaseException {
		Connection conn = null;
		List<Orders> result = new ArrayList<Orders>();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from Orders";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Orders o = new Orders();
				o.setONum(rs.getString(1));
				o.setOVNum(rs.getString(2));
				o.setOSuNum(rs.getString(3));
				o.setOBuNum(rs.getString(4));
				o.setOPrice(rs.getDouble(5));
				o.setOTime(rs.getTimestamp(6));
				o.setOType(rs.getInt(7));
				o.setOState(rs.getInt(8));
				result.add(o);
			}
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
	public List<Orders> loadAll2() throws BaseException {
		Connection conn = null;
		List<Orders> result = new ArrayList<Orders>();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from Orders where osunum = ? and OState = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Users.currentu.getUNum());
			pst.setInt(2, 0);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Orders o = new Orders();
				o.setONum(rs.getString(1));
				o.setOVNum(rs.getString(2));
				o.setOSuNum(rs.getString(3));
				o.setOBuNum(rs.getString(4));
				o.setOPrice(rs.getDouble(5));
				o.setOTime(rs.getTimestamp(6));
				o.setOType(rs.getInt(7));
				o.setOState(rs.getInt(8));
				result.add(o);
			}
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
	public Orders getOrders(String onum) throws BaseException {
		Connection conn = null;
		Orders o = new Orders();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from Orders where onum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, onum);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			o.setONum(rs.getString(1));
			o.setOVNum(rs.getString(2));
			o.setOSuNum(rs.getString(3));
			o.setOBuNum(rs.getString(4));
			o.setOPrice(rs.getDouble(5));
			o.setOTime(rs.getTimestamp(6));
			o.setOType(rs.getInt(7));
			o.setOState(rs.getInt(8));
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
		return o;
	}
}
