package vn.iostar.services.impl;

import vn.iostar.dao.IUserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;

public class UserService implements IUserService{

	IUserDao userDao = new UserDaoImpl();
	@Override
	public UserModel Login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}

	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
			}
		long millis=System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);
		userDao.insert(new UserModel(email, username, fullname,password,null,1,phone,date));
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}
}
