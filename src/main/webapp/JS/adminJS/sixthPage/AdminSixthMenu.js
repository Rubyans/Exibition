function addFunc() {
    displayAnother= document.getElementById("delDiv").style.display;
    if(displayAnother=='none') {
        display = document.getElementById("addDiv").style.display;
        if(display=='none') {
            document.getElementById("addDiv").style.display='block';
            document.getElementById("InputNameView").required = true;
        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameView").required = false;
        }
    }else {
        document.getElementById("delDiv").style.display='block';
        delFunc();
        addFunc();
    }
};
function delFunc() {
    displayAnother= document.getElementById("addDiv").style.display;
    if(displayAnother=='none') {
        display = document.getElementById("delDiv").style.display;
        if(display=='none') {
            document.getElementById("delDiv").style.display='block';
            document.getElementById("InputDelView").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelView").required = false;
        }
    }
    else {
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};