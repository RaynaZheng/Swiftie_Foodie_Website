<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>HowTo</title>
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
    </style>
    <link rel="stylesheet" href="css/detail.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>


 <!-- the very top part -->
    <div class="top" style="letter-spacing: 2px;">
        <ul>
            <li style="float:left!important" class="barlist"><a href="main.jsp">Swiftie Foodie</a></li>
          <% if (session.getAttribute("username") != null) { %>
            
            	<li style="padding-right: 30px;" class="barlist" id="admin"><a href="myfavourite.jsp">${firstname}'s saved recipes</a></li>
    			  
			<% } else {%>
    			 <li style="padding-right: 30px;" class="barlist" id="admin"><a href="#login"=>LogIn/SignUp</a></li>
			<% } %>
            <li class="barlist"><a href="/html/main.html#filter">Filter my recipes</a></li>
        </ul>
    </div>

    <div class="detailintro">
    	

        <h1 style="margin-left:500px;">${detailed_recipe.getRecipeName()}</h1>

        <img src="${detailed_recipe.getLink()}" alt="foodpicture" class="foodpic">

        <div class="part1">

            <h2>Preparation time: <span style="color:red">${detailed_recipe.getRecipeTime()} mins</span></h2>
            <h2>Ingredients:</h2>
            <hr>
         	<pre>
         	${detailed_recipe.getIngredients()}
         	</pre>
           
        </div>

        <div class="steps">
            <h2>Steps</h2>
            <pre class="steptxt">
            ${detailed_recipe.getRecipeInstruction()}
            </pre>
           
        </div>





    </div>

</body>
</html>