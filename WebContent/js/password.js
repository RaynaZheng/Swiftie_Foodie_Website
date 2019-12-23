var password = document.getElementById("password"),
    confirm_password = document.getElementById("confirm_password");

function validatePassword() {
    if (password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

var q1 = document.getElementById("q1"),
    q2 = document.getElementById("q2");

function checksecurityquestion() {
    if (q1.value == q2.value) {
        q2.setCustomValidity("The second security question has to be different as the first one");
    } else {
        q2.setCustomValidity('');
    }
}

q1.onchange = checksecurityquestion;
q2.onkeyup = checksecurityquestion;