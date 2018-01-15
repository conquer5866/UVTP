package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.uvtp.itf.IUsers;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.BusinessException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class UsersManager implements IUsers {

	@Override
	public Users create(String uname,String pwd1,String pwd2,String call) throws BaseException {
		Connection conn=null;
		if(call.length()!=11) throw new BusinessException("手机号码为11位");
		if(!isNumeric(call)) throw new BusinessException("手机号码不能包含除数字以外的字符");
		if(uname.length()>20||uname.length()<6) throw new BusinessException("用户名不得超过20个字且不能小于6个字");
		if(!pwd1.equals(pwd2)) throw new BusinessException("两次密码输入不相同");
		if(pwd2.length()>20||pwd2.length()<6) throw new BusinessException("密码需要大于等于6位并小于20位");
		try {
			Users u = new Users();
			conn=DBUtil.getConnection();
			String sql = "select * from Users where uname = ?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,uname);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) throw new BusinessException("用户已存在");
			rs.close();
            pst.close();

            sql = "select unum from Users";
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

            sql = "insert into Users (UNum, UName, UPwd, UMobileNum,UDate, UState) values(?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1, ""+i);
            pst.setString(2, uname);
            pst.setString(3, pwd2);
            pst.setString(4, call);
            pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pst.setInt(6, 0);
            pst.execute();
            pst.close();
            
            sql = "select UNum,UName,UPwd,UMobileNum,UDate,UState from users where unum = ?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,""+i);
            rs=pst.executeQuery();
            rs.next();
            u.setUNum(rs.getString(1));
            u.setUName(rs.getString(2));
            u.setUPwd(rs.getString(3));
            u.setUMobileNum(rs.getString(4));
            u.setUDate(rs.getDate(5));
            u.setUState(rs.getInt(6));
            rs.close();
            pst.close();
            
            sql = "select * from users where unum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1,""+i);
			rs = pst.executeQuery();
			rs.next();
            
            Users x = new Users();
			x.setUNum(rs.getString(1));
			x.setUName(rs.getString(2));
			x.setUPwd(rs.getString(3));
			x.setURName(rs.getString(4));
			x.setUAdress(rs.getString(5));
			x.setUMobileNum(rs.getString(6));
			x.setUEmail(rs.getString(7));
			x.setUQq(rs.getString(8));
			x.setUDate(rs.getTimestamp(9));
			x.setUState(rs.getInt(10));
			Users.currentu = x;
            		
            		
            return u;
            
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
	public void modify(Users u) throws BaseException {
		Connection conn=null;
		try{
			conn = DBUtil.getConnection();
			String sql = "update Users set UName = ?,URName = ?,UAdress = ?,UEmail = ?,UQq = ? where unum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getUName());
			pst.setString(2, u.getURName());
			pst.setString(3, u.getUAdress());
			pst.setString(4, u.getUEmail());
			pst.setString(5, u.getUQq());
			pst.setString(6, u.getUNum());
			pst.execute();
			pst.close();
			Users.currentu = u;
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
	public void modifycall(String unum,String call) throws BaseException{
		Connection conn=null;
		try{
			conn = DBUtil.getConnection();
			String sql = "update Users set UMobileNum = ? where unum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, call);
			pst.setString(2, unum);
			pst.execute();
			pst.close();
			Users.currentu.setUMobileNum(call);
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
	public void modifyPwd(String oPwd, String nPwd1, String nPwd2) throws BaseException {
		Connection conn=null;
		if(!nPwd1.equals(nPwd2)) throw new BusinessException("两次密码输入不一致");
		try{
			conn = DBUtil.getConnection();
			String sql = "select upwd from Users where unum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Users.currentu.getUNum());
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			if(!rs.getString(1).equals(oPwd)) throw new BusinessException("密码错误！");
			rs.close();
			pst.close();
			
			sql = "update Users set Upwd = ? where unum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, nPwd2);
			pst.setString(2, Users.currentu.getUNum());
			pst.execute();
			pst.close();
			Users.currentu.setUPwd(nPwd2);
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
	public void del(String userId) throws BaseException {
		Connection conn=null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from Orders where osunum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,userId);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("该用户有车辆正在挂牌中，请先取消挂牌");
			rs.close();
			pst.close();
			
			sql = "delete from Users where unum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
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
	public void lock(String userId) throws BaseException {
		Connection conn=null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select ustate from Users where unum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			int a = 0;
			if(rs.getInt(1) == 0) a = 1;
			else a = 0;
			rs.close();
			pst.close();
			
			sql = "update Users set UState = ? where unum = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, a);
			pst.setString(2, userId);
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
	public void ulock(String userId) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void login(String UMobileNum, String upwd) throws BaseException {
		Connection conn=null;
		try{
			conn = DBUtil.getConnection();
			if(UMobileNum.length()!=11) throw new BusinessException("请输入11位手机号码！");
			if(!isNumeric(UMobileNum)) throw new BusinessException("请输入11位手机号码！");
			String sql = "select * from users where UMobileNum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,UMobileNum);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			if(rs.getInt(10) == 1) throw new BusinessException("该用户已被锁定");
			if(rs.getString(1)== null) throw new BusinessException("不存在此用户");
			if(!rs.getString(3).equals(upwd)) throw new BusinessException("密码错误");
			Users u = new Users();
			u.setUNum(rs.getString(1));
			u.setUName(rs.getString(2));
			u.setUPwd(rs.getString(3));
			u.setURName(rs.getString(4));
			u.setUAdress(rs.getString(5));
			u.setUMobileNum(rs.getString(6));
			u.setUEmail(rs.getString(7));
			u.setUQq(rs.getString(8));
			u.setUDate(rs.getTimestamp(9));
			u.setUState(rs.getInt(10));
			Users.currentu = u;
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
	
	public List<Users> loadAll() throws BaseException{
		Connection conn=null;
		List<Users> result = new ArrayList<Users>();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from users";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Users u = new Users();
				u.setUNum(rs.getString(1));
				u.setUName(rs.getString(2));
				u.setUPwd(rs.getString(3));
				u.setURName(rs.getString(4));
				u.setUAdress(rs.getString(5));
				u.setUMobileNum(rs.getString(6));
				u.setUEmail(rs.getString(7));
				u.setUQq(rs.getString(8));
				u.setUDate(rs.getTimestamp(9));
				u.setUState(rs.getInt(10));
				result.add(u);
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
	public Users getUser(String unum) throws BaseException{
		Connection conn=null;
		Users u = new Users();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from users where unum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, unum);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
				u.setUNum(rs.getString(1));
				u.setUName(rs.getString(2));
				u.setUPwd(rs.getString(3));
				u.setURName(rs.getString(4));
				u.setUAdress(rs.getString(5));
				u.setUMobileNum(rs.getString(6));
				u.setUEmail(rs.getString(7));
				u.setUQq(rs.getString(8));
				u.setUDate(rs.getTimestamp(9));
				u.setUState(rs.getInt(10));
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
		return u;
	}
	
	public String getUnum(String call) throws BaseException{
		Connection conn=null;
		String a = "";
		try{
			conn = DBUtil.getConnection();
			String sql = "select Unum from users where UMobileNum = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, call);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			a = rs.getString(1);
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
		return a;
	}
	
	
	public static boolean isNumeric(String str){  
		  for (int i = str.length();--i>=0;){    
		   if (!Character.isDigit(str.charAt(i))){  
		    return false;  
		   }  
		  }  
		  return true;  
		}  
}
