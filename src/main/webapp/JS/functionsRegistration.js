var myInput = document.getElementById("passwordCheck");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");
var words = {
        en: [
          { id: "hReg", text: "Registration" },
          { id: "pReg", text: "Please fill out the form to create an account." },
          { id: "firstName", text: "First Name" },
          { id: "firstCheck", text: "Enter your first name" },
          { id: "lastName", text: "Last Name" },
          { id: "lastCheck", text: "Enter your last name" },
          { id: "email", text: "Mailbox" },
          { id: "emailCheck", text: "Enter a mailbox" },
          { id: "login", text: "Login" },
          { id: "loginCheck", text: "Enter your login" },
          { id: "password", text: "Password" },
          { id: "passwordCheck", text: "Enter your password" },
          { id: "checkPass", text: "View password" },
          { id: "autorizedButton", text: "Before authorization" },
          { id: "registrationButton", text: "Sign up" },
          { id: "passwordMessage", text: "The password must consist of:" },
          { id: "letter", text: "Lower case letters" },
          { id: "capital", text: "Upper case letters" },
          { id: "number", text: "Number" },
          { id: "length", text: "Minimum 8 characters" },
        ],

        ua: [
          { id: "hReg", text: "Реєстрація" },
          { id: "pReg", text: "Будь ласка заповніть форму для створення акаунту." },
          { id: "firstName", text: "Прізвище" },
          { id: "firstCheck", text: "Введіть прізвище" },
          { id: "lastName", text: "Ім`я" },
          { id: "lastCheck", text: "Введіть ім`я" },
          { id: "email", text: "Поштова скринька" },
          { id: "emailCheck", text: "Введіть поштову скриньку" },
          { id: "login", text: "Логін" },
          { id: "loginCheck", text: "Введіть логін" },
          { id: "password", text: "Пароль" },
          { id: "passwordCheck", text: "Введіть пароль" },
          { id: "checkPass", text: "Переглянути пароль" },
          { id: "autorizedButton", text: "До авторизації" },
          { id: "registrationButton", text: "Зареєструватися" },
          { id: "passwordMessage", text: "Пароль повинен складатися з:" },
          { id: "letter", text: "Літера нижнього регістру" },
          { id: "capital", text: "Літера верхнього регістру" },
          { id: "number", text: "Цифра" },
          { id: "length", text: "Мінімум 8 символів" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        if(item.id=="firstCheck") {
            $("#firstCheck").attr("placeholder",item.text);
            return;
        }
        if(item.id=="lastCheck") {
            $("#lastCheck").attr("placeholder",item.text);
            return;
        }
        if(item.id=="loginCheck") {
            $("#loginCheck").attr("placeholder",item.text);
            return;
        }
        if(item.id=="passwordCheck") {
            $("#passwordCheck").attr("placeholder",item.text);
                return;
        }
        if(item.id=="emailCheck") {
            $("#emailCheck").attr("placeholder",item.text);
            return;
        }
        document.getElementById(item.id).innerText = item.text;
    });
}



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