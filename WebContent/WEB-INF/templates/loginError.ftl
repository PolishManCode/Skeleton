<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>MLG Sign up page</title>
      <link rel="stylesheet" href="css/login.css">

  
</head>

<body>
<div id = "homelogo">
      <img src="pictures/logo.png" alt="Recruit Gaming Home" style="width:172px;height:92px;">
</div>
  <div class="form">
      
      <ul class="tab-group">
        <li class="tab"><a href="#signup">Sign Up</a></li>
        <li class="tab active"><a href="#login">Log In</a></li>
      </ul>
      
      <div class="tab-content">
              <div id="login">   
          <h1>Welcome to MLG Recruitment Website!</h1>
          <h3> Invalid password and/or user name. Please try again</h3>
          
          <form action="SkeletonServlet" method="post">
          
            <div class="field-wrap">
            <label>
              User Name<span class="req">*</span>
            </label>
            <input type ="text" name="userName" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type ="password" name="pass" required autocomplete="off"/>
          </div>
          
          <p class="forgot"><a href="SkeletonServlet?param=noLogIn">Continue without login in?</a></p>
          
          <button name="login" class="button button-block"/>Log In</button>
          
          </form>

        </div>
      
        <div id="signup">   
          <h1>Sign Up for the </br>MLG Recruitment Website</h1>
          
          <form action="SkeletonServlet" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                First Name<span class="req">*</span>
              </label>
              <input type ="text" name="fname" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                Last Name<span class="req">*</span>
              </label>
              <input type ="text" name="lname" required autocomplete="off" />
            </div>
          </div>
		<div class="top-row">
          <div class="field-wrap">
            <label>
              User Name<span class="req">*</span>
            </label>
            <input type="text" name="userName" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Email<span class="req">*</span>
            </label>
            <input type="text" name="email" required autocomplete="off"/>
          </div>
          
        </div>
          <div class="field-wrap">
            <label>
              Set A Password<span class="req">*</span>
            </label>
            <input type ="password" name="pass" required autocomplete="off"/>
          </div>
          
          <button type="submit" name="register" class="button button-block"/>Get Started</button>
          
          </form>

        </div>
       
        
      </div>
      
</div>
	<script src="js/jquery.min.js" type ="text/javascript"></script>
    <script src="js/login.js" type ="text/javascript"></script>

</body>
</html>
