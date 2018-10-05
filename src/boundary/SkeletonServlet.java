package boundary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import boundary.TemplateProcessor;
import logiclayer.SkeletonImpl;
import objectlayer.User;

/**
 * Servlet implementation class SkeletonServlet
 */
@WebServlet("/SkeletonServlet")
public class SkeletonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SkeletonImpl dataBase = new SkeletonImpl();
	private User userRegister = new User();
	private User userLogin = new User();
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor tmplProce;
	
	private User userToken = new User();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkeletonServlet() {
        super();
        dataBase.Connect();
        // TODO Auto-generated constructor stub
    }
    /**
     * Initialization for the template processor
     */
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmplProce = new TemplateProcessor(templateDir, getServletContext());
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("inside of servlet");
		
		if(request.getParameter("register")!=null){
			//System.out.println("In register");
			
			userRegister.setFname(request.getParameter("fname"));
			userRegister.setLname(request.getParameter("lname"));
			userRegister.setUserName(request.getParameter("userName"));
			userRegister.setPassword(request.getParameter("pass"));
			userRegister.setEmail(request.getParameter("email"));
			
			dataBase.create(userRegister);
			goToLogin(request, response);
			
			//System.out.println("inside register");
		}
		else if(request.getParameter("login")!=null){
			System.out.println("In Login");
			
			userLogin.setUserName(request.getParameter("userName"));
			//System.out.println(request.getParameter("userName"));
			
			userLogin.setPassword(request.getParameter("pass"));
			//System.out.println(request.getParameter("pass"));
			
			boolean check = false;
			try {
				check = dataBase.check(userLogin);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(check==true){
				System.out.println("Login Successful");
				
				HttpSession session = request.getSession(true);
		        if(session!=null)
		        session.setAttribute("name", userLogin.getUserName());
				setUserToken(userLogin);
				goToHomePage(request, response);
			}
			else{
				System.out.println("Login Unsuccessful");
				
				goToLoginError(request, response);
			}
		}
		else {
			loggedIn(request, response);
		}
		
	}

	private void loggedIn(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		System.out.println(session.getAttribute("name") +" User is logged in");
		
		System.out.println(request.getParameter("param"));
		String paramater = request.getParameter("param");
		System.out.println(session.getAttribute("name"));
		
		if(paramater.equals("home")){
			
			System.out.println("Back to HomePage");
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			String templateName = "homepage.ftl";
			tmplProce.processTemplate(templateName, root, request, response);
		
		}
		else if(paramater.equals("games")){
			System.out.println("Inside of Games");
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			String templateName = "games.ftl";
			
			tmplProce.processTemplate(templateName, root, request, response);
		}
		
		else if(paramater.equals("recruitment")){
			System.out.println("Inside of recruit");
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			String templateName = "recruit.ftl";
			
			tmplProce.processTemplate(templateName, root, request, response);
		}
		else if(paramater.equals("logOutIn")){
			System.out.println("Inside of logoutin");
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			String templateName = "login.ftl";
			
			tmplProce.processTemplate(templateName, root, request, response);
		}
		
		else if(paramater.equals("profile")){
			System.out.println("Our user is: " + userToken.getFname()+ " "+ userToken.getLname());
			User testUser = new User();
			testUser.setUserName((String) session.getAttribute("name"));
			System.out.println(session.getAttribute("name") +" User is logged in");
			try {
				testUser= dataBase.reutrnUserInfo(testUser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("User Name: "+testUser.getFname() + " " + testUser.getLname());
			System.out.println("UserID: "+ testUser.getUserID());
			System.out.println("UserName: " + testUser.getUserName());
			System.out.println("Password: " + testUser.getPassword());
			
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			String templateName = "profile.ftl";
			int id = testUser.getUserID();
			String userName = testUser.getUserName();
			String fname = testUser.getFname();
			String lname = testUser.getLname();
			String password = testUser.getPassword();
			String email = testUser.getEmail();
			
			root.put("userId", id);
			root.put("fName", fname);
			root.put("lName", lname);
			root.put("user", userName);
			root.put("password", password);
			root.put("email", email);
			
			
			tmplProce.processTemplate(templateName, root, request, response);
			
		}
		else if(paramater.equals("editInfo")){
			dataBase.changeUserInfo(Integer.parseInt(request.getParameter("user_id")),
					request.getParameter("FName"),
					request.getParameter("LName"),
					request.getParameter("UName"),
					request.getParameter("Password"),
					request.getParameter("email"));
			User changedUser= new User();
			String usName = request.getParameter("UName");
			changedUser.setUserName(usName);
			try {
				changedUser= dataBase.reutrnUserInfo(changedUser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("name", changedUser.getUserName());
			
			
			System.out.println("Our user is: " + userToken.getFname()+ " "+ userToken.getLname());
			User testUser = new User();
			testUser.setUserName((String) session.getAttribute("name"));
			System.out.println(session.getAttribute("name") +" User is logged in");
			try {
				testUser= dataBase.reutrnUserInfo(testUser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("User Name: "+testUser.getFname() + " " + testUser.getLname());
			System.out.println("UserID: "+ testUser.getUserID());
			System.out.println("UserName: " + testUser.getUserName());
			System.out.println("Password: " + testUser.getPassword());
			
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			String templateName = "profile.ftl";
			int id = testUser.getUserID();
			String userName = testUser.getUserName();
			String fname = testUser.getFname();
			String lname = testUser.getLname();
			String password = testUser.getPassword();
			String email = testUser.getEmail();
			
			root.put("userId", id);
			root.put("fName", fname);
			root.put("lName", lname);
			root.put("user", userName);
			root.put("password", password);
			root.put("email", email);
			
			tmplProce.processTemplate(templateName, root, request, response);
			
		}
		
	}
	
	private void goToLoginError(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "loginError.ftl";
		tmplProce.processTemplate(templateName, root, request, response);
		
		
	}
	private void goToHomePage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Insde gotoHomePage");

		System.out.println("Back to HomePage");
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		
		String templateName = "homepage.ftl";		
		
		tmplProce.processTemplate(templateName, root, request, response);
		
	}
	private void setUserToken(User userToken) {
		// TODO Auto-generated method stub
		this.userToken = userToken;
		
	}
	public User getUserToken() {
		return userToken;
	}
	private void goToLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "login.ftl";
		tmplProce.processTemplate(templateName, root, request, response);
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
