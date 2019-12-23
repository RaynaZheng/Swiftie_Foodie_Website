<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <jsp:include page="/GetFavouriteController" flush="true"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyResult</title>
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
</head>

<body>
<!-- the very top part -->
    <div class="top" style="letter-spacing: 2px;">
         <ul>
            <li style="float:left!important" class="barlist"><a href="main.jsp">Swiftie Foodie</a></li>
            <li style="padding-right: 30px;" class="barlist" id="admin"><a href="myfavourite.jsp">${firstname}'s saved recipes</a></li>
		 	<li class="barlist"><a href="main.jsp#filter">Filter my recipes</a></li>
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
    	
        <h1 id="result">We found ${resultrecipes.size()} dish(es) in your saved list</h1>
       <c:forEach items="${fav}" var ="recipe">
	        <div class="food-card">
	            <div class="card-contents">
	                <div class="front">
	                    <img src="${recipe.getLink()}" alt="food,img" style="width:250px;height:300px;">
	                </div>
	                <div class="back">
	                    <h2><c:out value="${recipe.getRecipeName()}" /></h2>
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
    
    <!-- myscript -->
    <script src="js/result.js"></script>
</body>
</html>