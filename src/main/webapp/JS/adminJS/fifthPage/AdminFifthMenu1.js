function addFunc() {
    displayAnother= document.getElementById("delDiv").style.display;
    if(displayAnother=='none') {
        display = document.getElementById("addDiv").style.display;
        if(display=='none') {
            document.getElementById("addDiv").style.display='block';
            document.getElementById("InputNameArt").required = true;
            document.getElementById("InputCreationArt").required = true;
            document.getElementById("InputPriceArt").required = true;
            document.getElementById("SelectAuthor").required = true;
            document.getElementById("SelectView").required = true;

        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameArt").required = false;
            document.getElementById("InputCreationArt").required = false;
            document.getElementById("InputPriceArt").required = false;
            document.getElementById("SelectAuthor").required = false;
            document.getElementById("SelectView").required = false;
        }
    }
    else {
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
            document.getElementById("InputDelArt").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelArt").required = false;
        }
    }
    else {
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};