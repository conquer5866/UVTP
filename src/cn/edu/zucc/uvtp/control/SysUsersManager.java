package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.uvtp.itf.ISysUsers;
import cn.edu.zucc.uvtp.model.SystemUsers;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.BusinessException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class SysUsersManager implements ISysUsers {

	@Override
	public void create(String suname,String pwd,String pwd2) throws BaseException {
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from SystemUsers where suname = ?";
			java.sql.PreparedStatement  pst = conn.prepareStatement(sql);
			pst.setString(1, suname);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("已存在该用户");
			rs.close();
			pst.close();

			sql = "select sunum from SystemUsers";
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

			sql = "insert into SystemUsers (sunum,suname,supwd,sudate) values (?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,i+"");
			pst.setString(2,suname);
			pst.setString(3,pwd2);
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			//pst.setDate(4,new java.sql.Date(x.getTime()));
			
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
	public void modifyPwd(String suname,String oPwd, String nPwd1, String nPwd2) throws BaseException {
		Connection conn = null;
		if(!nPwd2.equals(nPwd2)) throw new BusinessException("两次输入的新密码不一致");
		try{
			conn = DBUtil.getConnection();
			String sql = "select supwd from SystemUsers where suname = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,suname);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			if(!rs.getString(1).equals(oPwd)) throw new BusinessException("密码错误");
			rs.close();
			pst.close();

			sql = "update SystemUsers set supwd = ? where suname = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, nPwd2);
			pst.setString(2, suname);
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
	public void del(String sunum) throws BaseException {
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from SystemUsers where sunum = ?";
			java.sql.PreparedStatement  pst = conn.prepareStatement(sql);
			pst.setString(1, sunum);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			if(rs.getString(1) == null) throw new BusinessException("列表已更新，请刷新列表");
			if(rs.getString(2).equals(SystemUsers.currentsu.getSuName())) throw new BusinessException("无法删除当前登录用户");
			if(rs.getString(2).equals("root")) throw new BusinessException("无法删除root用户");
			rs.close();
			pst.close();
			sql = "delete from Systemusers where sunum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, sunum);
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
	public void login(String suname, String supwd) throws BaseException {
		Connection conn=null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from systemusers where suname = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,suname);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			if(rs.getString(1)== null) throw new BusinessException("不存在此用户");
			if(!rs.getString(3).equals(supwd)) throw new BusinessException("密码错误");
			SystemUsers su = new SystemUsers();
			su.setSuNum(rs.getString(1));
			su.setSuName(rs.getString(2));
			su.setSuPwd(rs.getString(3));
			su.setSuDate(rs.getDate(4));
			SystemUsers.currentsu = su;
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
	
	public List<SystemUsers> loadAll() throws BaseException{
		Connection conn=null;
		List<SystemUsers> result = new ArrayList<SystemUsers>();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from systemusers";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()){
				SystemUsers su = new SystemUsers();
				su.setSuNum(rs.getString(1));
				su.setSuName(rs.getString(2));	
				su.setSuPwd(rs.getString(3));
				su.setSuDate(rs.getTimestamp(4));
				result.add(su);
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
