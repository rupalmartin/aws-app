<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8">

    <title>AWS Project</title>
    <link rel="icon" href="../appServlet/resources/storm.png" sizes="24x24" type="image/png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"></a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="index">Home</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <h1 style="font-family:Comic Sans Ms" ;text-align="center" ;font-size:20pt;color:#00FF00;>
        Welcome to rupalmartin.com !!!
    </h1>
</div>
<br><br>
<div class="container" align="center">
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">Click to Login</button>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#registerModal">Click to Register
    </button>
    <br>
    <h3>${message}</h3>
</div>

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalTitle"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalTitle">Log In</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" modelAttribute="login" accept-charset=utf-8 action="login">
                    <div class="form-group">
                        <label>User Name</label>
                        <input type="text" class="form-control" id="usname" name="username" placeholder="User Name">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" id="paword" name="password" placeholder="Password">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Log In</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="loginModalRegister"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalRegister">Register</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" modelAttribute="user" accept-charset=utf-8 action="register">
                    <div class="form-group">
                        <label>User Name</label>
                        <input type="text" class="form-control" id="uname" name="username" placeholder="User Name">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" id="pword" name="password"
                               placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control" id="firstname" name="firstname"
                               placeholder="First Name">
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last Name">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Register</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>


</body>
</HTML>