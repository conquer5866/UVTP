package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.uvtp.itf.IMessages;
import cn.edu.zucc.uvtp.model.Messages;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class MessageManager implements IMessages {

	@Override
	public void add(Messages m) throws BaseException {
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from Messages";
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
			
			sql = "insert into messages (mnum,msender,mreceiver,monum,message,mstate) values (?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, i+"");
			pst.setString(2, m.getMSender());
			pst.setString(3, m.getMReceiver());
			pst.setString(4, m.getMONum());	
			pst.setInt(5, m.getMessage());
			pst.setInt(6, 0);
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
	public void changeState(String mnum,int state) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update Messages set mstate = ? where mnum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, state);
			pst.setString(2, mnum);
			pst.execute();
			pst.close();
		}  catch (SQLException e) {
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
	
	public void del() throws BaseException {
		Connection conn = null;
		try {
			String sql = "delete from Messages where mstate = ? or mstate = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setInt(2, 2);
			pst.execute();
			pst.close();
		}  catch (SQLException e) {
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
	
	public List<Messages> loadAll() throws BaseException{
		Connection conn = null;
		List<Messages> result = new ArrayList<Messages>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from messages where MReceiver = ? and mstate = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Users.currentu.getUNum());
			pst.setInt(2, 0);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Messages m = new Messages();
				m.setMNum(rs.getString(1));
				m.setMSender(rs.getString(2));
				m.setMReceiver(rs.getString(3));
				m.setMONum(rs.getString(4));
				m.setMessage(rs.getInt(5));
				m.setMState(rs.getInt(6));
				result.add(m);
			}
			rs.close();
			pst.close();
		}  catch (SQLException e) {
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
	
	public Messages getMessage(String mnum) throws BaseException{
		Connection conn = null;
		Messages m = new Messages();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from messages where mnum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, mnum);
			
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			m.setMNum(rs.getString(1));
			m.setMSender(rs.getString(2));
			m.setMReceiver(rs.getString(3));
			m.setMONum(rs.getString(4));
			m.setMessage(rs.getInt(5));
			m.setMState(rs.getInt(6));
			rs.close();
			pst.close();
		}  catch (SQLException e) {
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
		return m;
	}
}
