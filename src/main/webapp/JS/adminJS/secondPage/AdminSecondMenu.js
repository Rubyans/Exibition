function addFunc() {
    displayAnother= document.getElementById("delDiv").style.display;
    if(displayAnother=='none') {
        display = document.getElementById("addDiv").style.display;
        if(display=='none') {
            document.getElementById("addDiv").style.display='block';
            document.getElementById("InputNameHL").required = true;
            document.getElementById("InputSquareHL").required = true;
        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameHL").required = false;
            document.getElementById("InputSquareHL").required = false;
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
            document.getElementById("InputDelName").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelName").required = false;
        }
    }
    else {
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};