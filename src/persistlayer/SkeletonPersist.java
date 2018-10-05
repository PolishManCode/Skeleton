package persistlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objectlayer.User;

public class SkeletonPersist {
private DBAccessImpl dataBase = new DBAccessImpl();
	
public void Init()
{
	dataBase.Connect();
}

public void UnInit()
{
	dataBase.disconnect();
}

public void createUser(String fname, String lname, String userName, String password, String email)
{
	
	PreparedStatement stmt = dataBase.newPrepStm("insert into users(fname, lname, userName, password, email) values(?, ?, ?, ?, ?)");
	
	try {
		stmt.setString(1, fname);
		stmt.setString(2, lname);
		stmt.setString(3, userName);
		stmt.setString(4, password);
		stmt.setString(5, email);
		
	} catch (SQLException e) {
	}
	
	dataBase.exeStmUpdate(stmt);
	
}
public boolean checkUser(String userName, String password) throws SQLException{
	System.out.println("Inside checkUser");
	System.out.println(userName +" " + password);
	PreparedStatement stmt = dataBase.newPrepStm("select count(*) from users where userName = ? and password =?");
	try {
		stmt.setString(1, userName);
		stmt.setString(2, password);
		
	} catch (SQLException e) {
	}
	ResultSet rs = dataBase.exeStm(stmt);
	int rowcount =0;
	rs.next();
	rowcount = rs.getInt(1);
	if ( rowcount >0){
		return true;
	}
	else{
		return false;
	}
	
}
public User getUserInfo(String userName) throws SQLException{
	System.out.println("Inside checkUser");
	System.out.println(userName);
	User infoUser = new User();
	PreparedStatement stmt = dataBase.newPrepStm("select * from users where userName = ?");
	try {
		stmt.setString(1, userName);
		
	} catch (SQLException e) {
	}
	ResultSet rs = dataBase.exeStm(stmt);
	int id = 0;
	String firstName = null, lastName = null, uName = null, password = null, email = null;
	
	
	if(rs.next()) {
		id = rs.getInt("User_ID");
		System.out.println(id);
		firstName = rs.getString("fname");
		System.out.println(firstName);
		lastName = rs.getString("lname");
		uName = rs.getString("userName");
		password = rs.getString("password");
		email = rs.getString("email");
	}
	infoUser.setFname(firstName);
	infoUser.setLname(lastName);
	infoUser.setPassword(password);
	infoUser.setUserID(id);
	infoUser.setUserName(uName);
	infoUser.setEmail(email);
	
	return infoUser;

}

public String getUserNameFromId(int userId) {
	PreparedStatement stmt = dataBase.newPrepStm("select userName from users where User_ID = ?");

	try {
		stmt.setInt(1, userId);
		
	} catch (SQLException e) {
	}
	
	ResultSet rs = dataBase.exeStm(stmt);
	
	try {
		if (rs.next())
		{
			return rs.getString(1);
		}
	} catch (SQLException e) {

	}
	
	return null;
}

public void changeUserInfo(int userId, String fname, String lName, String uname, String password, String email) {
	String str = "UPDATE users set";
	boolean first = true;
	
	System.out.println("Change Info, New Info ");
	System.out.println(userId);
	System.out.println(fname);
	System.out.println(lName);
	System.out.println(uname);
	System.out.println(password);
	System.out.println(email);
	
	if (fname != null && fname.length() > 0)
	{
		str += " fname = " + "\'" + fname + "\'";
		first = false;
	}
	
	if (lName != null && lName.length() > 0)
	{
		if (!first)
			str += ",";
		str += " lname = " + "\'" + lName + "\'";		
	}
	
	if (uname != null && uname.length() > 0)
	{
		if (!first)
			str += ",";
		str += " userName = " + "\'"+ uname + "\'";	
	}
	if (password != null && password.length() > 0)
	{
		if (!first)
			str += ",";
		str += " password = " + "\'" + password + "\'";
	}
	
	if (email != null && email.length() > 0)
	{
		if (!first)
			str += ",";
		str += " email = " + "\'" + email + "\'";
	}
	
	str += " WHERE User_ID = " + userId;
	System.out.println(str);
	PreparedStatement stmt = dataBase.newPrepStm(str);
	
	dataBase.exeStmUpdate(stmt);
}

public User getUserInfoFromID(int userID) throws SQLException {
	System.out.println("Inside checkUser");
	System.out.println("User ID: " + userID);
	User infoUser = new User();
	PreparedStatement stmt = dataBase.newPrepStm("select * from users where User_ID = ?");
	try {
		stmt.setLong(1, userID);
		
	} catch (SQLException e) {
	}
	ResultSet rs = dataBase.exeStm(stmt);
	int id = 0;
	String firstName = null, lastName = null, uName = null, password = null, email = null;
	
	
	if(rs.next()) {
		id = rs.getInt("User_ID");
		System.out.println(id);
		firstName = rs.getString("fname");
		System.out.println(firstName);
		lastName = rs.getString("lname");
		uName = rs.getString("userName");
		password = rs.getString("password");
		email = rs.getString("email");
	}
	infoUser.setFname(firstName);
	infoUser.setLname(lastName);
	infoUser.setPassword(password);
	infoUser.setUserID(id);
	infoUser.setUserName(uName);
	infoUser.setEmail(email);
	
	return infoUser;
	
	
	
}

}
