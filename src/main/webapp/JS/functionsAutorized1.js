var words = { //array of translational words
    en: [
        { id: "authorization", text: "Authorization" },
        { id: "hyper", text: "Log in as a guest" },
        { id: "writeForm", text: "Please fill out the form." },
        { id: "login", text: "Login" },
        { id: "writeLogin", text: "Enter your login" },
        { id: "password", text: "Password" },
        { id: "passwordCheck", text: "Enter your password" },
        { id: "checkPass", text: "View password" },
        { id: "hyperReg", text: "Not registered?" },
        { id: "loginButton", text: "Sign in" },

    ],

    ua: [
        { id: "authorization", text: "Авторизація" },
        { id: "hyper", text: "Увійти як гість" },
        { id: "writeForm", text: "Будь ласка заповніть форму." },
        { id: "login", text: "Логін" },
        { id: "writeLogin", text: "Введіть логін" },
        { id: "password", text: "Пароль" },
        { id: "passwordCheck", text: "Введіть пароль" },
        { id: "checkPass", text: "Переглянути пароль" },
        { id: "hyperReg", text: "Не зареєструвались?" },
        { id: "loginButton", text: "Увійти" },
    ],
};

function changeLanguage(lan) { //function to change language
    words[lan].forEach((item) => {
        if(item.id=="writeLogin") {
            $("#writeLogin").attr("placeholder",item.text);
            return;
        }
        if(item.id=="passwordCheck") {
            $("#passwordCheck").attr("placeholder",item.text);
            return;
        }
        if(item.id=="loginButton") {
            $("#loginButton").attr("value",item.text);
            return;
        }
        document.getElementById(item.id).innerText = item.text;
    });
}
function checkPassword() { //function to change visible
    var x = document.getElementById("passwordCheck");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}
function requiredFalse() {
   document.getElementById("writeLogin").required=false;
   document.getElementById("passwordCheck").required=false;
}