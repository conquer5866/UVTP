package cn.edu.zucc.uvtp.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.uvtp.itf.ITransfer;
import cn.edu.zucc.uvtp.model.Transfer;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.util.BaseException;
import cn.edu.zucc.uvtp.util.DBUtil;
import cn.edu.zucc.uvtp.util.DbException;

public class TransferManager implements ITransfer {

	@Override
	public void add(Transfer t) throws BaseException {
		Connection conn = null;
		List<Transfer> result = new ArrayList<Transfer>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from Transfer";
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
			
			sql = "insert into transfer (tnum,tvnum,tbunum,tsunum,tprice,ttime,ttype) values (?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,""+i );
			pst.setString(2, t.getTVNum());
			pst.setString(3, t.getTBuNum());
			pst.setString(4, t.getTSuNum());
			pst.setDouble(5, t.getTPrice());
			pst.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			pst.setInt(7, t.getTType());
			pst.execute();
			pst.close();
			
			sql = "update Vehicles set vcownerid = ? where vcnum = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, t.getTBuNum());
			pst.setString(2, t.getTVNum());
			pst.execute();
			pst.close();
			
			sql = "update Orders set ostate = ? where ovnum =? ";
			pst = conn.prepareStatement(sql);
			if(t.getTType()==1){pst.setInt(1, 3);}
			else{pst.setInt(1, 2);}
			
			pst.setString(2, t.getTVNum());
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
					e.printStackTrace();
				}
		}

	}
	public List<Transfer> loadAll() throws BaseException {
		Connection conn = null;
		List<Transfer> result = new ArrayList<Transfer>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from Transfer where tbunum = ? or tsunum =?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Users.currentu.getUNum());
			pst.setString(2, Users.currentu.getUNum());
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next()){
				Transfer t = new Transfer();
				t.setTNum(rs.getString(1));
				t.setTVNum(rs.getString(2));
				t.setTBuNum(rs.getString(3));
				t.setTSuNum(rs.getString(4));
				t.setTPrice(rs.getDouble(5));
				t.setTTime(rs.getTimestamp(6));
				t.setTType(rs.getInt(7));
				result.add(t);
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
