package vn.iostar.dao;

import java.util.List;

import vn.iostar.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	UserModel findByUserName(String username);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);
	
	boolean check(String email,String username);

	boolean resetPassword(String username,String password);
}
