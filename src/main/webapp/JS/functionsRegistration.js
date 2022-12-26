var myInput = document.getElementById("passwordCheck");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");


myInput.onfocus = function() {
    document.getElementById("message").style.display = "block";
}

myInput.onblur = function() { // when the user clicks outside of the password field, hide the message box
    document.getElementById("message").style.display = "none";
}

myInput.onkeyup = function() { // when the user starts to type something inside the password field

    var lowerCaseLetters = /[a-z]/g; // validate lowercase letters
    if(myInput.value.match(lowerCaseLetters)) {
        letter.classList.remove("invalid");
        letter.classList.add("valid");
    } else {
        letter.classList.remove("valid");
        letter.classList.add("invalid");
    }

    var upperCaseLetters = /[A-Z]/g;  // validate capital letters
    if(myInput.value.match(upperCaseLetters)) {
        capital.classList.remove("invalid");
        capital.classList.add("valid");
    } else {
        capital.classList.remove("valid");
        capital.classList.add("invalid");
    }

    var numbers = /[0-9]/g; // validate numbers
    if(myInput.value.match(numbers)) {
        number.classList.remove("invalid");
        number.classList.add("valid");
    } else {
        number.classList.remove("valid");
        number.classList.add("invalid");
    }

    if(myInput.value.length >= 8) { // validate length
        length.classList.remove("invalid");
        length.classList.add("valid");
    } else {
        length.classList.remove("valid");
        length.classList.add("invalid");
    }
}

function checkPassword() { //chenges type checkbox
    var x = document.getElementById("passwordCheck");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}
function requiredFalse() {
   document.getElementById("firstCheck").required=false;
   document.getElementById("lastCheck").required=false;
   document.getElementById("emailCheck").required=false;
   document.getElementById("loginCheck").required=false;
   document.getElementById("passwordCheck").required=false;
   document.getElementById("registrationButton").required=false;
}