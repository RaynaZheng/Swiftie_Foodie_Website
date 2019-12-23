var statement;
var d = new Date();
var hr = d.getHours();
if (hr < 11 && hr > 4) {
    statement = "Good Morning, what do you want for breakfast?";
} else if (hr < 16) {
    statement = "Good afternoon, ready for lunch?";
} else if (hr < 21) {
    statement = "Good evening, remember to get something nice for dinner!";
} else {
    statement = "Good appetite, some snack?";
}
document.getElementById("greetings").innerHTML = statement;