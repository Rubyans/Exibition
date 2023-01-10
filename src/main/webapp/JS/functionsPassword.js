var myInput = document.getElementById("writePassword");
var myInputConfirm = document.getElementById("writeConfirmPassword");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");
var passwordCheck = document.getElementById("confirmPassword");

myInput.onfocus = function() {
    document.getElementById("message").style.display = "block";
}

myInput.onblur = function() { // when the user clicks outside of the password field, hide the message box
    document.getElementById("message").style.display = "none";
}


myInputConfirm.onfocus = function() {
    document.getElementById("messagePassword").style.display = "block";
}

myInputConfirm.onblur = function() { // when the user clicks outside of the password field, hide the message box
    document.getElementById("messagePassword").style.display = "none";
}


myInputConfirm.onkeyup = function() {
    if(myInput.value == myInputConfirm.value) { // validate new password
        passwordCheck.classList.remove("invalidPassword");
        passwordCheck.classList.add("validPassword");
        passwordCheck.innerText ="Паролі співпадають";
    } else {
        passwordCheck.classList.remove("validPassword");
        passwordCheck.classList.add("invalidPassword");
    }
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
    var x = document.getElementById("writePassword");
    var y = document.getElementById("writeConfirmPassword");
    if (x.type === "password" && y.type === "password") {
        x.type = "text";
        y.type = "text";
    } else {
        x.type = "password";
        y.type = "password";
    }
}

function requiredFalse() {
  document.getElementById("writeConfirmPassword").required=false;
  document.getElementById("writePassword").required=false;
  document.getElementById("writeCode").required=false;
}

function requiredSubmit() {
   var passwordConfirm = document.getElementById("writeConfirmPassword");
   var password = document.getElementById("writePassword");

    if(passwordConfirm.value == password.value) { // validate new password
        document.getElementById("writeConfirmPassword").required=false;
        document.getElementById("writePassword").required=false;
        document.getElementById("writeCode").required=false;
    }
}