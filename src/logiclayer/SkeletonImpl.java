package logiclayer;

import java.sql.SQLException;

import objectlayer.User;
import persistlayer.SkeletonPersist;

public class SkeletonImpl {

	private SkeletonPersist impl = new SkeletonPersist();

	public void Connect()
	{
		impl.Init();
	}
	
	public void Disconnect()
	{
		impl.UnInit();
	}
	
	
	public void create(User user)
	{
		impl.createUser(user.getFname(), user.getLname(), user.getUserName(), user.getPassword(), user.getEmail());
	}
	
	public boolean check(User user) throws SQLException{
		return impl.checkUser(user.getUserName(), user.getPassword());
		
	}
	public User reutrnUserInfo(User user) throws SQLException{
		User tempUser = new User();
		tempUser = impl.getUserInfo(user.getUserName());
		
		
		return tempUser;
		
	}

	public String getUserNameFromId(int userID) {
		// TODO Auto-generated method stub
		return impl.getUserNameFromId(userID);
	}
		
		public void changeUserInfo(int userId, String Fname, String LName, String Uname, String password, String email) {
		impl.changeUserInfo(userId, Fname, LName, Uname, password, email);
	}
	
	public User reutrnUserInfoByID(int userID) throws SQLException {
		// TODO Auto-generated method stub
		User tempUser = new User();
		tempUser = impl.getUserInfoFromID(userID);
		
		
		return tempUser;
		
	}

}
