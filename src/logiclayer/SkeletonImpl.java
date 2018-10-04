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
		impl.createUser(user.getFname(), user.getLname(), user.getUserName(), user.getPassword());
	}
	
	public boolean check(User user) throws SQLException{
		return impl.checkUser(user.getUserName(), user.getPassword());
		
	}
}
