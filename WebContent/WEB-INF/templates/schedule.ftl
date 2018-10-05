<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Skeleton</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/skeleton.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">


</head>

<body>
	   

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="SkeletonServlet?param=home" class="navbar-left"><img src='pictures/logo.png'
			alt='Fitness' style='width: 100px; height: 54px;'></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	<li>
                        <a href="SkeletonServlet?param=home" class="navbar-left"><img src='pictures/logo.png'
			alt='Fitness' style='width: 172px; height: 92px;'></a>
                    </li>
                    <li>
                        <a href="SkeletonServlet?param=home">Home</a>
                    </li>
                    <li>
                        <a href="SkeletonServlet?param=exercises">Exercises</a>
                    </li>
                    <li>
                        <li><a href="SkeletonServlet?param=schedule">Schedule</a></li>
                    </li>
                    <li>
                        <li><a href="SkeletonServlet?param=profile">Profile</a></li>
                    </li>
                    <li class="right">
                        <li><a href=SkeletonServlet?param=logOutIn class ="logIn">LogOut</a></li>
                    </li>
                    
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

   <div class="container">

        <div class="row">
            <div class="box">

            </div>
        </div>

    </div>
    <!-- /.container -->

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; Matt Bochenek Skeleton 2018</p>
                </div>
            </div>
        </div>
    </footer>

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
