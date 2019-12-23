<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/RecommendDailyRecipe" flush="true"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	session = request.getSession();
	%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SwiftieFoodie</title>
<style>
        body {
            font-family: "Times New Roman", Georgia, Serif;
        }
        
        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            font-family: "Playfair Display";
        }
        /* .mySlides {
            display: none;
        } */
    </style>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/loginsignup.css">
</head>
<body >


 <!-- the very top part -->
    <div class="top" style="letter-spacing: 2px;">
        <ul>
            <li style="float:left!important" class="barlist"><a href="main.jsp">Swiftie Foodie</a></li>
            
            <% if (session.getAttribute("username") != null) { %>
            
            	<li style="padding-right: 30px;" class="barlist" id="admin"><a href="myfavourite.jsp">${firstname}'s saved recipes</a></li>
    			  
			<% } else {%>
    			 <li style="padding-right: 30px;" class="barlist" id="admin"><a href="#login"=>LogIn/SignUp</a></li>
			<% } %>
			
			
            
            
         	<li style="padding-right: 30px;" class="barlist" id="admin"><a href="team.jsp"=>About the Team</a></li>
            <li class="barlist"><a href="#filter">Filter my recipes</a></li>
        </ul>
    </div>

    <!-- the goodmorning what u wanna eat part -->
    <div class="header">
        <h1 id="greetings"></h1>
        
      	
        <img src="img/headerimg.png" alt="headerimage" width=100%>
        <!-- the header pic  -->
        <!-- <div class="headerimg">
            <img class="mySlides" src="img/headerimg.png" style="width:100%">
            <img class="mySlides" src="img/headerimg2.jpg" style="width:100%">
        </div> -->

    </div>

    <br>


    <div class="recom">
    	<h1>
    	</h1>
        <h1>We find these items that might interest you.</h1>
		      <c:forEach items="${recommendrecipes}" var ="recipe">
			        <div class="food-card">
			            <div class="card-contents">
			                <div class="front">
			                    <img src="${recipe.getLink()}" alt="food,img" style="width:250px;height:300px;">
			                </div>
			                <div class="back">
			                	
			                    <h3><c:out value="${recipe.getRecipeid()}" />. <c:out value="${recipe.getRecipeName()}" /></h3>
			                    <p><c:out value="${recipe.getRecipeDescrip()}" /></p>
			                    <p>Prep time: <span class="pretime"><c:out value="${recipe.getRecipeTime()}" /></span> mins</p>
			                    
			                   
			                    
			                   
			               		<div style="display:flex; justify-content:center!important;text-align:center;">
			               		
			               		
			             		
			             		<form action="AddtoFavouriteController"> 
								<button type = "submit" name="recipe" class="startbtn">
								Save ${recipe.getRecipeid()} for later</button>
								</form>
								</div>
								
								<br>
								<form action="GetRecipeController">  
					            <input type="submit" value="Let's start ${recipe.getRecipeid()}   " name="idname" class="startbtn" style="margin:auto;text-align:center">  
					        	</form>
					        	
								
								
								
								
								
									                   
			
			                </div>
			            </div>
			        </div>
		       </c:forEach>

    </div>


    <div id="filter" class="filter" style="width: 100%;">
        <img src="img/foodguide.jpeg" alt="foodguide" class="filter-img">
        <h1 style="margin-left: 700px;">Find out what do you want.</h1>
        <div class="filterbar">
            <form action="SearchController">
                <!-- type in food you want -->
                <label>What do you want to eat? </label>
                <br>
                <input type="text" name="keyword" placeholder="Type in anything come to your mind">
                <br>
                <!-- the preferred food preparation time  -->
                <div style="margin-top:30px;">
                    <label>Your food preparation time:</label>
                    <br>
                    <select name="preptime">
                    <option value="showall">Show all</option>
                    <option value="lessequalto15mins">Less equal to 15 mins</option>
                    <option value="15to30mins">15 to 45 mins</option>
                    <option value="morethan30mins">More than 45 mins</option>
                  </select>
                </div>
                <br>
                <!-- check if is vegetarian  -->
              
                 <div style="margin-top:30px;">
                    <label>Your meal restriction:</label>
                    <br>
                    <select name="vegetarianfood">
                    <option value="showall">Show all</option>
                    <option value="Vegetarian">Vegetarian Food</option>
                    <option value="Vegan">Vegan Food</option>
                    <option value="Regular">Regular Food</option>
                  </select>
                </div>
                <br>
                <br>
                <input type="submit" value="Apply">
            </form>
        </div>

    </div>
    <br>
    <br>
    
  <% if (session.getAttribute("username") == null) { %>

    <!-- the part where user can login  -->
    <div id="login" class="login">

        <h1>Enjoy Swiftie Foodie?</h1>
        <h3 style="margin-left: 10px;font-weight: lighter;">Log in your account or create one to customise your favourite dish list!</h3>
        <br>
        <button class="loginbutton" onclick="document.getElementById('loginsheet').style.display='block'" style="width:auto;">Login</button>
        <button class="signupbutton" onclick="document.getElementById('signupsheet').style.display='block'" style="width:auto;">Sign Up</button>
        <br>
        <br>
        <br>
        <div id="loginsheet" class="modal1">
            <form class="modal1_content" action="LoginController">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('loginsheet').style.display='none'" class="closeit" title="Close Modal1">&times;</span>
                    <img src="img/avatar.png" alt="Avatar" class="avatar">
                </div>
                <!-- log in questions  -->
                <div class="container">
                    <label for="uname"><b>Username</b></label>
                    <input class="uname" type="text" placeholder="Enter Username" name="un" required>

                    <label for="psw"><b>Password</b></label>
                    <input class="pswinput" type="password" placeholder="Enter Password" name="pw" required>

                    <button type="submit" class="loginbtn">Login</button>
                    
                </div>
                <!-- forget password part  -->
                <div class="container" style="background-color:#f1f1f1">
                    
                    <br>

                    <!-- password page will add  -->
                </div>
            </form>
        </div>

      

<div id="signupsheet" class="modal1">
            <form class="modal2_content" action="SignupController" id="showsignup">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('signupsheet').style.display='none'" class="closeit" title="Close Modal1">&times;</span>
                </div>
                <div class="container">
                    <h1>Sign Up</h1>
                    <p>Please fill in this form to create an account.</p>
                    <hr>
                    <label for="uname"><b>Username</b></label>
                    <input class="uname" type="text" placeholder="Create Your Own Username" name="un" required>
                    <!-- Password -->
                    <label for="psw"><b>Password</b></label>
                    <input class="pswinput" type="password" placeholder="Enter Password" name="pw" required>
                    <!-- password -->
                    <label for="psw"><b>Repeat Password</b></label>
                    <input class="pswinput" type="password" placeholder="Repeat Password" name="psw-repeat" required>

                    <label for="fname"><b>First Name</b></label>
                    <input class="fname" type="text" placeholder="Enter your first name" name="fn" required>
                    <label for="lname"><b>Last Name</b></label>
                    <input class="lname" type="text" placeholder="Enter your last name" name="ln" required>
                    <label for="email"><b>Email Address</b></label>
                    <input class="pswinput" type="email" placeholder="Enter your email address" name="email" required>

                
                    <br>
                    <br>
                    <p>Please set up two security questions and provide answers for your account security.</p>
                    <hr>
                    <br>
                    <label for="sq1"><b>Security Question 1</b></label>
                    <input class="asq1" type="text" placeholder="Set up the first security question" name="a1" required>
                    <input class="asq1" type="text" placeholder="Answer for the first security question" name="q1" required>


                    <br>
                    <br>
                    <label for="sq2"><b>Security Question 2</b></label>
                    <input class="asq1" type="text" placeholder="Set up the first security question" name="a2" required>
                    <input class="asq2" type="text" placeholder="Answer for the second security question" name="q2" required>


                    <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

                    <div class="clearfix">

                        <button type="submit" class="signupbtn">Sign Up</button>
                        <button type="button" onclick="document.getElementById('signupsheet').style.display='none'" class="cancelbtn">Cancel</button>
                    </div>
                </div>
            </form>
        </div>



    </div>
    <img src="img/loginimg.jpg" alt="img" class="loginimg">

<% } %>

    <!-- myscript -->
    <script>
    var statement;
    var d = new Date();
    var hr = d.getHours();
    if (hr<11 && hr>4) {
        statement = "Good Morning, what do you want for breakfast?";
    } else if (hr>=11 && hr < 16) {
        statement = "Good afternoon, ready for lunch?";
    } else if (hr>=16 && hr < 21) {
        statement = "Good evening, remember to get something nice for dinner!";
    } else {
        statement = "Good appetite, some snack?";
    }
    document.getElementById("greetings").innerHTML = statement;
    </script>
   
 
  
    
</body>
</html>