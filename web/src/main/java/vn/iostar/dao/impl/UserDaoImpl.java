package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.configs.DBConnectMySQL;
import vn.iostar.dao.IUserDao;
import vn.iostar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao{

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * FROM users";
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(
					rs.getInt("id"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("fullname"),
					rs.getString("images"),
					rs.getString("phone"),
					Integer.parseInt(rs.getString("roleid")),
					rs.getDate("createDate")));
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ? ";
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
			UserModel user = new UserModel();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setFullname(rs.getString("fullname"));
			user.setImages(rs.getString("images"));
			user.setPhone(rs.getString("phone"));
			user.setRoleid(Integer.parseInt(rs.getString("roleid")));
			user.setCreateDate(rs.getDate("createDate"));
			return user;
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(id,username,password,email,fullname,images,phone,roleid,createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try
		{
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getFullname());
			ps.setString(6, user.getImages());
			ps.setString(7, user.getPhone());
			ps.setInt(8, user.getRoleid());
			ps.setDate(9, user.getCreateDate());
			
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}

	@Override
	public UserModel findByUserName(String username) {
			String sql = "SELECT * FROM users WHERE username = ? ";
			try {
				conn = new DBConnectMySQL().getDatabaseConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setImages(rs.getString("images"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
				}
			} catch (Exception e) {e.printStackTrace();}
			return null;
	}
	
	public static void main(String[] args) {
		 try {
			 IUserDao userDao = new UserDaoImpl();
			 System.out.println(userDao.findAll());
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from users where email = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
			duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from users where username = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
			duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from users where phone = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if (rs.next()) {
			duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean check(String email, String username) {
		boolean duplicate = false;
		String query = "select * from users where email = ? and username = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, username);
			rs = ps.executeQuery();
			if (rs.next()) {
			duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean resetPassword(String username, String password) {
		String sql = "Update users set password = ? where username = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {e.printStackTrace();}
		return false;
	}
}
