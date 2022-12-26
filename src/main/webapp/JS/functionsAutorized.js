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